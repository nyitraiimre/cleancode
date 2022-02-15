package hu.xxx.datamunging.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class FileReader {

    public List<String> readFileLines(String inputfileFullName) {
        List<String> lines = null;
        File inputFile = getFileFromResource(inputfileFullName);
        try {
            lines = Files.readAllLines(inputFile.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Reading file failed!(fileName=" + inputfileFullName + ")", e);
        }
        lines.forEach(System.out::println);
        return lines;
    }

    private File getFileFromResource(String inputfileFullName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(inputfileFullName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found!(fileName=" + inputfileFullName + ")");
        }
        File file = null;
        try {
            file = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("The URL format is wrong!(fileName=" + inputfileFullName + ")" , e);
        }
        return file;
    }

}