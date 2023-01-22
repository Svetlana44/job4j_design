/*
Под каждый тест будет свой файл.
1) на чтение файла с комментариями и пустыми строками
2) на чтение файла с нарушением шаблона ключ=значение
(напр. =значение, или ключ= , или строка без символа "=" ключзначение, или строка без ключа и без значения, но с символом =)
 в этом случае нужно ожидать исключение IllegalArgumentException
Строка вида "ключ=значение=1" или "ключ=значение=" должна быть обработана и распознана как ключ "ключ" и значение "значение=1"
 или "значение=" соответственно.
 */
package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        Config config = new Config(".\\data\\pair_without_comment.properties");
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithDoubleEquals() {
        Config config = new Config(".\\data\\pair_with_double_equals.properties");
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev=");
        assertThat(config.value("site")).isEqualTo("www.=you");
    }

    @Test
    void whenWithCommentAndEmptyLines() {
        Config config = new Config("./data/pair_with_comment_and_empty_lines.properties");
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("surname")).isEqualTo("Arsentev");
    }

    @Test
    void whenBreakingPatterns() {
        Config config = new Config("./data/pair_with_breaking_patterns.properties");
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this does breaking pattern");
    }

}