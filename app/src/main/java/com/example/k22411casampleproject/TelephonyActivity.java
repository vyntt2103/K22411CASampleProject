package com.example.k22411casampleproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.TelephonyInforAdapter;
import com.models.TelephonyInfor;

public class TelephonyActivity extends AppCompatActivity {
    ListView lvTelephonyInfo;
    //ArrayAdapter<TelephonyInfor> adapter;
    TelephonyInforAdapter adapter;
    MenuItem menu_viettel;
    MenuItem menu_mobiphone;
    MenuItem menu_other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        readAllContacts();
    }

    private void readAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null,null);
        adapter.clear();
        while (cursor.moveToNext()){
            int nameIndex =cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex); //Get Name
            int phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds. Phone.NUMBER);
            String phone = cursor.getString(phoneIndex); //Get Phone Number

            TelephonyInfor ti=new TelephonyInfor();
            ti.setDisplayName(name);
            ti.setPhoneNumber(phone);
            adapter.add(ti);
        }
        cursor.close();

    }

    private void addViews() {
        lvTelephonyInfo=findViewById(R.id.lvTelephonyInfo);
        //adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapter=new TelephonyInforAdapter(this,R.layout.item_telephonyinfor);
        lvTelephonyInfo.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_telephony,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_viettel) {
            filterByNetwork("viettel");
        } else if (id == R.id.menu_mobifone) {
            filterByNetwork("mobifone");
        } else if (id == R.id.menu_other) {
            filterByNetwork("other");
        }
        return super.onOptionsItemSelected(item);
    }

    private void filterByNetwork(String networkType) {
        adapter.clear();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null,null);

        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex);
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String phoneRaw = cursor.getString(phoneIndex).replaceAll("\\s+", "");
            String phone = phoneRaw.replace("+84", "0");

            String prefix = phone.length() >= 3 ? phone.substring(0, 3) : "";

            boolean isMatch = false;
            switch (networkType) {
                case "viettel":
                    isMatch = prefix.matches("03[2-9]|086|096|097|098");
                    break;
                case "mobifone":
                    isMatch = prefix.matches("07[0|6-9]|089|090|093");
                    break;
                case "other":
                    isMatch = !(prefix.matches("03[2-9]|086|096|097|098") ||
                            prefix.matches("07[0|6-9]|089|090|093"));
                    break;
            }

            if (isMatch) {
                TelephonyInfor ti = new TelephonyInfor();
                ti.setDisplayName(name);
                ti.setPhoneNumber(phone);
                adapter.add(ti);
            }
        }
        cursor.close();
    }

    public  void sendSms(TelephonyInfor ti, String content)
    {
        final SmsManager sms = SmsManager.getDefault();

        sms.sendTextMessage( ti.getPhoneNumber(), null, content, null, null);
        Toast.makeText(TelephonyActivity.this, "Đã gửi tin nhắn tới "+ti.getPhoneNumber(),
                Toast.LENGTH_LONG).show();
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public  void sendSmsPendingIntent(TelephonyInfor ti, String content)
    {
        //lấy mặc định SmsManager
        final SmsManager sms = SmsManager.getDefault();
        Intent msgSent = new Intent("ACTION_MSG_SENT");
//Khai báo pendingintent để kiểm tra kết quả
        final PendingIntent pendingMsgSent =
                PendingIntent.getBroadcast(this, 0, msgSent, PendingIntent.FLAG_IMMUTABLE);
        registerReceiver(new BroadcastReceiver() {
            @SuppressLint("UnspecifiedRegisterReceiverFlag")
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg="Send OK";
                if (result != Activity.RESULT_OK) {
                    msg="Send failed";
                }
                Toast.makeText(TelephonyActivity.this, msg,
                        Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter("ACTION_MSG_SENT"));
//Gọi hàm gửi tin nhắn đi
        sms.sendTextMessage(ti.getPhoneNumber(), null, content,
                pendingMsgSent, null);
    }

    public void callDirect(TelephonyInfor ti)
    {
        Uri uri=Uri.parse("tel:"+ti.getPhoneNumber());
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    public void callDialup(TelephonyInfor ti)
    {
        Uri uri=Uri.parse("tel:"+ti.getPhoneNumber());
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }
}