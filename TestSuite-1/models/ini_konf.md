# Validator Konfigurationen mittels INI-Dateien

Das Verzeichnis models/ dient als temporäre "Blaupause" für die Konfiguration der Modellvalidierung 
in GRETL und im ilivalidator-web-service.

models/ ist in .gitignore ausgeschlossen. Falls weitere Anpassungen in models/ geadded werden sollen:

    git add -f *

## INI-Dateien

* \*gretl\*.ini für die Validator-Konfiguration in GRETL (conf und metaConf).
* *web-meta.ini für die Validator-Konfiguration im ilivalidator-web-service.

## Ausführen mittels Shell-Skript

Zur Ausführung den Ordner jek/ kopieren und in der Kopie die Shell-Skripte auf den "eigenen" Ablageort des ilivalidators anpassen.

* web_validate.sh simuliert die Validierung im ilivalidator-web-service (nur metaConfig notwendig)
* gretl_validate.sh simuliert die Validierung in GRETL (metaConfig und config notwendig)

## Modelle

Die enthaltenen Modelle SO_AFU_Naturgefahren_* sind **absichtlich noch nicht** im Model-Repo eingecheckt.

## Jars für Funktionen und Daten

Für die Shell-Skripte sind die Jars nicht konfiguriert. Das Funktionieren von SO_AFU_Naturgefahren_20240515.ili mit den Jars ist über das Testbett abgedeckt.

Siehe dazu die referenzierten jars im [build.gradle](../../app/build.gradle)



