package me.igornostali.web.rest

import me.igornostali.model.User
import me.igornostali.test.util.T
import spock.lang.Specification
import spock.lang.Unroll

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType

@Unroll
class UserRestServiceATSpec extends Specification {

    private final String endpoint = "http://localhost:8080/module-demo/api/v1/users/register";

    def "REGISTER with ([#firstName]/[#lastName]) SHOULD register it"() {
        setup:
        def client = buildClientFor(endpoint);
        def user = T.userFor("$firstName.$lastName@somewhere.com", firstName, lastName);

        when:
        def persisted = client
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE), User.class);

        then:
        persisted.getId() > 0

        where:
        firstName | lastName
        "Julie"   | "Smith"
        "John"    | "Smith"
        "Janette" | "Smith"
    }

    WebTarget buildClientFor(String endpoint) {
        Client client = ClientBuilder.newClient();

        return client.target(endpoint)
    }
}


