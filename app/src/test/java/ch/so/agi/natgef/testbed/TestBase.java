package ch.so.agi.natgef.testbed;

import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public abstract class TestBase {
    @BeforeAll
    public static void setup() throws IOException, URISyntaxException {
        //downloadIliModel("https://raw.githubusercontent.com/GeoWerkstatt/interlis-functions-ngk-so/main/src/model/NGK_SO_FunctionsExt_23.ili", "NGK_SO_FunctionsExt_23.ili");
        //downloadIliModel("https://raw.githubusercontent.com/GeoWerkstatt/geow-interlis-functions/master/src/model/GeoW_FunctionsExt_23.ili", "GeoW_FunctionsExt_23.ili");
    }

    private static void downloadIliModel(String url, String fileName) throws IOException, URISyntaxException {
        Files.createDirectories(TestbedHelper.MODEL_PATH);

        Path filePath = TestbedHelper.MODEL_PATH.resolve(fileName);
        if (!Files.exists(filePath)) {
            System.out.println("Downloading " + fileName + " from " + url);
            try (InputStream inputStream = new URI(url).toURL().openStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
