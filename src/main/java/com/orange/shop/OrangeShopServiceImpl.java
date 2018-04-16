package com.orange.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrangeShopServiceImpl implements OrangeShopService {

    @Autowired
    OrangeShopFinder shop;

    @Override
    public String findOrangeShopWithMobileAvailable(double longitude, double latitude, String mobile) {
        String shopDescription =  shop.findOrangeShopWithMobileAvailable(longitude, latitude, mobile);

        if (shopDescription == null) {
            return "Désolé, votre téléphone n'est disponible dans aucun magasin";
        }
        return shopDescription;
    }
}
