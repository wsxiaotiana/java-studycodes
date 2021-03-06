package yxxy.c_019;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description: 使用wait notify代替t2的死循环。此例子是container3的改正版。
 * 注意：wait会释放锁，notify不会释放锁。
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束*
 * 分析下面这个程序，能完成这个功能吗？
 * @author: yanxiaotian
 * @create: 2020-12-01 17:20
 **/
public class Container4 {

    volatile  List list=new LinkedList<>();

    void add(Object o){
        list.add(o);
    }

    int get(){
        return list.size();
    }

    public static void main(String[] args) {
        Container4 c=new Container4();
        final Object lock=new Object();
        new Thread(()->{
            synchronized (lock){
                System.out.println("t2 start");
                if(c.get()!=5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //通知t1继续执行
                lock.notify();
                //t2不用再调用wait释放锁，因为t2执行完以后就会释放锁，在执行完之前需要调用notify通知t1
                /*try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
            System.out.println("t2 end");
        },"t2").start();
        new Thread(()->{
            synchronized (lock){
                for(int i=0; i<10; i++){
                    c.add(new Object());
                    System.out.println("add "+i);
                    if(c.get()==5){
                        lock.notify();
                        //调用wait方法，释放锁
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t1").start();
    }
}
