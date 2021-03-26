#include<iostream>

using namespace std;

class A;

/**
 * 声明为A的友元时，B::add_af需要前置声明
 */
class B {
public:
    int add_af(A &a, int i);
};

class A {
    int a;
public:
    A(int i) {
        a = i;
    }

    friend int B::add_af(A &a, int i);
    friend void pf(A &a);
};

/**
 * 声明为A的友元时，不需要前置声明
 * @param a
 */
void pf(A &a) {
    cout << a.a << endl;
}

int B::add_af(A &a, int i) {
    return a.a + i;
}

int main() {
    A a(1);
    B b;
    cout << b.add_af(a, 2) << endl;

    pf(a);
    return 0;
}