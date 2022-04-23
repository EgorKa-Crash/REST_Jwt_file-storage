package com.example.demoSpringSecurity.exception;

import java.io.Serializable;


public class ErrorObj implements Serializable {
    private static boolean isAble;
    private static String messange;

    public ErrorObj() {
    }

    public ErrorObj(boolean isAble, String messange) {
        this.isAble = isAble;
        this.messange = messange;
    }

    public static boolean isIsAble() {
        return isAble;
    }

    public static void setIsAble(boolean isAble) {
        ErrorObj.isAble = isAble;
    }

    public static String getMessange() {
        return messange;
    }

    public static void setMessange(String messange) {
        ErrorObj.messange = messange;
    }
}
