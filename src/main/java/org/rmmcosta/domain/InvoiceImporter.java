package org.rmmcosta.domain;

import org.rmmcosta.utils.FileUtils;
import org.rmmcosta.utils.Parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InvoiceImporter implements Importer {
    private static final String AMOUNT_LABEL = "Amount:";
    private static final String PATIENT_LABEL = "Dear";
    private static final String NEW_LINE = "\r\n";
    private static final char MONEY_SYMBOL = '$';

    @Override
    public Document importFile(File file) throws IOException {
        if (!(FileUtils.validateExtension(file.getPath(), "invoice"))) {
            throw new UnknownFileTypeException("No extension found for file:" + file.getPath());
        }

        final Map<String, String> invoiceAttributes = new HashMap<>();
        invoiceAttributes.put(Attributes.TYPE, String.valueOf(SupportedTypes.INVOICE));
        String extension = FileUtils.getExtension(file.getPath());
        invoiceAttributes.put(Attributes.EXTENSION, extension);

        String fileContent = FileUtils.getFileContent(file);

        invoiceAttributes.put(Attributes.AMOUNT, parseInvoiceAmount(fileContent));
        invoiceAttributes.put(Attributes.PATIENT, parseInvoicePatient(fileContent));
        return new Document(invoiceAttributes);
    }

    private String parseInvoiceAmount(String text) {
        return Parser.parseValueByLabel(text, AMOUNT_LABEL, Optional.of(NEW_LINE))
                .replace(MONEY_SYMBOL, ' ')
                .trim();
    }

    private String parseInvoicePatient(String text) {
        return Parser.parseValueByLabel(text, PATIENT_LABEL, Optional.of(NEW_LINE));
    }
}
