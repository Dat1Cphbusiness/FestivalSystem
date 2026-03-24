import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Concert {
    private int id;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private Genre genre;
    private List<Artist> artists;

    public Concert(int id, String name, LocalDate date, LocalTime time, Genre genre) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.genre = genre;
        this.artists = new ArrayList<>();
    }


    public void displayArtists() {
        for (Artist a : artists) {
            System.out.println(a);
        }
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public Genre getGenre() { return genre; }
    public List<Artist> getArtists() { return artists; }

    @Override
    public String toString() {
        return id + ": " + name + " (" + date + " " + time + " - " + genre + ")";
    }

    public void addArtist(int id, String name, String nationality) {

            this.artists.add(new Artist(id, name, nationality));

    }
}
