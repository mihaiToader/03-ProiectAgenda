package agenda.model.repository.classes;

import agenda.model.base.User;
import agenda.model.repository.interfaces.RepositoryUser;

import java.util.ArrayList;
import java.util.List;

public class RepositoryUserMock implements RepositoryUser {

    public List<User> users = new ArrayList<>();

    @Override
    public User getByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().get();
    }

    @Override
    public User getByName(String name) {
        return null;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public boolean changePasswd(User user, String oldPasswd, String newPasswd) {
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
