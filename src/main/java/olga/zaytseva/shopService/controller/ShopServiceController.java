package olga.zaytseva.shopService.controller;

import olga.zaytseva.shopService.DAO.BasketDAOSupport;
//import olga.zaytseva.shopService.DAO.OrderDAO;
import olga.zaytseva.shopService.DAO.ProductDAO;
import olga.zaytseva.shopService.dto.ProductDto;
import olga.zaytseva.shopService.model.Order;
import olga.zaytseva.shopService.model.Product;
import olga.zaytseva.shopService.business.ShopServiceBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class ShopServiceController {

//    @Autowired
//    private OrderDAO orderDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    BasketDAOSupport basketDAOSupport;

    @Autowired
    private ShopServiceBusiness shopService;

    @GetMapping
    public String getShopPage(Model model) {
//        model.addAttribute("list", orderDAO.getAll());
        return "ShopPage";
    }

    @GetMapping("/product")
    public String getProductPage(Model model) {
        model.addAttribute("list", productDAO.getAll());
        return "ProductPage";
    }

    @GetMapping("/save")
    public String saveOrder() {
        basketDAOSupport.save();
        return "redirect:/orders";
    }

    @PostMapping
    public String addOrder(@ModelAttribute("order") Order order) {
        System.out.println(order.getId());
//        orderDAO.createOrder(order);
        return "redirect:/orders";
    }

    @PostMapping("/product")
    public String addProduct(@ModelAttribute("product") ProductDto productDto) {
        Product product = null;
        try {
            product = productDto.getProduct();
            basketDAOSupport.addProduct(product);
            return "redirect:/orders/product";
        } catch (Exception e) {

            e.printStackTrace();
        }
        return "redirect:/orders/product";
    }

    @GetMapping("/{id}")
    public String editClientOrder(@PathVariable("id") long id, Model model,
                                  @ModelAttribute("order") Order order) {
//        model.addAttribute("shopOrder", orderDAO.getOrderId(id));
        return "ShopOrder";
    }

    @DeleteMapping("/{id}")
    public String deleteClientOrder(@PathVariable("id") int id) {
//        orderDAO.deleteOrder(id);
        return "redirect:/orders";
    }

    @PatchMapping("/{id}")
    public String updateClientOrder(@ModelAttribute("order") Order order, @PathVariable("id") long id) {
//        orderDAO.updateOrder(id, order);
        return "redirect:/orders";
    }

    @GetMapping("/new")
    public String newOrder() {
        basketDAOSupport = new BasketDAOSupport();
        return "redirect:/orders/product";
    }


}




