package org.rmmcosta;

import org.rmmcosta.domain.*;
import org.rmmcosta.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentManagementSystem {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("PATH", "src/main/resources/image.jpg");
        Document document = new Document(map);
        System.out.println(document.getAttributeValue("PATH"));

        final Map<String, Importer> extensionToImporter = new HashMap<>();
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());
        extensionToImporter.put("png", new ImageImporter());

        List<Document> documentList = new ArrayList<>();
        String catFilePath = "src/main/resources/cat.jpg";
        try {
            documentList.add(extensionToImporter.get(FileUtils.getExtension(catFilePath)).importFile(new File(catFilePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String wrongCatFilePath = "src/main/resources/wrong_cat.png";
        try {
            documentList.add(extensionToImporter.get(FileUtils.getExtension(wrongCatFilePath)).importFile(new File(wrongCatFilePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Document eachDocument : documentList) {
            System.out.println(eachDocument);
        }
    }

    public static void main2(String[] args) throws IOException {
        FileUtils.printFileContentInChars(new File("src/main/resources/JoeBloggs26082023.letter"));
    }
}