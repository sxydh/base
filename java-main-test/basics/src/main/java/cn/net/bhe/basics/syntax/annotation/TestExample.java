package cn.net.bhe.basics.syntax.annotation;

import cn.net.bhe.basics.syntax.annotation.TesterInfo.Priority;

@TesterInfo(priority = Priority.HIGH, createdBy = "mkyong.com", tags = { "sales", "test" })
public class TestExample {

    @Test // 默认值是true
    void testA() {
        if (true) throw new RuntimeException("This test always failed");
    }

    @Test(enabled = false)
    void testB() {
        // This test always passed
    }

    @Test(enabled = true)
    void testC() {
        if (10 > 1) {
            // do nothing, this test always passed.
        }
    }

}
