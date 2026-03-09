package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("开始");
        method1();           // 第1个调用
        System.out.println("结束");
    }

    static void method1() {
        System.out.println("进入method1");
        method2();           // 第2个调用
        System.out.println("退出method1");
    }

    static void method2() {
        System.out.println("进入method2");
        System.out.println("退出method2");  // 最后调用，但最先结束！
    }
}