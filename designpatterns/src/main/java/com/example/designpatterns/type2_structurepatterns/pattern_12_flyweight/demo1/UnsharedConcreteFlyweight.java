package com.example.designpatterns.type2_structurepatterns.pattern_12_flyweight.demo1;

/**
 * @description: 不共享实现类
 * @author: yanxiaotian
 * @create: 2020-11-19 10:19
 **/
public class UnsharedConcreteFlyweight extends Flyweight {
    public UnsharedConcreteFlyweight(String extrinsic) {
        super(extrinsic);
    }

    @Override
    public void operate(int extrinsic) {
        System.out.println("不共享的具体Flyweight:"+extrinsic);
    }
}
