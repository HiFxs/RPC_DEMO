package com.hifix;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class serviceproxy {
    public static  Object getproxy(Class clazzname) {
        InvocationHandler h = new InvocationHandler() {
            @Override
            public  Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1", 8888);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //拿到服务名
                String clazzName = clazzname.getName();
                //拿到远程调用的方法名
                String methodName = method.getName();
                //拿到远程调用方法的参数类型
                Class[] parametersTypes = method.getParameterTypes();
                //把服务名写入服务器
                oos.writeUTF(clazzName);
                //把方法名传递给服务端
                oos.writeUTF(methodName);
                //把方法参数类型传递给服务端
                oos.writeObject(parametersTypes);
                //把方法参数传递给服务端
                oos.writeObject(args);
                oos.flush();
                //获取远程调用的返回结果
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object o =  ois.readObject();

                ois.close();
                oos.close();
                socket.close();
                return o;
            }
        };
        Object o= Proxy.newProxyInstance(clazzname.getClassLoader(),new Class[]{clazzname},h);
        return o;
    }


}
