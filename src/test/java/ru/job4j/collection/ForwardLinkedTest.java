package ru.job4j.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;

    @BeforeEach
    public void init() {
        linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
    }

    @Test
    void whenDeleteFirst() {
        assertThat(linked.deleteFirst()).isEqualTo(1);
        assertThat(linked.deleteFirst()).isEqualTo(2);
        assertThat(linked.deleteFirst()).isEqualTo(3);
        assertThat(linked.deleteFirst()).isEqualTo(4);
        assertThatThrownBy(linked.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        assertThatThrownBy(linked::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenMultiDelete() {
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    void whenDeleteAndAddFirst() {
        assertThat(linked.deleteFirst()).isEqualTo(1);
        assertThat(linked.deleteFirst()).isEqualTo(2);
        assertThat(linked.deleteFirst()).isEqualTo(3);
        assertThat(linked.deleteFirst()).isEqualTo(4);
        linked.addFirst(5);
        assertThat(linked.deleteFirst()).isEqualTo(5);
        assertThatThrownBy(linked::deleteFirst).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenAdd() {
        linked.add(5);
        String expected = "ForwardLinked|head=Node{value=1, next=Node{value=2, next=Node{value=3, next=Node{value=4, next=Node{value=5, next=null}}}}}|";
        assertThat(linked.toString()).isEqualTo(expected);
    }

    @Test
    void whenAddFirst() {
        assertThat(linked.deleteFirst()).isEqualTo(1);
        assertThat(linked.deleteFirst()).isEqualTo(2);
        assertThat(linked.deleteFirst()).isEqualTo(3);
        assertThat(linked.deleteFirst()).isEqualTo(4);
        linked.addFirst(1);
        linked.addFirst(2);
        linked.addFirst(3);
        linked.addFirst(4);
        linked.addFirst(5);
        String expected = "ForwardLinked|head=Node{value=5, next=Node{value=4, next=Node{value=3, next=Node{value=2, next=Node{value=1, next=null}}}}}|";
        assertThat(linked.toString()).isEqualTo(expected);

    }
}