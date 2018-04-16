package com.orange.shop.service.impl;

import com.orange.shop.model.Line;
import com.orange.shop.service.FileShopReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileShopReaderImpl implements FileShopReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileShopReaderImpl.class);

    private String nameFile = "orange_shop.csv";

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    @Override
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
        return Paths.get("src/main/resources/" + nameFile).toUri();
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
