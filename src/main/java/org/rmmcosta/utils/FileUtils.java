package org.rmmcosta.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {
    public static String getExtension(String filePath) {
        if (!filePath.contains(".")) {
            return "";
        }
        return filePath.substring(filePath.lastIndexOf(".") + 1);
    }

    public static boolean validateExtension(String filePath, String expectedExtension) {
        return expectedExtension.equals(getExtension(filePath));
    }

    public static String getFileContent(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return new String(fileInputStream.readAllBytes());
    }

    public static void printFileContentInChars(File file) throws IOException {
        for (char c : getFileContent(file).toCharArray()) {
            System.out.printf("%c (%d)\n",c,(int)c);
        }
    }
}
