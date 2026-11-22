// ============================================================
// Track.java  -  one song on an album
//
// We wrote NO constructor here.
// So Java automatically adds a DEFAULT constructor:  Track() { }
// The fields start with default values: title = null, seconds = 0
// ============================================================

public class Track {

    String title;
    int seconds;

    // Length of this track, like "6:09"
    String getLength() {
        return formatTime(seconds);
    }

    // STATIC helper: turns 369 seconds into "6:09". Needs no Track object.
    static String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int secs = totalSeconds % 60;
        if (secs < 10) {
            return minutes + ":0" + secs;
        }
        return minutes + ":" + secs;
    }
}
