package utilis;

public class IdGenerator {
    private static int menuId = 102; // starting ids
    private static int customerId = 202;
    private static int orderId = 301;

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
