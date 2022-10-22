package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (; index < data.length; index++) {
            if (data[index] % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

}
/*метод next в случае отсутствия элементов к возврату генерирует NoSuchElementException.
метод next должен возвращать верные значения вне зависимости от того, вызвал ли перед этим программист метод hasNext.
 Аналогично для hasNext. Результат работы ваших методов не должен зависеть от последовательности, в которой программист вызывает методы,
  т.е. не полагайтесь на то, что программист будет вызывать методы именно в том порядке в котором вы ожидаете.
не допускайте дублирования кода. Если вы видите, что методы next и hasNext имеют одинаковый код, то выносите этот код в отдельный метод и делайте уже его вызов.
не используйте эксепшены для управления логикой вашей программы.
 Они созданы для обработки критических ситуаций + очень дороги в создании по сравнению с обычными объектами в Java.
не оставляйте пустых методов в коде. Обратите внимание, что метод remove объявлен как дефолтный
 - это значит, что нет необходимости его реализовывать пустым, если вы не собираетесь переопределять его поведение.
  В коде не должно быть пустых методов, если ваша программа не поддерживает какой-либо функционал,
   задекларированный в интерфейсе - прокидывайте UnsupportedOperationException.*/