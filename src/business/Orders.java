package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

public class Orders extends HashSet<Order> implements Workable<Order> {
    private final String ORDER_TABLE_HEADER = 
        "|-------------------------------------------------------------------------|\n" +
        "| Order ID     | Event date | Customer code| Set menu| Table  | Province  |\n" +
        "|-------------------------------------------------------------------------|";
    private final String ORDER_TABLE_FOOTER =
        "|-------------------------------------------------------------------------|\n";
    private String pathFile;
    private boolean saved;

    //constructor
    public Orders() {
        super();
        this.saved = true;
        this.pathFile = "./feast_order_service.dat";
        this.readFromFile();
    }

    public boolean isSaved() {
        return saved;
    }

    public boolean isDuplicate(Order x) {
        return this.contains(x);
    }

    @Override
    public void addNew(Order x) {
        if(!this.isDuplicate(x)){
            this.add(x);
        }
    }
    
    @Override
    public void update(Order x) {
        for (Order i : this) {
            if (i.getOrderCode().equalsIgnoreCase(x.getOrderCode())) {
                
                this.remove(i);
                this.add(x);
                this.saved = false;
                break;
            }
        }
    }
    
    @Override
    public Order searchById(String orderCode){
        Order result = null;
        Iterator<Order> it = this.iterator();
        while(it.hasNext() && result == null){
            Order x = it.next();
            if(x.getOrderCode().equalsIgnoreCase(orderCode)){
                result = x;
            }
        }
        return result;
    }
    
    @Override
    public void showAll(){
        showAll(this);
    }

    public void showAll(Collection<Order> l){
        System.out.println(ORDER_TABLE_HEADER);
        for (Order i : l) {
            System.out.println(i);
        }
        System.out.println(ORDER_TABLE_FOOTER);
    }

    public void saveToFile() {
        try {
            // 0. kiểm tra nếu đã lưu rồi thì không ghi nữa
            if (this.saved) {
                return;
            }
            FileOutputStream fos = null;

            //1. Tạo File object
            File f = new File(this.pathFile);
            //2. Tạo FileObjectStream ánh xạ tới File object
            fos = new FileOutputStream(f);
            //3. Tạo ObjectOutputStream để chuyển dữ liệu xuống thiết bị
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // 4. Lặp để ghi dữ liệu
            for (Order i : this) {
                oos.writeObject(i);
            }
            //5. Đóng các object tương ứng
            oos.close();
            fos.close();
            //6. Ghi nhận trạng thái mới
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
            //1. Tạo File object
            File f = new File(this.pathFile);
            if (!f.exists()) {
                System.out.println("feast_order_service.dat file not found!.");
                return;
            }
            //2. Tạo lường ánh xạ
            fis = new FileInputStream(f);
            //3. Tạo đối tượng
            ObjectInputStream ois = new ObjectInputStream(fis);
            //4. Lặp để đọc dữ liệu
            while (fis.available() > 0) {
                Order x = (Order) ois.readObject();
                this.add(x);
            }
            //5. Đóng đối tượng
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

