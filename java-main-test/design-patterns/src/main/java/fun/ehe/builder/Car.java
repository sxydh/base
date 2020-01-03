package fun.ehe.builder;

public class Car {

    private Engine engine;
    private Chassis chassis;
    private Body body;
    private Electronics electronics;

    private Car(CarBuilder builder) {
        engine = builder.engine;
        chassis = builder.chassis;
        body = builder.body;
        electronics = builder.electronics;
    }

    public static class CarBuilder extends Builder<Car> {

        private Engine engine;
        private Chassis chassis;
        private Body body;
        private Electronics electronics;

        @Override
        public Builder<Car> buildRequirement() {
            System.err.println("Which kind of car is needed in the market?");
            return this;
        }

        @Override
        public Builder<Car> buildDesign() {
            System.err.println("Car drawing design");
            return this;
        }

        @Override
        public Builder<Car> buildDevelopment() {
            engine = new Engine();
            chassis = new Chassis();
            body = new Body();
            electronics = new Electronics();
            System.err.println("Manufacturing parts and assembling");
            return this;
        }

        @Override
        public Builder<Car> buildTest() {
            System.err.println("Vehicle performance test");
            return this;
        }

        @Override
        public Car getProduct() {
            return new Car(this);
        }
    }

    public static class Engine {

    }

    public static class Chassis {

    }

    public static class Body {

    }

    public static class Electronics {

    }

    public Engine getEngine() {
        return engine;
    }

    public Chassis getChassis() {
        return chassis;
    }

    public Body getBody() {
        return body;
    }

    public Electronics getElectronics() {
        return electronics;
    }

    @Override
    public String toString() {
        return "\nProduct is ".toString()
                //
                + super.toString() + ", it includes: " + "\n"
                //
                + "engine: " + this.engine + "\n"
                //
                + "chassis: " + this.chassis + "\n"
                //
                + "body: " + this.body + "\n"
                //
                + "electronics: " + this.electronics + "\n";
    }
}
