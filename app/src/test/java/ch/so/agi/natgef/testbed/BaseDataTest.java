package ch.so.agi.natgef.testbed;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class BaseDataTest extends TestBase {
    @Test
    public void validateBaseData() {
        Path logFile = TestbedHelper.OUTPUT_PATH.resolve("NGK_SO_Testbeddata.log");

        assertTrue(TestbedHelper.validate(TestbedHelper.BASE_DATA_FILE, logFile), "Base data should be valid.");
    }
}
