package jquiz;

public class Test {
    public static void main(String[] args) {
        String str = "STR";
        System.out.print(str.substring(0, str.length()));
        System.out.print(str.substring(0, str.length()).equals(str));
        System.out.print(" -- ");
        System.out.println(str.toUpperCase().equals(str));
    }
}
