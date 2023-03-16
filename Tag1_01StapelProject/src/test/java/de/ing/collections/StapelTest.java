package de.ing.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StapelTest {

    private final int limit = 10;

    @InjectMocks
    private Stapel objectUnderTest;
//    @BeforeEach
//    void setUp() {
//        objectUnderTest = new Stapel();
//    }

    @Test
    //@DisplayName("Given is an empty Stack when isEmpty called then return true")
    void isEmpty_emptyStack_returnsTrue() {
        // Arrange


        // Act
        // Assertion
        assertTrue(objectUnderTest.isEmpty());
    }
    @Test
        //@DisplayName("Given is an empty Stack when isEmpty called then return true")
    void isEmpty_notEmptyStack_returnsFalse() throws Exception{
        // Arrange

        objectUnderTest.push(new Object());
        // Act
        // Assertion
        assertFalse(objectUnderTest.isEmpty());
    }

    @Test
        //@DisplayName("Given is an empty Stack when isEmpty called then return true")
    void push_fillUpToLimit_noExceptionIsThrown() throws Exception{
        // Arrange

        for (int i = 0; i < limit; i++) {
            assertDoesNotThrow(()->objectUnderTest.push(new Object()));
        }

        // Act

        // Assertion

    }

    @Test
        //@DisplayName("Given is an empty Stack when isEmpty called then return true")
    void push_overflow_throwsStapelException() throws Exception{
        // Arrange

        for (int i = 0; i < limit; i++) {
            assertDoesNotThrow(()->objectUnderTest.push(new Object()));
        }

        // Act
        StapelException ex = assertThrows(StapelException.class, ()->objectUnderTest.push(new Object()));
        assertEquals("Overflow", ex.getMessage());
        //assertNotNull(ex.getCause());

    }
}