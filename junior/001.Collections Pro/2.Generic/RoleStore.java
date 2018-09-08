package dstelmachenko.services;

import java.util.Iterator;

public class RoleStore extends AbstractStore implements Store<Role>{

    @Override
    public void add(Role model) {
        array.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return baseReplace(id, (Base)model);
    }

    @Override
    public boolean delete(String id) {
        return super.delete(id);
    }

    @Override
    public Role findById(String id) {
        return (Role)super.findbyId(id);
    }
}
