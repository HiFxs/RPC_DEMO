package com.hifix;

public class upperimpl implements uppercaseinterface {
    @Override
    public String uppercase(String str) {
        return str.toUpperCase();
    }
}
