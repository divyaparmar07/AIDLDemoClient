// IMyAidlAdditionInterface.aidl
package com.example.aidldemoserver;
import com.example.aidldemoserver.Student;
import com.example.aidldemoserver.EnumStudent;
import com.example.aidldemoserver.Result;
import com.example.aidldemoserver.Marks;

interface IMyAidlAdditionInterface {
    int calculateAddition(int a,int b);
    int calculateSubtraction(int a,int b);
    int getRandomNumber();
    int sumOfArray(in int[] intArray);
    String concateString(in String[] strArray);
    boolean basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString);
    String getStudentDetails(in Student student);
    Student createStudent();
    String getStudentDetails1();
    Student createStudent1(in Student student);
    Result getResult(in Student student);
    Student createStudentFromBundle(in Bundle bundle);
    String sendStudents(in List<Student> listOfStudents);
    int sendStudentEnum(in EnumStudent enumStudent);
}