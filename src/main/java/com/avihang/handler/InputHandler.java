package com.avihang.handler;

public class InputHandler {

    public static boolean checkExit(String input) {
        if (input.equals("00")) {
            System.out.println(" خروج از برنامه...");
            return true;
        }
        return false;
    }

    public static boolean checkBack(String input) {
        return input.equals("0");
    }
}