package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", "six");
        assertThat(list).isNotNull()
                .hasSize(6)
                .contains("six")
                .containsAnyOf("true", "1", "-", "four")
                .doesNotContain("where")
                .endsWith("six");

    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("zero", "first", "second", "three", "four", "five", "six");
        assertThat(set).isNotNull()
                .hasSize(7)
                .contains("six")
                .containsAnyOf("true", "1", "-", "four")
                .doesNotContain("where")
                .startsWith("zero")
                .endsWith("six");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("zero", "first", "second", "three", "four", "five", "six");
        assertThat(map).isNotNull()
                .isNotEmpty()
                .hasSize(7)
                .containsKeys("six")
                .doesNotContainKey("where")
                .containsEntry("second", 2);
    }
}