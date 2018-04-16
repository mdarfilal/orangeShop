package com.orange.shop;

/**
 * Recherche de boutiques Orange.
 * <p>
 * Le point d'entrée doit implémenter cette interface.
 */
public interface OrangeShopFinder {

    String findOrangeShopWithMobileAvailable(double longitude, double latitude, String mobileName);

    double findDistanceBetweenPoints(double firstLongitude, double firstLatitude, double secondLongitude, double secondLatitude);

}
