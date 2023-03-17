package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ComputerPlayerTest {

    @InjectMocks
    private ComputerPlayer objectUnderTest;


    @ParameterizedTest(name = "Run={index} stones = {0} expected={1}")
    @CsvSource({"20,3","21,1","22,1","23,2"})
    void doTurn_irgendwieanders(int stones, int expected) {
        assertEquals(expected, objectUnderTest.doTurn(stones));
    }

    //private List<String> stringsMock = Mockito.mock(List.class) ;



    @Test
    void demo() {

        

//        List<String> list = new ArrayList<String>();
//        List<String> spyList = Mockito.spy(list);
//
//        spyList.add("one");
//        spyList.add("two");
//
//        Mockito.verify(spyList).add("one");
//        Mockito.verify(spyList).add("two");
//
//        when(spyList.size()).thenReturn(20);
//        assertEquals(2,spyList.size());
    }

}