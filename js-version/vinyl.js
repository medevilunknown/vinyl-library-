const baseUrl = process.env.VINYL_API_URL || "http://localhost:3000";
const [command, ...args] = process.argv.slice(2);

const usage = `\nVinyl Collection CLI\n\nCommands:\n  list\n  search <text>\n  album <id>\n  status\n  play [recordId trackId]\n  pause | next | previous\n  skip <seconds>                 (negative values rewind)\n  volume <0-100>\n`;

const request = async (path, body) => {
  const response = await fetch(`${baseUrl}${path}`, body && { method: "POST", headers: { "Content-Type": "application/json" }, body: JSON.stringify(body) });
  const payload = await response.json();
  if (!response.ok) throw new Error(payload.error?.message || "Request failed.");
  console.log(JSON.stringify(payload.data, null, 2));
};

try {
  switch (command) {
    case "list": await request("/api/vinyls"); break;
    case "search": await request(`/api/vinyls?search=${encodeURIComponent(args.join(" "))}`); break;
    case "album": await request(`/api/vinyls/${args[0]}`); break;
    case "status": await request("/api/player"); break;
    case "play": await request("/api/player/play", args.length ? { recordId: args[0], trackId: args[1] } : {}); break;
    case "pause": case "next": case "previous": await request(`/api/player/${command}`, {}); break;
    case "skip": await request("/api/player/seek", { seconds: Number(args[0]) }); break;
    case "volume": await request("/api/player/volume", { volume: Number(args[0]) }); break;
    default: console.log(usage);
  }
} catch (error) {
  console.error(`Error: ${error.message}`);
  process.exitCode = 1;
}
