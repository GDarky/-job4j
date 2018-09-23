package dstelmachenko.collections;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    private UserStore store;
    private User user5;
    private User user6;

    @Before
    public void beforeTest() {
        store = new UserStore();
        store.add(new User("User1"));
        store.add(new User("User2"));
        store.add(new User("User3"));
        store.add(new User("User4"));
        user5 = new User("User5");
        user6 = new User("User6");
    }

    @Test
    public void whenDeletedUser3ThenUser3NoLongerFound() {
        store.delete("User3");
        assertNull(store.findById("User3"));
    }

    @Test
    public void whenFondUser2ByIdThenGotUser2() {
        assertThat(store.findById("User2").getId(), is("User2"));
    }

    @Test
    public void whenAddedUser5ThenFoundUser5() {
        store.add(user5);
        assertThat(store.findById("User5").getId(), is("User5"));
    }

    @Test
    public void whenUser1WasReplacedbyUser6ThenFoundUser6andBotFoundUser1() {
        store.replace("User1", user6);
        assertThat(store.findById("User6").getId(), is("User6"));
        assertNull(store.findById("User1"));
    }
}