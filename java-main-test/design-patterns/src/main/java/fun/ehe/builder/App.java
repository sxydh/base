package fun.ehe.builder;

import fun.ehe.builder.Phone.PhoneBuilder;

/**
 * See {@link Director}
 *
 * @author sxydh
 */
public class App {

    public static void main(String[] args) {
        Director director = new Director();
        /*Car car = director.construct(new CarBuilder());
        System.out.println(car);*/
        Phone phone = director.construct(new PhoneBuilder());
        System.out.println(phone);
    }

}
