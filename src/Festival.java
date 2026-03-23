import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Festival {
    private List<Stage> stages;
    private String stagesheader;
    private String concertsheader;


    public Festival() {
        this.stages = new ArrayList<>();
    }

    public void startSession() {
        ArrayList<String> stagesdata = FileIO.readData("data/stages.csv");
        createStages(stagesdata);
        ArrayList<String> consertsdata = FileIO.readData("data/stages.csv");
        createStages(consertsdata);
        //load concerts
        //load artist

       run();
    }


    public void run() {

        String choice = "";
        while (!choice.equals("0")) {
            showMainMenu();
            choice = TextUI.promptText("Vælg:").trim();

            switch (choice) {
                case "1": displayStages();  break;
                case "2": displayConcerts();break;
                case "3": displayArtists(); break;
                case "4": addStage();     break;
             //   case "5": addConcert();    break;
             //   case "6": addArtist();   break;
                case "0": System.out.println("Afslutter...");
                default :System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
        endSession();
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

    private void createStages(ArrayList<String> data) {
        stagesheader = data.removeFirst();
        for(String s:data){
            String [] fields = s.split(",");
            int id = Integer.parseInt(fields[0].trim());
            String name = fields[1].trim();
            int capacity = Integer.parseInt(fields[2].trim());
            Stage stage = new Stage(id, name, capacity);
            stages.add(stage);
        }
        //displayStages();
    }

    private void createConcerts(ArrayList <String> concertsData) {
        concertsheader = concertsData.removeFirst();
        for (String s : concertsData) {
            String[] fields = s.split(",");

            int id = Integer.parseInt(fields[0].trim());
            String name = fields[1].trim();
            LocalDate date = LocalDate.parse(fields[2].trim());
            LocalTime time = LocalTime.parse(fields[3].trim());
            Genre genre = Genre.valueOf(fields[4].trim());
            // Concert concert = new Concert(id, name, date, time, genre);

            for (Stage stage : stages) {
                if (stage.getId() == Integer.parseInt(fields[5].trim())) {
                    stage.addConcert(id, name, date, time, genre);
                    break;
                }
            }
        }
    }
    private void createArtists(ArrayList<String> artistData) {
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
        //get name from user
        String stagename = TextUI.promptText("Skriv scenens navn:");
        int capacity = TextUI.promptNumber("Skriv scenens kapacitet:");
        Stage stage = new Stage(stages.size()+1, stagename,capacity);
        stages.add(stage);
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

       ArrayList<String> data = new ArrayList<>();
        //build csv
        //loop through stages list
        for(Stage s: stages){
            String csvline = s.getId()+","+s.getName()+","+s.getCapacity();
            data.add(csvline);
        }
            //for each stage, create a csv line
            //add that line to a String array
        //call FileIO saveData method with the array as argument

        FileIO.saveData(data);



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
