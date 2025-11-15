package interfaces;

import exceptions.DuplicateEntryException;
import exceptions.ResourceNotFoundException;
import models.Order;

import java.util.Map;

public interface IOrderService {
    Map<Integer, Order> getAll();
    void create(Order order) throws DuplicateEntryException, ResourceNotFoundException;
    Order getById(int id) throws ResourceNotFoundException;
    void addMenuItemToOrder(int orderId, int menuItemId, int qty) throws ResourceNotFoundException;
    void removeMenuItemFromOrder(int orderId, int menuItemId, int qty) throws ResourceNotFoundException;
    void cancelOrder(int orderId) throws ResourceNotFoundException;
    void deleteOrder(int orderId) throws ResourceNotFoundException;
}
