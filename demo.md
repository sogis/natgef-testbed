# Demo IPW-Validator

## Alle Tests durchlaufen lassen

    gr clean test

Toll: Automatische Fortschrittsanzeige...

### Uebersicht der Testresultate

    ...
    There were failing tests. See the report at: file:///home/oliver/code/ipw-validator/functionalTests/build/reports/tests/test/index.html
    ...

## Fehlschlagenden Test(s) einzeln wiederholen

Beispiel: Alle Tests einer Klasse

    gr clean test --tests ch.so.agi.ipwvalidator.Organisation

### Uebersicht der automatisch eingeschränkten Testresultate

    ...
    There were failing tests. See the report at: file:///home/oliver/code/ipw-validator/functionalTests/build/reports/tests/test/index.html
    ...

## Log-Level anpassen

    ./gradlew clean test --tests ch.so.agi.ipwvalidator.Organisation --info

## Jars (Validator, Testing, ....) aufgrund build.gradle verknüpft

    ...
    dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.8.1'

    api 'ch.interlis:ilivalidator:1.11.14-SNAPSHOT'
    }
    ...

## API-Aufruf des Validators

    ...
    boolean valid = Validator.runValidation(TEST_IN+"organisation/14901/14901_fail.xtf", settings);
    ...

# Uns wichtig dabei (Zusammenfassung)

* Übersicht der Test-Resultate "pass" oder "fail" ohne "Logzeilen-Urwald"
* "Drill-Down" Möglichkeit, sofern ein Test im Detail interessiert
* Test(s) auch einzeln ausführen
* Notwendige jars sind nur via build.gradle verknüpft
* Aufruf via API, nicht via Kommandozeile
* Log-Level anpassbar
* Fortschrittsanzeige


