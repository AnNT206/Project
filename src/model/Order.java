package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private String orderCode;
    private String customerId;
    private String province;
    private String menuId;
    private int numOfTables;
    private Date eventDate;

    //constructor
    public Order() {
        this.orderCode = generateOrderCode();
    }

    public Order(String orderCode, String customerId, String province, String menuId, int numOfTables, Date eventDate) {
        this.orderCode = generateOrderCode();
        this.customerId = customerId;
        this.province = province;
        this.menuId = menuId;
        this.numOfTables = numOfTables;
        this.eventDate = eventDate;
    }

    //Getter and setter
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public int getNumOfTables() {
        return numOfTables;
    }

    public void setNumOfTables(int numOfTables) {
        this.numOfTables = numOfTables;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

//    Tạo mã đơn hàng dựa vào TimeStamp
    private String generateOrderCode() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(now);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.customerId);
        hash = 67 * hash + Objects.hashCode(this.menuId);
        hash = 67 * hash + Objects.hashCode(this.eventDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.customerId, other.customerId)) {
            return false;
        }
        if (!Objects.equals(this.menuId, other.menuId)) {
            return false;
        }
        return Objects.equals(this.eventDate, other.eventDate);
    }

    @Override
    public String toString() {
        return String.format("|%5s|%-12s|%5s|%5s|%-8d|%-14s|", this.orderCode, this.eventDate, this.customerId, this.menuId, this.numOfTables, this.province);
    }
}
