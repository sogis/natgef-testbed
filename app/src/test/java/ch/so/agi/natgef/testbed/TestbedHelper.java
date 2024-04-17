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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class TestbedHelper {
    public static final Path BASE_PATH = Path.of("../TestSuite-1");
    public static final Path BASE_DATA_FILE = BASE_PATH.resolve("NGK_SO_Testbeddata.xtf");
    public static final Path MODEL_PATH = BASE_PATH.resolve("models");
    public static final Path OUTPUT_PATH = BASE_PATH.resolve("output");

    private static final Map<String, Class<?>> CUSTOM_FUNCTIONS;

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

    public static void assertMergeHasConstraintError(Path patchFile, String constraintName) {
        String filenameWithoutExtension = patchFile.getFileName().toString().replace(".xtf", "");
        Path outputFolder = TestbedHelper.OUTPUT_PATH.resolve(constraintName);
        Path mergedFile = outputFolder.resolve(filenameWithoutExtension + "-merged.xtf");
        Path logFile = outputFolder.resolve(filenameWithoutExtension + "-merged.log");

        assertMerge(patchFile, mergedFile);
        assertInvalidXtf(mergedFile, logFile);
        assertHasConstraintError(logFile, constraintName);
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
