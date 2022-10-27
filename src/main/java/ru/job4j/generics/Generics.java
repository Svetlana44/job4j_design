package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

/*        gen.printBoundedWildCard(first);
<Animal> cannot be converted to <? extends Predator>, because Predator extends Animal
* */
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);

/*        <Tiger> cannot be converted to <? super Predator>, because Tiger extends Predator
        gen.printLowerBoundedWildCard(third);
*/
    }

    public void printObject(Collection<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}
/*Он содержит ошибки компиляции. Вам необходимо их поправить: закомментировать многострочным комментарием строки, которые их вызывают,
 а также правильно применить wildcards. При этом:

1-ый метод - работает без ограничений, т.е. в него можно передавать коллекцию, которая хранит любые типы.

2-ой метод - должен иметь ограничение сверху и ограничиваться классом Predator.

3-ий метод - должен иметь ограничение снизу и ограничиваться классом Predator.*/