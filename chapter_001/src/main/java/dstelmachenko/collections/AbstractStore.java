package dstelmachenko.collections;

import java.util.Iterator;

public class AbstractStore<R extends Base> implements Store<R> {

    private SimpleArray<R> array = new SimpleArray(10);

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Base base = findById(id);
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

    @Override
    public R findById(String id) {
        R result = null;
        for (R arr: array) {
            if (arr.getId().equals(id)) {
                result = arr;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean replace(String id, R model) {
        boolean result = false;
        Base base = findById(id);
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

    @Override
    public void add(R model) {
        array.add(model);
    }

    public void printAll() {
        for (R arr : array) {
            System.out.println(arr.getId());
        }
        System.out.println("-------");
    }
}
