package services;

import exceptions.DuplicateEntryException;
import exceptions.ResourceNotFoundException;
import interfaces.IMenuService;
import models.MenuItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MenuService implements IMenuService {
private ArrayList<MenuItem> items = new ArrayList<>();
private HashSet<String> categories = new HashSet<>();

public MenuService() {
    // initial demo data
    items.add(new MenuItem(101, "Masala Dosa", 80.0, "South"));
    items.add(new MenuItem(102, "Paneer Butter Masala", 180.0, "North"));
    categories.add("South"); categories.add("North");
}

@Override
public List<MenuItem> getAll() {
    return items;
}

@Override
public void add(MenuItem m) throws DuplicateEntryException {
    for (MenuItem it : items) if (it.getId() == m.getId())
        throw new DuplicateEntryException("MenuItem with ID exists: " + m.getId());
    items.add(m);
    categories.add(m.getCategory());
}

@Override
public MenuItem getById(int id) throws ResourceNotFoundException {
    for (MenuItem it : items) if (it.getId() == id) return it;
    throw new ResourceNotFoundException("MenuItem not found: " + id);
}

@Override
public void update(MenuItem updated) throws ResourceNotFoundException {
    boolean found = false;
    for (int i = 0; i < items.size(); i++) {
        if (items.get(i).getId() == updated.getId()) {
            items.set(i, updated);
            categories.add(updated.getCategory());
            found = true;
            break;
        }
    }
    if (!found) throw new ResourceNotFoundException("MenuItem not found: " + updated.getId());
}

@Override
public void delete(int id) throws ResourceNotFoundException {
    MenuItem toRemove = null;
    for (MenuItem it : items) if (it.getId() == id) { toRemove = it; break; }
    if (toRemove == null) throw new ResourceNotFoundException("MenuItem not found: " + id);
    items.remove(toRemove);
}

@Override
public HashSet<String> getCategories() {
    return categories;
}
}

