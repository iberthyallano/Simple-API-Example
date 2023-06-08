package com.iberthy.backend.util;

public class CommonMethods {

    public static String getNameFunction(){
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return stackTrace[1].getMethodName();
    }

}
