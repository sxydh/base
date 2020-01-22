package cn.net.bhe.spring.framework.circulardep;

public class TestA {
    
    TestB testB;
    
    public TestA() {
    }

    public TestB getTestB() {
        return testB;
    }

    public void setTestB(TestB testB) {
        this.testB = testB;
    }

}
