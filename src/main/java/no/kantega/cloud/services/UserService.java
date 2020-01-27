package no.kantega.cloud.services;

import io.agroal.api.AgroalDataSource;
import lombok.extern.slf4j.Slf4j;
import no.kantega.cloud.domain.User;
import no.kantega.cloud.util.SqlUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@ApplicationScoped
public class UserService {

    private AgroalDataSource dataSource;

    @Inject
    public UserService(AgroalDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> getUsers() {
        return SqlUtils.executeQuery(dataSource, "SELECT * FROM USERS", this::mapRow);
    }

    public Optional<User> getUserById(UUID id) {
        return SqlUtils.executeQuery(dataSource, "SELECT * FROM USERS WHERE UUID = ?", this::mapRow, id.toString())
            .stream()
            .findAny();
    }

    public boolean addUser(User user) {
        int affectedRows = SqlUtils.executeUpdate(
            dataSource,
            "INSERT INTO USERS (UUID, USERNAME, NAME, EMAIL) VALUES (?,?,?,?)",
            user.getId(),
            user.getUsername(),
            user.getName(),
            user.getEmail()
        );

        return affectedRows > 0;
    }

    public boolean deleteUser(UUID id) {
        int affectedRows = SqlUtils.executeUpdate(dataSource, "DELETE FROM USERS WHERE UUID = ?", id.toString());
        return affectedRows > 0;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        return User.builder()
            .id(UUID.fromString(rs.getString("UUID")))
            .username(rs.getString("USERNAME"))
            .name(rs.getString("NAME"))
            .email(rs.getString("EMAIL"))
            .build();
    }
}
