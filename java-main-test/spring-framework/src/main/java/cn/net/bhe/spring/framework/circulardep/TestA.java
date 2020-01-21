package cn.net.bhe.spring.framework.circulardep;

public class TestA {
    
    TestB testB;
    
    public TestA(TestB testB) {
        this.testB = testB;
    }

}
