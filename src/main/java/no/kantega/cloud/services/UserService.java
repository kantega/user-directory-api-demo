package no.kantega.cloud.services;

import no.kantega.cloud.domain.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class UserService {
    private final Map<UUID, User> users = new HashMap<>();

    public UserService() {
        addUser(User.builder().username("and").name("Andreas").email("a.o@ka.no").build());
        addUser(User.builder().username("edv").name("Edvard").email("e.k.k@ka.no").build());
        addUser(User.builder().username("erl").name("Erling").email("e.s@ka.no").build());
        addUser(User.builder().username("esp").name("Espen").email("e.f@ka.no").build());
        addUser(User.builder().username("fro").name("Frode").email("f.b@ka.no").build());
        addUser(User.builder().username("fro").name("Frode").email("f.s@ka.no").build());
        addUser(User.builder().username("jor").name("Jørund").email("j.l@ka.no").build());
        addUser(User.builder().username("oyv").name("Øyvind").email("o.k@ka.no").build());
        addUser(User.builder().username("tor").name("Tore").email("t.e.a@ka.no").build());
        addUser(User.builder().username("tor").name("Torstein").email("t.s@ka.no").build());
        addUser(User.builder().username("veg").name("Vegard").email("v.s@ka.no").build());
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> getUserById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public boolean deleteUser(UUID id) {
        return users.remove(id) != null;
    }
}
