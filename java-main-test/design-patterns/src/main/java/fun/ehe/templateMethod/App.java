package fun.ehe.templateMethod;

/**
 * Intent: <br/>
 * Define the skeleton of an algorithm in an operation, deferring some steps to
 * subclasses. Template Method lets subclasses redefine certain steps of an
 * algorithm without changing the algorithm's structure.
 */
public class App {
    public static void main(String[] args) {

        Tea tea = new Tea();
        Coffee coffee = new Coffee();

        System.out.println("\nMaking tea...");
        tea.prepareRecipe();

        System.out.println("\nMaking coffee...");
        coffee.prepareRecipe();

        TeaWithHook teaHook = new TeaWithHook();
        CoffeeWithHook coffeeHook = new CoffeeWithHook();

        System.out.println("\nMaking tea...");
        teaHook.prepareRecipe();

        System.out.println("\nMaking coffee...");
        coffeeHook.prepareRecipe();
    }
}
