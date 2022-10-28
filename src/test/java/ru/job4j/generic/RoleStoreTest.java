package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {
    RoleStore roleStore = new RoleStore();

    @Test
    void whenAddAndFindThenRoleNameIsPM() {
        roleStore.add(new Role("1", "PM"));
        Role rsl = roleStore.findById("1");
        assertThat(rsl.getRoleName()).isEqualTo("PM");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        roleStore.add(new Role("1", "PM"));
        assertThat(roleStore.findById("10")).isEqualTo(null);
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsPM() {
        roleStore.add(new Role("1", "PM"));
        roleStore.add(new Role("1", "DevOps"));
        Role rsl = roleStore.findById("1");
        assertThat(rsl.getRoleName()).isEqualTo("PM");
    }

    @Test
    void whenReplaceThenRoleNameIsBisness() {
        roleStore.add(new Role("3", "Owner"));
        roleStore.replace("3", new Role("3", "Bisness"));
        Role rsl = roleStore.findById("3");
        assertThat(rsl.getRoleName()).isEqualTo("Bisness");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        roleStore.add(new Role("4", "Developer"));
        roleStore.replace("3", new Role("10", "JavaDeveloper"));
        Role rsl = roleStore.findById("4");
        assertThat(rsl.getRoleName()).isEqualTo("Developer");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        roleStore.add(new Role("5", "JavaDeveloper"));
        roleStore.delete("5");
        assertThat(roleStore.findById("5")).isEqualTo(null);
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsPM() {
        roleStore.add(new Role("6", "PM"));
        roleStore.delete("10");
        assertThat(roleStore.findById("6").getRoleName()).isEqualTo("PM");
    }

    @Test
    void whenReplaceOkThenTrue() {
        roleStore.add(new Role("4", "Developer"));
        assertThat(roleStore.replace("4", new Role("4", "Lead of Developer"))).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        roleStore.add(new Role("4", "Developer"));
        assertThat(roleStore.replace("14", new Role("14", "Lead of Developer"))).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        roleStore.add(new Role("4", "Developer"));
        assertThat(roleStore.delete("4")).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        roleStore.add(new Role("4", "Developer"));
        assertThat(roleStore.delete("24")).isFalse();
    }
}