package jquiz;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Listerator {

    static public void main(String[] args) {
        List<String> list = new ArrayList<>();
/*        ListIterator listIterator = list.listIterator(); */
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        Stream.iterate(
                        list.listIterator(
                                list.size()), i -> i.hasPrevious(), i -> i)
                .forEach(i -> System.out.println(i.previous()));
    }

}
