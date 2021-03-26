#include<iostream>

using namespace std;

/*
 * 复制构造函数
 *
 * 特性
 * - 复制构造函数是构造函数的一种，也称为拷贝构造函数。它的作用是使用一个已存在的对象去初始化另一个正在创建的对象。
 * - 复制构造函数只有一个参数，参数类型是本类的引用。复制构造函数的参数可以是const引用，也可以是非const引用。
 * - 如果类中没有给出复制构造函数，那么编译器会自动生成一个复制构造函数。在大多数情况下，其作用是实现从源对象到目标对象逐个字节的复制，
 *   即使得目标对象的每个成员变量都变得和源对象相等。编译器自动生成的复制构造函数称为默认复制构造函数。默认构造函数不一定存在，但是复制构
 *   造函数总是存在的。如果程序定义了复制构造函数，则编译器只调用自定义的复制构造函数。
 *
 * 复制构造函数调用的三种情况
 * - 当用一个对象去初始化本类的另一个对象时；
 * - 如果函数F的参数是类A的对象，那么调用F时，会调用类A的复制构造函数；
 * - 如果函数的返回值是类A的对象，那么当函数返回时，会调用类A的复制构造函数；
 */

class Student {
public:
    int score;

    Student(int score) {
        this->score = score;
    }

    Student(Student &s) {
        cout << "call Student(Student &s)" << endl;
        this->score = s.score;
    };

};

int main() {
    Student s(98);
    Student s1(s);
    Student s2 = s;
    return 0;
}