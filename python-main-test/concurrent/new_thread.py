import _thread as thread
import time
import threading


# 为线程定义一个函数
def print_time(threadName, delay=0):
    count = 0
    while True:
        time.sleep(delay)
        count += 1
        print("%s: %s" % (threadName, time.ctime(time.time())))
        threading.currentThread().ident


threading.currentThread().ident

# 创建两个线程
try:
    thread.start_new_thread(print_time, ("Thread-1",))
    thread.start_new_thread(print_time, ("Thread-2",))
except:
    print("Error: unable to start thread")

while 1:
    pass
