package com.orange.shop;

import java.util.List;
import java.util.stream.Collectors;

public class OrangeShopFinderImpl implements OrangeShopFinder {

    FileShopReader fileShopReader;

    public OrangeShopFinderImpl(FileShopReader fileShopReader) {
        this.fileShopReader = fileShopReader;
    }

    @Override
    public String findOrangeShopWithMobileAvailable(double longitude, double latitude, String mobileName) {
        List<Line> allLines = fileShopReader.setAllLine();

        List<Line> availableShop = allLines.stream()
                .filter(line -> {
                    if (mobileName.equals("sunusng")) {
                        return line.getSunusng() > 0;
                    } else if (mobileName.equals("ipom")) {
                        return line.getIpom() > 0;
                    } else {
                        return line.getWeiwei() > 0;
                    }
                }).collect(Collectors.toList());


        availableShop.forEach(line -> line.setDistance(findDistanceBetweenPoints(longitude, latitude, line.getLongitude(), line.getLatitude())));

        return availableShop.stream()
                .reduce((l1, l2) -> {
                    if (l1.getDistance() < l2.getDistance()) {
                        return l1;
                    } else {
                        return l2;
                    }
                }).orElse(new Line()).getShopDescription();
    }
    
    @Override
    public double findDistanceBetweenPoints(double firstLongitude, double firstLatitude, double secondLongitude,
                                            double secondLatitude) {

        // Haversine : https://en.wikipedia.org/wiki/Haversine_formula
        // d = 2r arcsin (x)
        // r = Earth radius : 6 371 000m
        // x = Sqrt(y + z)
        // y = sin2(lat2 - lat1 / 2)
        // z = cos(lat1)*cos(lat2)sin2(long2 - long1 / 2)

        double z = Math.cos(Math.toRadians(firstLatitude)) * Math.cos(Math.toRadians(secondLatitude)) *
                Math.pow(Math.sin((secondLongitude - firstLongitude) / 2), 2);

        double y = Math.pow(Math.sin((secondLatitude - firstLatitude) / 2), 2);

        double x = Math.sqrt(y + z);

        int r = 6_371_000;

        return 2 * r * Math.asin(Math.toRadians(x));
    }

}