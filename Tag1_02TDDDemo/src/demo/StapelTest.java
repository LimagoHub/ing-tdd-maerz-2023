package demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StapelTest {

    private Stapel objectUnderTest;

    @BeforeEach
    void setUp() {
        objectUnderTest = new Stapel();
    }

    @Test
    void abc() {
        Assertions.assertTrue(objectUnderTest.isEmpty());
    }

    @Test
    void bcd() {
        objectUnderTest.push(new Object());
        Assertions.assertFalse(objectUnderTest.isEmpty());
    }
}