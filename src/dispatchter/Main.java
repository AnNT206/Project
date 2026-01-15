package dispatchter;

import business.Customers;
import business.Orders;
import business.SetMenus;
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
                    + "\"8.Display Customer or O rder lists.\n"
                    + "\"Others- Quit.\n"
                    + "------------------------------------------\n"
                    + "Your choice: ");
            switch (choice) {
                case 1:
                    Customer x = ndl.getCustomerInfo();
                    dskh.addNew(x);
                    break;
                case 2:
                    Customer y = ndl.getCustomerInfo();
                    dskh.update(y);
                    break;
                case 3:
                    String name = ndl.getName("Enter customer name: ");
                    List<Customer> result = dskh.filterByName(name);
                    if (result.isEmpty()) {
                        System.out.println("No customer found.");
                    } else {
                        dskh.showAll(result);
                    }
                    break;
                case 4:
                    mn.showAll();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("DANH SACH KHACH HANG:");
                    dskh.showAll();
                    break;
                default:
                    System.out.println("END...");

            }
        } while (choice >= 1 && choice <= 8);
    }

}
