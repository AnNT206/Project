package tools;

import java.util.Scanner;
import model.Customer;

public class Inputter {

    private Scanner ndl;

    public Inputter() {
        this.ndl = new Scanner(System.in);
    }

    //Nhập dữ liệu chuỗi từ bàn phím
    public String getString(String mess) {
        System.out.print(mess);
        return this.ndl.nextLine();
    }

    //Nhập dữ liệu số nguyên từ bàn phím và kiểm tra tính hợp lệ
    public int getInt(String mess) {
        int kq = 0;
        String tam = getString(mess);
        if (Acceptable.isValid(tam, Acceptable.INTEGER_VALID)) {
            kq = Integer.parseInt(tam);
        }
        return kq;
    }

    //Nhập dữ liệu số thực từ bàn phím và kiểm tra tính hợp lệ
    public double getDouble(String mess) {
        double kq = 0;
        String tam = getString(mess);
        if (Acceptable.isValid(tam, Acceptable.DOUBLE_VALID)) {
            kq = Double.parseDouble(tam);
        }
        return kq;
    }

    //Kiểm tra dữ liệu nhập vào có hợp lệ hay không
    public String inputAndLoop(String mess, String pattern, boolean isLoop) {
        String result = "";
        boolean more = true;
        do {
            result = getString(mess);
            more = !Acceptable.isValid(result, pattern);
            if (more) {
                System.out.println("Data is invalid!. Re-enter...");
            }
        } while (isLoop && more);
        return result.trim();
    }

    //Nhập dữ liệu thông tin khách hàng từ bàn phím
    public String getCusId(String mess) {
        return inputAndLoop("Customer ID: ", Acceptable.CUS_ID_VALID, true);
    }

    public String getName(String mess) {
        return inputAndLoop("Customer Name: ", Acceptable.NAME_VALID, true);
    }

    public String getPhone(String mess) {
        return inputAndLoop("Phone number [10 digits]: ", Acceptable.PHONE_VALID, true);
    }

    public String getEmail(String mess) {
        return inputAndLoop("Email address: ", Acceptable.EMAIL_VALID, true);
    }

    public Customer getCustomerInfo() {
        Customer x = new Customer();
        x.setId(inputAndLoop("Customer ID: ", Acceptable.CUS_ID_VALID, true));
        x.setName(inputAndLoop("Customer name: ", Acceptable.NAME_VALID, true));
        x.setPhone(inputAndLoop("Phone number: ", Acceptable.PHONE_VALID, true));
        x.setEmail(inputAndLoop("Customer email: ", Acceptable.EMAIL_VALID, true));
        return x;
    }

    public Customer getCustomerInfoToUpdate(String existingId) {
        Customer x = new Customer();
        x.setId(existingId);
        x.setName(inputAndLoop("Customer name: ", Acceptable.NAME_VALID, true));
        x.setPhone(inputAndLoop("Phone number: ", Acceptable.PHONE_VALID, true));
        x.setEmail(inputAndLoop("Customer email: ", Acceptable.EMAIL_VALID, true));
        return x;
    }
}
