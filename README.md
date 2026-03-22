# FestivalSystem

Et Java-konsolprogram til administration af et musikfestival. Systemet demonstrerer indlæsning, parsing og persistering af data fra CSV-filer, samt princippet om separation of concerns.

---

## Projektbeskrivelse

FestivalSystem administrerer et festival med scener, koncerter og kunstnere. Data gemmes i CSV-filer og indlæses ved opstart. Brugeren kan tilføje nye scener, koncerter og kunstnere via en menubaseret dialog, og alle ændringer persisteres når programmet afsluttes.

---

## Domænemodel

```
Festival
    └── Stage          (én festival har mange scener)
            └── Concert    (én scene har mange koncerter)
                    └── Artist (én koncert har mange kunstnere)
```

Hierarkiet er strengt én-til-mange hele vejen ned, hvilket afspejles i CSV-filernes foreign key-struktur.

---

## Klasser

| Klasse | Ansvar |
|---|---|
| `Festival` | Koordinerer indlæsning, persistering og brugerdialog |
| `Stage` | Repræsenterer en scene og dens koncerter |
| `Concert` | Repræsenterer en koncert med dato, tid og genre |
| `Artist` | Repræsenterer en kunstner |
| `Genre` | Enum med musikgenrer |
| `FileIO` | Utility-klasse — læser og skriver CSV-filer |
| `TextUI` | Utility-klasse — håndterer brugerinput via konsol |

---

## CSV-filer

Data gemmes i fire CSV-filer i projektets rodmappe:

**stages.csv**
```
id, name, capacity
1, Main Stage, 5000
```

**concerts.csv**
```
id, name, date, time, genre, stageId
1, Midnight Groove, 2024-08-10, 22:00, HOUSE, 1
```

**artists.csv**
```
id, name, nationality, concertId
1, DJ Koze, German, 1
```

Foreign keys (`stageId`, `concertId`) sikrer at objekter kan kobles korrekt ved indlæsning. Uden disse kan hierarkiet ikke rekonstrueres fra filer.

---

## Separation of concerns

En central designbeslutning i projektet er at holde ansvarsområder adskilt:

| Klasse | Hvad den ved |
|---|---|
| `FileIO` | Hvordan data læses og skrives — intet om domænet |
| `Festival` | Hvordan data koordineres og brugeren interagerer — intet om filformater |
| `Stage`, `Concert`, `Artist` | Hvad data betyder — intet om filer eller brugerinput |

Dette betyder at hvis filformatet ændres, er det kun `FileIO` der skal opdateres. Hvis domænet ændres, er det kun domæneklasserne. Klasserne er løst koblede og har ét ansvarsområde hver.

---

## Læringsmål

Efter dette projekt forventes den studerende at kunne:

### Viden
- Forklare hvad separation of concerns betyder og hvorfor det er et nyttigt designprincip
- Beskrive forskellen på at indlæse, parse og persistere data
- Forklare hvad en foreign key er i konteksten af CSV-filer, og hvorfor den er nødvendig for at rekonstruere et objekthierarki

### Færdigheder
- Implementere en metode der læser et `String[]` og konverterer hver linje til et domæneobjekt
- Parse primitive typer og datoer fra strings (`Integer.parseInt`, `LocalDate.parse`, `LocalTime.parse`, `Genre.valueOf`)
- Koble objekter til hinanden via foreign keys ved indlæsning
- Implementere en metode der serialiserer et objekthierarki tilbage til CSV-format
- Organisere kode i klasser med veldefinerede ansvarsområder

### Kompetencer
- Designe en simpel filbaseret persistensløsning til et objektorienteret system
- Identificere hvilke klasser der bør eje hvilke ansvarsområder i et givet design
- Reflektere over begrænsningerne ved filbaseret persistering sammenlignet med en relationel database

---

## Kendte begrænsninger

- **Ingen sletning** — sletning af objekter kræver håndtering af forældreløse foreign keys på tværs af filer. Dette er en af motivationerne for at skifte til en database.
- **Ingen fejlhåndtering** — forkert formateret input vil kaste en exception. Exception handling er næste skridt.
- **Sekventielle IDs** — nye objekter får `max eksisterende id + 1`. Huller i nummerrækkefølgen efter eventuel manuel sletning er forventede og uproblematiske.

---

## Kom i gang

1. Klon projektet og åbn det i IntelliJ
2. Genbrug FileIO og TextUI utility klasser ved at placere dem i src folderen
3. Følg instruktionerne og implementer ufærdige metoder
4. Kør `main`-metoden
5. Brug menuen til at navigere og tilføje data
6. Test
