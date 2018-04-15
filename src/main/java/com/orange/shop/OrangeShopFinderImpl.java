package com.orange.shop;

import java.util.List;

public class OrangeShopFinderImpl implements OrangeShopFinder {

    FileShopReader fileShopReader;

    public OrangeShopFinderImpl(FileShopReader fileShopReader) {
        this.fileShopReader = fileShopReader;
    }

    @Override
    public String findOrangeShopWithMobileAvailable(double longitude, double latitude, String mobileName) {
        List<Line> allLines = fileShopReader.setAllLine();

        return allLines.stream()
                .filter(line -> {
                    if (mobileName.equals("sunusng")) {
                        return line.getSunusng() > 0;
                    } else if (mobileName.equals("ipom")) {
                        return line.getIpom() > 0;
                    } else {
                        return line.getWeiwei() > 0;
                    }
                })
                .findFirst().orElse(new Line()).getShopDescription();
    }
}