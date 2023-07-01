package org.rmmcosta.domain;

import org.rmmcosta.utils.FileUtils;
import org.rmmcosta.utils.Parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReportImporter implements Importer {
    @Override
    public Document importFile(final File file) throws IOException {
        Map<String, String> attributes = new HashMap<>();
        attributes.put(Attributes.EXTENSION, FileUtils.getExtension(file.getPath()));
        String fileContent = FileUtils.getFileContent(file);
        attributes.put(Attributes.PATIENT, parseReportPatient(fileContent));
        attributes.put(Attributes.BODY, parseReportBody(fileContent));
        return new Document(attributes);
    }

    private String parseReportPatient(String text) {
        return Parser.parseValueByLabel(text, "Patient:", System.lineSeparator());
    }

    private String parseReportBody(String text) {
        return Parser.parseValueByLabel(text, System.lineSeparator() + System.lineSeparator(), "");
    }
}
