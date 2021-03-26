#include <iostream>

using namespace std;

/*
 * 类
 *
 * - 类成员默认是private的
 *
 * 静态成员
 * - 没有const修饰，只能类外初始化
 * - 有const修饰，声明时直接初始化，或类外初始化
 * 非静态成员
▪ - 没有const修饰，声明时直接初始化，或构造函数初始化列表初始化，或构造函数体初始化
▪ - 有const修饰，声明时直接初始化，或构造函数初始化列表初始化
 *
 * - 释放数组动态分配的内存时，delete后必须加[]
 *
 * - 被const修饰的对象必须显示提供构造器
 *
 * - 若方法返回的是引用，则该引用不要尝试指向局部变量，否则局部变量销毁后，该引用就是一个空引用，这是不允许的
 */
class Student {
    int test; // 类成员默认是private的
private:
    string name;
    int age;
    string sex;
public:
    Student() { // 显示声明默认构造函数
        this->name = "Default Name";
        this->age = 28;
    };

    Student(string name, int age) {
        this->name = name;
        this->age = age;
    }

    Student(string name, int age, string sex) : name(name), age(age) { // 不同的成员变量初始化方式
        this->sex = sex;
    }

    ~Student() { // 析构函数
        cout << "~Student: " << this << endl;
    }

    void setName(string name) {
        this->name = name;
    }

    void setAge(int); // 形参可以只给出类型而不给名称

    string getName();

    int getAge() { // 函数可以在声明时给出定义
        return this->age;
    }
};

string Student::getName() { // 类体外定义函数
    return this->name;
}

int funptr(int i) {
    cout << "funptr: " << i << endl;
    return 0;
}

int main() {
    Student a;
    cout << a.getName() << endl;
    cout << a.getAge() << endl;
    cout << &a << endl;

    cout << "---" << endl;

    Student b("Tony", 29);
    cout << b.getName() << endl;
    cout << b.getAge() << endl;
    cout << &b << endl;

    cout << "---" << endl;

    const Student c; // 被const修饰的对象必须显示提供构造器

    cout << "---" << endl;

    Student *d = new Student[3];
    delete[] d; // 必须加[]，否则只释放数组中一个对象的内存

    // 函数指针
    int (*fptr)(int i);
    fptr = funptr;
    funptr(1);
}