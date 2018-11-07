package dstelmachenko.multithreading;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final ArrayList<User> users = new ArrayList<>();

    public synchronized User get(int id) {
        return users.get(getUserIndexById(id));
    }

    public synchronized boolean add(User user) {
        return users.add(user);
    }

    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        int userIndex = getUserIndexById(user.getId());
        if (userIndex > -1) {
            users.set(userIndex, user);
            result = true;
        }
        return result;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        int fromIndex = getUserIndexById(fromId);
        int toIndex = getUserIndexById(toId);
        User userFrom = users.get(fromIndex);
        User userTo = users.get(toIndex);
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
        update(userFrom);
        update(userTo);
    }

    private synchronized int getUserIndexById(int id)  throws NoSuchElementException {
        int userIndex = -1;
        for (User currUser : users) {
            if (currUser.getId() == id) {
                userIndex = users.indexOf(currUser);
                break;
            }
        }
        if (userIndex == -1) {
            throw new NoSuchElementException();
        }
        return userIndex;
    }
}
