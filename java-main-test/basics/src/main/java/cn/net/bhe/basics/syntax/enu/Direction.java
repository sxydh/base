package cn.net.bhe.basics.syntax.enu;

public enum Direction {

    /** 
     * Logically, each enum is an instance of enum type itself. 
     * 每个枚举成员都是该枚举类型的一个实例，每声明一个枚举成员时，相当于在构建一个该枚举类型的实例，带参数相当于调用了有参构造器
     */
    EAST(0), WEST(180), NORTH(90),
    
    /**
     * We can add abstract method in enums. In this case, we must implement the
     * abstract method at each enum field, individually.
     * 枚举可以有抽象方法，但是枚举成员必须实现该方法，实现方式和构建一个匿名类类似
     */
    SOUTH(270) {
        @Override
        public String printDirection() {
            String message = "You are moving in south. Sea ahead.";
            return message;
        }
    },
    
    NODIR;

    /**
     * 枚举可以有私有字段，和类实例的私有字段类似
     */
    private int angle = 0;

    /**
     * 枚举不需要显示声明一个无参构造器（在没有有参构造器的情况下）
     */
    private Direction() {}

    /**
     * 枚举的有参构造器
     * @param angle
     */
    private Direction(final int angle) {
        this.angle = angle;
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
    
}
