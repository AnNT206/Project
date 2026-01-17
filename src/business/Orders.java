package business;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import model.Order;
import java.util.List;
import java.util.ArrayList;
public class Orders extends HashSet<Order> implements Workable<Order> {
    private final String ORDER_TABLE_HEADER = 
        "-------------------------------------------------------------------------\n" +
        "| Order ID| Event date | Customer code| Set menu| Price | Table | Cost   \n" +
        "-------------------------------------------------------------------------\n";
    private final String ORDER_TABLE_FOOTER =
        "-------------------------------------------------------------------------\n";
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
                i = x;
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
    }
    
    public void showAll(Collection<Order> l){
        for (Order i : l) {
            System.out.println(i);
        }
    }
}

