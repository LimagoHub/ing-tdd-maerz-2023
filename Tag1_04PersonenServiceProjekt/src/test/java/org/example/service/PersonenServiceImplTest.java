package org.example.service;

import org.example.repository.Person;
import org.example.repository.PersonenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;

    @Mock // Strictmock
    private PersonenRepository personenRepositoryMock;

    @Mock
    private BlacklistService blacklistServiceMock;

    private int counter = 0;

    @Nested
    @DisplayName("Tests fue einfache Validierungen")
    class simpleValidation {

        @Test
        void speichern_personNull_throwsPersonenServiceException() throws Exception {
            Person p = null;
            PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(p));
            assertEquals("Person should not be null", ex.getMessage());
        }

        @Test
        void speichern_vornameNull_throwsPersonenServiceException() throws Exception {
            Person invalidPerson = Person.builder().id("1").vorname(null).nachname("Doe").build();
            PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(invalidPerson));
            assertEquals("Vorname too short", ex.getMessage());
        }

        @Test
        void speichern_vornameTooShort_throwsPersonenServiceException() throws Exception {
            Person invalidPerson = Person.builder().id("1").vorname("J").nachname("Doe").build();
            PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(invalidPerson));
            assertEquals("Vorname too short", ex.getMessage());
        }

        @Test
        void speichern_nachnameNull_throwsPersonenServiceException() throws Exception {
            Person invalidPerson = Person.builder().id("1").vorname("John").nachname(null).build();
            PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(invalidPerson));
            assertEquals("Nachname too short", ex.getMessage());
        }

        @Test
        void speichern_nachnameTooShort_throwsPersonenServiceException() throws Exception {
            Person invalidPerson = Person.builder().id("1").vorname("John").nachname(null).build();
            PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(invalidPerson));
            assertEquals("Nachname too short", ex.getMessage());
        }

    }
    @Nested
    @DisplayName("Tests fuer fachliche Pruefungen")
    class businessCheck {
        @Test
        void speichern_unerwuenschtePerson_throwsPersonenServiceException() throws Exception {
            when(blacklistServiceMock.isBlacklisted(any(Person.class))).thenReturn(true);
            Person invalidPerson = Person.builder().id("1").vorname("John").nachname("Doe").build();
            PersonenServiceException ex = assertThrows(PersonenServiceException.class, () -> objectUnderTest.speichern(invalidPerson));
            assertEquals("Blacklisted Person", ex.getMessage());
        }
    }

    @Test
    void speichern_unexpectedExceptionInUnderlyingService_throwsPersonenServiceException() throws Exception{
        when(blacklistServiceMock.isBlacklisted(any(Person.class))).thenReturn(false);
        doThrow(ArithmeticException.class).when(personenRepositoryMock).save(any(Person.class));
        Person validPerson = Person.builder().id("1").vorname("John").nachname("Doe").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(validPerson));
        assertEquals("Interner Fehler", ex.getMessage());
        assertEquals(ex.getCause().getClass(), ArithmeticException.class);
    }

    @Test
    void speichern_happyDay_personPassedToRepo() throws Exception{
        when(blacklistServiceMock.isBlacklisted(any(Person.class))).thenReturn(false);
        Person validPerson = Person.builder().id("1").vorname("John").nachname("Doe").build();
        objectUnderTest.speichern(validPerson);

        InOrder inOrder = inOrder(blacklistServiceMock, personenRepositoryMock);

        inOrder.verify(blacklistServiceMock,times(1)).isBlacklisted(validPerson);
        inOrder.verify(personenRepositoryMock, times(1)).save(validPerson);


    }

    @Test
    void speichernOverloaded_happyDay_personPassedToRepo() throws Exception{

        doAnswer(invocationOnMock -> {
            Person p = invocationOnMock.getArgument(0);
            assertEquals("John", p.getVorname());
            assertEquals("Doe", p.getNachname());
            counter ++;
            return null;
        }).when(personenRepositoryMock).save(any(Person.class));
        when(blacklistServiceMock.isBlacklisted(any(Person.class))).thenAnswer(invocationOnMock -> {
            // irgendein Kram
            return false;
        });
        Person validPerson = Person.builder().id("1").vorname("John").nachname("Doe").build();
        objectUnderTest.speichern("John","Doe");






    }
}