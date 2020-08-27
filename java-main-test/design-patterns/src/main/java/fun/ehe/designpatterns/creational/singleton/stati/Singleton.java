package fun.ehe.designpatterns.creational.singleton.stati;

/**
 * It should be noted that the parent class instance needs to exist before the
 * subclass is built. To ensure the parent class's singleton, the
 * {@link fun.ehe.singleton.stati.SubClass} constructor needs special handling.
 *
 * @author sxydh
 */
public class Singleton {

    /**
     * About the volatile: <br/>
     * Suppose that two threads are working on SharedObj. If two threads run on
     * different processors each thread may have its own local copy of
     * sharedVariable. If one thread modifies its value the change might not
     * reflect in the original one in the main memory instantly. This depends on
     * the write policy of cache. Now the other thread is not aware of the
     * modified value which leads to data inconsistency.
     */
    private volatile static Singleton uniqueInstance;

    protected Singleton() {
    }

    /**
     * Use "synchronized" to prevent multiple instances from being created, pay
     * attention to the location of the "synchronized", otherwise it will reduce
     * the concurrency performance when obtained the instance.
     *
     * @return
     */
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

    /**
     * This ensures that nobody can create another instance by serializing and
     * deserializing the singleton.
     * <p/>
     * For Serializable and Externalizable classes, the readResolve method
     * allows a class to replace/resolve the object read from the stream before
     * it is returned to the caller. By implementing the readResolve method, a
     * class can directly control the types and instances of its own instances
     * being deserialized.
     * <p/>
     * The readResolve method is called when ObjectInputStream has read an
     * object from the stream and is preparing to return it to the caller.
     * ObjectInputStream checks whether the class of the object defines the
     * readResolve method. If the method is defined, the readResolve method is
     * called to allow the object in the stream to designate the object to be
     * returned. The object returned should be of a type that is compatible with
     * all uses. If it is not compatible, a ClassCastException will be thrown
     * when the type mismatch is discovered.
     *
     * @return
     */
    private Object readResolve() {
        return uniqueInstance;
    }
}
