# Digital Vinyl Store API

Everything runs from Terminal. Run the original OOP demo with `npm run demo`. Start the collection API in one terminal:

```sh
npm run api
```

Leave that terminal running. Use a second Terminal window for the commands below. You do not need to open `localhost:3000` in a browser; if you want to see the API help directly, use `curl http://localhost:3000`.

If port 3000 is occupied, start it on port 3001 instead, then point the CLI at that port:

```sh
PORT=3001 npm run api
VINYL_API_URL=http://127.0.0.1:3001 npm run vinyl -- list
```

Use these commands from a second terminal (no installation required):

```sh
npm run vinyl -- list
npm run vinyl -- search daft
npm run vinyl -- album ram
npm run vinyl -- play ram get-lucky
npm run vinyl -- pause
npm run vinyl -- skip 30
npm run vinyl -- skip -10
npm run vinyl -- next
npm run vinyl -- previous
npm run vinyl -- volume 60
npm run vinyl -- status
```

The API is local at `http://localhost:3000`. Main endpoints are `GET /api/vinyls?search=...`, `GET /api/vinyls/:id`, `GET /api/player`, and `POST /api/player/{play,pause,next,previous,seek,volume}`. All responses are JSON; failed requests have `{ "error": { "code", "message" } }`.
