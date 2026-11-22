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

    // Turns 369 seconds into "6:09"
    String getLength() {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        if (secs < 10) {
            return minutes + ":0" + secs;
        }
        return minutes + ":" + secs;
    }
}
