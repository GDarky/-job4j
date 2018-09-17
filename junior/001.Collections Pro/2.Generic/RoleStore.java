package dstelmachenko.collections;

public class RoleStore extends AbstractStore<Role> implements Store<Role> {

    @Override
    public void add(Role model) {
        array.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return baseReplace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return super.delete(id);
    }

    @Override
    public Role findById(String id) {
        return (Role) super.findbyId(id);
    }
}
