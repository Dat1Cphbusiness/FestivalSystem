# Gruppeøvelse: Concert-gruppen

I dag skal I implementere indlæsning, oprettelse og persistering af `Concert`-objekter i vores `Festival`-applikation.

Jeres opgave er delt i to dele:

2. Implementer `createConcerts()` i `Festival`
3. Implementer `saveConcerts()` i `Festival`

---

## Kontekst

`FileIO`-klassen er allerede implementeret med to metoder:
- `FileIO.loadData(filename)` — læser en CSV-fil og returnerer et `String[]`, én linje pr. element, uden header-linjen
- `FileIO.saveData(filename, lines)` — skriver et `List<String>` til en CSV-fil

`Festival.startSession()` kalder `createConcerts()`, og `Festival.endSession()` kalder `saveConcerts()`. Begge metoder skal I implementere.

---

## CSV-format

Filen `concerts.csv` har følgende kolonner:

```
id, name, date, time, genre, stageId
```

Se data sample i data folderen.

---

## Del 1: Implementer Concert-klassen

`Concert` skal have følgende felter:

```java
private int id;
private String name;
private LocalDate date;
private LocalTime time;
private Genre genre;
private List<Artist> artists;
```

Implementer klassen inkl. konstruktør, `addArtist(int id, String name, String nationality)` og `toString()`.

---

## Del 2: Implementer createConcerts()

I `Festival`-klassen skal I implementere:

```java
private void createConcerts(String[] data) {
    // 1. Iterer over linjerne i data
    // 2. Split hver linje på komma
    // 3. Udpak og konverter hvert felt
    // 4. Opret et Concert-objekt
    // 5. Find den Stage der matcher stageId
    // 6. Tilføj Concert til Stage
}
```

**Bemærk:** `stageId` fortæller hvilken `Stage` koncerten tilhører. I skal søge i `stages`-listen efter den rigtige stage.

<details>
<summary>Hint: finde den rigtige Stage</summary>

```
Gennemgå alle stages
    Hvis stage-id matcher stageId
        Tilføj concert til stage
        Stop søgning (break)
```

</details>

---

## Del 3: Implementer saveConcerts()

Når programmet lukker, skal alle concerts gemmes tilbage til `concerts.csv`.

I `Festival`-klassen skal I implementere:

```java
private void saveConcerts() {
    // 1. Opret en tom List<String>
    // 2. Gennemgå alle stages
    // 3. Gennemgå alle concerts i stage
    // 4. Byg en CSV-linje for hver concert
    // 5. Kald FileIO.saveData()
}
```

**Vigtigt:** CSV-linjen skal indeholde `stageId` — ellers kan I ikke genlæse dataen næste gang programmet starter. I ved hvilken stage en concert tilhører, fordi I sidder i den inderste løkke over stagenes concerts.

<details>
<summary>Hint: bygge en CSV-linje</summary>

En CSV-linje er bare en String med komma-separerede værdier, i samme rækkefølge som kolonneheaderen:

```java
String line = concert.getId() + ", " + concert.getName() + ", " + concert.getDate() + ", "
            + concert.getTime() + ", " + concert.getGenre() + ", " + stage.getId();
```

</details>

<details>
<summary>Hint: struktur for den dobbelte løkke</summary>

```
Gennemgå alle stages
    Gennemgå alle concerts i stage
        Byg CSV-linje og tilføj til listen
```

Læg mærke til at det er præcis den samme struktur som da I søgte efter den rigtige stage i Del 2 — bare nu gennemgår I *alle* stages og concerts i stedet for at stoppe når I finder én.

</details>

---




## Hvad sker der bag kulisserne?

```
startSession()                         endSession()
    │                                      │
    ├── FileIO.loadData("concerts.csv")    └── saveConcerts()
    │        → String[]                            │
    └── createConcerts(String[])                   ├── bygger List<String>
            │                                      └── FileIO.saveData("concerts.csv", lines)
            ├── parser felter
            ├── opretter Concert
            └── finder Stage og tilføjer Concert

addConcert()  ← håndteres af Festival og TextUI, ikke af jer
```

`FileIO` ved ingenting om koncerter. `Concert` ved ingenting om filer. `Festival` koordinerer begge veje. Det er separation of concerns i praksis.