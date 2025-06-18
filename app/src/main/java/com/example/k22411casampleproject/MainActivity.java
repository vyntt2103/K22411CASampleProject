package com.example.k22411casampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView imgEmployee;
    TextView txtEmployee;
    ImageView imgEmployeeList;
    TextView txtEmployeeList;
    ImageView imgCustomer;
    TextView txtCustomer;
    ImageView imgProduct;
    TextView txtProduct;
    ImageView imgAdvancedProduct;
    TextView txtAdvancedProduct;
    ImageView imgPaymentMethod;
    TextView txtPaymentMethod;
    ImageView imgOrders;
    TextView txtOrders;
    ImageView imgContacts;
    TextView txtContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        addViews();
        addEvents();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addEvents() {
        imgEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi code mở màn hình quản trị nhân sự: chỉ có 2 dòng lệnh thui nhé
                openEmployeeManagementActivity();
            }
        });
        txtEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi code mở màn hình quản trị nhân sự:
                openEmployeeManagementActivity();
            }
        });

        imgEmployeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi code mở màn hình danh sach nhân sự:
                openEmployeeListManagementActivity();
            }
        });
        txtEmployeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi code mở màn hình danh sach nhân sự:
                openEmployeeListManagementActivity();
            }
        });

        imgCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi code mở màn hình danh sach khach hang:
                openCustomerManagementActivity();
            }
        });
        txtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi code mở màn hình danh sach khach hang:
                openCustomerManagementActivity();
            }
        });

        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi code mở màn hình danh sach khach hang:
                openProductManagementActivity();
            }
        });
        txtProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi code mở màn hình danh sach khach hang:
                openProductManagementActivity();
            }
        });

        imgAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity();
            }
        });
        txtAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity();
            }
        });

        imgPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });
        txtPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });

        imgOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersViewerActivity();
            }
        });
        txtOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersViewerActivity();
            }
        });

        imgContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephonyActivity();
            }
        });
        txtContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephonyActivity();
            }
        });
    }



    private void addViews() {
        imgEmployee=findViewById(R.id.imgEmployee);
        txtEmployee=findViewById(R.id.txtEmployee);

        imgEmployeeList=findViewById(R.id.imgEmployeeList);
        txtEmployeeList=findViewById(R.id.txtEmployeeList);

        imgCustomer=findViewById(R.id.imgCustomer);
        txtCustomer=findViewById(R.id.txtCustomer);

        imgProduct=findViewById(R.id.imgProduct);
        txtProduct=findViewById(R.id.txtProduct);

        imgAdvancedProduct=findViewById(R.id.imgAdvancedProduct);
        txtAdvancedProduct=findViewById(R.id.txtAdvancedProduct);

        imgPaymentMethod=findViewById(R.id.imgPaymentMethod);
        txtPaymentMethod=findViewById(R.id.txtPaymentMethod);

        imgOrders=findViewById(R.id.imgOrders);
        txtOrders=findViewById(R.id.txtOrders);

        imgContacts=findViewById(R.id.imgContacts);
        txtContacts=findViewById(R.id.txtContacts);

    }

    //Dùng Intent để chuyển giữa các màn hình, nhấn là mở Activity mới
    private void openEmployeeListManagementActivity() {
        Intent intent = new Intent(MainActivity.this, EmployeeListManagementActivity.class);
        startActivity(intent);
    }

    private void openEmployeeManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, EmployeeManagementActivity.class);
        startActivity(intent);
    }

    private void openCustomerManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, CustomerManagementActivity.class);
        startActivity(intent);
    }

    private void openProductManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, ProductManagementActivity.class);
        startActivity(intent);
    }

    private void openAdvancedProductManagementActivity() {
        Intent intent=new Intent(MainActivity.this, AdvancedProductManagementActivity.class);
        startActivity(intent);
    }

    private void openPaymentMethodActivity() {
        Intent intent=new Intent(MainActivity.this, PaymentMethodActivity.class);
        startActivity(intent);
    }

    private void openOrdersViewerActivity() {
        Intent intent=new Intent(MainActivity.this, OrdersViewerActivity.class);
        startActivity(intent);
    }

    private void openTelephonyActivity() {
        Intent intent=new Intent(MainActivity.this, TelephonyActivity.class);
        startActivity(intent);
    }

}