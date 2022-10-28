package ru.job4j.generic;

public class RoleStore implements Store<Role> {
    Store<Role> memStore = new MemStore<>();

    @Override
    public void add(Role model) {
        memStore.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return memStore.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return memStore.delete(id);
    }

    @Override
    public Role findById(String id) {
        return memStore.findById(id);
    }
}
