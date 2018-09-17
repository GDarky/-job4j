package dstelmachenko.collections;

import java.util.Iterator;

public abstract class AbstractStore<R extends Base> /*implements Store<Base>*/ {

    protected SimpleArray<R> array = new SimpleArray(10);

    protected dstelmachenko.collections.Base findbyId(String id) {
        dstelmachenko.collections.Base result = null;
        for (R arr: array) {
            if (arr.getId().equals(id)) {
                result = arr;
                break;
            }
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        Base base = findbyId(id);

        if (base != null) {
            for (Iterator<R> it = array.iterator(); it.hasNext();) {
                if (it.next().equals(base)) {
                    it.remove();
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    protected boolean baseReplace(String id, R model) {
        boolean result = false;
        Base base = findbyId(id);

        //тут не сообразил, как без счетчика сделать
        int i = 0;
        if (base != null) {
            for (R rl: array) {
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
        for (R arr : array) {
            System.out.println(arr.getId());
        }
        System.out.println("-------");
    }
}
