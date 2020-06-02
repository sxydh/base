# 引入当前目录下的模块
from module_a import *
module_a_method1()

# 引入其它目录下的模块
import sys
sys.path.append('../')
from extend.car import Car
my_new_car = Car('audi', 'a4', 2016)
print(my_new_car.get_descriptive_name())
my_new_car.odometer_reading = 23
my_new_car.read_odometer()
