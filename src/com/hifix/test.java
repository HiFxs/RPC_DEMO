package com.hifix;

public class test {
    public static void main(String[] args) {

Thread thread1=new Thread(() -> {
    suminterface sumser= (suminterface) serviceproxy.getproxy(suminterface.class);
    System.out.println(sumser.sum(10,210));
});

Thread thread2=new Thread(() -> {
    uppercaseinterface upper= (uppercaseinterface) serviceproxy.getproxy(uppercaseinterface.class);
    System.out.println(upper.uppercase("acbd"));
});

    thread1.start();
    thread2.start();




    }
}
