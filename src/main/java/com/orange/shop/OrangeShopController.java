package com.orange.shop;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/shop")
public class OrangeShopController {

    @RequestMapping(method = RequestMethod.GET)
    public String findTheNearestShop(@RequestParam double longitude, @RequestParam double latitude, @RequestParam String mobile) {
        FileShopReader reader = new FileShopReader();
        OrangeShopFinder shop = new OrangeShopFinderImpl(reader);
        return shop.findOrangeShopWithMobileAvailable(longitude, latitude, mobile);
    }
}
