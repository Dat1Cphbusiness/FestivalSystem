import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class Stage {
    // Stage
    private int id;
    private String name;
    private int capacity;
    private List<Concert> concerts;

    public Stage(int id, String name, int capacity) {
        this.concerts = concerts;
        this.capacity = capacity;
        this.name = name;
        this.id = id;
    }

    public void addConcert(int id, String name, LocalDate date, LocalTime time, Genre genre) {
        Concert concert = new Concert(id, name, date, time, genre);
        concerts.add(concert);
    }

    // -------------------------
    // GETTERS
    // -------------------------

    public List<Concert> getConcerts() {
        return concerts;
    }
    public void displayConcerts() {
        for (int i = 0; i < concerts.size(); i++) {
            System.out.println(i+1 + ": " + concerts.get(i).getName());
        }
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
}
