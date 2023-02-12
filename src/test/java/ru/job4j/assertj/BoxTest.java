package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void tetrahedronHas4Vertices() {
        Box box = new Box(4, 5);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(4);
    }

    @Test
    void sphereHas0Vertices() {
        Box box = new Box(0, 5);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(0);
    }

    @Test
    void verticesIsUNKNOWNIsEqualsToMinus1() {
        Box box = new Box(35, 5);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(-1);
        String type = box.whatsThis();
        assertThat(type).isEqualTo("Unknown object");
    }

    @Test
    void verticesIsEqualToMinus1AndTypeQualsToUnknownIfEdgeLessThan1() {
        Box box = new Box(4, 0);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(-1);
        String type = box.whatsThis();
        assertThat(type).isEqualTo("Unknown object");
    }

    @Test
    void boxIsKnown() {
        Box box = new Box(4, 1);
        boolean known = box.isExist();
        assertThat(known).isTrue();
    }

    @Test
    void boxIsUnknown() {
        Box box = new Box(4, -1);
        boolean known = box.isExist();
        assertThat(known).isFalse();
    }

    @Test
    void squareOfSphereIsAbout1256() {
        Box box = new Box(0, 1);
        double square = box.getArea();
        assertThat(((Double) (square)).toString().contains("12.56")).isTrue();
    }

    @Test
    void squareOfCubeIs24() {
        Box box = new Box(8, 2);
        double square = box.getArea();
        assertThat(square).isEqualTo(24.0);
    }
}