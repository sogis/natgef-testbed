package ch.so.agi.natgef.testbed;

import ch.ehi.basics.settings.Settings;
import ch.geowerkstatt.ilivalidator.extensions.functions.*;
import ch.geowerkstatt.ilivalidator.extensions.functions.ngk.IsInsideAreaByCodeIoxPlugin;
import ch.interlis.iox_j.validator.InterlisFunction;
import ch.interlis.iox_j.validator.ValidationConfig;
import org.interlis2.validator.Validator;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String testbedDir = BASE_PATH.toAbsolutePath().normalize().toString();
        String testbedModelDir = MODEL_PATH.toAbsolutePath().normalize().toString();

        Settings settings = new Settings();
        settings.setValue(Validator.SETTING_ILIDIRS, testbedDir + ";" + testbedModelDir + ";http://models.interlis.ch/");
        settings.setValue(Validator.SETTING_LOGFILE, logFile.toAbsolutePath().normalize().toString());
        settings.setTransientValue(ch.interlis.iox_j.validator.Validator.CONFIG_VERBOSE, ValidationConfig.TRUE);
        settings.setTransientObject(ch.interlis.iox_j.validator.Validator.CONFIG_CUSTOM_FUNCTIONS, CUSTOM_FUNCTIONS);

        return Validator.runValidation(xtfFile.toAbsolutePath().normalize().toString(), settings);
    }
}
