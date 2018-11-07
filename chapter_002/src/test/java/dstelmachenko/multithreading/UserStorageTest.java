package dstelmachenko.multithreading;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {
    UserStorage userStorage;
    User user1, user2;

    @Before
    public void beforeTest() {
        userStorage = new UserStorage();
        user1 = new User(1, 100);
        userStorage.add(user1);
        user2 = new User(2, 400);
        userStorage.add(user2);
    }

    @Test
    public void whenAddUserThenUserInListAbdAnountis300() {
        userStorage.add(new User(3, 300));
        assertThat(userStorage.get(3).getAmount(), is(300));
    }


    @Test (expected = NoSuchElementException.class)
    public void whenDeleteUser2AndGetUser2ThenNoSuchElementExcepion() {
        userStorage.delete(user2);
        userStorage.get(2);
    }

    @Test
    public void whenUpdateUser1AmountTo700ThenUser1AmmountIs700() {
        user1.setAmount(700);
        userStorage.update(user1);
        assertThat(userStorage.get(1).getAmount(), is(700));
    }

    @Test
    public void whenTransfer50FromUser2ToUser1ThenUser1Have150() {
        userStorage.transfer(2, 1, 50);
        assertThat(userStorage.get(1).getAmount(), is(150));
    }

    @Test
    public void whenTransfer50FromUser2ToUser1ThenUser2Have350() {
        userStorage.transfer(2, 1, 50);
        assertThat(userStorage.get(2).getAmount(), is(350));
    }
}