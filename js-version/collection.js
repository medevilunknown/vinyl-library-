const records = [
  {
    id: "ram",
    albumName: "Random Access Memories",
    artist: "Daft Punk",
    price: 2499,
    stockCount: 10,
    artwork: "https://placehold.co/600x600/151515/ffffff?text=RAM",
    tracks: [
      { id: "give-life-back-to-music", title: "Give Life Back to Music", durationSeconds: 274 },
      { id: "get-lucky", title: "Get Lucky", durationSeconds: 369 },
      { id: "contact", title: "Contact", durationSeconds: 381 }
    ]
  },
  {
    id: "kind-of-blue",
    albumName: "Kind of Blue",
    artist: "Miles Davis",
    price: 1899,
    stockCount: 6,
    artwork: "https://placehold.co/600x600/0d3351/ffffff?text=KIND+OF+BLUE",
    tracks: [
      { id: "so-what", title: "So What", durationSeconds: 545 },
      { id: "freddie-freeloader", title: "Freddie Freeloader", durationSeconds: 589 }
    ]
  },
  {
    id: "rumours",
    albumName: "Rumours",
    artist: "Fleetwood Mac",
    price: 2099,
    stockCount: 8,
    artwork: "https://placehold.co/600x600/6b4d25/ffffff?text=RUMOURS",
    tracks: [
      { id: "dreams", title: "Dreams", durationSeconds: 257 },
      { id: "the-chain", title: "The Chain", durationSeconds: 270 }
    ]
  }
];

export function listRecords(search = "") {
  const term = search.trim().toLowerCase();
  if (!term) return records;
  return records.filter((record) =>
    [record.albumName, record.artist, ...record.tracks.map((track) => track.title)]
      .some((value) => value.toLowerCase().includes(term))
  );
}

export function getRecord(id) {
  return records.find((record) => record.id === id);
}

export function getTrack(recordId, trackId) {
  const record = getRecord(recordId);
  const track = record?.tracks.find((item) => item.id === trackId);
  return track && { record, track };
}

export function allTracks() {
  return records.flatMap((record) => record.tracks.map((track) => ({ record, track })));
}
