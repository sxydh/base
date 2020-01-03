package fun.ehe.factory.abstractFactory;

/**
 * Intent: </br>
 * Provide an interface for creating families of related or dependent objects
 * without specifying their concrete classes.
 */
public interface PizzaIngredientFactory {

    public Dough createDough();

    public Sauce createSauce();

    public Cheese createCheese();

    public Veggies[] createVeggies();

    public Pepperoni createPepperoni();

    public Clams createClam();

}
