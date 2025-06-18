package com.example.k22411casampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.PaymentMethodAdapter;
import com.example.connectors.PaymentMethodConnector;
import com.example.connectors.SQLiteConnector;
import com.models.ListPaymentMethod;
import com.models.PaymentMethod;

import java.util.ArrayList;

public class PaymentMethodActivity extends AppCompatActivity {

    ListView lvPaymentMethod;
    PaymentMethodAdapter adapter;
    ListPaymentMethod lpm;

    PaymentMethodConnector pmc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvPaymentMethod=findViewById(R.id.lvPaymentMethod);
        adapter=new PaymentMethodAdapter(
                PaymentMethodActivity.this,
                R.layout.item_payment_method);
        lvPaymentMethod.setAdapter(adapter);
        /*lpm=new ListPaymentMethod(); //"ctrl+shift+/"
        lpm.gen_payment_method();
        adapter.addAll(lpm.getPaymentMethods());*/

        pmc = new PaymentMethodConnector();
        SQLiteConnector connector = new SQLiteConnector(this);
        ArrayList<PaymentMethod>datasets=pmc.getAllPaymentMethods(connector.openDatabase());
        adapter.addAll(datasets);
    }
}