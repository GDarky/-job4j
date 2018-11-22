package dstelmachenko.multithreading;

public class Base {
    private int id;
    private String name;
    private int version;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 0;
    }

    public Base(int id, String name, int version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public Base(Base model) {
        this.id = model.id;
        this.name = model.name;
        this.version = model.version;
    }

    public Base addVersion() {
        version++;
        return this;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        version++;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        version++;
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
