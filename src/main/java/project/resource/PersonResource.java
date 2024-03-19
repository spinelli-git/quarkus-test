package project.resource;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import project.model.Person;

import java.util.List;

@Singleton
public class PersonResource {

    @Inject
    private EntityManager entityManager;

    public List<Person> getPersons() {
        return entityManager.createQuery("SELECT c FROM Person c").getResultList();
    }

    public Person getPerson(Long id) {
        return entityManager.find(Person.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Person addPerson(Person person) {
        entityManager.persist(person);
        return person;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void updatePerson(Long id, Person person) {
        Person personToUpdate = entityManager.find(Person.class, id);
        if (null != personToUpdate) {
            personToUpdate.setName(person.getName());
            personToUpdate.setEmail(person.getEmail());
            personToUpdate.setActived(person.isActived());
        } else {
            throw new RuntimeException("No such contact available");
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deletePerson(Long id) {
        Person contact = getPerson(id);
        entityManager.remove(contact);
    }
}
