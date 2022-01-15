package olga.zaytseva.shopService.controller;

import olga.zaytseva.shopService.dto.OrderListDto;
import olga.zaytseva.shopService.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class OrderController {

    @GetMapping("/shop/orders")
    public String returnOrdersPage(Model model) {
        List<Order> orders = getOrdersFromClientServiceApp();
        model.addAttribute("orderList", orders);
        return "ordersPage";
    }

    private List<Order> getOrdersFromClientServiceApp() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8083/orders/list/1";

        try {
            Order order = restTemplate.getForObject(fooResourceUrl, Order.class);
            System.out.println();
        } catch (RestClientException e) {
            System.out.println();

        }

        String fooResourceUrl2 = "http://localhost:8083/orders/list";
        try {
            OrderListDto orderListDto = restTemplate.getForObject(fooResourceUrl2, OrderListDto.class);
            System.out.println();
        } catch (RestClientException e) {
            System.out.println();

        }

        try {
            ResponseEntity<Order[]> response = restTemplate.getForEntity(fooResourceUrl2, Order[].class);
            Order[] employees = response.getBody();
            return Arrays.asList(employees);
        } catch (RestClientException e) {
            e.printStackTrace();
        }


        return Stream.of(
                new Order(1, 15, 300, new Date(), "Processing"),
                new Order(2, 1543, 3900, new Date(), "Processing"),
                new Order(3, 555, 5300, new Date(), "Completed"),
                new Order(4, 25, 8300, new Date(), "Processing")
        ).collect(Collectors.toList());

    }
}
