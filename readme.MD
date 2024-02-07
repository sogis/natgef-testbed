# Testbed Constraints Datenmodell 'SO_AFU_Naturgefahren_20230802'

Mittels diesem Testbed kann das Modell 'SO_AFU_Naturgefahren_20230802' in bezug auf die spezifischen Constraints getestet werden.
Die 76 Constraints wurden dafür in 12 Typen-Kategorien eingeteilt und für jeden Kategorie liegt mindestens ein Failcase vor:

## Übersicht Constraint-Typen / Testbed-Abdeckung

|Constraint Typ|Repräsentant|Liste der Constraints|Testbed|
|:----|:----|:----|:----|
|1: Mandatory.ObjectCount|MANDATORY CONSTRAINT INTERLIS.objectCount(THIS)==1;|CheckAuftragEintrag|OK|
| | |CheckWasserkennwerte| |
|2: Unique|UNIQUE Kennung;|CheckAuftragKennungen|OK|
| | |CheckTeilauftragEindeutigkeit| |
| | |CheckProzessquelleKennung| |
|3: Mandatory.OR|MANDATORY CONSTRAINT (Hauptprozess != #Wasser) OR Wasserkennwerte;|CheckTeilauftragWasserKennwert|OK|
|4: Mandatory.GetArea|MANDATORY CONSTRAINT GeoW_FunctionsExt.GetArea(THIS,"Geometrie") > 100;|CheckAbklperimeterFlaechenGroesse|OK|
| | |CheckBefundFlaechenGroesse| |
|5: Mandatory.IsInsideExternal|MANDATORY CONSTRAINT GeoW_FunctionsExt.IsInsideExternalDataset("SO_Hoheitsgrenzen_Publikation_20170626.Hoheitsgrenzen.Kantonsgrenze.Geometrie", "c6db23e0-0536-4055-a50c-11d2d1e4c6ef" ,THIS, "Geometrie");|CheckAbklperimeterInnerhalbSO|OK|
|6: Mandatory.RingCount|MANDATORY CONSTRAINT GeoW_FunctionsExt.GetInnerRingsCount(THIS,  "Geometrie") == 0; |CheckAbklperimeterKeineLoecher|OK|
| | |CheckBefundKeineLoecher| |
|7: Mandatory.Equal|MANDATORY CONSTRAINT Prozessquelle_R->Teilauftrag_R->Hauptprozess == #Sturz;|CheckBefundSteinBlockschlag|OK|
| | |CheckBefundSpontaneRutschung| |
| | |CheckBefundHangmure| |
| | |CheckBefundBergFelssturz| |
| | |CheckFliessrichtungspfeilHauptprozess| |
| | |CheckBefundBergFelssturzHauptprozess| |
| | |CheckBefundHangmureHauptprozess| |
| | |CheckBefundUfererosionHauptprozess| |
| | |CheckBefundAbsenkungHauptprozess| |
| | |CheckBefundEinsturzHauptprozess| |
| | |CheckBefundPermanenteRutschungHauptprozess| |
| | |CheckBefundSpontaneRutschungHauptprozess| |
| | |CheckBefundUebermurungHauptprozess| |
| | |CheckBefundUeberschwemmungDynamischHauptprozess| |
| | |CheckBefundUeberschwemmungStatischHauptprozess| |
| | |CheckBefundBefundJaehrlichkeitHauptprozess| |
|8: Mandatory.Defined|MANDATORY CONSTRAINT Prozessquelle_R->Teilauftrag_R->Hauptprozess == #Wasser AND DEFINED(Fliessrichtungspfeil_R);|CheckFliessrichtungspfeilZuHauptprozess|OK|
| | |CheckProzessquelleBefundJaehrlichkeit| |
|9: Set.Ueberragung|SET CONSTRAINT GeoW_FunctionsExt.IsInsideAreaByCodeEnum(GeoW_FunctionsExt.GetInGroups(ALL, "PQ_Jaehrlichkeit_SteinBlockschlag_R->Prozessquelle_R"), "Geometrie" ,"IWCode");|CheckBefundSteinBlockschlagKeineUeberragung|OK|
| | |CheckBefundBergFelssturzKeineUeberragung| |
| | |CheckBefundHangmureKeineUeberragung| |
| | |CheckBefundAbsenkungKeineUeberragung| |
| | |CheckBefundEinsturzKeineUeberragung| |
| | |CheckBefundSpontaneRutschungKeineUeberragung|OK|
| | |CheckBefundUebermurungKeineUeberragung| |
| | |CheckBefundUeberschwemmungDynamischKeineUeberragung| |
| | |CheckBefundUeberschwemmungStatischKeineUeberragung| |
| | |CheckBefundJaehrlichkeitKeineUeberragung| |
| | |CheckKennwertUeberschwemmungFliesstiefeKeineUeberragungH| |
| | |CheckKennwertUeberschwemmungFliesstiefeKeineUeberragungJaehrlichkeit| |
| | |CheckKennwertUeberschwemmungFliessgeschwindigkeitKeineUeberragungH| |
| | |CheckKennwertUebermurungFliesstiefeKeineUeberragungH| |
| | |CheckKennwertUebermurungFliesstiefeKeineUeberragungJaehrlichkeit| |
| | |CheckKennwertUebermurungFliessgeschwindigkeitKeineUeberragungH| |
|10: Mandatory.NOT-OR|MANDATORY CONSTRAINT NOT((IWCode == #rot_stark_30) OR (IWCode == #rot_mittel_30) OR (IWCode == #blau_schwach_30)) OR (PQ_Jaehrlichkeit_SteinBlockschlag_R->Jaehrlichkeit == 30);|CheckAbklperimeterBeurteilungUeberschwemmung_statisch| |
| | |CheckAbklperimeterBeurteilungUeberschwemmung_dynamisch| |
| | |CheckAbklperimeterBeurteilungUebermurung| |
| | |CheckAbklperimeterBeurteilungUfererosion| |
| | |CheckAbklperimeterBeurteilungEinsturz| |
| | |CheckAbklperimeterBeurteilungAbsenkung| |
| | |CheckAbklperimeterBeurteilungSteinBlockschlag| |
| | |CheckAbklperimeterBeurteilungBerg_Felssturz| |
| | |CheckAbklperimeterBeurteilungHangmure| |
| | |CheckAbklperimeterBeurteilungSpontaneRutschung| |
| | |CheckAbklperimeterBeurteilungPermanenteRutschung| |
| | |CheckBefundSteinBlockschlagJaehrlichkeit-30|OK|
| | |CheckBefundSteinBlockschlagJaehrlichkeit-100| |
| | |CheckBefundSteinBlockschlagJaehrlichkeit-300| |
| | |CheckBefundHangmureJaehrlichkeit-30| |
| | |CheckBefundHangmureJaehrlichkeit-100| |
| | |CheckBefundHangmureJaehrlichkeit-300| |
| | |CheckBefundSpontaneRutschungJaehrlichkeit-30| |
| | |CheckBefundSpontaneRutschungJaehrlichkeit-100| |
| | |CheckBefundSpontaneRutschungJaehrlichkeit-300| |
|11: Mandatory.IsInside|MANDATORY CONSTRAINT (GeoW_FunctionsExt.IsInside(GeoW_FunctionsExt.Union(PQ_Jaehrlichkeit_SteinBlockschlag_R->Prozessquelle_R->Teilauftrag_R->Abklaerungsperimeter_R, "Geometrie"), THIS, "Geometrie"));|CheckBefundSteinBlockschlagImAbklPerimeter|OK|
| | |CheckBefundBergFelssturzImAbklPerimeter| |
| | |CheckBefundHangmureImAbklPerimeter| |
| | |CheckBefundUfererosionImAbklPerimeter| |
| | |CheckBefundAbsenkungImAbklPerimeter| |
| | |CheckBefundEinsturzImAbklPerimeter| |
| | |CheckBefundPermanenteRutschungImAbklPerimeter| |
| | |CheckBefundSpontaneRutschungImAbklPerimeter| |
| | |CheckBefundUebermurungImAbklPerimeter| |
| | |CheckBefundUeberschwemmungDynamischImAbklPerimeter| |
| | |CheckBefundUeberschwemmungStatischImAbklPerimeter| |
| | |CheckBefundJaehrlichkeitImAbklPerimeter| |
|12: Set.areAreas|SET CONSTRAINT INTERLIS_ext.areAreas3(ALL,>Prozessquelle,"Geometrie");|CheckBefundBefundJaehrlichkeitFlaechendeckung|keine Testdaten|

## Spezialitäten

* Generell: Das aktuell vorliegende Transferfile des Testprojekts ist nicht fehlerfrei. Dementsprechend müssen für eine fehlerfreie Validierung verschiedene Constraints auskommentiert werden.

* 1: Hier wird im Failcase mit der delete-Anweisung gearbeitet, um die Situation zu erreichen, dass im Failcase kein Auftrag vorliegt:
```<SO_AFU_Naturgefahren_20230802.Auftraege.Auftrag TID="701051de-6f2f-476d-81fb-43b8885ae7fc" delete="">```

* 12: Für diesen Typen liegen im aktuellen Testprojekt keine Daten vor. Es existiertdarum kein Failcase