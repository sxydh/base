package cn.net.bhe.basics.io;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.SerializationUtils;

public class SerializationProxyTest {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Date start = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date end = calendar.getTime();
        Period period = new Period(start, end);
        byte[] bytes = SerializationUtils.serialize(period);
        Period des = (Period) SerializationUtils.deserialize(bytes);
        System.out.println(des);
    }
}

//Immutable class that uses defensive copying
final class Period implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Date start;
    private final Date end;

    /**
     * @param start
     *            the beginning of the period
     * @param end
     *            the end of the period; must not precede start
     * @throws IllegalArgumentException
     *             if start is after end
     * @throws NullPointerException
     *             if start or end is null
     */
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (this.start.compareTo(this.end) > 0)
            throw new IllegalArgumentException(start + " after " + end);
    }

    // Serialization proxy for Period class
    private static class SerializationProxy implements Serializable {
        private final Date start;
        private final Date end;

        SerializationProxy(Period p) {
            this.start = p.start;
            this.end = p.end;
        }

        // readResolve method for Period.SerializationProxy
        private Object readResolve() {
            return new Period(start, end); // Uses public constructor
        }

        private static final long serialVersionUID = 234098243823485285L; // Any number will do (Item 75)
    }

    // readObject method for the serialization proxy pattern
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    // writeReplace method for the serialization proxy pattern
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }
}
