package fun.ehe.designpatterns.behavioral.iterator;

import java.util.ArrayList;

/**
 * Intent: <br/>
 * Provide a way to access the elements of an aggregate object sequentially
 * without exposing its underlying representation.
 */
public class App {
    public static void main(String args[]) {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();
        ArrayList<Menu> menus = new ArrayList<Menu>();
        menus.add(pancakeHouseMenu);
        menus.add(dinerMenu);
        Waitress waitress = new Waitress(menus);
        waitress.printMenu();

    }

}
