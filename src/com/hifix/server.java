package com.hifix;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private static boolean running = true;
    public static void main(String[] args) throws Exception {
        //建立服务端Socket
        ServerSocket ss = new ServerSocket(8888);
        System.out.println("---等待客户端连接---");
        //不断监听，处理客户端请求
        while (running) {

            Socket socket = ss.accept();
            process(socket);
            socket.close();
        }
        ss.close();
    }

    private static  void process(Socket socket) throws Exception {
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        ObjectInputStream ois=new ObjectInputStream(is);

        String clazzname=ois.readUTF();
        String methodname=ois.readUTF();
        Class[] parameterTypes=(Class[]) ois.readObject();
        Object[] args=(Object[]) ois.readObject();
        System.out.println("---客户端请求"+clazzname+"服务！---");

        Class clazz=null;
//从服务注册表里找到具体的服务
        if(clazzname.equals("com.hifix.uppercaseinterface")) {
            clazz = upperimpl.class;
        }else{
            clazz=sumimpl.class;
        }
        System.out.println("---服务器注册"+clazz.getName()+"服务！---");

        Method method=clazz.getMethod(methodname,parameterTypes);


        Object o=method.invoke(clazz.newInstance(),args);

        ObjectOutputStream oos=new ObjectOutputStream(os);
        //服务器返回结果
        System.out.println("服务"+clazzname+"处理完毕，正在返回处理结果！");
        oos.writeObject(o);
        oos.flush();
        os.close();
        is.close();
    }


}
