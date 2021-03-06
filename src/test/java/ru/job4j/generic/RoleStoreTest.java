package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("admin"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.add(new Role("1", "user"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("admin"));
    }

    @Test
    public void whenReplaceThenRolenameIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.replace("1", new Role("1", "user"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("user"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.replace("10", new Role("10", "user"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("admin"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("admin"));
    }
}
