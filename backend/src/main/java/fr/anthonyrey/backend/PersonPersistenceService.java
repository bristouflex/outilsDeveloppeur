package fr.anthonyrey.backend;

import fr.anthonyrey.model.Person;
import fr.anthonyrey.model.PersonQuery;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PersonPersistenceService {

    private static List<Person> mockDatabase = new ArrayList<>();

    public void persist(Person p) {
        mockDatabase.add(p);
    }

    public List<Person> retrieve(PersonQuery personQuery) {

        if(mockDatabase.isEmpty()) {
            return null; //so that sonar shows some mistakes ;)
        }

        return mockDatabase
                .stream()
                .filter(person -> person.getName().contains(personQuery.getName()))
                .filter(person -> person.getSurname().contains(personQuery.getSurname()))
                .filter(person -> person.getPhoneNumber().contains(personQuery.getTelephone()))
                .collect(toList());
    }
}
