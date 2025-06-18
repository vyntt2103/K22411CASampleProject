package com.example.k22411casampleproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.utils.BMIStatus;
import com.utils.HealthCare;

//Activity này đóng vai trò là Controller,
// kết nối View (giao diện người dùng)
// với Model (BMIStatus thông qua HealthCare)
public class EmployeeHealthcareActivity extends AppCompatActivity {

    EditText edtHeight;
    EditText edtWeight;
    Button btnCalculate;
    Button btnClear;
    Button btnClose;
    TextView txtResult;
    View.OnClickListener myClick; //biến chứa đối tượng xử lý sự kiện dùng chung

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_healthcare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews(); //ánh xạ giao diện từ XML sang Java
        addEvents(); //gán xử lý sự kiện cho các nút
    }

    private void addEvents() {
        myClick=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xử lý sự kiện chi tiết trong này:
                if(v.equals(btnCalculate))
                {
                    //nút Calculate đang dùng sự kiện
                    double h=Double.parseDouble(edtHeight.getText().toString());
                    double w=Double.parseDouble(edtWeight.getText().toString());
                    BMIStatus bmiStatus= HealthCare.calculate_bmi(h,w);
                    txtResult.setText(bmiStatus.getBMI()+"=>"+bmiStatus.getDescription());

                }
                else if (v.equals(btnClear))
                {
                    //nút Clear đang dùng sự kiện
                    edtHeight.setText("");
                    edtWeight.setText("");
                    txtResult.setText("");
                    edtHeight.requestFocus();
                }
                else if (v.equals(btnClose))
                {
                    //nút Close đang dùng sự kiện
                    finish();
                }
            }
        };
        //gán chia sẻ chung sự kiện:
        btnCalculate.setOnClickListener(myClick);
        btnClear.setOnClickListener(myClick);
        btnClose.setOnClickListener(myClick);
    }

    private void addViews() {
        txtResult=findViewById(R.id.txtResult);
        edtHeight=findViewById(R.id.edtHeight);
        edtWeight=findViewById(R.id.edtWeight);
        btnCalculate=findViewById(R.id.btnCalculate);
        btnClear=findViewById(R.id.btnClear);
        btnClose=findViewById(R.id.btnClose);
    }
}