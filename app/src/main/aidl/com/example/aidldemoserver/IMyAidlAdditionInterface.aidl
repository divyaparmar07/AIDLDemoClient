// IMyAidlAdditionInterface.aidl
package com.example.aidldemoserver;

// Declare any non-default types here with import statements

interface IMyAidlAdditionInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int calculateAddition(int a,int b);
}