package com.example.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.models.OrdersViewer;
import com.models.PaymentMethod;

import java.util.ArrayList;

public class OrdersViewerConnector {
    public ArrayList<OrdersViewer>getAllOrdersViewers(SQLiteDatabase database)
    {
        StringBuilder sqlBuilder=new StringBuilder();
        sqlBuilder.append(" SELECT ");
        sqlBuilder.append(" o.Id AS OrderId, ");
        sqlBuilder.append(" o.Code AS OrderCode, ");
        sqlBuilder.append(" c.Name AS CustomerName, ");
        sqlBuilder.append(" e.Name AS EmployeeName, ");
        sqlBuilder.append(" o.OrderDate, ");

        /*sqlBuilder.append(" -- Tổng giá gốc ");*/
        sqlBuilder.append(" ROUND(SUM(od.Price * od.Quantity), 2) AS GrossTotal, ");

        /*sqlBuilder.append(" -- Tổng Discount thực tế ");*/
        sqlBuilder.append(" ROUND(SUM(od.Price * od.Quantity * (REPLACE(od.Discount, '%', '') / 100.0)), 2) AS TotalDiscount, ");

        /*sqlBuilder.append(" -- Tổng VAT sau khi trừ Discount ");*/
        sqlBuilder.append(" ROUND(SUM(\n" +
                "        (od.Price * od.Quantity * (1 - REPLACE(od.Discount, '%', '') / 100.0)) \n" +
                "        * (REPLACE(od.VAT, '%', '') / 100.0)\n" +
                "    ), 2) AS TotalVAT, ");

        /*sqlBuilder.append(" -- Tổng tiền thanh toán cuối cùng ");*/
        sqlBuilder.append(" ROUND(SUM(\n" +
                "        (od.Price * od.Quantity * (1 - REPLACE(od.Discount, '%', '') / 100.0)) \n" +
                "        * (1 + REPLACE(od.VAT, '%', '') / 100.0)\n" +
                "    ), 2) AS FinalTotal ");

        sqlBuilder.append(" FROM Orders o ");

        sqlBuilder.append(" LEFT JOIN Customer c ON o.CustomerId = c.Id ");
        sqlBuilder.append(" LEFT JOIN Employee e ON o.EmployeeId = e.Id ");
        sqlBuilder.append(" LEFT JOIN OrderDetails od ON o.Code = od.OrderId ");

        sqlBuilder.append(" GROUP BY o.Id; ");

        String sql=sqlBuilder.toString();
        Cursor cursor = database.rawQuery(sql,
                null);
        ArrayList<OrdersViewer> datasets=new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ordercode = cursor.getString(1);
            String cust_name = cursor.getString(2);
            String employee_name = cursor.getString(3);
            String orderdate = cursor.getString(4);
            double grosstotal = cursor.getDouble(5);
            double totaldiscount = cursor.getDouble(6);
            double totalVAT = cursor.getDouble(7);
            double FinalTotal = cursor.getDouble(8);

            OrdersViewer ov=new OrdersViewer();
            ov.setId(id);
            ov.setCode(ordercode);
            ov.setCustomerName(cust_name);
            ov.setEmployeeName(employee_name);
            ov.setOrderDate(orderdate);
            ov.setGrossTotal(grosstotal);
            ov.setTotalDiscount(totaldiscount);
            ov.setTotalVAT(totalVAT);
            ov.setFinalTotal(FinalTotal);

            datasets.add(ov);
        }
        cursor.close();
        return datasets;
    }
}
