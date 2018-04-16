package com.orange.shop.controller;

import com.orange.shop.service.OrangeShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/shop")
public class OrangeShopController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrangeShopController.class);

    @Autowired
    OrangeShopService shopService;

    @RequestMapping(method = RequestMethod.GET)
    public String findTheNearestShop(@RequestParam double longitude, @RequestParam double latitude, @RequestParam String mobile) {
        LOGGER.info("Demande d'information au sujet de : longitude {} - latitude {} - mobile {}", longitude, latitude, mobile);
        return shopService.findOrangeShopWithMobileAvailable(longitude, latitude, mobile);
    }
}
