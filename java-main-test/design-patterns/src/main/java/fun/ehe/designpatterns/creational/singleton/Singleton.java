package fun.ehe.designpatterns.creational.singleton;

/**
 * Intent: <br/>
 * Ensure a class only has one instance, and provide a global point of access to
 * it.
 *
 * @author sxydh
 */
public enum Singleton {
    INSTANCE;

    private int id;
    private String name;
    private String phone;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.getDeclaringClass()
                //
                + "\n"
                //
                + "id: " + this.id + "\n"
                //
                + "name: " + this.name + "\n"
                //
                + "phone: " + this.phone + "\n";
    }

}
