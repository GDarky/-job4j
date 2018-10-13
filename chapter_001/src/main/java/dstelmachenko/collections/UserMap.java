package dstelmachenko.collections;

import java.util.Calendar;
import java.util.HashMap;

/**
 * @author Denis Stelmachenko
 * @since 2018-09-25
 *
 */

public class UserMap {
    private String name;
    private int children;
    private Calendar birthday;

    public UserMap(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserMap user = (UserMap) obj;
        if (name != null ? !name.equals(user.name) : user.name != null) {
            if (children != user.children) {
                if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * birthday.hashCode();
        return result;    }
}
