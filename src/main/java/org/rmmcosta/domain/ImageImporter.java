package org.rmmcosta.domain;

import org.rmmcosta.utils.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageImporter implements Importer {
    @Override
    public Document importFile(final File file) throws IOException {
        final Map<String, String> attributes = new HashMap<>();
        if (!(FileUtils.validateExtension(file.getPath(), "jpg") || FileUtils.validateExtension(file.getPath(), "png"))) {
            throw new UnknownFileTypeException("No extension found for file:" + file.getPath());
        }
        attributes.put("TYPE", String.valueOf(SupportedTypes.IMAGE));
        String extension = FileUtils.getExtension(file.getPath());
        attributes.put("EXTENSION", extension);
        final BufferedImage image = ImageIO.read(file);
        attributes.put("WIDTH", String.valueOf(image.getWidth()));
        attributes.put("HEIGHT", String.valueOf(image.getHeight()));
        return new Document(attributes);
    }
}
