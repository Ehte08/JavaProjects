public class Playlist {
    private final static int MAX_SONGS = 50;
    private SongRecord[] songs;
    private int count;

    public Playlist() {
        songs = new SongRecord[MAX_SONGS];
        count = 0;
    }

    public Object clone() {
        Playlist clonePlaylist = new Playlist();
        for (int i = 0; i < count; i++) {
            clonePlaylist.songs[i] = songs[i];
        }
        clonePlaylist.count = count;
        return clonePlaylist;
    } // method to clone the playlist

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Playlist)) return false;
        Playlist other = (Playlist) obj;
        if (this.count != other.count) return false;
        for (int i = 0; i < count; i++) {
            if (!this.songs[i].equals(other.songs[i])) return false;
        }
        return true;
    }

    public int size() {
        return count;
    } // method to get the number of songs in the playlist

    public void addSong(SongRecord song, int position) {
        if (count >= MAX_SONGS) throw new FullPlaylistException("Playlist is full.");
        if (position < 1 || position > count + 1) throw new IllegalArgumentException("Invalid position for adding the new song");
        for (int i = count; i >= position; i--) {
            songs[i] = songs[i - 1];
        }
        songs[position - 1] = song;
        count++;
    } // method to add a song at a given position in the playlist

    public void removeSong(int position) { 
        if (position < 1 || position > count) throw new IllegalArgumentException("No song at position " + position + " to remove");
        for (int i = position - 1; i < count - 1; i++) {
            songs[i] = songs[i + 1];
        }
        songs[count - 1] = null;
        count--;
    } // method to remove a song from the playlist by its position

    public SongRecord getSong(int position) {
        if (position < 1 || position > count) throw new IllegalArgumentException("Invalid position");
        return songs[position - 1];
    }

public String toString() { // method to return a string representation of the playlist
    StringBuilder sb = new StringBuilder();
    sb.append("Song#      Title               Artist              Length\n");
    sb.append("-----------------------------------------------------------\n");
    for (int i = 0; i < count; i++) {
        sb.append(String.format("%-10d%s\n", i + 1, songs[i].toString()));
    }
    return sb.toString();
}

    public void printAllSongs() {
    System.out.print(this.toString()); // prints all songs in the playlist
}

public static Playlist getSongsByArtist(Playlist originalList, String artist) { // method to search songs by artist
    if (originalList == null || artist == null) return null;

Playlist result = new Playlist();
String search = artist.toLowerCase();  // Case-insensitive search

for (int i = 0; i < originalList.count; i++) {
        SongRecord song = originalList.songs[i];
        if (song.getArtist().equalsIgnoreCase(search)) {
            result.addSong(song, result.count + 1);
         }
    }

    return result;
}

}

