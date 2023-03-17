package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ComputerPlayerTest {

    @InjectMocks
    private ComputerPlayer objectUnderTest;


    @ParameterizedTest(name = "Run={index} stones = {0} expected={1}")
    @CsvSource({"20,3","21,1","22,1","23,2"})
    void doTurn_irgendwieanders(int stones, int expected) {
        assertEquals(expected, objectUnderTest.doTurn(stones));
    }

}