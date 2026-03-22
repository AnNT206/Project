package business;

import com.sun.corba.se.impl.io.IIOPInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

public class Customers extends HashMap<String, Customer> implements Workable<Customer> {

    private final String TABLE_HEADER = "|-------------------------------------------------------------------------|\n"
            + "| ID  | FULL NAME               | PHONE    | EMAIL                        |\n"
            + "|-------------------------------------------------------------------------|";
    private final String TABLE_FOOTER = "|-------------------------------------------------------------------------|\n";
    private String pathFile; // lưu đường dẫn để ánh xạ vào tập 
    private boolean saved; // biến trạng thái đã lưu hay chưa lưu

    //constructor
    public Customers() {
        super();
        this.saved = true;
        this.pathFile = "./customers.dat";
        this.readFromFile();
        this.saveToFile();
        // Add test customers for testing
        this.put("C0001", new Customer("C0001", "Nguyen Van A", "0123456789", "vana@gmail.com"));
        this.put("C0002", new Customer("C0002", "Tran Thi B", "0987654321", "btran@gmail.com"));
        this.put("C0003", new Customer("C0003", "Le Van C", "0789654321", "cvan@gmail.com"));
    }

    public boolean isSaved() {
        return saved;
    }

    @Override
    public void addNew(Customer x) {
        this.put(x.getId(), x);
        this.saved = false;
    }

    @Override
    public void update(Customer x) {
        Customer existing = this.get(x.getId());
        if(existing != null){
            existing.setName(x.getName());
            existing.setEmail(x.getEmail());
            existing.setPhone(x.getPhone());
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

    public void showAll(Collection<Customer> l) {
        System.out.println(TABLE_HEADER);
        for (Customer i : l) {
            System.out.println(i);
        }
        System.out.println(TABLE_FOOTER);
    }

    public void saveToFile() {
        try {
            
            if (this.saved) {
                return;
            }
            FileOutputStream fos = null;
            
            File f = new File(this.pathFile);
            
            fos = new FileOutputStream(f);
            
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            for (Customer i : this.values()) {
                oos.writeObject(i);
            }
            
            oos.close();
            fos.close();
            
            this.saved = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void readFromFile() {
        try {
            FileInputStream fis = null;
            
            File f = new File(this.pathFile);
            if (!f.exists()) {
                System.out.println("Customer.dat file not found!.");
                return;
            }
            
            fis = new FileInputStream(f);
            
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while (fis.available() > 0) {
                Customer x = (Customer) ois.readObject();
                this.put(x.getId(), x);
            }
            
            ois.close();
            fis.close();
            this.saved = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
