package cn.net.bhe.basics.base.enu;

/**
 * <p/>
 * Java enum, also called Java enumeration type, is a type whose fields consist
 * of a fixed set of constants. The very purpose of enum is to enforce compile
 * time type safety. enum keyword is reserved keyword in Java.
 * <p/>
 * We should use enum when we know all possible values of a variable at compile
 * time or design time, though we can add more values in future as and when we
 * identify them.
 * <p/>
 * As noted all enums extends java.lang.Enum, so enum cannot extend any other
 * class because Java does not support multiple inheritance this way. But enums
 * can implement any number of interfaces.
 * <p/>
 * All enums are by default comparable and singletons as well. It means you can
 * compare them with equals() method, even with "==" operator.
 * <p/>
 * Summary:
 * <ul>
 * <li>Enums are implicitly final subclasses of {@link java.lang.Enum}.
 * <li>If an enum is a member of a class, it's implicitly static.
 * <li>New keyword can not be used to intialize an enum, even within the enum
 * type itself.
 * <li>Name() and valueOf() methods simply use the text of the enum constants,
 * while toString() method may be overridden to provide any content, if desired.
 * <li>For enum constants, equals() and "==" evaluates to same result, and can
 * be used interchangeably.
 * <li>Enum constants are implicitly public static final.
 * <li>The order of appearance of list of enum constants is called their
 * "natural order", and defines the order used by other items as well:
 * {@link java.lang.Enum#compareTo(Enum)} method, iteration order of values in
 * {@link java.util.EnumSet#range(Enum, Enum)}.
 * <li>Enum constructors should be declared as private. The compiler allows non
 * private constructors, but this seems misleading to the reader, since new can
 * never be used with enum types.
 * <li>Since these enumeration instances are all effectively singletons, they
 * can be compared for equality using identity ("==").
 * <li>You can use enum in switch statement like int or char primitive data
 * type.
 * </ul>
 *
 * @see <a href=
 *      "https://howtodoinjava.com/java/enum/guide-for-understanding-enum-in-java/">reference</a>
 * @author sxydh
 */
public enum Direction {

    /** Logically, each enum is an instance of enum type itself. */
    EAST(0),
    /***/
    WEST(180),
    /***/
    NORTH(90),
    /**
     * We can add abstract method in enums. In this case, we must implement the
     * abstract method at each enum field, individually.
     */
    SOUTH(270) {

        @Override
        public String printDirection() {
            String message = "You are moving in south. Sea ahead.";
            return message;
        }
    };

    private int angle;

    /**
     * By default, enums don’t require constructor definitions and their default
     * values are always the string used in the declaration. Though, you can
     * give define your own constructors to initialize the state of enum types.
     */
    private Direction() {

    }

    private Direction(final int angle) {

    }

    /**
     * Remember that enum is basically a special class type, and can have
     * methods and fields just like any other class. You can add methods which
     * are abstract as well as concrete methods as well. Both methods are
     * allowed in enum.
     *
     * @return
     */
    protected String printDirection() {
        String message = "You are moving in " + this + " direction";
        System.out.println(message);
        return message;
    }

    public int getAngle() {
        return angle;
    }

    public static void main(String[] args) {
        System.out.println(EAST.ordinal()); // 0
    }

}
