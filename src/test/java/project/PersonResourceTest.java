package project;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import project.model.Person;
import project.resource.PersonResource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PersonResourceTest {

    @Mock
    EntityManager entityManager;

    @InjectMocks
    PersonResource personResource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    public void testGetPersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1L, "john@example.com", "", true));
        persons.add(new Person(2L, "jane@example.com", "", false));
        Query queryMock = mock(Query.class);
        when(queryMock.getResultList()).thenReturn(persons);
        when(entityManager.createQuery("SELECT c FROM Person c")).thenReturn(queryMock);

        List<Person> result = personResource.getPersons();

        assertEquals(persons.size(), result.size());
    }

}
