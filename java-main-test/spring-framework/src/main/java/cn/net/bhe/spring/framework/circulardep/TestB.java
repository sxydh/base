package cn.net.bhe.spring.framework.circulardep;

public class TestB {
    
    TestA testA;
    
    public TestB(TestA testA) {
        this.testA = testA;
    }

}
