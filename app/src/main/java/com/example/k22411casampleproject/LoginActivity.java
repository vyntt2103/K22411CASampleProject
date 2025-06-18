package com.example.k22411casampleproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.connectors.EmployeeConnector;
import com.models.Employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName;
    EditText edtPassword;
    CheckBox chkSaveLogin;

    String DATABASE_NAME="SalesDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null; //3 dòng code này copy từ file MainActivity của thầy gửi trong dtb mẫu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        addViews();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        processCopy(); //copy file SQLite DB từ assets
    }

    //addViews() dùng để gán các EditText, Checkbox trong layout vào biến Java
    private void addViews() {  //gán UI vào biến, tạo liên kết giữa XML và code java
        edtUserName=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPassword);
        chkSaveLogin=findViewById(R.id.chkSaveLoginInfor);
    }

    //hàm xử lý nút Login
    public void do_login(View view) {
        EmployeeConnector ec=new EmployeeConnector(); //gọi EmployeeConnector để ktra tài khoản

        String usr=edtUserName.getText().toString();
        String pwd=edtPassword.getText().toString();

        Employee e_login=ec.login(this,usr,pwd);
        if (e_login!=null) //nếu đúng, tạo intent chuyển sang MainActivity
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Login failed! Check your account again!",Toast.LENGTH_LONG).show();
        }
    }

    //hàm xử lý nút Exit
    public void do_exit(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        //Thiết lập tiêu đề:
        Resources res=getResources();
        builder.setTitle(res.getText(R.string.title_confirm_exit_title));
        builder.setMessage(res.getText(R.string.title_confirm_exit_message));
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //Thiết lập tương tác người dùng:
        builder.setPositiveButton(res.getText(R.string.title_confirm_exit_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton(res.getText(R.string.title_confirm_exit_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                dialog.cancel();
            }
        });

        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void saveLoginInformation()
    {  //SharedPreferences --> ghi nhớ username và password mà người dùng đã nhập cho lần sau
        SharedPreferences preferences=getSharedPreferences("LOGIN_PREFERENCE",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        String usr=edtUserName.getText().toString();
        String pwd=edtPassword.getText().toString();
        boolean isSave=chkSaveLogin.isChecked();
        editor.putString("USER_NAME",usr);
        editor.putString("PASSWORD",pwd);
        editor.putBoolean("SAVED",isSave);
        editor.commit();
    }

    //onPause() --> lưu lại dữ liệu khi user sắp rời khỏi màn hình
    @Override
    protected void onPause() {
        super.onPause();
        saveLoginInformation();
    }

    //onResume() --> khôi phục dữ liệu (vào lại màn hình)
    @Override
    protected void onResume() {
        super.onResume();
        restoreLoginInformation();
    }

    public void restoreLoginInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_PREFERENCE",MODE_PRIVATE);
        String usr=preferences.getString("USER_NAME","");
        String pwd=preferences.getString("PASSWORD","");
        boolean isSave=preferences.getBoolean("SAVED",false);
        if(isSave)
        {
            edtUserName.setText(usr);
            edtPassword.setText(pwd);
            chkSaveLogin.setChecked(isSave);
        }
    }

    private void processCopy() {
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists())
        {
            try
            {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }  //copy từ file MainActivity của thầy gửi trong dtb mẫu

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }  //copy từ file MainActivity của thầy gửi trong dtb mẫu
    public void CopyDataBaseFromAsset()
    {
        try {
            InputStream myInput;

            myInput = getAssets().open(DATABASE_NAME);


            // Path to the just created empty db
            String outFileName = getDatabasePath();

            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }  //copy từ file MainActivity của thầy gửi trong dtb mẫu
}