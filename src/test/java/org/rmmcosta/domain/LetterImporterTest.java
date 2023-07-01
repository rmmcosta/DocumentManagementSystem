package org.rmmcosta.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.rmmcosta.utils.FileUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LetterImporterTest {
    private static final String LETTER = "src/test/resources/JoeBloggs26082023.letter";
    private static final String EXPECTED_LETTER_PATIENT = "Joe Bloggs";
    private static Document letter;

    @BeforeAll
    static void setup() throws IOException {
        LetterImporter letterImporter = new LetterImporter();
        letter = letterImporter.importFile(new File(LETTER));
    }

    @Test
    void shouldParseTheCorrectLetterExtension() {
        assertEquals("letter", letter.getAttributeValue("EXTENSION"));
    }

    @Test
    void shouldParseTheCorrectLetterPatient() {
        assertEquals(EXPECTED_LETTER_PATIENT, letter.getAttributeValue("PATIENT"));
    }

    @Test
    void shouldParseTheCorrectLetterAddress() throws IOException {
        String expectedLetterAddress = FileUtils.getFileContent(new File("src/test/resources/ExpectedLetterAddress"));
        assertEquals(expectedLetterAddress, letter.getAttributeValue("ADDRESS"));
    }

    @Test
    void shouldParseTheCorrectLetterBody() throws IOException {
        String expectedLetterBody = FileUtils.getFileContent(new File("src/test/resources/ExpectedLetterBody"));
        assertEquals(expectedLetterBody, letter.getAttributeValue("BODY"));
    }

}