import { allTracks, getTrack } from "./collection.js";

const queue = allTracks();
let currentIndex = 0;
let positionSeconds = 0;
let isPlaying = false;
let volume = 75;

function current() {
  return queue[currentIndex];
}

export function status() {
  const item = current();
  return {
    isPlaying,
    positionSeconds,
    volume,
    track: item.track,
    record: { id: item.record.id, albumName: item.record.albumName, artist: item.record.artist }
  };
}

export function play(recordId, trackId) {
  if (recordId || trackId) {
    if (!recordId || !trackId) throw new Error("recordId and trackId must be provided together.");
    const selected = getTrack(recordId, trackId);
    if (!selected) throw new Error("Track not found.");
    currentIndex = queue.findIndex((item) => item.track.id === trackId && item.record.id === recordId);
    positionSeconds = 0;
  }
  isPlaying = true;
  return status();
}

export function pause() { isPlaying = false; return status(); }
export function next() { currentIndex = (currentIndex + 1) % queue.length; positionSeconds = 0; return status(); }
export function previous() { currentIndex = (currentIndex - 1 + queue.length) % queue.length; positionSeconds = 0; return status(); }

export function seek(seconds) {
  if (!Number.isFinite(seconds)) throw new Error("seconds must be a finite number.");
  positionSeconds = Math.max(0, Math.min(current().track.durationSeconds, Math.round(positionSeconds + seconds)));
  return status();
}

export function setVolume(value) {
  if (!Number.isInteger(value) || value < 0 || value > 100) throw new Error("volume must be an integer from 0 to 100.");
  volume = value;
  return status();
}
