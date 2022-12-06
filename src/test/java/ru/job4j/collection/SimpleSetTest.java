package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    @Test
    void whenContainsOne() {
        Set<Integer> simpleSet = new SimpleSet<>();
        simpleSet.add(1);
        assertThat(simpleSet.contains(1)).isTrue();
    }

    @Test
    void whenNotContainsTwo() {
        Set<Integer> simpleSet = new SimpleSet<>();
        assertThat(simpleSet.contains(2)).isFalse();
    }

    @Test
    void whenAddOnlyNull() {
        Set<Objects> objectsSet = new SimpleSet<>();
        assertThat(objectsSet.add(null)).isTrue();
    }

    @Test
    void whenNotContainsNull() {
        Set<Integer> simpleSet = new SimpleSet<>();
        assertThat(simpleSet.contains(null)).isFalse();
        simpleSet.add(3);
        assertThat(simpleSet.contains(null)).isFalse();
    }

    @Test
    void whenContainsNull() {
        Set<Objects> objectsSet = new SimpleSet<>();
        assertThat(objectsSet.add(null)).isTrue();
        assertThat(objectsSet.contains(null)).isTrue();
    }

    @Test
    void whenAddOne() {
        Set<Integer> simpleSet = new SimpleSet<>();
        assertThat(simpleSet.add(1)).isTrue();
        assertThat(simpleSet.add(1)).isFalse();
    }

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenEqual() {
        Set<Integer> simpleSet1 = new SimpleSet<>();
        Set<Integer> simpleSet2 = new SimpleSet<>();
        assertThat(simpleSet1.add(1)).isTrue();
        assertThat(simpleSet2.add(1)).isTrue();
        assertThat((simpleSet1.toString()).equals(simpleSet2.toString())).isTrue();
    }
}