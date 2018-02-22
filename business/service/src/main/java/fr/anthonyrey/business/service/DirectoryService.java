package fr.anthonyrey.business.service;

import fr.anthonyrey.backend.PersonPersistenceService;
import fr.anthonyrey.business.exceptions.BusinessException;
import fr.anthonyrey.model.Person;
import fr.anthonyrey.model.PersonQuery;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public class DirectoryService {

    private PersonPersistenceService pps;

    public DirectoryService() {
        pps = new PersonPersistenceService();
    }

    public void createEntry(Person p) throws BusinessException {

        checkEmptiness(p);
        checkRegex(p);
        pps.persist(p);
    }

    private void checkRegex(Person p) throws BusinessException {
        Pattern name = Pattern.compile("^[A-z]{1,15}$");
        Pattern surname = name;
        Pattern phone = Pattern.compile("^(01|06|07)[0-9]{8}$");

        if(!name.matcher(p.getName()).find())
            throw new BusinessException("Le nom n'est pas conforme à la règle métier");
        else if(!surname.matcher(p.getSurname()).find())
            throw new BusinessException("Le prénom n'est pas conforme à la règle métier");
        else if(!phone.matcher(p.getPhoneNumber()).find())
            throw new BusinessException("Le numéro de téléphone n'est pas conforme à la règle métier");
    }

    private void checkEmptiness(Person p) throws BusinessException {

        if(p.getName() == null)
            throw new BusinessException("Le nom ne doit pas être vide");

        //TODO put it back so sonar stops complaining :)
        /*
        else if (p.getPhoneNumber() == null)
            throw new BusinessException("Le numéro de téléphone ne doit pas être vide");
        else if (p.getSurname() == null)
            throw new BusinessException("Le prénom ne doit pas être vide");
        */
    }

    public String getPersons(PersonQuery p) {

        List<Person> persons = pps.retrieve(p);
        if(persons.isEmpty())
            return null;
            //return "Pas de résultats";
        else
           return persons.stream()
                    .map(Person::toString)
                    .collect(joining("\n"));
    }
}
