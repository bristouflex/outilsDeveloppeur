package fr.anthonyrey.business.service;

import fr.anthonyrey.business.exception.BusinessException;
import fr.anthonyrey.business.service.DirectoryService;
import fr.anthonyrey.model.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;


public class DirectoryServiceTest {

    private DirectoryService directoryService;

    @Before
    public void init() {
        directoryService = new DirectoryService();
    }

    @Test()
    public void nominal_case() {

        try {
            directoryService.createEntry(generateNominalPerson());
        } catch (BusinessException e) {
            fail();
        }
    }

    @Test(expected = BusinessException.class)
    public void should_fail_if_name_is_null() throws BusinessException {

        directoryService.createEntry(generatePersonWithNullName());
    }

    @Test(expected = BusinessException.class)
    public void should_fail_if_number_does_not_fit_business_rule() throws BusinessException {

        directoryService.createEntry(generatePersonWithBadNumber());
    }

    private Person generateNominalPerson() {
        return new Person("Rey", "Anthony", "0112345678");
    }

    private Person generatePersonWithNullName() {
        Person person = generateNominalPerson();
        person.setName(null);
        return person;
    }

    private Person generatePersonWithBadNumber() {
        Person person = generateNominalPerson();
        person.setPhoneNumber("08123448828");
        return person;
    }
}
