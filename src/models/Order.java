package models;

import java.util.LinkedList;

public class Order {
    private int id;
    private int customerId;
    private LinkedList<OrderItem> items=new LinkedList<>();
    private boolean canceled=false;

    public Order(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LinkedList<OrderItem> getItems() {
        return items;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void cancel() {
        this.canceled = true;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem oi : items) total += oi.totalPrice();
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Order ID: %d | Customer ID: %d | Canceled: %b\n", id, customerId, canceled));
        for (OrderItem oi : items) sb.append("  - ").append(oi.toString()).append("\n");
        sb.append(String.format("Total: Rs %.2f", calculateTotal()));
        return sb.toString();
    }
}
