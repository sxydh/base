package cn.net.bhe.cmpp.huawei;

public class Config {

    public static final String SPID = "333";
    public static final String PWD = "0555";
    public static final String IP = "172.16.20.130";
    public static final int PORT = 7890;
    
    public static class CommandId {
        public static final int CMPP_CONNECT = 0x00000001;
        public static final int CMPP_CONNECT_RESP = 0x80000001;
        
        public static final int CMPP_TERMINATE = 0x00000002;
        public static final int CMPP_TERMINATE_RESP = 0x80000002;
        
        public static final int CMPP_SUBMIT = 0x00000004;
        public static final int CMPP_SUBMIT_RESP = 0x80000004;
        
        public static final int CMPP_DELIVER = 0x00000005;
        public static final int CMPP_DELIVER_RESP = 0x80000005;
        
        public static final int CMPP_ACTIVE_TEST = 0x00000008;
        public static final int CMPP_ACTIVE_TEST_RESP = 0x80000008;
    }
    
    public static class Login {
        public static final int SUC = 0;
    }
    
    public static class RegisteredDelivery {
        public static final int IS_REPORT = 1;
    }
    
}
