package com.example.aidldemoclient;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aidldemoserver.IMyAidlAdditionInterface;

public class MainActivity extends AppCompatActivity {
    IMyAidlAdditionInterface iMyAidlAdditionInterface;
    private static final String TAG = "AIDLDemoClient";

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlAdditionInterface = IMyAidlAdditionInterface.Stub.asInterface(iBinder);
            Toast.makeText(MainActivity.this, "Service connected", Toast.LENGTH_LONG)
                    .show();
            Log.d("Service", "Service is connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("Service", "Service is disconnected");
            Toast.makeText(MainActivity.this, "Service disconnected", Toast.LENGTH_LONG)
                    .show();
        }
    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent("com.example.service.AIDL");
        Intent intent = new Intent("com.intent.action.AIDLAdditionService");
        intent.setPackage("com.example.aidldemoserver");
        bindService(intent, connection, BIND_AUTO_CREATE);

        EditText n1 = findViewById(R.id.editTextNumber1);
        EditText n2 = findViewById(R.id.editTextNumber2);
        TextView textView = findViewById(R.id.textViewAnswer);

        Button button = findViewById(R.id.buttonSubmitAdd);
        button.setOnClickListener(view -> {
            int answer = 0;
            int num1 = Integer.parseInt(n1.getText().toString());
            int num2 = Integer.parseInt(n2.getText().toString());

            try {

                if (iMyAidlAdditionInterface != null) {
                    Log.d("variable","Go to try block");
//                Log.d("result","Addition is "+iMyAidlAdditionInterface.calculateAddition(num1,num2));
                    answer = iMyAidlAdditionInterface.calculateAddition(num1, num2);
                    Log.d("variable",""+answer);
                } else {
                    Log.d(TAG, "iMyAidlAdditionInterface null");
                }

            } catch (RemoteException e) {
                Log.d("variable","Go to catch block");
                Log.d(TAG, "onClick failed with: " + e);
                throw new RuntimeException(e);
            }
            textView.setText(Integer.toString(answer));
        });

    }

}