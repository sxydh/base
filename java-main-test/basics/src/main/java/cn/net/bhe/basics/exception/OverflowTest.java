package cn.net.bhe.basics.exception;
import java.util.Arrays;
import java.util.EmptyStackException;

// Can you spot the "memory leak"?
public class OverflowTest {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public static void main(String[] args) {
        Integer i = Integer.MAX_VALUE;
        System.out.println(i + 10);
    }

    public OverflowTest() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    /**
     * Ensure space for at least one more element, roughly doubling the capacity
     * each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
