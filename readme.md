# Testbed Constraints Datenmodell 'SO_AFU_Naturgefahren_20240515'

## Aufruf Testbed

```
./gradlew clean test
```

### Ausführen eines einzelnen Tests

```
./gradlew clean test --tests AuftragKennungenTest
```

## Übersicht Constraint-Typen / Testbed-Abdeckung

Mittels diesem Testbed kann das Modell 'SO_AFU_Naturgefahren_20240515' in bezug auf die spezifischen Constraints getestet werden.
Die ca. 110 Constraints wurden dafür in 12 Typen-Kategorien eingeteilt und für jeden Kategorie liegt mindestens ein Failcase vor:

### Objektcount

* Beispiele:
  * **MANDATORY CONSTRAINT INTERLIS.objectCount(THIS)==1;**
* Abgedeckte Constraints:
  * CheckAuftragEintrag
* Weitere Constraints:
  * CheckWasserkennwerte

### Unique

* Beispiele:
  * **UNIQUE Kennung;**
* Abgedeckte Constraints:
  * CheckAuftragKennungen
* Weitere Constraints:
  * CheckTeilauftragEindeutigkeit
  * CheckProzessquelleKennung

### OR

* Beispiele:
  * **MANDATORY CONSTRAINT (Hauptprozess != #Wasser) OR Wasserkennwerte;**
* Abgedeckte Constraints:
  * CheckTeilauftragWasserKennwert
* Weitere Constraints:
  * Keine

### GetArea

* Beispiele:
  * **MANDATORY CONSTRAINT GeoW_FunctionsExt.GetArea(THIS,"Geometrie") > 100;**
* Abgedeckte Constraints:
  * CheckAbklperimeterFlaechenGroesse
* Weitere Constraints:
  * CheckBefundSteinBlockschlagFlaechenGroesse
  * CheckBefundBergFelssturzFlaechenGroesse
  * CheckBefundHangmureFlaechenGroesse
  * CheckBefundAbsenkungFlaechenGroesse
  * CheckBefundEinsturzFlaechenGroesse
  * CheckBefundPermanenteRutschungFlaechenGroesse
  * CheckBefundSpontaneRutschungFlaechenGroesse
  * CheckBefundUebermurungFlaechenGroesse
  * CheckBefundUeberschwemmungDynamischFlaechenGroesse
  * CheckBefundUeberschwemmungStatischFlaechenGroesse

### IsInsideExternal

* Beispiele:
  * **MANDATORY CONSTRAINT GeoW_FunctionsExt.IsInsideExternalXtf("SO_Hoheitsgrenzen_Publikation_20170626.Hoheitsgrenzen.Kantonsgrenze.Geometrie", "c6db23e0-0536-4055-a50c-11d2d1e4c6ef" ,THIS, "Geometrie");**
* Abgedeckte Constraints:
  * CheckAbklperimeterFlaechenGroesse
* Weitere Constraints:
  * Keine

### RingCount

* Beispiele:
  * **MANDATORY CONSTRAINT GeoW_FunctionsExt.GetInnerRingsCount(THIS,  "Geometrie") == 0;**
* Abgedeckte Constraints:
  * CheckAbklperimeterKeineLoecher
* Weitere Constraints:
  * CheckBefundKeineLoecher

### Equal

* Beispiele:
  * **MANDATORY CONSTRAINT Prozessquelle_R->Teilauftrag_R->Hauptprozess == #Sturz;**
* Abgedeckte Constraints:
  * CheckBefundSteinBlockschlag
* Weitere Constraints:
  * CheckBefundSpontaneRutschung
  * CheckBefundHangmure
  * CheckBefundBergFelssturz
  * CheckFliessrichtungspfeilHauptprozess
  * CheckBefundBergFelssturzHauptprozess
  * CheckBefundHangmureHauptprozess
  * CheckBefundUfererosionHauptprozess
  * CheckBefundAbsenkungHauptprozess
  * CheckBefundEinsturzHauptprozess
  * CheckBefundPermanenteRutschungHauptprozess
  * CheckBefundSpontaneRutschungHauptprozess
  * CheckBefundUebermurungHauptprozess
  * CheckBefundUeberschwemmungDynamischHauptprozess
  * CheckBefundUeberschwemmungStatischHauptprozess
  * CheckBefundBefundJaehrlichkeitHauptprozess

### Defined

* Beispiele:
  * **MANDATORY CONSTRAINT Prozessquelle_R->Teilauftrag_R->Hauptprozess == #Wasser AND DEFINED(Fliessrichtungspfeil_R);**
* Abgedeckte Constraints:
  * CheckFliessrichtungspfeilZuHauptprozess
* Weitere Constraints:
  * Keine

### IsInsideAreaByCode (Überragung)

IWCodeSteinBlockschlag:

* CheckBefundSteinBlockschlagKeineUeberragung 

IWCodeStandard:

* CheckBefundSpontaneRutschungKeineUeberragung
* CheckBefundHangmureKeineUeberragung
* CheckBefundUeberschwemmungDynamischKeineUeberragung
* CheckBefundUeberschwemmungStatischKeineUeberragung


IWCodeBergFelssturz:

* CheckBefundBergFelssturzKeineUeberragung

IWCodeAbsenkungEinsturz:

* CheckBefundAbsenkungKeineUeberragung
* CheckBefundEinsturzKeineUeberragung

IWCodeUebermurung:

* CheckBefundUebermurungKeineUeberragung

Jährlichkeit als Einzel-Zahlattribut:

* CheckBefundJaehrlichkeitKeineUeberragung

Jährlichkeit als Einzel-Codeattribut:

  * CheckKennwertUeberschwemmungFliesstiefeKeineUeberragungJaehrlichkeit
  * CheckKennwertUebermurungFliesstiefeKeineUeberragungJaehrlichkeit
  * ?CheckKennwertUeberschwemmungFliessgeschwindigkeitKeineUeberragungJaehrlichkeit
  * ?CheckKennwertUebermurungFliessgeschwindigkeitKeineUeberragungJaehrlichkeit

Kennwert als Einzel-Codeattribut Höhe oder Geschwindigkeit:

  * CheckKennwertUeberschwemmungFliesstiefeKeineUeberragungH
  * CheckKennwertUeberschwemmungFliessgeschwindigkeitKeineUeberragungH
  * CheckKennwertUebermurungFliesstiefeKeineUeberragungH
  * CheckKennwertUebermurungFliessgeschwindigkeitKeineUeberragungH

    


* Beispiele:
  * **SET CONSTRAINT GeoW_FunctionsExt.IsInsideAreaByCode(GeoW_FunctionsExt.GetInGroups(ALL, "PQ_Jaehrlichkeit_SteinBlockschlag_R->Prozessquelle_R"), "Geometrie" ,"IWCode");**
* Abgedeckte Constraints:
  * CheckBefundSteinBlockschlagKeineUeberragung
  * CheckBefundSpontaneRutschungKeineUeberragung
* Weitere Constraints:
  * CheckBefundBergFelssturzKeineUeberragung
  * CheckBefundHangmureKeineUeberragung
  * CheckBefundAbsenkungKeineUeberragung
  * CheckBefundEinsturzKeineUeberragung
  * CheckBefundUebermurungKeineUeberragung
  * CheckBefundUeberschwemmungDynamischKeineUeberragung
  * CheckBefundUeberschwemmungStatischKeineUeberragung
  * CheckBefundJaehrlichkeitKeineUeberragung
  * CheckKennwertUeberschwemmungFliesstiefeKeineUeberragungH
  * CheckKennwertUeberschwemmungFliesstiefeKeineUeberragungJaehrlichkeit
  * CheckKennwertUeberschwemmungFliessgeschwindigkeitKeineUeberragungH
  * CheckKennwertUebermurungFliesstiefeKeineUeberragungH
  * CheckKennwertUebermurungFliesstiefeKeineUeberragungJaehrlichkeit
  * CheckKennwertUebermurungFliessgeschwindigkeitKeineUeberragungH

### NOT-OR

* Beispiele:
  * **MANDATORY CONSTRAINT NOT((IWCode == #rot_stark_30) OR (IWCode == #rot_mittel_30) OR (IWCode == #blau_schwach_30)) OR (PQ_Jaehrlichkeit_SteinBlockschlag_R->Jaehrlichkeit == 30);**
* Abgedeckte Constraints:
  * CheckBefundSteinBlockschlagJaehrlichkeit-30
* Weitere Constraints:
  * CheckBefundSteinBlockschlagJaehrlichkeit-100
  * CheckBefundSteinBlockschlagJaehrlichkeit-300
  * CheckBefundHangmureJaehrlichkeit-30
  * CheckBefundHangmureJaehrlichkeit-100
  * CheckBefundHangmureJaehrlichkeit-300
  * CheckBefundSpontaneRutschungJaehrlichkeit-30
  * CheckBefundSpontaneRutschungJaehrlichkeit-100
  * CheckBefundSpontaneRutschungJaehrlichkeit-300
  * CheckAbklperimeterBeurteilungUeberschwemmung_statisch
  * CheckAbklperimeterBeurteilungUeberschwemmung_dynamisch
  * CheckAbklperimeterBeurteilungUebermurung
  * CheckAbklperimeterBeurteilungUfererosion
  * CheckAbklperimeterBeurteilungEinsturz
  * CheckAbklperimeterBeurteilungAbsenkung
  * CheckAbklperimeterBeurteilungSteinBlockschlag
  * CheckAbklperimeterBeurteilungBerg_Felssturz
  * CheckAbklperimeterBeurteilungHangmure
  * CheckAbklperimeterBeurteilungSpontaneRutschung
  * CheckAbklperimeterBeurteilungPermanenteRutschung

### IsInside

* Beispiele:
  * **MANDATORY CONSTRAINT (GeoW_FunctionsExt.IsInside(GeoW_FunctionsExt.Union(PQ_Jaehrlichkeit_SteinBlockschlag_R->Prozessquelle_R->Teilauftrag_R->Abklaerungsperimeter_R, "Geometrie"), UNDEFINED, THIS, "Geometrie"));**
* Abgedeckte Constraints:
  * CheckBefundSteinBlockschlagImAbklPerimeter
* Weitere Constraints:
  * CheckBefundBergFelssturzImAbklPerimeter
  * CheckBefundHangmureImAbklPerimeter
  * CheckBefundUfererosionImAbklPerimeter
  * CheckBefundAbsenkungImAbklPerimeter
  * CheckBefundEinsturzImAbklPerimeter
  * CheckBefundPermanenteRutschungImAbklPerimeter
  * CheckBefundSpontaneRutschungImAbklPerimeter
  * CheckBefundUebermurungImAbklPerimeter
  * CheckBefundUeberschwemmungDynamischImAbklPerimeter
  * CheckBefundUeberschwemmungStatischImAbklPerimeter
  * CheckBefundJaehrlichkeitImAbklPerimeter

### AreAreas mit Filter

* Beispiele:
  * **SET CONSTRAINT WHERE IWCode == #rot_stark_30 OR IWCode == #rot_mittel_30 OR IWCode == #blau_schwach_30: INTERLIS.areAreas(GeoW_FunctionsExt.GetInGroups(ALL, "Prozessquelle_R"),UNDEFINED, >> Geometrie);**
* Abgedeckte Constraints:
  * CheckBefundUeberschwemmungStatischKeineUeberlappung-300
* Weitere Constraints:
  * CheckBefundSteinBlockschlagKeineUeberlappung-30
  * CheckBefundSteinBlockschlagKeineUeberlappung-100
  * CheckBefundSteinBlockschlagKeineUeberlappung-300
  * CheckBefundSteinBlockschlagKeineUeberlappung-Restgef
  * CheckBefundBergFelssturzKeineUeberlappung-30
  * CheckBefundBergFelssturzKeineUeberlappung-100
  * CheckBefundBergFelssturzKeineUeberlappung-300
  * CheckBefundBergFelssturzKeineUeberlappung-Restgef
  * CheckBefundHangmureKeineUeberlappung-30
  * CheckBefundHangmureKeineUeberlappung-100
  * CheckBefundHangmureKeineUeberlappung-300
  * CheckBefundHangmureKeineUeberlappung-Restgef
  * CheckBefundAbsenkungKeineUeberlappung-permanent
  * CheckBefundeEinsturzKeineUeberlappung-permanent
  * CheckBefundePermanenteRutschungKeineUeberlappung-permanent
  * CheckBefundePermanenteRutschungKeineUeberlappung-Restgef
  * CheckBefundSpontaneRutschungKeineUeberlappung-30
  * CheckBefundSpontaneRutschungKeineUeberlappung-100
  * CheckBefundSpontaneRutschungKeineUeberlappung-300
  * CheckBefundSpontaneRutschungKeineUeberlappung-Restgef
  * CheckBefundUebermurungKeineUeberlappung-30
  * CheckBefundUebermurungKeineUeberlappung-100
  * CheckBefundUebermurungKeineUeberlappung-300
  * CheckBefundUeberschwemmungDynamischKeineUeberlappung-30
  * CheckBefundUeberschwemmungDynamischKeineUeberlappung-100
  * CheckBefundUeberschwemmungDynamischKeineUeberlappung-300
  * CheckBefundUeberschwemmungDynamischKeineUeberlappung-Restgef
  * CheckBefundUeberschwemmungStatischKeineUeberlappung-30
  * CheckBefundUeberschwemmungStatischKeineUeberlappung-100
  * CheckBefundUeberschwemmungStatischKeineUeberlappung-300
  * CheckBefundUeberschwemmungStatischKeineUeberlappung-Restgef

## Merge-Logik der XTF-Dateien siehe im Repo:

https://github.com/GeoWerkstatt/interlis-testbed-runner