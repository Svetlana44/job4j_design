/*
* 100  10  12  1
* 100
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long sum = 0;
        while (scanner.hasNext()) {
            sum += scanner.nextInt();
        }
        System.out.println(sum);

    }
}
* */

package jquiz;

import java.util.Scanner;

public class Tinek {

    public static void main(String[] args) {
        int[] values = new int[]{0, 0, 0, 0};
        int index = 0;
        int result = values[1];

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            values[index] = scanner.nextInt();
            index++;
        }

        if (values[3] <= values[1]) {
            result = values[0];
        }
        if (values[3] > values[1]) {
            result = (values[3] - values[1]) * values[2] + values[0];
        }
        System.out.println(result);
    }
}
