package olga.zaytseva.shopService.dto;

import olga.zaytseva.shopService.model.Order;

import java.util.ArrayList;

public class OrderListDto {

    private ArrayList<Order> list;

    public OrderListDto() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Order> getList() {
        return list;
    }

    public void setList(ArrayList<Order> list) {
        this.list = list;
    }
}
