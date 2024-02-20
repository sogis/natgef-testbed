# Testbed Constraints Datenmodell 'SO_AFU_Naturgefahren_20230802'

Mittels diesem Testbed kann das Modell 'SO_AFU_Naturgefahren_20230802' in bezug auf die spezifischen Constraints getestet werden.
Die 76 Constraints wurden dafür in 12 Typen-Kategorien eingeteilt und für jeden Kategorie liegt mindestens ein Failcase vor:

## Übersicht Constraint-Typen / Testbed-Abdeckung

|Constraint Typ|Repräsentant|Liste der Constraints|Failcase im Testbed|
|:----|:----|:----|:----|
|1: Mandatory.ObjectCount|MANDATORY CONSTRAINT INTERLIS.objectCount(THIS)==1;|CheckAuftragEintrag|Der Failcase beschreibt einen zweiten Auftrag.|
| | |CheckWasserkennwerte| |
|2: Unique|UNIQUE Kennung;|CheckAuftragKennungen|Der Failcase beschreibt einen zweiten Auftrag mit gleicher Kennung.|
| | |CheckTeilauftragEindeutigkeit| |
| | |CheckProzessquelleKennung| |
|3: Mandatory.OR|MANDATORY CONSTRAINT (Hauptprozess != #Wasser) OR Wasserkennwerte;|CheckTeilauftragWasserKennwert|Im Failcase liegt ein Teilauftrag vor mit Hauptprozess: #Wasser und Wasserkennwerte: false.|
|4: Mandatory.GetArea|MANDATORY CONSTRAINT GeoW_FunctionsExt.GetArea(THIS,"Geometrie") > 100;|CheckAbklperimeterFlaechenGroesse|Der Failcase enthält einen Abklärungsperimeter mit einer Fläche < 100m2.|
| | |CheckBefundFlaechenGroesse| |
|5: Mandatory.IsInsideExternal|MANDATORY CONSTRAINT GeoW_FunctionsExt.IsInsideExternalDataset("SO_Hoheitsgrenzen_Publikation_20170626.Hoheitsgrenzen.Kantonsgrenze.Geometrie", "c6db23e0-0536-4055-a50c-11d2d1e4c6ef" ,THIS, "Geometrie");|CheckAbklperimeterInnerhalbSO|Der Abklärungsperimeter im Failcase wurde aus dem Kantonsperimeter verschoben.|
|6: Mandatory.RingCount|MANDATORY CONSTRAINT GeoW_FunctionsExt.GetInnerRingsCount(THIS,  "Geometrie") == 0; |CheckAbklperimeterKeineLoecher|Der Failcase enthält einen Abklärungsperimeter mit einer Enklave.|
| | |CheckBefundKeineLoecher| |
|7: Mandatory.Equal|MANDATORY CONSTRAINT Prozessquelle_R->Teilauftrag_R->Hauptprozess == #Sturz;|CheckBefundSteinBlockschlag|Der Failcase enthält einen den referenzierten Teilauftrag mit modifiziertem Hauptprozess (#Wasser).|
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
|8: Mandatory.Defined|MANDATORY CONSTRAINT Prozessquelle_R->Teilauftrag_R->Hauptprozess == #Wasser AND DEFINED(Fliessrichtungspfeil_R);|CheckFliessrichtungspfeilZuHauptprozess|Der Failcase enthält den referenzierten Teilauftrag mit modifiziertem Hauptprozess (#Rutschung).|
| | |CheckProzessquelleBefundJaehrlichkeit| |
|9: Set.Ueberragung|SET CONSTRAINT GeoW_FunctionsExt.IsInsideAreaByCodeEnum(GeoW_FunctionsExt.GetInGroups(ALL, "PQ_Jaehrlichkeit_SteinBlockschlag_R->Prozessquelle_R"), "Geometrie" ,"IWCode");|CheckBefundSteinBlockschlagKeineUeberragung|Der Failcase enthält drei Befunde, welche zur gleichen Prozessquelle referenzieren, sich aber bzgl. aufsteigendem IWCode überragen (100>300) oder gemeinsame Kanten (30>100) haben.|
| | |CheckBefundBergFelssturzKeineUeberragung| |
| | |CheckBefundHangmureKeineUeberragung| |
| | |CheckBefundAbsenkungKeineUeberragung| |
| | |CheckBefundEinsturzKeineUeberragung| |
| | |CheckBefundSpontaneRutschungKeineUeberragung| |
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
|10: Mandatory.NOT-OR|MANDATORY CONSTRAINT NOT((IWCode == #rot_stark_30) OR (IWCode == #rot_mittel_30) OR (IWCode == #blau_schwach_30)) OR (PQ_Jaehrlichkeit_SteinBlockschlag_R->Jaehrlichkeit == 30);|CheckBefundSteinBlockschlagJaehrlichkeit-30|Der Failcase enthält das referenzierte Objekt aus der Klasse 'PQ_Jaehrlichkeit_SteinBlockschlag' mit einer widersprüchlichen Jährlichkeit.|
| | |CheckBefundSteinBlockschlagJaehrlichkeit-100| |
| | |CheckBefundSteinBlockschlagJaehrlichkeit-300| |
| | |CheckBefundHangmureJaehrlichkeit-30| |
| | |CheckBefundHangmureJaehrlichkeit-100| |
| | |CheckBefundHangmureJaehrlichkeit-300| |
| | |CheckBefundSpontaneRutschungJaehrlichkeit-30| |
| | |CheckBefundSpontaneRutschungJaehrlichkeit-100| |
| | |CheckBefundSpontaneRutschungJaehrlichkeit-300| |
| | |CheckAbklperimeterBeurteilungUeberschwemmung_statisch| |
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
|11: Mandatory.IsInside|MANDATORY CONSTRAINT (GeoW_FunctionsExt.IsInside(GeoW_FunctionsExt.Union(PQ_Jaehrlichkeit_SteinBlockschlag_R->Prozessquelle_R->Teilauftrag_R->Abklaerungsperimeter_R, "Geometrie"), THIS, "Geometrie"));|CheckBefundSteinBlockschlagImAbklPerimeter|Der Failcase enthält ein Befund-Objekt, welches den Abklärungsperimeter in einem Stützpunkt überragt.|
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
|12: Set.areAreas|SET CONSTRAINT INTERLIS.areAreas(GeoW_FunctionsExt.GetInGroups(ALL, "Prozessquelle_R"), UNDEFINED, >> Geometrie);|CheckBefundBefundJaehrlichkeitFlaechendeckung|Der Failcase beschreibt ein Objekt der Klasse BefundJaehrlichkeit, welches das Nachbarsobjekt mit gleicher Prozessquelle in einem Stützpunkt überragt.|
