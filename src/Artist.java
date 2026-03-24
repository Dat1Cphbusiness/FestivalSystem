import java.util.Scanner;

public class Artist {
    private int id;
    private String name;
    private String nationality;

    public Artist(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getNationality() { return nationality; }

    @Override
    public String toString() {
        return id + ": " + name + " (" + nationality + ")";
    }

    public String toCSV() {
        String s = this.getId()+","+this.getName()+","+this.getNationality();
        return s;

    }
}
