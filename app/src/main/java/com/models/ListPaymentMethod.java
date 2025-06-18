package com.models;

import java.util.ArrayList;

public class ListPaymentMethod {
    ArrayList<PaymentMethod>paymentMethods;
    public ListPaymentMethod()
    {

    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public void gen_payment_method()
    {
        paymentMethods.add(new PaymentMethod(1, "Banking Account","Chuyển khoản ngân hàng"));
        paymentMethods.add(new PaymentMethod(2, "MoMo", "Dùng ví điện tử MoMo"));
        paymentMethods.add(new PaymentMethod(3, "Cash", "Trả tiền mặt"));
        paymentMethods.add(new PaymentMethod(4,"COD", "Trả tiền khi nhận hàng"));
    }
}
