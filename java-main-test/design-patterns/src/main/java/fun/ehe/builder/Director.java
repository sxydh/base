package fun.ehe.builder;

/**
 * Intent: <br/>
 * Separate the construction of a complex object from its representation so that
 * the same construction process can create different representations.
 * 
 * @author sxydh
 */
public class Director {

    public <X> X construct(Builder<X> builder) {
        return builder
                //
                .buildRequirement()
                //
                .buildDesign()
                //
                .buildDevelopment()
                //
                .buildTest()
                //
                .getProduct();
    }
}
