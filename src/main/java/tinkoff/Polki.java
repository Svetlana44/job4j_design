package tinkoff;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Polki {

    static Map<Integer, Integer> polki = new HashMap<>();
    static int m = 0;
    static StringBuilder events = new StringBuilder();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            m = scanner.nextInt();
            scanner.nextLine();
        }

        for (int i = 1; i < m + 1; i++) {
            polki.put(i, 0);
        }

        while (scanner.hasNextLine()) {
            String event = scanner.nextLine();
            if (event == "") {
                break;
            }
            if (event.charAt(0) == '+') {
                for (int j = 1; j < m + 1; j++) {
                    if (polki.get(j) == 0) {
                        polki.put(j, Integer.parseInt(event.substring(1)));
                        events.append(j);
                        break;
                    }
                }
            }
            if (event.charAt(0) == '-') {
                for (int j = 1; j < m + 1; j++) {
                    if (polki.get(j) == Integer.parseInt(event.substring(1))) {
                        polki.put(j, 0);
                        break;
                    }
                }
            }
        }
        for (char c : events.toString().toCharArray()) {
            System.out.println(c);
        }
    }
}
