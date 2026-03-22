package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

public class Orders extends HashSet<Order> implements Workable<Order> {

    private final String ORDER_TABLE_HEADER
            = "|-------------------------------------------------------------------------|\n"
            + "| Order ID     | Event date | Customer code| Set menu| Table  | Province  |\n"
            + "|-------------------------------------------------------------------------|";
    private final String ORDER_TABLE_FOOTER
            = "|-------------------------------------------------------------------------|\n";
    private String pathFile;
    private boolean saved;

    //constructor
    public Orders() {
        super();
        this.saved = true;
        this.pathFile = "./feast_order_service.dat";
        this.readFromFile();
        this.saveToFile();
        // Test
        Order o1 = new Order();
        o1.setOrderCode("20260101100001");
        o1.setCustomerId("C0000");
        o1.setProvince("Hanoi");
        o1.setMenuId("PW001");
        o1.setNumOfTables(5);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            o1.setEventDate(sdf.parse("15/06/2026"));
        } catch (Exception e) {
            o1.setEventDate(new Date());
        }
        this.add(o1);

        Order o2 = new Order();
        o2.setOrderCode("20260102100002");
        o2.setCustomerId("C0002");
        o2.setProvince("Ho Chi Minh");
        o2.setMenuId("PW002");
        o2.setNumOfTables(10);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            o2.setEventDate(sdf.parse("20/07/2026"));
        } catch (Exception e) {
            o2.setEventDate(new Date());
        }
        this.add(o2);

        Order o3 = new Order();
        o3.setOrderCode("20260103100003");
        o3.setCustomerId("C0003");
        o3.setProvince("Da Nang");
        o3.setMenuId("PW003");
        o3.setNumOfTables(8);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            o3.setEventDate(sdf.parse("25/08/2026"));
        } catch (Exception e) {
            o3.setEventDate(new Date());
        }
        this.add(o3);
    }

    public boolean isSaved() {
        return saved;
    }

    public boolean isDuplicate(Order x) {
        return this.contains(x);
    }

    @Override
    public void addNew(Order x) {
        if (!isDuplicate(x)) {
            this.add(x);
        }
    }

    @Override
    public void update(Order x) {
        for (Order i : this) {
            if (i.getOrderCode().equalsIgnoreCase(x.getOrderCode())) {
                i.setEventDate(x.getEventDate());
                i.setMenuId(x.getMenuId());
                i.setNumOfTables(x.getNumOfTables());
                i.setProvince(x.getProvince());
                this.saved = false;
                return;
            }
        }
    }

    @Override
    public Order searchById(String orderCode) {
        Order result  = null;
        Iterator<Order> it = this.iterator();
        while (it.hasNext() && result == null) {
            Order x =it.next();
            if (x.getOrderCode().equalsIgnoreCase(orderCode)) {
                result = x;
            }
        }
        return result;
    }

    @Override
    public void showAll() {
        showAll(this);
    }

    public void showAll(Collection<Order> l) {
        System.out.println(ORDER_TABLE_HEADER);
        for (Order i : l) {
            System.out.println(i);
        }
        System.out.println(ORDER_TABLE_FOOTER);
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

            for (Order i : this) {
                oos.writeObject(i);
            }

            oos.close();
            fos.close();

            this.saved = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readFromFile() {
        try {
            FileInputStream fis = null;

            File f = new File(this.pathFile);
            if (!f.exists()) {
                System.out.println("feast_order_service.dat file not found!.");
                return;
            }

            fis = new FileInputStream(f);

            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available() > 0) {
                Order x = (Order) ois.readObject();
                this.add(x);
            }

            ois.close();
            fis.close();
            this.saved = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
