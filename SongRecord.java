// SongRecord.java
public class SongRecord {
    private String title;
    private String artist;
    private int minutes;
    private int seconds;

    public SongRecord() {
        this.title = "";
        this.artist = "";
        this.minutes = 0;
        this.seconds = 0;
    } // default constructors

    public SongRecord(String title, String artist, int minutes, int seconds) {
        this.setTitle(title);
        this.setArtist(artist);
        this.setMinutes(minutes);
        this.setSeconds(seconds);
    } // parameterized constructors

    public String getTitle() {
        return title;
    } // getter for the title

    public void setTitle(String title) {
        this.title = title;
    } // setter for the title

    public String getArtist() {
        return artist;
    } // getter for the artist

    public void setArtist(String artist) {
        this.artist = artist;
    } // setter for the artist

    public int getMinutes() {
        return minutes;
    }   // getter for the minutes

    public void setMinutes(int minutes) {
        if (minutes < 0 || minutes > 59)
            throw new IllegalArgumentException("Invalid song length");
        this.minutes = minutes;
    } // setter for the minutes

    public int getSeconds() {
        return seconds;
    } // getter for the seconds

    public void setSeconds(int seconds) {
        if (seconds < 0 || seconds > 59)
            throw new IllegalArgumentException("Invalid song length");
        this.seconds = seconds;
    }   // setter for the seconds

    public String toString() {
        return String.format("%-20s%-20s%2d:%02d", title, artist, minutes, seconds);
    } // method to return a string representation of the song record properly formatted

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SongRecord)) return false;
        SongRecord other = (SongRecord) obj;
        return title.equals(other.title) && artist.equals(other.artist)
            && minutes == other.minutes && seconds == other.seconds;
    } // method to compare two song records to avoid duplicates
}