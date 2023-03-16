package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.Person;
import org.example.repository.PersonenRepository;

@RequiredArgsConstructor
public class PersonenServiceImpl {

    private final PersonenRepository repo;


    /*
        person == null => PSE
        vorname == null vorname länge min 2 Zeichen =>PSE
        nachname == null nachname länge min 2 Zeichen =>PSE
        Vorname = "Attila" =>PSE
        RuntimeException im Repository =>PSE
        happy day pass Person to Repo
     */
    void speichern(Person person) throws PersonenServiceException {

    }
}
