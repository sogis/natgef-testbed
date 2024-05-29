package ch.so.agi.natgef.testbed;

import ch.ehi.basics.settings.Settings;
import ch.geowerkstatt.ilivalidator.extensions.functions.*;
import ch.geowerkstatt.ilivalidator.extensions.functions.ngk.IsInsideAreaByCodeIoxPlugin;
import ch.geowerkstatt.interlis.testbed.runner.validation.IliValidatorLogParser;
import ch.geowerkstatt.interlis.testbed.runner.validation.ValidatorException;
import ch.geowerkstatt.interlis.testbed.runner.xtf.XtfFileMerger;
import ch.interlis.iox_j.validator.InterlisFunction;
import ch.interlis.iox_j.validator.ValidationConfig;
import org.interlis2.validator.Validator;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class TestbedHelper {
    public static final Path BASE_PATH = Path.of("../TestSuite-1");
    public static final Path BASE_DATA_FILE = BASE_PATH.resolve("AllPassBaseData.xtf");
    public static final Path MODEL_PATH = BASE_PATH.resolve("models");
    public static final Path OUTPUT_PATH = BASE_PATH.resolve("output");
    public static final String FAILCASE_NAME_DEFAULT = "Failcase-1.xtf";

    private static final Map<String, Class<?>> CUSTOM_FUNCTIONS;
    private static final Map<String, String> CONSTRAINT_PREFIXES;

    static {
        List<InterlisFunction> pluginFunctions = List.of(
                new GetAreaIoxPlugin(),
                new GetInGroupsIoxPlugin(),
                new GetInnerRingsCountIoxPlugin(),
                new IsInsideExternalXtfResourceIoxPlugin(),
                new IsInsideIoxPlugin(),
                new UnionIoxPlugin(),
                new IsInsideAreaByCodeIoxPlugin()
        );
        CUSTOM_FUNCTIONS = pluginFunctions.stream()
                .collect(Collectors.toMap(InterlisFunction::getQualifiedIliName, InterlisFunction::getClass));

        Map<String, String> prefixes = new HashMap<>();
        prefixes.put("CheckAbklperimeterFlaechenGroesse", "SO_AFU_Naturgefahren_20240515.Auftraege.Abklaerungsperimeter.");
        prefixes.put("CheckAbklperimeterInnerhalbSO", "SO_AFU_Naturgefahren_20240515.Auftraege.Abklaerungsperimeter.");
        prefixes.put("CheckAbklperimeterKeineLoecher", "SO_AFU_Naturgefahren_20240515.Auftraege.Abklaerungsperimeter.");
        prefixes.put("CheckAuftragVorhanden", "SO_AFU_Naturgefahren_20240515.Auftraege.Auftrag.");
        prefixes.put("CheckAuftragKennungen", "SO_AFU_Naturgefahren_20240515.Auftraege.Auftrag.");
        prefixes.put("CheckBefundSpontaneRutschungKeineUeberragung","SO_AFU_Naturgefahren_20240515.Befunde.BefundSpontaneRutschung.");
        prefixes.put("CheckBefundSteinBlockschlagImAbklPerimeter", "SO_AFU_Naturgefahren_20240515.Befunde.BefundSteinBlockschlag.");
        prefixes.put("CheckBefundSteinBlockschlagJaehrlichkeit-30", "SO_AFU_Naturgefahren_20240515.Befunde.BefundSteinBlockschlag.");
        prefixes.put("CheckBefundSteinBlockschlagKeineUeberlappung-300", "SO_AFU_Naturgefahren_20240515.Befunde.BefundSteinBlockschlag.");
        prefixes.put("CheckBefundSteinBlockschlagKeineUeberragung", "SO_AFU_Naturgefahren_20240515.Befunde.BefundSteinBlockschlag.");
        prefixes.put("CheckBefundSteinBlockschlag", "SO_AFU_Naturgefahren_20240515.Befunde.PQ_Jaehrlichkeit_SteinBlockschlag__Prozessquelle.");
        prefixes.put("CheckProzessquelleKennung", "SO_AFU_Naturgefahren_20240515.Befunde.Prozessquelle.");
        prefixes.put("CheckTeilauftragWasserKennwert", "SO_AFU_Naturgefahren_20240515.Befunde.Prozessquelle.");
        prefixes.put("CheckFliessrichtungspfeilZuHauptprozess", "SO_AFU_Naturgefahren_20240515.Befunde.Prozessquelle__Fliessrichtungspfeil.");

        CONSTRAINT_PREFIXES = prefixes;
    }

    public static boolean validate(Path xtfFile, Path logFile) {
        logFile = logFile.toAbsolutePath().normalize();
        createParentDirectories(logFile);

        String testbedDir = BASE_PATH.toAbsolutePath().normalize().toString();
        String testbedModelDir = MODEL_PATH.toAbsolutePath().normalize().toString();

        Settings settings = new Settings();
        settings.setValue(Validator.SETTING_ILIDIRS, testbedDir + ";" + testbedModelDir + ";http://models.interlis.ch/");
        settings.setValue(Validator.SETTING_LOGFILE, logFile.toString());
        settings.setTransientValue(ch.interlis.iox_j.validator.Validator.CONFIG_VERBOSE, ValidationConfig.TRUE);
        settings.setTransientObject(ch.interlis.iox_j.validator.Validator.CONFIG_CUSTOM_FUNCTIONS, CUSTOM_FUNCTIONS);

        return Validator.runValidation(xtfFile.toAbsolutePath().normalize().toString(), settings);
    }

    public static void assertMergeHasConstraintError(String constraintName){
        assertMergeHasConstraintError(constraintName, TestbedHelper.FAILCASE_NAME_DEFAULT);
    }

    public static void assertMergeHasConstraintError(String constraintName, String failCaseFileName) {

        String prefix = CONSTRAINT_PREFIXES.get(constraintName);
        if(prefix == null)
            throw new RuntimeException("Qualified name prefix not defined for Constraint: " + constraintName);

        String qualConstraintName = prefix + constraintName;
        Path patchFilePath = TestbedHelper.BASE_PATH.resolve(constraintName).resolve(failCaseFileName);
        String filenameWithoutExtension = patchFilePath.getFileName().toString().replace(".xtf", "");
        Path outputFolder = TestbedHelper.OUTPUT_PATH.resolve(constraintName);
        Path mergedFile = outputFolder.resolve(filenameWithoutExtension + "-merged.xtf");
        Path logFile = outputFolder.resolve(filenameWithoutExtension + "-merged.log");

        assertMerge(patchFilePath, mergedFile);
        assertInvalidXtf(mergedFile, logFile);
        assertHasConstraintError(logFile, qualConstraintName);
    }

    private static void assertMerge(Path patchFile, Path mergedFile) {
        XtfFileMerger merger = new XtfFileMerger();
        assertTrue(merger.merge(BASE_DATA_FILE, patchFile, mergedFile), "XTF Merge should be successful.");
    }

    private static void assertInvalidXtf(Path xtfFile, Path logFile) {
        boolean isValid = validate(xtfFile, logFile);
        assertFalse(isValid, "Merged XTF should be invalid.");
    }

    private static void assertHasConstraintError(Path logFile, String constraintName) {
        try {
            boolean hasConstraintError = IliValidatorLogParser.containsConstraintError(logFile, constraintName);
            assertTrue(hasConstraintError, "Log file should contain constraint error.");
        } catch (ValidatorException e) {
            Assertions.fail(e);
        }
    }

    private static void createParentDirectories(Path path) {
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
