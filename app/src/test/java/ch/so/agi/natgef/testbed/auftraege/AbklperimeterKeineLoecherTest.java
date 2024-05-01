package ch.so.agi.natgef.testbed.auftraege;

import ch.so.agi.natgef.testbed.TestBase;
import ch.so.agi.natgef.testbed.TestbedHelper;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class AbklperimeterKeineLoecherTest extends TestBase {
    @Test
    public void checkConstraint() {
        TestbedHelper.assertMergeHasConstraintError("CheckAbklperimeterKeineLoecher");
    }
}
