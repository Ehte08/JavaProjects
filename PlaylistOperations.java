
import java.util.*; 

public class PlaylistOperations {
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Playlist> playlists = new HashMap<>();
        String currentPlaylistName = "default";
        playlists.put(currentPlaylistName, new Playlist());

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("A) Add Song\nB) Print Songs by Artist\nG) Get Song\nR) Remove Song\nP) Print All Songs\nS) Size\nQ) Quit");
            System.out.println("N) New Playlist\nV) Switch Playlist\nC) Copy Playlist\nE) Compare Playlists\nD) Display Playlist Names");
            System.out.print("Select a menu option: ");
            String input = scanner.nextLine().trim().toUpperCase();
            Playlist playlist = playlists.get(currentPlaylistName);

            try {
                switch (input) {
                    case "A":
                        System.out.print("Enter the song title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter the song artist: ");
                        String artist = scanner.nextLine();
                        int minutes = readInt(scanner, "Enter the song length (minutes): ");
                        int seconds = readInt(scanner, "Enter the song length (seconds): ");
                        int position = readInt(scanner, "Enter the position: ");
                        SongRecord newSong = new SongRecord(title, artist, minutes, seconds);
                        playlist.addSong(newSong, position);
                        System.out.println("Song Added: " + title + " By " + artist);
                        break;
                    case "B":
                        System.out.print("Enter the artist: ");
                        String searchArtist = scanner.nextLine();
                        Playlist result = Playlist.getSongsByArtist(playlist, searchArtist);
                        if (result.size() == 0) {
                            System.out.println("No songs found by artist: " + searchArtist);
                        } else {
                            result.printAllSongs();
                        }
                        break;
                    case "G":
                        int getPos = readInt(scanner, "Enter the position: ");
                        System.out.println(playlist.getSong(getPos));
                        break;
                    case "R":
                        int remPos = readInt(scanner, "Enter the position: ");
                        playlist.removeSong(remPos);
                        System.out.println("Song Removed at position " + remPos);
                        break;
                    case "P":
                        playlist.printAllSongs();
                        break;
                    case "S":
                        System.out.println("There are " + playlist.size() + " song(s) in the current playlist.");
                        break;
            // Extra Credit
                    case "N":
                        System.out.print("Enter new playlist name: ");
                        String newName = scanner.nextLine();
                        if (playlists.containsKey(newName)) {
                            System.out.println("Playlist already exists.");
                        } else {
                            playlists.put(newName, new Playlist());
                            currentPlaylistName = newName;
                            System.out.println("Created and switched to playlist: " + newName);
                        }
                        break;
                    case "V":
                        System.out.print("Enter playlist name to switch to: ");
                        String switchTo = scanner.nextLine();
                        if (playlists.containsKey(switchTo)) {
                            currentPlaylistName = switchTo;
                            System.out.println("Switched to playlist: " + switchTo);
                        } else {
                            System.out.println("Playlist not found.");
                        }
                        break;
                    case "C":
                        System.out.print("Enter name for copied playlist: ");
                        String copyPlaylist = scanner.nextLine();
                        playlists.put(copyPlaylist, (Playlist) playlist.clone());
                        System.out.println("Copied playlist to: " + copyPlaylist);
                        break;
                    case "E":
                        System.out.print("Enter playlist name to compare with: ");
                        String compareTo = scanner.nextLine();
                        Playlist compareList = playlists.get(compareTo);
                        if (compareList == null) {
                            System.out.println("Playlist not found.");
                        } else {
                            System.out.println(playlist.equals(compareList)
                                ? "Playlists are equal."
                                : "Playlists are different.");
                        }
                        break;
                    case "D":
                        System.out.println("Playlists:");
                        for (String name : playlists.keySet()) {
                            System.out.println("- " + name);
                        }
                        break;
                    case "Q":
                        running = false;
                        System.out.println("Program terminating normally...");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
