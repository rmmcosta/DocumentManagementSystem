package org.rmmcosta.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceImporterTest {
    private final static String INVOICE = "src/test/resources/JoeBloggs100.invoice";
    private final static String EXPECTED_PATIENT = "Joe Bloggs";
    private final static Double EXPECTED_AMOUNT = 100.0;

    private static Document invoice;

    @BeforeAll
    static void setup() throws IOException {
        final InvoiceImporter invoiceImporter = new InvoiceImporter();
        invoice = invoiceImporter.importFile(new File(INVOICE));
    }

    @Test
    void shouldParseTheCorrectInvoicePatient(){
        assertEquals(EXPECTED_PATIENT, invoice.getAttributeValue("PATIENT"));
    }

    @Test
    void shouldParseTheCorrectInvoiceAmount() {
        assertEquals(EXPECTED_AMOUNT, Double.valueOf(invoice.getAttributeValue("AMOUNT")));
    }
}