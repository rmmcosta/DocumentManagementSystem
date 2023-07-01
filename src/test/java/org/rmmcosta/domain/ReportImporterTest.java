package org.rmmcosta.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.rmmcosta.utils.FileUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReportImporterTest {

    private static Document report;

    @BeforeAll
    static void setup() throws IOException {
        ReportImporter reportImporter = new ReportImporter();
        report = reportImporter.importFile(new File("src/test/resources/JoeBloggs05012017.report"));
    }

    @Test
    void shouldParseTheCorrectReportPatient() {
        assertEquals("Joe Bloggs", report.getAttributeValue("PATIENT"));
    }

    @Test
    void shouldParseTheCorrectReportBody() throws IOException {
        String expectedBody = FileUtils.getFileContent(new File("src/test/resources/ExpectedReportBody"));
        assertEquals(expectedBody, report.getAttributeValue("BODY"));
    }

}