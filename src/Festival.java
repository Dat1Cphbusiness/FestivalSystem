import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Festival {
    private List<Stage> stages;
    String header;
    public Festival() {
        this.stages = new ArrayList<>();
    }

    public void startSession() {

        //load data

        ArrayList<String> stagesdata = FileIO.readData("data/stages.csv");
        String header = stagesdata.removeFirst();
        for(String s:stagesdata){

            String [] fields = s.split(",");
            int id = Integer.parseInt(fields[0].trim());
            String name = fields[1].trim();
            int capacity = Integer.parseInt(fields[2].trim());
            Stage stage = new Stage(id, name, capacity);
            stages.add(stage);

        }

        displayStages();

        //load concerts
        //load artist


      //  run();

    }






    public void run() {
    /*    startSession();
        
        String choice = "";
        while (!choice.equals("0")) {
            showMainMenu();
            choice = TextUI.promptText("Vælg:").trim();

            switch (choice) {
                case "1": displayStages();  break;
                case "2": displayConcerts();break;
                case "3": displayArtists(); break;
                case "4": addStage();     break;
                case "5": addConcert();    break;
                case "6": addArtist();   break;
                case "0": System.out.println("Afslutter...");
                default :System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
        endSession();*/
    }

    private void showMainMenu() {
        System.out.println("\n--- Festival Menu ---");
        System.out.println("1. Vis scener");
        System.out.println("2. Vis koncerter");
        System.out.println("3. Vis kunstnere");
        System.out.println("4. Tilføj scene");
        System.out.println("5. Tilføj koncert");
        System.out.println("6. Tilføj kunstner");
        System.out.println("0. Afslut");
    }

    public void endSession() {
        saveStages();
        saveConcerts();
        saveArtists();
    }

    // -------------------------
    // CREATE METHODS (with data loaded from file)
    // -------------------------

    private void createStages(String[] data) {

    }

    private void createConcerts(String[] data) {
        // Gennemgå alle linjer i data
        // Split linjen i felter på komma
        // Udpak og konverter hvert felt: id, name, date, time, genre, stageId
        // Opret et Concert-objekt
        // Gennemgå alle stages
        // Hvis stage.id matcher stageId
        // Tilføj concert til stage
        // Stop søgning
    }

    private void createArtists(String[] data) {
        // Gennemgå alle linjer i data
        // Split linjen i felter på komma
        // Udpak og konverter hvert felt: id, name, nationality, concertId
        // Opret et Artist-objekt
        // Gennemgå alle stages
        // Gennemgå alle concerts i stage
        // Hvis concert.id matcher concertId
        // Tilføj artist til concert
        // Stop søgning
    }

    // -------------------------
    // ADD METHODS (user input at runtime)
    // -------------------------
    public void addStage() {

    }


  /*  public void addConcert() {
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
*/
    // -------------------------
    // SAVE METHODS (persist to file)
    // -------------------------

    private void saveStages() {


        //build csv
        //write to file
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
            System.out.println(s.getName());
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
