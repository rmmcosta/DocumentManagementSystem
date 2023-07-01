package org.rmmcosta.domain;

import org.rmmcosta.utils.FileUtils;
import org.rmmcosta.utils.Parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LetterImporter implements Importer {

    @Override
    public Document importFile(final File file) throws IOException {
        Map<String, String> attributes = new HashMap<>();

        attributes.put(Attributes.EXTENSION, parseLetterExtension(file));

        String fileContent = FileUtils.getFileContent(file);
        attributes.put(Attributes.PATIENT, parseLetterPatient(fileContent));
        attributes.put(Attributes.ADDRESS, parseLetterAddress(fileContent));
        attributes.put(Attributes.BODY, parseLetterBody(fileContent));

        return new Document(attributes);
    }

    private String parseLetterExtension(File file) {
        return FileUtils.getExtension(file.getPath());
    }

    private String parseLetterPatient(String text) {
        return Parser.parseValueByLabel(text, "Dear", System.lineSeparator());
    }

    private String parseLetterAddress(String text) {
        return Parser.parseValueByLabel(text, System.lineSeparator()+System.lineSeparator(),0, System.lineSeparator()+System.lineSeparator());
    }

    private String parseLetterBody(String text) {
        return Parser.parseValueByLabel(text, System.lineSeparator()+System.lineSeparator(), 1, "regards,");
    }
}
