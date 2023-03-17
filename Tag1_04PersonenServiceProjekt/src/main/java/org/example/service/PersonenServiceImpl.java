package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.Person;
import org.example.repository.PersonenRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class PersonenServiceImpl {

    private final PersonenRepository repo;
    private final BlacklistService blacklistService;

    /*
        person == null => PSE
        vorname == null vorname länge min 2 Zeichen =>PSE
        nachname == null nachname länge min 2 Zeichen =>PSE
        Vorname = "Attila" =>PSE
        RuntimeException im Repository =>PSE
        happy day pass Person to Repo
     */
    void speichern(Person person) throws PersonenServiceException {
        try {
            speichernImpl(person);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Interner Fehler", e);
        }
    }

    private void speichernImpl(Person person) throws PersonenServiceException {
        checkPerson(person);
        repo.save(person);
    }

    private void checkPerson(Person person) throws PersonenServiceException {
        validate(person);
        businessCheck(person);
    }

    private void businessCheck(Person person) throws PersonenServiceException {
        if(blacklistService.isBlacklisted(person))
            throw new PersonenServiceException("Blacklisted Person");
    }

    private static void validate(Person person) throws PersonenServiceException {
        if(person ==null)
            throw new PersonenServiceException("Person should not be null");
        if(person.getVorname() == null || person.getVorname().length() < 2)
            throw new PersonenServiceException("Vorname too short");
        if(person.getNachname() == null || person.getNachname().length() < 2)
            throw new PersonenServiceException("Nachname too short");
    }

    public void speichern(String vorname, String nachname) throws PersonenServiceException{
        Person p = Person.builder().id(UUID.randomUUID().toString()).vorname(vorname).nachname(nachname).build();
        speichern(p);
    }
}
