package ru.job4j.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleMapTest {

    private final SimpleMap<Integer, String> map = new SimpleMap<>();

    @BeforeEach
    void setUp() {
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
    }

    @Test
    void checkSimpleIterator() {
        final SimpleMap<Integer, String> mapSimple = new SimpleMap<>();
        mapSimple.put(1, "1");
        mapSimple.put(2, "2");
        mapSimple.put(3, "3");
        mapSimple.put(4, "4");
        assertThat(mapSimple).hasSize(4)
                .contains(1, 2, 3, 4);
        System.out.println(mapSimple);
    }

    @Test
    void whenCheckGet() {
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(4);
        assertThat(map.get(5)).isNull();
        assertThat(map).hasSize(4);
    }

    @Test
    void whenCheckPut() {
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map).hasSize(5);
        assertThat(map.put(8, "8")).isFalse();
        assertThat(map).hasSize(5);
        assertThat(map.put(1, "10")).isFalse();
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemove() {
        assertThat(map.remove(2)).isTrue();
        assertThat(map).hasSize(3);
        assertThat(map.remove(2)).isFalse();
        assertThat(map).hasSize(3);
        assertThat(map.remove(5)).isFalse();
        assertThat(map).hasSize(3);
    }

    @Test
    void whenCheckIterator() {
        map.remove(2);
        map.remove(3);
        map.put(null, "0000");
        System.out.println(map);
        Iterator<Integer> it = map.iterator();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isNull();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.hasNext()).isFalse();
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenConcurrentIteratorAdd() {
        Iterator<Integer> it = map.iterator();
        map.put(0, "0");
        assertThatThrownBy(it::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenConcurrentIteratorRemove() {
        Iterator<Integer> it = map.iterator();
        map.remove(1);
        assertThatThrownBy(it::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenNotConcurrentIteratorGet() {
        Iterator<Integer> it = map.iterator();
        map.get(1);
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenMapExpand() {
        map.put(null, "0000");
        assertThat(map.put(15, "15")).isTrue();
        assertThat(map).hasSize(6);
        assertThat(map.put(8, "8")).isTrue();
        assertThat(map.put(16, "16")).isFalse();
        assertThat(map.get(4)).isEqualTo("4");
        assertThat(map.get(8)).isEqualTo("8");
        assertThat(map.get(15)).isEqualTo("15");
        assertThat(map).hasSize(7).contains(null, 1, 2, 3, 4, 8, 15);
    }

    @Test
    void whenCheckPutKeyNull() {
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckGetKeyNull() {
        map.put(null, "0000");
        assertThat(map.get(null)).isEqualTo("0000");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemoveKeyNull() {
        map.put(null, "0000");
        assertThat(map.remove(null)).isTrue();
        assertThat(map).hasSize(4);
    }


    @Test
    void whenCheckPutZeroAndNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.put(0, "0")).isFalse();
    }

    @Test
    void whenCheckPutNullAndZero() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.put(null, "0000")).isFalse();
    }

    @Test
    void whenCheckGetZeroAndNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        System.out.println(map);
        assertThat(map.get(0)).isNull();
    }

    @Test
    void whenCheckGetNullAndZero() {
        SimpleMap<Integer, String> map2 = new SimpleMap<>();
        System.out.println(map2);
        assertThat(map2.put(0, "0")).isTrue();
        System.out.println(map2);
        assertThat(map2.get(null)).isNull();
    }

    @Test
    void whenPutNullKeyAgane() {
        SimpleMap<Integer, String> mapHash = new SimpleMap<>();
        assertThat(mapHash.put(null, "it's null")).isTrue();
        assertThat(mapHash.put(null, "it's null")).isFalse();
    }

    @Test
    void whenPutKeyEnough() {
        SimpleMap<Integer, String> mapHash = new SimpleMap<>();
        assertThat(mapHash.put(null, "it's null")).isTrue();
        assertThat(mapHash.put(1, "One")).isTrue();
        assertThat(mapHash.put(2, "Two")).isTrue();
        assertThat(mapHash.put(3, "Three")).isTrue();
        assertThat(mapHash.put(4, "Four")).isTrue();
        assertThat(mapHash.put(5, "Five")).isTrue();
        assertThat(mapHash.put(6, "Six")).isTrue();
        assertThat(mapHash.put(7, "Seven")).isTrue();
        assertThat(mapHash.put(8, "Eight")).isTrue();
        assertThat(mapHash.toString())
                .isEqualTo("SimpleMap{[MapEntry{key=null, value=it's null}, "
                        + "MapEntry{key=1, value=One}, MapEntry{key=2, value=Two}, "
                        + "MapEntry{key=3, value=Three}, MapEntry{key=4, value=Four}, "
                        + "MapEntry{key=5, value=Five}, MapEntry{key=6, value=Six}, "
                        + "MapEntry{key=7, value=Seven}, MapEntry{key=8, value=Eight}, "
                        + "null, null, null, null, null, null, null]}");
    }

    @Test
    void whenGetNull() {
        SimpleMap<Integer, String> mapGet = new SimpleMap<>();
        assertThat(mapGet.put(null, "it's null")).isTrue();
        assertThat(mapGet.get(null)).isEqualTo("it's null");
    }

    @Test
    void whenGetNothing() {
        SimpleMap<Integer, String> mapGet = new SimpleMap<>();
        assertThat(mapGet.put(0, "0")).isTrue();
        assertThat(mapGet.get(1)).isNull();
    }

    @Test
    void whenRemove() {
        SimpleMap<Integer, String> mapRem = new SimpleMap<>();
        assertThat(mapRem.put(0, "0")).isTrue();
        assertThat(mapRem.remove(0)).isTrue();
        assertThat(mapRem.put(null, "it's null")).isTrue();
    }

    @Test
    void whenRemoveTwice() {
        SimpleMap<Integer, String> mapRem = new SimpleMap<>();
        assertThat(mapRem.put(0, "0")).isTrue();
        assertThat(mapRem.remove(0)).isTrue();
        assertThat(mapRem.remove(0)).isFalse();
    }

    @Test
    void whenIterate() {
        SimpleMap<Integer, String> mapIt = new SimpleMap<>();
        assertThat(mapIt.put(0, "0")).isTrue();
        assertThat(mapIt.put(1, "1")).isTrue();
        assertThat(mapIt.put(2, "2")).isTrue();
        Iterator<Integer> it = mapIt.iterator();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenDontIterate() {
        SimpleMap<Integer, String> mapIt = new SimpleMap<>();
        assertThat(mapIt.put(null, "0")).isTrue();
        assertThat(mapIt.put(1, "1")).isTrue();
        assertThat(mapIt.put(2, "2")).isTrue();
        Iterator<Integer> it = mapIt.iterator();
        assertThat(it.next()).isNull();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);

    }
}