package utilis;

public class IdGenerator {
    private static int menuId = 100; // starting ids
    private static int customerId = 200;
    private static int orderId = 300;

    public static int nextMenuId() {
        return ++menuId;
    }

    public static int nextCustomerId() {
        return ++customerId;
    }

    public static int nextOrderId() {
        return ++orderId;
    }
}
