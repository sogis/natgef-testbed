package ch.so.agi.natgef.testbed.befunde;

import ch.so.agi.natgef.testbed.TestBase;
import ch.so.agi.natgef.testbed.TestbedHelper;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class BefundSteinBlockschlagJaehrlichkeit30Test extends TestBase {
    @Test
    public void checkConstraint() {
        String constraintName = "SO_AFU_Naturgefahren_20230802.Befunde.BefundSteinBlockschlag.CheckBefundSteinBlockschlagJaehrlichkeit-30";
        Path patchFile = TestbedHelper.BASE_PATH.resolve(constraintName).resolve("Failcase-1.xtf");

        TestbedHelper.assertMergeHasConstraintError(patchFile, constraintName);
    }
}
