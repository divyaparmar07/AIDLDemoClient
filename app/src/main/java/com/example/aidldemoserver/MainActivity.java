package com.example.aidldemoserver;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aidldemoclient.R;

import java.util.ArrayList;
import java.util.List;

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

        Intent intent = new Intent("com.intent.action.AIDLAdditionService");
        intent.setPackage("com.example.aidldemoserver");
        bindService(intent, connection, BIND_AUTO_CREATE);

        EditText n1 = findViewById(R.id.editTextNumber1);
        EditText n2 = findViewById(R.id.editTextNumber2);
        TextView textView = findViewById(R.id.textViewAnswer);

        Button addition = findViewById(R.id.buttonSubmitAdd);
        addition.setOnClickListener(view -> {

            int answer = 0;
            int num1 = Integer.parseInt(n1.getText().toString());
            int num2 = Integer.parseInt(n2.getText().toString());

            try {

                if (iMyAidlAdditionInterface != null) {
                    Log.d("variable", "Go to try block");
                    answer = iMyAidlAdditionInterface.calculateAddition(num1, num2);
                    Log.d("variable", "" + answer);
                } else {
                    Log.d(TAG, "iMyAidlAdditionInterface null");
                }

            } catch (RemoteException e) {
                Log.d("variable", "Go to catch block");
                Log.d(TAG, "onClick failed with: " + e);
                throw new RuntimeException(e);
            }
            textView.setText("Addition is: " + answer);
        });

        Button subtraction = findViewById(R.id.buttonSubmitSub);
        subtraction.setOnClickListener(view -> {
            int answer = 0;
            int num1 = Integer.parseInt(n1.getText().toString());
            int num2 = Integer.parseInt(n2.getText().toString());

            try {

                if (iMyAidlAdditionInterface != null) {
                    Log.d("variable", "Go to try block");
                    answer = iMyAidlAdditionInterface.calculateSubtraction(num1, num2);
                    Log.d("variable", "" + answer);
                } else {
                    Log.d(TAG, "iMyAidlAdditionInterface null");
                }

            } catch (RemoteException e) {
                Log.d("variable", "Go to catch block");
                Log.d(TAG, "onClick failed with: " + e);
                throw new RuntimeException(e);
            }
            textView.setText("Subtraction is: " + answer);
        });

        Button randomNo = findViewById(R.id.buttonRandomNumber);
        randomNo.setOnClickListener(view -> {
            int answer = 0;
            try {
                if (iMyAidlAdditionInterface != null) {
                    Log.d("variable", "Go to try block");
                    answer = iMyAidlAdditionInterface.getRandomNumber();
                    Log.d("variable", "" + answer);
                } else {
                    Log.d(TAG, "iMyAidlAdditionInterface null");
                }

            } catch (RemoteException e) {
                Log.d("variable", "Go to catch block");
                Log.d(TAG, "onClick failed with: " + e);
                throw new RuntimeException(e);
            }
            textView.setText("Random Number is: " + answer);
        });

        Button sumArray = findViewById(R.id.buttonSumOfArray);
        sumArray.setOnClickListener(view -> {
            int answer = 0;
            try {
                if (iMyAidlAdditionInterface != null) {
                    answer = iMyAidlAdditionInterface.sumOfArray(new int[]{10, 20, 30, 40, 50});
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            textView.setText("Sum Of Array is: " + answer);
        });

        Button concatStr = findViewById(R.id.buttonConcateString);
        concatStr.setOnClickListener(view -> {
            String answer = "";
            try {
                if (iMyAidlAdditionInterface != null) {
                    answer = iMyAidlAdditionInterface.concateString(new String[]{"Divya", "Parmar", "Riddhi", "Parmar"});
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            textView.setText("Concat Array is: " + answer);
        });

        Button basicType = findViewById(R.id.buttonBasicTypes);
        basicType.setOnClickListener(view -> {
            boolean answer = false;
            try {
                if (iMyAidlAdditionInterface != null) {
                    answer = iMyAidlAdditionInterface.basicTypes(10, 10L, true, 10f, 10d, "Divya");
/*                    answer = iMyAidlAdditionInterface.basicTypes(1000000000, 100000000L, true, 10000000000000000000000000000000000f, 100000000000000000000000000000000d, "Divya");
/                     answer = iMyAidlAdditionInterface.basicTypes(0,0,false,0,0,null);*/
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            textView.setText("Type is: " + answer);
        });

        Button createStudent = findViewById(R.id.buttonCreateStudent);
        createStudent.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                try {
                    if (iMyAidlAdditionInterface != null) {
                        Student interfaceStudent = iMyAidlAdditionInterface.createStudent();
                        List<Marks> marksList = interfaceStudent.getMarks();
                        StringBuilder marksStringBuilder = new StringBuilder();

                        for (Marks marks : marksList) {
                            marksStringBuilder.append(marks.getSubjectName()).append(": ").append(marks.getMark()).append(" ");
                        }

                        String allMarks = marksStringBuilder.toString();

                        textView.setText("First Name : " + interfaceStudent.getFname() + ", Last Name : " + interfaceStudent.getLname() + ", RollNo : " + interfaceStudent.getRollno() + ", Address : " + interfaceStudent.getAddress() + ", Marks : " + allMarks);
                    } else {
                        Log.d("TAG", "anInterface null");
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button getStudentDetails = findViewById(R.id.buttonGetStudentDetails);
        getStudentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ans = null;
                try {
                    if (iMyAidlAdditionInterface != null) {
                        List<Marks> marksList = new ArrayList<>();
                        marksList.add(new Marks("Maths", 95));
                        marksList.add(new Marks("Science", 85));
                        marksList.add(new Marks("English", 90));
                        Log.d("Data", marksList.toString());
                        ans = iMyAidlAdditionInterface.getStudentDetails(new Student("Div", "Parmar", 123, 123456789, "Ahmedabad,Gujarat", marksList));
                    } else {
                        Log.d("TAG", "anInterface null");
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                textView.setText(ans);
            }
        });

        Button getResult = findViewById(R.id.buttonGetResult);
        getResult.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                List<Marks> marksList = new ArrayList<>();
                marksList.add(new Marks("Maths", 25));
                marksList.add(new Marks("Science", 30));
                marksList.add(new Marks("English", 25));
                try {
                    if (iMyAidlAdditionInterface != null) {
                        Result result = iMyAidlAdditionInterface.getResult(new Student("Div", "Parmar", 123, 123456789, "Ahmedabad,Gujarat", marksList));
                        textView.setText("FirstName: " + result.getFname() + ", LastName: " + result.getLname() + ", RollNo: " + result.getRollno() + ", Percentage: " + result.getPercentage() + ", Result: " + result.isPass());
                    } else {
                        Log.d("TAG", "anInterface null");
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        List<Marks> marksList = new ArrayList<>();
        marksList.add(new Marks("Maths", 90));
        marksList.add(new Marks("Science", 85));
        marksList.add(new Marks("English", 95));

        Button createStudentFromBundle = findViewById(R.id.buttonCreateStudentFromBundle);
        createStudentFromBundle.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                List<Marks> marksList = new ArrayList<>();
                marksList.add(new Marks("Maths", 90));
                marksList.add(new Marks("Science", 85));
                marksList.add(new Marks("English", 95));

                Student student = new Student("Ridhi", "Patel", 123124124, 1231231231, "Rajkot", marksList);

                Bundle studentData = new Bundle();
                studentData.setClassLoader(Student.class.getClassLoader());
                studentData.putParcelable("Student", student);

                try {
                    Log.d("TAG", "Go to try block");
                    if (iMyAidlAdditionInterface != null) {
                        Log.d("TAG", "Go to if block");
                        Student result = iMyAidlAdditionInterface.createStudentFromBundle(studentData);
                        List<Marks> marksList1 = result.getMarks();
                        StringBuilder marksStringBuilder = new StringBuilder();
                        for (Marks marks : marksList1) {
                            marksStringBuilder.append(marks.getSubjectName()).append(": ").append(marks.getMark()).append(" ");
                        }

                        String allMarks = marksStringBuilder.toString();

                        textView.setText("First Name : " + result.getFname() + ", Last Name : " + result.getLname() + ", RollNo : " + result.getRollno() + ", Address : " + result.getAddress() + ", Marks : [ " + allMarks + " ]");

                    } else {
                        Log.d("TAG", "Go to else block");
                        Log.d("TAG", "anInterface null");
                        Toast.makeText(getApplicationContext(), "Interface Null", Toast.LENGTH_SHORT).show();
                    }
                } catch (RemoteException e) {
                    Log.d("TAG", "Go to catch block");
                    Toast.makeText(getApplicationContext(), "Crash", Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }

            }

        });

        Button sendStudents = findViewById(R.id.buttonSendStudents);
        sendStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Student> students = new ArrayList<>();
                Student student = new Student("Divya", "Parmar", 1234, 1234567890, "Rajkot,Gujarat", marksList);
                Student student2 = new Student("Div", "Parmar", 123, 123456789, "Ahmedabad,Gujarat", marksList);
                Student student3 = new Student("Riddhi", "Parmar", 12, 123454321, "Rajkot,Gujarat", marksList);

                students.add(student);
                students.add(student2);
                students.add(student3);

                try {
                    String ans = iMyAidlAdditionInterface.sendStudents(students);
                    textView.setText(ans);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button sendStudentEnum = findViewById(R.id.buttonSendStudentEnum);
        sendStudentEnum.setOnClickListener(view -> {
            try {
                int ans = iMyAidlAdditionInterface.sendStudentEnum(EnumStudent.PARTHIV);
                Log.d("ans", "" + ans);
                textView.setText(String.valueOf(ans));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

}