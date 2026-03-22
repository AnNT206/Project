package dispatchter;

import business.Customers;
import business.Orders;
import business.SetMenus;
import java.time.LocalDate;
import model.Customer;
import model.Order;
import tools.Inputter;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Inputter ndl = new Inputter();
        int choice = 0;
        Customers dskh = new Customers();
        SetMenus mn = new SetMenus();
        Orders dsdh = new Orders();
        //--------- MENU ---------//
        do {
            choice = ndl.getInt("------------------------------------------\n"
                    + "\"1.Register customers.\n"
                    + "\"2.Update customer information.\n"
                    + "\"3.Search for customer information by name.\n"
                    + "\"4.Display feast menus.\n"
                    + "\"5.Place a feast order.\n"
                    + "\"6.Update order information.\n"
                    + "\"7.Save data to file.\n"
                    + "\"8.Display Customer or Order lists.\n"
                    + "\"Others- Quit.\n"
                    + "------------------------------------------\n"
                    + "Your choice: ");
            switch (choice) {
                case 1:
                    Customer x = ndl.getCustomerInfo();
                    dskh.addNew(x);
                    break;
                case 2:
                    String customerId = ndl.getCusId("Enter customer ID to update: ");
                    Customer existingCustomer = dskh.searchById(customerId);
                    if (existingCustomer == null) {
                        System.out.println("Customer not found!");
                    } else {
                        System.out.println("Current customer information:");
                        System.out.println(existingCustomer);
                        System.out.println("Update new information:");
                        Customer updatedCustomer = ndl.getCustomerInfoToUpdate(customerId);
                        dskh.update(updatedCustomer);
                        System.out.println("Customer updated successfully!");
                    }
                    break;
                case 3:
                    String name = ndl.getString("Enter customer name to search: ");
                    List<Customer> result = dskh.filterByName(name);
                    if (result == null) {
                        System.out.println("Customer not found!");
                    } else {
                        dskh.showAll(result);
                    }
                    break;
                case 4:
                    mn.showAll();
                    break;
                case 5:
                    Order order = ndl.getOrderInfo();
                    Customer customer = dskh.searchById(order.getCustomerId());

                    if (customer == null) {
                        System.out.println("Customer not found!");
                    } else if (dsdh.isDuplicate(order)) {
                        System.out.println("Order already exists!");
                    } else {
                        LocalDate currentDate = LocalDate.now();
                        LocalDate eventDate = order.getEventDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

                        if (!eventDate.isAfter(currentDate)) {
                            System.out.println("Event date must be after current date");
                        } else {
                            dsdh.addNew(order);
                            System.out.println("Order placed successfully!");
                        }
                    }
                    break;
                case 6:
                    String orderCode = ndl.getString("Enter order code to update: ");
                    Order existingOrder = dsdh.searchById(orderCode);
                    
                    if (existingOrder == null) {
                        System.out.println("Order not found!");
                    } else {
                        System.out.println("Current order information:");
                        System.out.println(existingOrder);
                        
                        System.out.println("Update new information:");
                        Order updatedOrder = ndl.getOrderInfoToUpdate(orderCode);
                        
                        LocalDate currentDate = LocalDate.now();
                        LocalDate eventDate = updatedOrder.getEventDate().toInstant()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toLocalDate();
                        
                        if (!eventDate.isAfter(currentDate)) {
                            System.out.println("Event date must be after current date!");
                        } else {
                            dsdh.update(updatedOrder);
                            System.out.println("Order updated successfully!");
                        }
                    }
            case 7:
                    dskh.saveToFile();
                    dsdh.saveToFile();
                    System.out.println("Data saved successfully!");
                    break;
                case 8:
                    int displayChoice = 0;
                    do {
                        displayChoice = ndl.getInt(
                                "------------------------------------------\n"
                                + "\"1.Display customer list.\n"
                                + "\"2.Display order list.\n"
                                + "\"Others- Back to main menu.\n"
                                + "------------------------------------------\n"
                                + "Your choice: ");
                        switch (displayChoice) {
                            case 1:
                                System.out.println("DANH SACH KHACH HANG:");
                                dskh.showAll();
                                break;
                            case 2:
                                System.out.println("DANH SACH DON HANG:");
                                dsdh.showAll();
                                break;
                            default:
                                System.out.println("Back to main menu...");
                        }
                    } while (displayChoice >= 1 && displayChoice <= 2);
                    break;
                default:
                    System.out.println("END...");

            }
        } while (choice >= 1 && choice <= 8);
        }

    
}
