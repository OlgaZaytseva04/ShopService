package olga.zaytseva.shopService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShopServiceController {

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getShopPage(){
        return "ShopPage";
    }

    @RequestMapping(value = "/orders/product", method = RequestMethod.GET)
    public String getClientPage(){
        return "redirect://localhost:8083/orders";
    }
}
