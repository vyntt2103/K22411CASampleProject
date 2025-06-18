package com.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ListCustomer {
    private ArrayList<Customer> customers;

    public ListCustomer() {

        customers=new ArrayList<>();
    }
    public void addCustomer(Customer c)
    {
        customers.add(c);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
    public void generate_sample_dataset()
    {
        addCustomer(new Customer(1, "An", "an@gmail.com", "0911002200", "an", "anpass"));
        addCustomer(new Customer(2, "Binh", "binh@gmail.com", "0933004400", "binh", "binh123"));
        addCustomer(new Customer(3, "Dao", "dao@gmail.com", "0955006600", "dao", "dao789"));
        addCustomer(new Customer(4, "Lan", "lan@gmail.com", "0911222333", "lan", "abc123"));
        addCustomer(new Customer(5, "Long", "long@gmail.com", "0933444555", "long", "xyz456"));
        addCustomer(new Customer(6, "Hoa", "hoa@gmail.com", "0944666777", "hoa", "pass789"));
        addCustomer(new Customer(7, "Minh", "minh@gmail.com", "0966888999", "minh", "secure001"));
        addCustomer(new Customer(8, "Nam", "nam@gmail.com", "0977001122", "nam", "qwerty"));
        addCustomer(new Customer(9, "Linh", "linh@gmail.com", "0988111222", "linh", "hello123"));
        addCustomer(new Customer(10, "Huy", "huy@gmail.com", "0900999888", "huy", "huy456"));
    }
    public boolean isExisting(Customer c)
    {
        for(Customer cus: customers)
        {
            if(cus.getId()== c.getId() ||
               cus.getPhone().equals(c.getPhone()) ||
               cus.getEmail().equalsIgnoreCase(c.getEmail()) ||
               cus.getUsername().equalsIgnoreCase(c.getUsername())
               )
               return true;
        }
        return false;
    }
    public void getAllCustomers(SQLiteDatabase database)
    {
        Cursor cursor = database.rawQuery("SELECT * FROM Customer",
                null);
        customers.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            String username = cursor.getString(4);
            String password = cursor.getString(5);
            int saveInfor = cursor.getInt(6);
            Customer c=new Customer();
            c.setId(id);
            c.setName(name);
            c.setPhone(phone);
            c.setEmail(email);
            c.setUsername(username);
            c.setPassword(password);
            customers.add(c);
        }
        cursor.close();

    }
}
