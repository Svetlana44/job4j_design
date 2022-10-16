package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void testWhatsThisIsThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void testWhatsThisIsThisUnknown() {
        Box box = new Box(1, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void testGetNumberOfVerticesWhenCubeThenVertex8() {
        Box box = new Box(8, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(8);
    }

    @Test
    void testGetNumberOfVerticesWhenUnknownThenMinus1() {
        Box box = new Box(33, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(-1);
    }

    @Test
    void testisExistWhen4ThenTrue() {
        Box box = new Box(4, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void testisExistWhenMinus1ThenFalse() {
        Box box = new Box(-1, 10);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void testGetAreaWhenSphere() {
        Box box = new Box(4, 10);
        assertThat(box.getArea())
                .isNotZero()
                .isPositive()
                .isEqualTo(173d, withPrecision(0.3));
    }

    @Test
    void testGetAreaWhenUnknown() {
        Box box = new Box(-1, 10);
        assertThat(box.getArea()).isEqualTo(0d, withPrecision(0.01));
    }

}