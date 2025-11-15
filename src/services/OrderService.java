package services;

import models.Order;
import models.OrderItem;
import models.MenuItem;
import interfaces.ICustomerService;
import interfaces.IMenuService;
import interfaces.IOrderService;
import exceptions.MenuSelectionException;
import exceptions.DuplicateEntryException;
import exceptions.ResourceNotFoundException;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class OrderService implements IOrderService {
    private HashMap<Integer, Order> orders = new HashMap<>();
    private IMenuService menuService;
    private ICustomerService customerService;

    public OrderService(IMenuService ms, ICustomerService cs) {
        this.menuService = ms;
        this.customerService = cs;

        // sample order
        Order o = new Order(301, 201);
        try {
            MenuItem mi = menuService.getById(101);
            o.getItems().add(new OrderItem(mi.getId(), mi.getName(), mi.getPrice(), 2));
            orders.put(o.getId(), o);
        } catch (Exception e) { /* ignore sample init issues */ }
    }

    @Override
    public Map<Integer, Order> getAll() { return orders; }

    @Override
    public void create(Order order) throws DuplicateEntryException, ResourceNotFoundException {
        if (orders.containsKey(order.getId())) throw new DuplicateEntryException("Order exists: " + order.getId());
        // validate customer id
        customerService.getById(order.getCustomerId());
        orders.put(order.getId(), order);
    }

    @Override
    public Order getById(int id) throws ResourceNotFoundException {
        Order o = orders.get(id);
        if (o == null) throw new ResourceNotFoundException("Order not found: " + id);
        return o;
    }

    @Override
    public void addMenuItemToOrder(int orderId, int menuItemId, int qty) throws ResourceNotFoundException {
        Order o = getById(orderId);
        if (o.isCanceled()) throw new ResourceNotFoundException("Order is canceled: " + orderId);
        MenuItem mi = menuService.getById(menuItemId);
        for (OrderItem oi : o.getItems()) {
            if (oi.getMenuItemId() == menuItemId) {
                oi.setQuantity(oi.getQuantity() + qty);
                return;
            }
        }
        o.getItems().add(new OrderItem(mi.getId(), mi.getName(), mi.getPrice(), qty));
    }

    @Override
    public void removeMenuItemFromOrder(int orderId, int menuItemId, int qty) throws ResourceNotFoundException {
        Order o = getById(orderId);
        LinkedList<OrderItem> items = o.getItems();
        OrderItem found = null;
        for (OrderItem oi : items) if (oi.getMenuItemId() == menuItemId) { found = oi; break; }
        if (found == null) throw new ResourceNotFoundException("Menu item not in order: " + menuItemId);
        if (qty >= found.getQuantity()) items.remove(found);
        else found.setQuantity(found.getQuantity() - qty);
    }

    @Override
    public void cancelOrder(int orderId) throws ResourceNotFoundException {
        Order o = getById(orderId);
        o.cancel();
    }

    @Override
    public void deleteOrder(int orderId) throws ResourceNotFoundException {
        if (!orders.containsKey(orderId)) throw new ResourceNotFoundException("Order not found: " + orderId);
        orders.remove(orderId);
    }
}




