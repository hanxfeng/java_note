package hanxfeng.test;

import java.util.Scanner;

public class switch_tset2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入月份");
        int month =  sc.nextInt();

        switch (month) {
            case 1,2,3 -> {
                System.out.println("春季");
            }
            case 4,5,6 -> System.out.println("夏季");
            case 7,8,9 -> System.out.println("秋季");
            case 10,11,12 -> System.out.println("冬季");
            default -> System.out.println("输入错误");
        }
    }
}
