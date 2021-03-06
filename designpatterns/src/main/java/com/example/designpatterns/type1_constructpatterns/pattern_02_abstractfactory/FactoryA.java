package com.example.designpatterns.type1_constructpatterns.pattern_02_abstractfactory;

/**
 * @description: 工厂实现类
 * @author: yanxiaotian
 * @create: 2020-11-18 10:10
 **/
public class FactoryA implements IFactory {
    @Override
    public IProduct1 getProduct1() {
        return new Product1A();
    }

    @Override
    public IProduct2 getProduct2() {
        return new Product2A();
    }
}
