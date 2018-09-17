package dstelmachenko.collections;

public class UserStore extends AbstractStore<User> implements Store<User> {

    public void add(User model) {
        array.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return baseReplace(id, model);
    }

    public User findById(String id) {
        return (User) findbyId(id);
    }

    public boolean delete(String id) {
        return super.delete(id);
    }

    public static void main(String[] args) {
        UserStore us = new UserStore();
        us.add(new User("Дима"));
        us.add(new User("Саша"));
        us.add(new User("Паша"));
        us.printAll();

        RoleStore rs = new RoleStore();
        rs.add(new Role("Админ"));
        rs.add(new Role("Юзер"));
        rs.printAll();
    }

}
