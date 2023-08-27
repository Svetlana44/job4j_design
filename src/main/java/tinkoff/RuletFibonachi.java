/*Задача B. Рулет
Пусть F(n) — число разрезов для числа n. F(1) = 0.
Заметим, что F(2k
) = k, потому что можно k раз совмещать все рулеты и резать их пополам.
Также заметим, что F(n) 6 F(n + 1), потому что разрезание рулета на n частей —подзадача от
разрезания рулета на n + 1 часть. Кроме того, заметим, что за k разрезов можно получить не
больше, чем 2
k кусков.
Совместив полученные знания, получаем, что для n ∈ (2k−1
; 2k
] ответом будет число k. Значит, нужно найти старший бит числа n, что можно сделать с помощью цикла, в котором n будет
итеративно делиться на два. Число итераций и будет ответом.*/

package tinkoff;

import java.util.Scanner;

public class RuletFibonachi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int res = 0;
        while (n != 1) {
            n >>= 1;
            res++;
        }
        System.out.println(res);


//        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//        int e = 0;
//        int p = 1;
//
//        while (p < n) {
//            e++;
//            p *= 2;
//
//        }
//        System.out.println(e);




    }
}
