package business;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import model.Order;

public class Orders extends HashSet<Order> {

    private String pathFile;
    private boolean saved;

    //constructor
    public Orders() {
        super();
        this.saved = true;
        this.pathFile = "./feast_order_service.dat";
    }

    public boolean isSaved() {
        return saved;
    }

    public void addNew(Order x) {
        this.add(x);
    }

    public void update(Order x) {
        for (Order i : this) {
            if (i.getOrderCode().equalsIgnoreCase(x.getOrderCode())) {
                i = x;
                break;
            }
        }
    }
    
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
    
    public void showAll(){
    }
    
    public void showAll(Collection<Order> l){
        for (Order i : l) {
            System.out.println(i);
        }
    }
}