package org.rmmcosta.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageImporterTest {
    private final static String JPG_IMAGE = "src/test/resources/cat.jpg";
    private final static String JPG_EXPECTED_WIDTH = "600";
    private final static String JPG_EXPECTED_HEIGHT = "400";
    private final static String PNG_IMAGE = "src/test/resources/wrong_cat.png";
    private final static String PNG_EXPECTED_WIDTH = "920";
    private final static String PNG_EXPECTED_HEIGHT = "469";

    private static Document jpgImage;
    private static Document pngImage;

    @BeforeAll
    static void setup() throws IOException {
        ImageImporter imageImporter = new ImageImporter();
        jpgImage = imageImporter.importFile(new File(JPG_IMAGE));
        pngImage = imageImporter.importFile(new File(PNG_IMAGE));
    }

    @Test
    void shouldParseTheCorrectJPGExtension() {
        assertEquals("jpg", jpgImage.getAttributeValue("EXTENSION"));
    }

    @Test
    void shouldParseTheCorrectPNGExtension() {
        assertEquals("png",pngImage.getAttributeValue("EXTENSION"));
    }

    @Test
    void shouldParseTheCorrectJPGSize() {
        assertEquals(JPG_EXPECTED_WIDTH, jpgImage.getAttributeValue("WIDTH"));
        assertEquals(JPG_EXPECTED_HEIGHT, jpgImage.getAttributeValue("HEIGHT"));
    }

    @Test
    void shouldParseTheCorrectPNGSize() {
        assertEquals(PNG_EXPECTED_WIDTH, pngImage.getAttributeValue("WIDTH"));
        assertEquals(PNG_EXPECTED_HEIGHT, pngImage.getAttributeValue("HEIGHT"));
    }

}