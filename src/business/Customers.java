package business;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import model.Customer;

public class Customers extends HashMap<String, Customer> implements Workable<Customer> {

    private String pathFile; // lưu đường dẫn để ánh xạ vào tập 
    private boolean saved; // biến trạng thái đã lưu hay chưa lưu

    //constructor
    public Customers() {
        super();
        this.saved = true;
        this.pathFile = "./customers.dat";
    }

    public boolean isSaved() {
        return saved;
    }

    @Override
    public void addNew(Customer x) {
        this.put(x.getId(), x); //put hoặc putIfAbsent
        this.saved = false;
    }

    @Override
    public void update(Customer x) {
        Customer z = this.get(x.getId());
        if (z != null) {
            z = x;
        }
    }

    @Override
    public Customer searchById(String id) {
        return this.get(id);
    }

    public List<Customer> filterByName(String name) {
        List<Customer> result = new ArrayList<>();
        for (Customer i : this.values()) {
            if (i.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(i);
            }
        }
        return result;
    }

    @Override
    public void showAll() {
        showAll(this.values());
    }
    
    public void showAll(Collection<Customer>l){
        for (Customer i : l) {
            System.out.println(i);
        }
    }
}
