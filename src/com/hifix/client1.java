package com.hifix;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

public class client1 {
    public static void main(String[] args) throws IOException {

        suminterface sumser= (suminterface) serviceproxy.getproxy(suminterface.class);
        System.out.println(sumser.sum(10,210));


    }
}
