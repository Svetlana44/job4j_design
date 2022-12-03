
package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3)
                .containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3)
                .containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterAgane() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        assertThat(input).hasSize(4)
                .containsSequence(1, 2, 3, 4);
    }

    @Test
    void whenDidNotRemoveIf() {
        ListUtils.removeIf(input, (n -> n > 3));
        assertThat(input).hasSize(2)
                .containsSequence(1, 3);
    }

    @Test
    void whenRemoveIf() {
        input.removeIf(n -> n < 3);
        assertThat(input).hasSize(1)
                .doesNotContainSequence(1, 3);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(input, (n -> n == 3), 5);
        assertThat(input).hasSize(2)
                .containsSequence(1, 5);
    }

    @Test
    void whenDidNotReplaceIf() {
        ListUtils.replaceIf(input, (n -> n == 7), 5);
        assertThat(input).hasSize(2)
                .containsSequence(1, 3);
    }

    @Test
    void whenReplaceAll() {
        ListUtils.removeAll(input, Arrays.asList(1, 2, 3));
        assertThat(input).hasSize(0)
                .doesNotContainSequence(1, 2);
    }

    @Test
    void whenDidNotReplaceAll() {
        ListUtils.removeAll(input, Arrays.asList(5, 6, 7));
        assertThat(input).hasSize(2)
                .containsSequence(1, 3);
    }

    @Test
    void whenRemovAllSecond() {
        ListUtils.removeAll(input, Arrays.asList(2, 3));
        assertThat(input).hasSize(1)
                .containsSequence(1);
    }
}