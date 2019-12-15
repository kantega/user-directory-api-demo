package no.kantega.cloud;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import no.kantega.cloud.domain.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
public class UserResourceTest {
    @Test
    public void testUsersEndpoint() {
        List<User> users = given()
            .when().get("/api/users")
            .as(new TypeRef<List<User>>() {});

        assertThat(users.size(), is(greaterThan(0)));
        users.forEach(user -> {
            assertThat(user.getId().toString(), not(emptyOrNullString()));
            assertThat(user.getUsername(), not(emptyOrNullString()));
        });
    }

}
