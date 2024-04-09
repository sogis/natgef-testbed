# Im Testbett durch AGI nach Auftragsabschluss nachzutragende ILI-Codeänderungen (to lower case)

## Hauptprozess

Von

      Hauptprozess = (
        Wasser,
        Rutschung,
        Sturz,
        Absenkung_Einsturz
      );

Nach

      Hauptprozess = (
        wasser,
        rutschung,
        sturz,
        absenkung_einsturz
      );

## Deklaration

Von

      Deklaration = (
        Nachfuehrung,
        Beurteilung_nach_Massnahmen,
        Neubeurteilung
      ) ORDERED;

Nach

      Deklaration = (
        nachfuehrung,
        beurteilung_nach_massnahmen,
        neubeurteilung
      ) ORDERED;

## Teilprozess

Von

      Teilprozess = (
        Ueberschwemmung_statisch,
        Ueberschwemmung_dynamisch,
        Uebermurung,
        Ufererosion,
        spontane_Rutschung,
        Hangmure,
        permanente_Rutschung,
        Stein_Blockschlag,
        Fels_Bergsturz,     
        Einsturz,
        Absenkung        
      ) ORDERED;

Nach

      Teilprozess = (
        ueberschwemmung_statisch,
        ueberschwemmung_dynamisch,
        uebermurung,
        ufererosion,
        spontane_rutschung,
        hangmure,
        permanente_rutschung,
        stein_blockschlag,
        fels_bergsturz,     
        einsturz,
        absenkung        
      ) ORDERED;

## rKorrektur

Von

      rKorrektur = (
        keine,
        Entschaerfung1,
        Verschaerfung1,
        Verschaerfung2
      ) ORDERED;

Nach

      rKorrektur = (
        keine,
        entschaerfung1,
        verschaerfung1,
        verschaerfung2
      ) ORDERED;