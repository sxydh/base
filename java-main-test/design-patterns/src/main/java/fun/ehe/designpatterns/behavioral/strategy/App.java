package fun.ehe.designpatterns.behavioral.strategy;

/**
 * See {@link Duck}
 */
public class App {

    public static void main(String[] args) {

        MallardDuck mallard = new MallardDuck();
        RubberDuck rubberDuckie = new RubberDuck();
        DecoyDuck decoy = new DecoyDuck();

        mallard.performQuack();
        mallard.performFly();

        rubberDuckie.performQuack();
        decoy.performQuack();

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
