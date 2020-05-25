"""
Python编程：从入门到实践 [美]埃里克·马瑟斯
9.3
"""
from car import Car


class ElectricCar(Car):
    """
    创建子类时，父类必须包含在当前文件中，且位于子类前面。
    定义子类时，必须在括号内指定父类的名称。方法__init__()接受创建Car实例所需的信息。
    """

    def __init__(self, manufacturer, model, year):
        """
        super()是一个特殊函数，帮助Python将父类和子类关联起来。
        这行代码让Python调用ElectricCar的父类的方法__init__()，让ElectricCar实例包含父类的所有属性。
        父类也称为超类（superclass），名称super因此而得名。
        """
        super().__init__(manufacturer, model, year)
        self.battery_size = 70  # 添加了新属性，但父类不包含

    def fill_gas_tank(self):
        """
        对于父类的方法，只要它不符合子类模拟的实物的行为，都可对其进行重写。
        为此，可在子类中定义一个这样的方法，即它与要重写的父类方法同名。这样，Python将不会考虑这个父类方法，而只关注你在子类中定义的相应方法。
        """
        return "电动汽车没有油箱"


my_tesla = ElectricCar('tesla', 'model s', 2016)
print(my_tesla.get_descriptive_name())
print(my_tesla.fill_gas_tank())
