package org.rmmcosta.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private static String text;

    @BeforeAll
    static void setup() throws IOException {
        text = FileUtils.getFileContent(new File("src/test/resources/ParserSimpleText"));
    }

    @Test
    void shouldFindTheCorrectIndex() {
        assertEquals(0, Parser.findIndex(text, "1st", 0));
        assertEquals(6, Parser.findIndex(text, "o", 0));
        assertEquals(9, Parser.findIndex(text, "o", 1));
        assertEquals(10, Parser.findIndex(text, System.lineSeparator(), 0));
        assertEquals(12, Parser.findIndex(text, System.lineSeparator(), 1));
        assertEquals(14, Parser.findIndex(text, "Cenas", 0));
        assertEquals(39, Parser.findIndex(text, "re", 0));
        assertEquals(46, Parser.findIndex(text, "re", 1));
    }

    @Test
    void shouldParseBasedOnLabel() {
        assertEquals("Coiso", Parser.parseValueByLabel(text, "1st: ", System.lineSeparator()));
    }

    @Test
    void shouldParseBasedOnCharsPattern() {
        assertEquals("Cenas", Parser.parseValueByLabel(text, System.lineSeparator() + System.lineSeparator(), System.lineSeparator() + System.lineSeparator()));
    }

    @Test
    void shouldParseTextFile() throws IOException {
        File file = new File("src/test/resources/JoeBloggs26082023.letter");
        String fileContent = FileUtils.getFileContent(file);
        assertEquals("123 Fake Street", Parser.parseValueByLabel(fileContent, System.lineSeparator(), 1, System.lineSeparator()));
        String expectedAddress = "123 Fake Street" + System.lineSeparator() + "Westminster" + System.lineSeparator() + "London" + System.lineSeparator() + "United Kingdom";
        assertEquals(expectedAddress, Parser.parseValueByLabel(fileContent, System.lineSeparator(), 1, System.lineSeparator() + System.lineSeparator()));
    }

}