package cn.net.bhe.springframework.circulardep;

public class TestB {
    
    TestA testA;
    
    public TestB() {
    }

    public TestA getTestA() {
        return testA;
    }

    public void setTestA(TestA testA) {
        this.testA = testA;
    }

}
