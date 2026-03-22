import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Festival {
    private List<Stage> stages;

    public Festival() {
        this.stages = new ArrayList<>();
    }

    public void startSession() {
        createStages(FileIO.loadData("stages.csv"));
        createConcerts(FileIO.loadData("concerts.csv"));
        createArtists(FileIO.loadData("artists.csv"));
    }

    public void endSession() {
        saveStages();
        saveConcerts();
        saveArtists();
    }

    // -------------------------
    // CREATE METHODS (load from file)
    // -------------------------

    private void createStages(String[] data) {
        for (String line : data) {
            String[] fields = line.split(",");
            int id = Integer.parseInt(fields[0].trim());
            String name = fields[1].trim();
            int capacity = Integer.parseInt(fields[2].trim());
            stages.add(new Stage(id, name, capacity));
        }
    }

    private void createConcerts(String[] data) {
        // Implemented by Concert-gruppen
        // Wire each Concert to the correct Stage using stageId
    }

    private void createArtists(String[] data) {
        // Implemented by Artist-gruppen
        // Wire each Artist to the correct Concert using concertId
    }

    // -------------------------
    // ADD METHODS (user input at runtime)
    // -------------------------
    public void addStage() {
        String name = TextUI.promptText("Scenens navn:");
        int capacity = Integer.parseInt(TextUI.promptText("Kapacitet:").trim());
        int newId = nextStageId();
        stages.add(new Stage(newId, name, capacity));
    }


    public void addConcert() {
        displayStages();
        int stageId = Integer.parseInt(TextUI.promptText("Indtast scene-id:").trim());

        Stage selectedStage = null;
        for (Stage s : stages) {
            if (s.getId() == stageId) {
                selectedStage = s;
                break;
            }
        }

        if (selectedStage == null) {
            System.out.println("Scene ikke fundet.");
            return;
        }

        String name = TextUI.promptText("Koncertens navn:");
        LocalDate date = LocalDate.parse(TextUI.promptText("Dato (yyyy-mm-dd):").trim());
        LocalTime time = LocalTime.parse(TextUI.promptText("Tidspunkt (hh:mm):").trim());
        Genre genre = Genre.valueOf(TextUI.promptText("Genre (HOUSE/JAZZ/ROCK/TECHNO/HIPHOP):").trim().toUpperCase());
        int newId = nextConcertId();

        selectedStage.addConcert(newId, name, date, time, genre);
    }

    public void addArtist() {
        displayConcerts();
        int concertId = Integer.parseInt(TextUI.promptText("Indtast koncert-id:").trim());

        Concert selectedConcert = null;
        for (Stage s : stages) {
            for (Concert c : s.getConcerts()) {
                if (c.getId() == concertId) {
                    selectedConcert = c;
                    break;
                }
            }
        }

        if (selectedConcert == null) {
            System.out.println("Koncert ikke fundet.");
            return;
        }

        String name = TextUI.promptText("Kunstnerens navn:");
        String nationality = TextUI.promptText("Nationalitet:");
        int newId = nextArtistId();

        selectedConcert.addArtist(newId, name, nationality);
    }

    // -------------------------
    // SAVE METHODS (persist to file)
    // -------------------------

    private void saveStages() {
        List<String> lines = new ArrayList<>();
        for (Stage s : stages) {
            lines.add(s.getId() + ", " + s.getName() + ", " + s.getCapacity());
        }
        FileIO.saveData("stages.csv", lines);
    }

    private void saveConcerts() {
        // Implemented by Concert-gruppen
        // Remember to include stageId in each CSV line
    }

    private void saveArtists() {
        // Implemented by Artist-gruppen
        // Remember to include concertId in each CSV line
    }

    // -------------------------
    // DISPLAY METHODS
    // -------------------------

    public void displayStages() {
        for (Stage s : stages) {
            System.out.println(s);
        }
    }
    public void displayConcerts() {
        for (Stage s : stages) {
            s.displayConcerts();
        }
    }
    public void displayArtists() {
        for (Stage s : stages) {
            for (Concert c : s.getConcerts()) {
                c.displayArtists();
            }
        }
    }


    // -------------------------
    // HELPERS
    // -------------------------
    private int nextStageId() {
        int max = 0;
        for (Stage s : stages) {
            if (s.getId() > max) max = s.getId();
        }
        return max + 1;
    }
    private int nextConcertId() {
        int max = 0;
        for (Stage s : stages) {
            for (Concert c : s.getConcerts()) {
                if (c.getId() > max) max = c.getId();
            }
        }
        return max + 1;
    }

    private int nextArtistId() {
        int max = 0;
        for (Stage s : stages) {
            for (Concert c : s.getConcerts()) {
                for (Artist a : c.getArtists()) {
                    if (a.getId() > max) max = a.getId();
                }
            }
        }
        return max + 1;
    }
}
