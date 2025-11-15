package rms;

import interfaces.*;
import models.*;
import services.*;
import exceptions.*;
import utilis.IdGenerator;


import java.util.Scanner;
import java.util.InputMismatchException;


public class Application {
    private static IMenuService menuService = new MenuService();
    private static ICustomerService customerService = new CustomerService();
    private static IOrderService orderService = new OrderService(menuService, customerService);

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            try {
                System.out.println("\n--- Restaurant Management System ---");
                System.out.println("1. Menu Management\n2. Customer Management\n3. Order Management\n4. Exit");
                System.out.print("Select your choice for managing: ");
                int mainChoice = sc.nextInt();
                sc.nextLine();
                switch (mainChoice) {
                    case 1:
                        menuManagement(sc);
                    break;
                    case 2:
                        customerManagement(sc);
                        break;
                    case 3:
                        orderManagement(sc);
                        break;
                    case 4:
                        System.out.println("Thank you for using RMS Application..");
                    running = false;
                    break;
                    default:
                        throw new MenuSelectionException("Invalid main menu choice");
                }
            }
            catch (MenuSelectionException mse) {
                System.out.println("[MenuSelectionException] " + mse.getMessage());
            }
            catch (InputMismatchException ime) {
                System.out.println("[InputMismatch] Invalid input type. Try again.");
                sc.nextLine();
            }
            catch (Exception e) {
                System.out.println("[Error] " + e.getMessage());
            }
        }
        sc.close();
    }

    private static void menuManagement(Scanner sc) {
        boolean back = false;
        while (!back) {
            try {
                System.out.println("\n-- Menu Management --");
                System.out.println("1. Add Menu Item\n2. View All\n3. Update\n4. Delete\n5. Back");
                System.out.print("Selects: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        int mid = IdGenerator.nextMenuId();
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Category: ");
                        String cat = sc.nextLine();
                        try {
                            menuService.add(new MenuItem(mid, name, price, cat));
                            System.out.println("Added with ID: " + mid);
                        }
                        catch (DuplicateEntryException de) {
                            System.out.println(de.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("All Menu Items:");
                        for (MenuItem it : menuService.getAll())
                            System.out.println(it);
                        break;
                    case 3:
                        System.out.print("Enter Menu ID to update: ");
                        int uid = sc.nextInt();
                        sc.nextLine();
                        try {
                            MenuItem existing = menuService.getById(uid);
                            System.out.println("Existing: " + existing);
                            System.out.print("New name (enter to keep): ");
                            String nname = sc.nextLine();
                            if (!nname.trim().isEmpty()) {
                                existing.setName(nname);
                            }
                            System.out.print("New price (0 to keep): ");
                            double nprice = sc.nextDouble(); sc.nextLine();
                            if (nprice > 0) existing.setPrice(nprice);
                            System.out.print("New category (enter to keep): ");
                            String ncat = sc.nextLine();
                            if (!ncat.trim().isEmpty()) existing.setCategory(ncat);
                            menuService.update(existing);
                            System.out.println("Updated.");
                        } catch (ResourceNotFoundException rnfe) { System.out.println(rnfe.getMessage()); }
                        break;
                    case 4:
                        System.out.print("Enter Menu ID to delete: "); int did = sc.nextInt(); sc.nextLine();
                        try {
                            menuService.delete(did);
                            System.out.println("Deleted.");
                        }
                        catch (ResourceNotFoundException rnfe) {
                            System.out.println(rnfe.getMessage());
                        }
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        throw new MenuSelectionException("Invalid menu management choice");
                }
            }
            catch (MenuSelectionException mse) {
                System.out.println(mse.getMessage());
            }
            catch (InputMismatchException ime) {
                System.out.println("Invalid input type.");
                sc.nextLine();
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void customerManagement(Scanner sc) {
        boolean back = false;
        while (!back) {
            try {
                System.out.println("\n-- Customer Management --");
                System.out.println("1. Add Customer\n2. View All\n3. Update\n4. Delete\n5. Back");
                System.out.print("Select: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        int cid = IdGenerator.nextCustomerId();
                        System.out.print("Name: "); String name = sc.nextLine();
                        System.out.print("Phone: "); String phone = sc.nextLine();
                        try {
                            customerService.add(new Customer(cid, name, phone));
                            System.out.println("Customer added: " + cid);
                        }
                        catch (DuplicateEntryException de) {
                            System.out.println(de.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("All Customers:");
                        for (Customer c : customerService.getAll())
                            System.out.println(c);
                        break;
                    case 3:
                        System.out.print("Enter Customer ID to update: ");
                        int uid = sc.nextInt(); sc.nextLine();
                        try {
                            Customer existing = customerService.getById(uid);
                            System.out.println("Existing: " + existing);
                            System.out.print("New name (enter to keep): ");
                            String nname = sc.nextLine();
                            if (!nname.trim().isEmpty()) existing.setName(nname);
                            System.out.print("New phone (enter to keep): ");
                            String nphone = sc.nextLine();
                            if (!nphone.trim().isEmpty()) existing.setPhone(nphone);
                            customerService.update(existing);
                            System.out.println("Updated.");
                        }
                        catch (ResourceNotFoundException rnfe) {
                            System.out.println(rnfe.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("Enter Customer ID to delete: ");
                        int did = sc.nextInt(); sc.nextLine();
                        try {
                            customerService.delete(did);
                            System.out.println("Deleted.");
                        }
                        catch (ResourceNotFoundException rnfe) {
                            System.out.println(rnfe.getMessage());
                        }
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        throw new MenuSelectionException("Invalid customer management choice");
                }
            }
            catch (MenuSelectionException mse) {
                System.out.println(mse.getMessage());
            }
            catch (InputMismatchException ime) {
                System.out.println("Invalid input type.");
                sc.nextLine();
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void orderManagement(Scanner sc) {
        boolean back = false;
        while (!back) {
            try {
                System.out.println("\n-- Order Management --");
                System.out.println("1. Create Order\n2. View All Orders\n3. Update Order (add/remove items)\n4. Cancel Order\n5. Delete Order\n6. Back");
                System.out.print("Select: ");
                int choice = sc.nextInt(); sc.nextLine();
                switch (choice) {
                    case 1:
                        int oid = IdGenerator.nextOrderId();
                        System.out.print("Customer ID: ");
                        int cid = sc.nextInt(); sc.nextLine();
                        Order o = new Order(oid, cid);
                        try {
                            orderService.create(o);
                            System.out.println("Order created with ID: " + oid);
                        }
                        catch (DuplicateEntryException de) {
                            System.out.println(de.getMessage());
                        }
                        catch (ResourceNotFoundException rnfe) {
                            System.out.println(rnfe.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("All Orders:");
                        for (Order ord : orderService.getAll().values())
                            System.out.println(ord + "\n");
                        break;
                    case 3:
                        System.out.print("Order ID: ");
                        int upId = sc.nextInt(); sc.nextLine();
                        System.out.println("1. Add Item\n2. Remove Item");
                        int sub = sc.nextInt(); sc.nextLine();
                        if (sub == 1) {
                            System.out.print("Menu Item ID: ");
                            int mid = sc.nextInt(); sc.nextLine();
                            System.out.print("Quantity: ");
                            int q = sc.nextInt(); sc.nextLine();
                            try {
                                orderService.addMenuItemToOrder(upId, mid, q);
                                System.out.println("Item added.");
                            }
                            catch (ResourceNotFoundException rnfe) {
                                System.out.println(rnfe.getMessage());
                            }
                        }
                        else if (sub == 2) {
                            System.out.print("Menu Item ID: ");
                            int mid = sc.nextInt(); sc.nextLine();
                            System.out.print("Quantity to remove: ");
                            int q = sc.nextInt(); sc.nextLine();
                            try {
                                orderService.removeMenuItemFromOrder(upId, mid, q);
                                System.out.println("Item removed/updated.");
                            }
                            catch (ResourceNotFoundException rnfe) {
                                System.out.println(rnfe.getMessage());
                            }
                        }
                        else {
                            System.out.println("Invalid sub-choice.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter Order ID to cancel: ");
                        int cidc = sc.nextInt(); sc.nextLine();
                        try {
                            orderService.cancelOrder(cidc);
                            System.out.println("Canceled."); }
                        catch (ResourceNotFoundException rnfe) {
                            System.out.println(rnfe.getMessage());
                        }
                        break;
                    case 5:
                        System.out.print("Enter Order ID to delete: ");
                        int did = sc.nextInt(); sc.nextLine();
                        try {
                            orderService.deleteOrder(did);
                            System.out.println("Deleted.");
                        }
                        catch (ResourceNotFoundException rnfe) {
                            System.out.println(rnfe.getMessage());
                        }
                        break;
                    case 6:
                        back = true;
                        break;
                    default:
                        throw new MenuSelectionException("Invalid order management choice");
                }
            } catch (MenuSelectionException mse) {
                System.out.println(mse.getMessage());
            }
            catch (InputMismatchException ime) {
                System.out.println("Invalid input type.");
                sc.nextLine();
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}