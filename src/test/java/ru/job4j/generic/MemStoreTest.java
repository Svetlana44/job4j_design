package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemStoreTest {

    @Test
    void whenDeleteOkThenTrue() {
        var memStore = new MemStore<>();
        memStore.add(new User("1", "TestUser1"));
        memStore.add(new User("Id2", "Test2User"));
        boolean rsl = memStore.delete("Id2");
        assertThat(rsl).isTrue();
        memStore.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        var memStore = new MemStore<>();
        memStore.add(new User("1", "TestUser1"));
        memStore.add(new User("Id2", "Test2User"));
        boolean rsl = memStore.delete("Id2");
        assertThat(rsl).isTrue();
        rsl = memStore.delete("Id1");
        assertThat(rsl).isFalse();
    }

    @Test
    void whenAddAndFindThenUsernameIsPetr() {
        var memStore = new MemStore<>();
        memStore.add(new User("1", "Petr"));
        memStore.add(new User("Id2", "Test2User"));
        User rsl = (User) memStore.findById("1");
        assertThat(rsl.getUsername()).isEqualTo("Petr");
        memStore.delete("1");
        assertThat(memStore.findById("1")).isEqualTo(null);
    }
}