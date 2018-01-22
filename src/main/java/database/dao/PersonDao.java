package database.dao;

import database.model.Person;

import java.util.Set;

/**
 * Created by Patryk on 2017-08-10.
 */
interface PersonDao {
    void updatePerson(Person person);

    void deletePerson(int id);

    void addPerson(Person person);

    Set<Person> getAll();

    Person getPersonById(int id);
}
