# Gruppeøvelse: Artist-gruppen

I dag skal I implementere indlæsning, oprettelse og persistering af `Artist`-objekter i vores `Festival`-applikation.

Jeres opgave er delt i to dele:

1. Implementer `createArtists()` i `Festival`
2. Implementer `saveArtists()` i `Festival`

---

## Kontekst

`FileIO`-klassen er allerede implementeret med to metoder:
- `FileIO.loadData(filename)` — læser en CSV-fil og returnerer et `String[]`, én linje pr. element, uden header-linjen
- `FileIO.saveData(filename, lines)` — skriver et `List<String>` til en CSV-fil

`Festival.startSession()` kalder `createArtists()`, og `Festival.endSession()` kalder `saveArtists()`. Begge metoder skal I implementere.

---

## CSV-format

Filen `artists.csv` har følgende kolonner:

```
id, name, nationality, concertId
```

Se data sample i data folderen.

---

## Del 1: Implementer Artist-klassen

`Artist` har følgende felter:

```java
private int id;
private String name;
private String nationality;
```

Implementer klassen inkl. konstruktør og `toString()`.

---

## Del 2: Implementer createArtists()

I `Festival`-klassen skal I implementere:

```java
private void createArtists(String[] data) {
    // 1. Iterer over linjerne i data
    // 2. Split hver linje på komma (isolerer felterne)
    // 3. Udpak hvert felt (fx String name = fields[1].trim();)
    // 4. Opret et Artist-objekt
    // 5. Find den Concert der matcher concertId
    // 6. Tilføj Artist til Concert
}
```

**Bemærk:** `concertId` fortæller hvilken `Concert` kunstneren tilhører. I skal søge i alle stages' koncerter.

<details>
<summary>Hint: finde den rigtige Concert</summary>

Koncerterne ligger inde i stages, så I skal søge i to niveauer:

**pseudo-code:**
```
Gennemgå alle stages
    Gennemgå alle koncerter i stage
        Hvis koncertens id matcher concertId
            Tilføj artist til koncert
            Stop søgning (break)
```

</details>

---

## Del 3: Implementer saveArtists()

Når programmet lukker, skal alle artists gemmes tilbage til `artists.csv`.

I `Festival`-klassen skal I implementere:

```java
private void saveArtists() {
    // 1. Opret en tom List<String>
    // 2. Gennemgå alle stages og deres concerts
    // 3. Gennemgå alle artists i hver concert
    // 4. Byg en CSV-linje for hver artist
    // 5. Kald FileIO.saveData()
}
```

**Vigtigt:** CSV-linjen skal indeholde `concertId` — ellers kan I ikke genlæse dataen næste gang programmet starter. I ved hvilken concert en artist tilhører, fordi I sidder i den inderste løkke over koncertens artists.

<details>
<summary>Hint: bygge en CSV-linje</summary>

En CSV-linje er bare en String med komma-separerede værdier, i samme rækkefølge som kolonneheaderen:

```java
String line = artist.getId() + ", " + artist.getName() + ", " + artist.getNationality() + ", " + concert.getId();
```

</details>

<details>
<summary>Hint: struktur for den dobbelte løkke</summary>

```
Gennemgå alle stages
    Gennemgå alle concerts i stage
        Gennemgå alle artists i concert
            Byg CSV-linje og tilføj til listen
```

Læg mærke til at det er præcis den samme struktur som da I søgte efter den rigtige concert i Del 2 — bare nu gennemgår I *alle* koncerter i stedet for at stoppe når I finder én.

</details>

---



## Hvad sker der bag kulisserne?

```
startSession()                         endSession()
    │                                      │
    ├── FileIO.loadData("artists.csv")     └── saveArtists()
    │        → String[]                            │
    └── createArtists(String[])                    ├── bygger List<String>
            │                                      └── FileIO.saveData("artists.csv", lines)
            ├── parser felter
            ├── opretter Artist
            └── finder Concert og tilføjer Artist

addArtist()  ← håndteres af Festival og TextUI, ikke af jer
```

`FileIO` ved ingenting om kunstnere. `Artist` ved ingenting om filer. `Festival` koordinerer begge veje. Det er separation of concerns i praksis.