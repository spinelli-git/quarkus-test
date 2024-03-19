package project.endpoint;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import project.model.Person;
import project.resource.PersonResource;

import java.util.List;

@Path("/api/persons")
public class PersonEndpoint {

    @Inject
    PersonResource personResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersons() {
        return personResource.getPersons();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("id") Long id) {
        return personResource.getPerson(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePerson(@PathParam("id") Long id, Person c) {
        personResource.updatePerson(id, c);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person addPerson(Person c) {
        return personResource.addPerson(c);
    }

    @DELETE
    @Path("/{id}")
    public void deletePerson(@PathParam("id") Long id) {
        personResource.deletePerson(id);
    }
}
