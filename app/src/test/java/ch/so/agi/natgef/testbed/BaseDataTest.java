package ch.so.agi.natgef.testbed;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class BaseDataTest extends TestBase {
    @Test
    public void validateBaseDataFile() {
        Path logFile = deriveLogLocation(TestbedHelper.OUTPUT_PATH, TestbedHelper.BASE_DATA_FILE);
        assertTrue(TestbedHelper.validate(TestbedHelper.BASE_DATA_FILE, logFile), "Base data must be valid.");
    }

    @Test
    public void validateAllOtherDataFiles() {
        for(Path dataFile : TestbedHelper.DATA_FILES){
            Path logFile = deriveLogLocation(TestbedHelper.OUTPUT_PATH, dataFile);
            assertTrue(TestbedHelper.validate(dataFile, logFile), "Invalid data file: " + dataFile);
        }
    }

    private static Path deriveLogLocation(Path outputPath, Path xtfPath){
        String logFileName = xtfPath.getFileName().toString().replace(".xtf",".log");
        return outputPath.resolve(logFileName);
    }
}
