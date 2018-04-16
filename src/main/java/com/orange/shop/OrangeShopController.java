package com.orange.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/shop")
public class OrangeShopController {

    @Autowired
    OrangeShopService shopService;

    @RequestMapping(method = RequestMethod.GET)
    public String findTheNearestShop(@RequestParam double longitude, @RequestParam double latitude, @RequestParam String mobile) {
        return shopService.findOrangeShopWithMobileAvailable(longitude, latitude, mobile);
    }
}
