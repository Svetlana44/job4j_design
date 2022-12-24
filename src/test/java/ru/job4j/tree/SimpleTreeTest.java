package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import ru.job4j.tree.Tree.Node;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenFindByValue() {
        Tree<Integer> tree = new SimpleTree<>(2);
        assertThat(tree.add(2, 3)).isTrue();
        assertThat(tree.findBy(2).get().toString()).isEqualTo("Node{value=2, children=[Node{value=3, children=[]}]}");
    }

    @Test
    void whenDidntAddFind() {
        Tree<Integer> tree = new SimpleTree<>(3);
        assertThat(tree.findBy(3)).isPresent();
        assertThat(tree.findBy(3).get()).isEqualTo(new Node<Integer>(3));
        assertThat(tree.findBy(2)).isNotPresent();
    }
}