package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleModelTest {
    @Test
    void checkGetName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(sm::getName)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(() -> sm.setName("Nick", 5))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty()
                .isEqualTo("this word: Nick has length not equal to : 5");
                /*с помощью регулярного выражения проверяем факт наличия сообщения
                .hasMessageMatching("^.+")
                проверяем, что в сообщении есть соответствующие параметры:
                .hasMessageContaining(word, number)
                проверяем наличие конкретного слова в сообщении:
                .hasMessageContaining("name");*/
    }
}