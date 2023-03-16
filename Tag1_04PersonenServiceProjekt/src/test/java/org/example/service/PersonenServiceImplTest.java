package org.example.service;

import org.example.repository.Person;
import org.example.repository.PersonenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;

    @Mock // Strictmock
    private PersonenRepository personenRepositoryMock;

    @Test
    void speichern_personNull_throwsPersonenServiceException() throws Exception{
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(null));
        assertEquals("Person should not be null", ex.getMessage());
    }
    @Test
    void speichern_vornameNull_throwsPersonenServiceException() throws Exception{
        Person invalidPerson = Person.builder().id("1").vorname(null).nachname("Doe").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(invalidPerson));
        assertEquals("Vorname too short", ex.getMessage());
    }
    @Test
    void speichern_vornameTooShort_throwsPersonenServiceException() throws Exception{
        Person invalidPerson = Person.builder().id("1").vorname("J").nachname("Doe").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(invalidPerson));
        assertEquals("Vorname too short", ex.getMessage());
    }

    @Test
    void speichern_nachnameNull_throwsPersonenServiceException() throws Exception{
        Person invalidPerson = Person.builder().id("1").vorname("John").nachname(null).build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(invalidPerson));
        assertEquals("Nachname too short", ex.getMessage());
    }
}