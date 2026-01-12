package business;

import model.Customer;

public interface CustomerWorkable {
     void addNew(Customer x);
     void update(Customer x);
     Customer searchById(String id);
     void showAll();
}
