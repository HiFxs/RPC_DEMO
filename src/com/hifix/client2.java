package com.hifix;

public class client2 {
    public static void main(String[] args) {
        uppercaseinterface upper= (uppercaseinterface) serviceproxy.getproxy(uppercaseinterface.class);
        System.out.println(upper.uppercase("acbd"));
    }
}
