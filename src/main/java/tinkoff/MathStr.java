/*
Ввод:
1+(2*2-3).
вывод:
2
Ввод:
1+a+1.
вывод:
WRONG

*/

package tinkoff;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathStr {

    public static boolean validation(String inStr) {
        boolean result = true;
        String regex = "[^0-9\\+\\-\\*\\/\\(\\)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inStr);

        String regexSign = "(?<!\\d)[+\\-*/](?!\\d)";
        Pattern patternSign = Pattern.compile(regexSign);
        Matcher matcherSign = patternSign.matcher(inStr);

        if (matcher.find()) {
            result = false;
        }

        if (matcherSign.find()) {
            result = false;
        }
        int scobki = 0;
        for (int i = 0; i < inStr.length(); i++) {
            if (scobki >= 0 && inStr.charAt(i) == '(') {
                scobki += 1;
            }
            if (scobki > 0 && inStr.charAt(i) == ')') {
                scobki -= 1;
            } else if (scobki < 0 && inStr.charAt(i) == ')') {
                result = false;
                return result;
            }
        }
        if (scobki != 0) {
            result = false;
            return result;
        }

        return result;
    }

    static public void count(String str) {
        Scanner sc = new Scanner(str);


    }

    public static void main(String[] args) {

        String inStr = "";
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            inStr = scanner.nextLine();
        }
        if (validation(inStr.substring(0, inStr.length() - 1))) {
            count(inStr.substring(0, inStr.length() - 1));
        } else {
            System.out.println("WRONG");
        }
    }
}
