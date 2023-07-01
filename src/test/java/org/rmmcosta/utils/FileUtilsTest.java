package org.rmmcosta.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {
    private static final String NEW_LINE = "\r\n";

    @Test
    void validatesPngAsValidForPngExtension() {
        assertTrue(FileUtils.validateExtension("src/main/resources/cat.png", "png"));
    }

    @Test
    void validatesPngAsInvalidForJpgExtension() {
        assertFalse(FileUtils.validateExtension("src/main/resources/cat.jpg", "png"));
    }

    @Test
    void validatesPngAsInvalidForNoExtension() {
        assertFalse(FileUtils.validateExtension("src/main/resources/cat", "png"));
    }

    @Test
    void shouldReadATextFile() throws IOException {
        final String expectedFileContent = String.format("xpto e cenas%se coiso e tal.%sBy Ricardo Costa", NEW_LINE, NEW_LINE);
        assertEquals(expectedFileContent, FileUtils.getFileContent(new File("src/test/resources/SimpleTextFile")));
    }
}