package fun.ehe.www.cal_prime;

public class MainActivity extends android.app.Activity {
    static final String UPPER_NUM = "upper";
    android.widget.EditText etNum;
    fun.ehe.www.cal_prime.MainActivity.CalThread calThread;

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etNum = (android.widget.EditText) findViewById(R.id.etNum);
        calThread = new fun.ehe.www.cal_prime.MainActivity.CalThread();
        // 启动新线程
        calThread.start();
    }

    // 为按钮的点击事件提供事件处理方法
    public void cal(android.view.View source) {
        // 创建消息
        android.os.Message msg = new android.os.Message();
        msg.what = 0x123;
        android.os.Bundle bundle = new android.os.Bundle();
        bundle.putInt(UPPER_NUM,
                Integer.parseInt(etNum.getText().toString()));
        msg.setData(bundle);
        // 向新线程中的Handler发送消息
        calThread.mHandler.sendMessage(msg);
    }

    // 定义一个线程类
    class CalThread extends Thread {
        public android.os.Handler mHandler;

        public void run() {
            android.os.Looper.prepare();
            mHandler = new android.os.Handler() {
                // 定义处理消息的方法
                @Override
                public void handleMessage(android.os.Message msg) {
                    if (msg.what == 0x123) {
                        int upper = msg.getData().getInt(UPPER_NUM);
                        java.util.List<Integer> nums = new java.util.ArrayList<Integer>();
                        // 计算从2开始、到upper的所有质数
                        outer:
                        for (int i = 2; i <= upper; i++) {
                            // 用i除以从2开始、到i的平方根的所有数
                            for (int j = 2; j <= Math.sqrt(i); j++) {
                                // 如果可以整除，则表明这个数不是质数
                                if (i != 2 && i % j == 0) {
                                    continue outer;
                                }
                            }
                            nums.add(i);
                        }
                        // 使用Toast显示统计出来的所有质数
                        android.widget.Toast.makeText(fun.ehe.www.cal_prime.MainActivity.this,
								nums.toString()
                                , android.widget.Toast.LENGTH_LONG).show();
                    }
                }
            };
            android.os.Looper.loop();
        }
    }
}

