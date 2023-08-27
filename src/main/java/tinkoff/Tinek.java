/*
*Задача C. Лифты и переговорки
Найдем максимум и минимум во входных данных.
Мы знаем, что ответ не меньше чем max − min. Если нельзя успеть пройти всех сотрудников
за это время, то это значит, что сотрудник успеет уйти. Тогда выгоднее всего сразу доехать до его
этажа, а потом сделать одну из двух операций — либо подняться на самый верх, а потом спуститься
вниз, либо спуститься на самый низ, а потом подняться наверх. Если уезжающий сотрудник был
на этаже x, то получившимися формулами будет max − min + max − x и max − min + x − min
соответственно, осталось только найти минимум из этих двух величин
* */
package tinkoff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Tinek {
    public static Map<Integer, int[]> values = new HashMap<>();

    static Map<Integer, int[]> scanerIn() {
        /*кол-во стррудников  время уйти */
        /*номера этажей*/
        /*номер сотрудника*/

        Scanner scanner = new Scanner(System.in);

        for (int index = 1; index < 4; index++) {
            if (scanner.hasNextLine()) {
                String[] arrStr = scanner.nextLine().split(" ");
                int[] arrInt = Arrays.stream(arrStr).mapToInt(s -> Integer.parseInt(s)).toArray();


                if (index % 3 == 1) {
                    values.put(1, arrInt);
                }
                if (index % 3 == 2) {
                    values.put(2, arrInt);
                }
                if (index % 3 == 0) {
                    values.put(3, arrInt);
                }
            }
        }

        return values;
    }


    static int bisness() {
        int result = 0;

        int employeeNumber = values.get(3)[0];
        int countYmpl = values.get(1)[0];
        int time = values.get(1)[1];

        int[] etgi = values.get(2);
        int etgiMax = etgi[etgi.length - 1];
        int etgiMin = etgi[0];

        int f1 = etgiMax - etgiMin + etgiMax - employeeNumber;
        int f2 = etgiMax - etgiMin + employeeNumber - etgiMin;



        if (etgiMax == time) {
            result = time;
        } else {
            result = Math.min(f1, f2);
        }

        return result;
    }

    public static void main(String[] args) {

        values = scanerIn();

        System.out.println(bisness());
    }
}
