package fun.ehe.designpatterns.creational.builder;

public abstract class Builder<X> {

    public Builder<X> buildRequirement() {
        return this;
    }

    public Builder<X> buildDesign() {
        return this;
    }

    public Builder<X> buildDevelopment() {
        return this;
    }

    public Builder<X> buildTest() {
        return this;
    }

    public X getProduct() {
        return null;
    }
}
