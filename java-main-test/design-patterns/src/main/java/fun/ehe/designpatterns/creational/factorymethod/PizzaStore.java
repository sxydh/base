package fun.ehe.designpatterns.creational.factorymethod;

/**
 * Intent: </br>
 * Define an interface for creating an object, but let subclasses decide which
 * class to instantiate. Factory Method lets a class defer instantiation to
 * subclasses.
 */
public abstract class PizzaStore {

    abstract Pizza createPizza(String item);

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        System.out.println("--- Making a " + pizza.getName() + " ---");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
