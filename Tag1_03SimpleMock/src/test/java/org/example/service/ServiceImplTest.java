package org.example.service;

import org.example.dependency.Dependency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceImplTest {

    @InjectMocks
    private ServiceImpl objectUnderTest;

    @Mock
    private Dependency dependencyMock;

//    @BeforeEach
//    void init() {
//        dependencyMock = Mockito.mock(Dependency.class);
//        objectUnderTest = new ServiceImpl(dependencyMock);
//    }

    @Test
    void abc_bla_bla() {
        //doThrow(ArithmeticException.class).when(dependencyMock).foo(anyString());
        objectUnderTest.abc();
        verify(dependencyMock).foo("Hallo");
    }

    @Test
    void xyz_bla_bla() {
        //doThrow(ArithmeticException.class).when(dependencyMock).foo(anyString());
        when(dependencyMock.bar()).thenReturn(42);
        var result = objectUnderTest.xyz(5);
        verify(dependencyMock, times(1)).bar();
        assertEquals(47, result);
    }

    @Test
    void sdf() {
        // Recordmode
//        when(dependencyMock.foobar("Hello")).thenReturn(42);
//        when(dependencyMock.foobar("World")).thenReturn(3);
        when(dependencyMock.foobar(anyString())).thenReturn(3);
        // Replaymode
        var result = objectUnderTest.sdf("Hallo");
        verify(dependencyMock, times(1)).foobar("HALLO");
        assertEquals(6, result);
    }
}