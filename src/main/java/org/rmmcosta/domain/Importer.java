package org.rmmcosta.domain;

import java.io.File;
import java.io.IOException;

public interface Importer {
    Document importFile(final File file) throws IOException;
}
