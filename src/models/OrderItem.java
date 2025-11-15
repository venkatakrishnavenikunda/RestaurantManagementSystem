package models;

public class OrderItem {
    private int menuItemId;
    private String name;
    private double price;
    private int quantity;

    public OrderItem(int menuItemId, String name, double price, int quantity) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getMenuItemId() {
        return menuItemId;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int q) {
        this.quantity = q;
    }

    public double totalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s x%d (ID:%d) - Rs %.2f", name, quantity, menuItemId, totalPrice());
    }
}
