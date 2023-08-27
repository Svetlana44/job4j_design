package tinkoff;

import java.util.Scanner;

public class Cells {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 1) {
            System.out.println(1);
        }
        if (n > 1) {
            System.out.println((n - 1) * 4);
        }
    }
}
