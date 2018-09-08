package dstelmachenko.services;

import java.util.Iterator;

public abstract class AbstractStore /*implements Store<Base>*/{

    protected SimpleArray<Base> array = new SimpleArray(10);

    protected Base findbyId(String id) {
        Base result = null;
        for (Base arr: array) {
            if (arr.getId().equals(id)) {
                result = arr;
                break;
            }
        }
        return result;
    }

    protected boolean delete(String id) {
        boolean result = false;
        Base base = findbyId(id);

        if (base!=null) {
            for (Iterator<Base> it = array.iterator(); it.hasNext();) {
                if (it.next().equals(base)) {
                    it.remove();
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    protected boolean baseReplace(String id, Base model) {
        boolean result = false;
        Base base = findbyId(id);

        //тут не сообразил, как без счетчика сделать
        int i=0;
        if (base!=null) {
            for (Base rl: array) {
                if (rl.getId().equals(id)) {
                    array.set(i, model);
                    result = true;
                    break;
                }
                i++;
            }
        }
        return result;
    }

    public void printAll() {
        for (Base arr : array) {
            System.out.println(arr.getId());
        }
        System.out.println("-------");
    }
}
