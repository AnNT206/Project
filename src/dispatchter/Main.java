package dispatchter;

import business.Customers;
import business.Orders;
import model.Customer;
import model.Order;
import tools.Inputter;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Inputter ndl = new Inputter();
        int choice = 0;
        Customers dskh = new Customers();
        Orders dsdh = new Orders();
        //--------- MENU ---------//
        do {
            choice = ndl.getInt("-------------------\n"
                    + "\"1.Register customers.\n"
                    + "\"2.Update customer information.\n"         
                    + "\"3.Search for customer information by name.\n"
                    + "\"4.Display feast menus.\n"
                    + "\"5.Place a feast order.\n"
                    + "\"6.Update order information.\n"
                    + "\"7.Save data to file.\n"
                    + "\"8.Display Customer or O rder lists.\n"
                    + "\"Others- Quit.\n"
                    + "-----------------------------\n");
            switch(choice){
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
                    if(result.isEmpty()){
                        System.out.println("No customer found.");
                    }else{
                        for(Customer i : result){
                            i.showAll();
                        }
                    }
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    dskh.showAll();
                    dsdh.showAll();
                    break;

            }
        } while (choice >= 1 && choice <= 8);
    }

}
