package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void testGetMapCheckEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void testParseEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void testValidateEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Nick"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: Nick does not contain the symbol \"=\"");
    }

    @Test
    void testValidateStartWith() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=Name"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: =Name does not contain a key");
    }

    @Test
    void testValidateEndWith() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Name="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: Name= does not contain a value");
    }
}