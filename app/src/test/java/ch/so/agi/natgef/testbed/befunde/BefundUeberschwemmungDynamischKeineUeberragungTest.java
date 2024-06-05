package ch.so.agi.natgef.testbed.befunde;

import ch.so.agi.natgef.testbed.TestBase;
import ch.so.agi.natgef.testbed.TestbedHelper;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class BefundUeberschwemmungDynamischKeineUeberragungTest extends TestBase {
    @Test
    public void checkConstraint() {
        TestbedHelper.assertMergeHasConstraintError("CheckBefundUeberschwemmungDynamischKeineUeberragung");
    }
}
