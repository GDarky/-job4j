package dstelmachenko.multithreading;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class SimpleNonBlockingCache {
    @GuardedBy("this")
    private ConcurrentHashMap<Integer, Base> storage = new ConcurrentHashMap<>();

    public Base add(Base model) {
        return storage.put(model.getId(), model);
    }

    public void update(Base model) {
        storage.computeIfPresent(model.getId(), (k, v) -> {
            if (v.getVersion() >= model.getVersion()) {
                throw new OptimisticException("Object is blocked");
            } else {
               return model;
            }
        });
    }

    public boolean delete(Base model) {
        return storage.remove(model.getId(), model);
    }

    public Base getBasebyId(int id) {
        return storage.get(id);
    }
}
