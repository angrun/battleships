package test;

import test.order.Order;

import java.util.HashMap;
import java.util.Map;

public class Bank {


    public static Long id = 0L;
    public static Map<Long, Order> orderHash = new HashMap<>();
    Order order;

    public void bankAdd(Order order) {
        id++;
        order.setId(id);
        orderHash.put(id, order);
    }

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        Bank.id = id;
    }

    public static Map<Long, Order> getOrderHash() {
        return orderHash;
    }

    public static void setOrderHash(Map<Long, Order> orderHash) {
        Bank.orderHash = orderHash;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "order=" + order +
                '}';
    }
}
