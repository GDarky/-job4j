package dstelmachenko.services;

import java.util.Iterator;

public class UserStore extends AbstractStore implements Store<User> {

    public void add(User model) {
        array.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return baseReplace(id, (Base) model);
    }

    public User findById(String id) {
        return (User)findbyId(id);
    }

    public boolean delete(String id) {
        return super.delete(id);
    }


}
