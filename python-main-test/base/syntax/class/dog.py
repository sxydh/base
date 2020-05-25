"""
Python编程：从入门到实践 [美]埃里克·马瑟斯
9.1-9.2
"""


class Dog:
    """
    首字母大写的名称指的是类。这个类定义中的括号是空的，因为我们要从空白创建这个类。
    注意注释的位置。
    """

    def __init__(self, name, age):
        """
         __init__() 是一个特殊的方法，每当你根据Dog类创建新实例时，Python都会自动运行它。
         在这个方法的名称中，开头和末尾各有两个下划线，这是一种约定，旨在避免Python默认方法与普通方法发生名称冲突。
         
         我们将方法__init__()定义成了包含三个形参：self、name和age。
         在这个方法的定义中，形参self必不可少，还必须位于其他形参的前面。
         为何必须在方法定义中包含形参self呢？因为Python调用这个__init__()方法来创建Dog实例时，将自动传入实参self。
         每个与类相关联的方法调用都自动传递实参self，它是一个指向实例本身的引用，让实例能够访问类中的属性和方法。
         我们创建Dog实例时，Python将调用Dog类的方法__init__()。
         我们将通过实参向Dog()传递名字和年龄；self会自动传递，因此我们不需要传递它。
         每当我们根据Dog类创建实例时，都只需给最后两个形参（name和age）提供值。
        """
        self.name = name
        self.age = age

    def sit(self):
        """Simulate a dog sitting in response to a command."""
        print(self.name.title() + " is now sitting.")

    def roll_over(self):
        """Simulate rolling over in response to a command."""
        print(self.name.title() + " rolled over!")


'''
使用实参'willie'和6调用Dog类中的方法__init__()。
方法__init__()创建一个表示特定小狗的示例，并使用我们提供的值来设置属性name和age。
方法__init__()并未显式地包含return语句，但Python自动返回一个表示这条小狗的实例。
我们将这个实例存储在变量my_dog中。在这里，命名约定很有用：我们通常可以认为首字母大写的名称（如Dog）指的是类，而小写的名称（如my_dog）指的是根据类创建的实例。
'''
my_dog = Dog('willie', 6)
your_dog = Dog('lucy', 3)

print("My dog's name is " + my_dog.name.title() + ".")  # title()将首字母改为大写
print("My dog is " + str(my_dog.age) + " years old.")
my_dog.sit()

print("\nMy dog's name is " + your_dog.name.title() + ".")
print("My dog is " + str(your_dog.age) + " years old.")
your_dog.sit()
