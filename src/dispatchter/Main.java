package dispatchter;

import business.Customers;
import model.Customer;
import tools.Inputter;

public class Main {

    public static void main(String[] args) {
        Inputter ndl = new Inputter();
        int choice = 0;
        Customers dskh = new Customers();
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
            }
        } while (choice >= 1 && choice <= 8);
    }

}
