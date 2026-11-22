import http from "node:http";
import { listRecords, getRecord } from "./collection.js";
import * as player from "./player.js";

const PORT = Number(process.env.PORT) || 3000;

function send(response, status, body) {
  response.writeHead(status, { "Content-Type": "application/json; charset=utf-8" });
  response.end(JSON.stringify(body));
}

function error(response, status, code, message) {
  send(response, status, { error: { code, message } });
}

function readJson(request) {
  return new Promise((resolve, reject) => {
    let body = "";
    request.on("data", (chunk) => { body += chunk; });
    request.on("end", () => {
      try { resolve(body ? JSON.parse(body) : {}); }
      catch { reject(new Error("Request body must be valid JSON.")); }
    });
  });
}

const server = http.createServer(async (request, response) => {
  const url = new URL(request.url, `http://${request.headers.host}`);
  try {
    if (request.method === "GET" && url.pathname === "/") {
      return send(response, 200, {
        name: "Vinyl Collection API",
        terminal: [
          "npm run vinyl -- list",
          "npm run vinyl -- search daft",
          "npm run vinyl -- play ram get-lucky",
          "npm run vinyl -- skip 30",
          "npm run vinyl -- next",
          "npm run vinyl -- status"
        ],
        endpoints: ["GET /api/vinyls", "GET /api/vinyls/:id", "GET /api/player", "POST /api/player/:action"]
      });
    }
    if (request.method === "GET" && url.pathname === "/health") return send(response, 200, { ok: true });
    if (request.method === "GET" && url.pathname === "/api/vinyls") return send(response, 200, { data: listRecords(url.searchParams.get("search") || "") });
    if (request.method === "GET" && url.pathname.startsWith("/api/vinyls/")) {
      const record = getRecord(url.pathname.split("/").pop());
      return record ? send(response, 200, { data: record }) : error(response, 404, "NOT_FOUND", "Vinyl record not found.");
    }
    if (request.method === "GET" && url.pathname === "/api/player") return send(response, 200, { data: player.status() });

    if (request.method === "POST" && url.pathname.startsWith("/api/player/")) {
      const action = url.pathname.split("/").pop();
      const body = await readJson(request);
      const handlers = {
        play: () => player.play(body.recordId, body.trackId), pause: () => player.pause(),
        next: () => player.next(), previous: () => player.previous(),
        seek: () => player.seek(Number(body.seconds)), volume: () => player.setVolume(body.volume)
      };
      if (!handlers[action]) return error(response, 404, "NOT_FOUND", "Player action not found.");
      return send(response, 200, { data: handlers[action]() });
    }
    return error(response, 404, "NOT_FOUND", "Route not found.");
  } catch (caught) {
    return error(response, 422, "VALIDATION_ERROR", caught.message);
  }
});

server.on("error", (caught) => {
  if (caught.code === "EADDRINUSE") {
    console.error(`Port ${PORT} is already in use. Try: PORT=3001 npm run api`);
  } else {
    console.error(`Could not start Vinyl Collection API: ${caught.message}`);
  }
  process.exitCode = 1;
});

server.listen(PORT, "127.0.0.1", () => console.log(`Vinyl Collection API listening at http://127.0.0.1:${PORT}`));
