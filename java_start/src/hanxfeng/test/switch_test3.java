package hanxfeng.test;

import java.util.Scanner;

public class switch_test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入月份");
        int month = sc.nextInt();

        String name = switch (month) {
            case 1, 2, 3 -> {
                yield "春季";
            }
            case 4, 5, 6 -> {
                yield "夏季";
            }
            case 7, 8, 9 -> {
                yield "秋季";
            }
            case 10, 11, 12 -> {

                yield "冬季";
            }
            default -> {
                yield "输入错误";
            }
        };
        System.out.println(name);
    }
}
