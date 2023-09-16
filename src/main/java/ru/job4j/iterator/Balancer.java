package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int index = 0;
        while (source.hasNext()) {
            ArrayList<Integer> arrayList = nodes.get(index);
            arrayList.add(source.next());
            if (index < nodes.size() - 1) {
                index++;
            } else {
                index = 0;
            }
        }
    }
}