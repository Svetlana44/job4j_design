package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import ru.job4j.tree.Tree.Node;

import java.util.function.Predicate;

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
        assertThat(tree.findBy(3).get()).isEqualTo(new Node<>(3));
        assertThat(tree.findBy(2)).isNotPresent();
    }

    @Test
    void whenFindByPredicate() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        Predicate<Node<Integer>> predicate = node -> node.children.size() > 2;
        Node<Integer> node = new Node<>(1);

        /* if node.children will not final, then may be:
        node.children = Arrays.asList(new Node<>(2), new Node<>(3), new Node<>(4));
         */
        assertThat(tree.findByPredicate(predicate)).isPresent();
    }

    @Test
    void whenIsBinary() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenIsNotBinary() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary()).isFalse();
    }
}