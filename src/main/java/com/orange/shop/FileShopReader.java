package com.orange.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileShopReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileShopReader.class);

    private String nameFile;

    public void setNameFile(String nameFile) {
        this.nameFile = "orange_shop.csv";
        if (nameFile != null) {
            this.nameFile = nameFile;
        }
    }

    public List<Line> setAllLine() {
        URI uri = getFile();
        List<String> allLines = readShopFile(uri);

        removeHeaderLine(allLines);

        return createListShops(allLines);

    }

    private List<String> readShopFile(URI uri) {

        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(uri));
        } catch (IOException e) {
            LOGGER.error("Erreur lors de la lecture du fichier {} ", nameFile, e);
        }

        return lines;
    }

    private URI getFile() {
        URI uri = null;
        URL url = ClassLoader.getSystemResource(nameFile);
        try {
            uri = url.toURI();
        } catch (URISyntaxException | NullPointerException e) {
            throw new RuntimeException("Erreur lors de la récupération du fichier", e);
        }
        return uri;
    }

    private void removeHeaderLine(List<String> allLines) {
        allLines.remove(0);
    }

    private List<Line> createListShops(List<String> allLines) {
        return allLines.stream().map(line -> line.split(";")) //
                .map(line -> new Line(line[0], line[1], line[2], line[3], line[4], line[5])) //
                .collect(Collectors.toList());
    }
}
