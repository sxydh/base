#include<iostream>
#include<math.h>
#include <cstring>

using namespace std;

/*
 * https://stackoverflow.com/questions/24790287/templates-int-t-c
 * template <int N> struct Foo                        N is a value
 * template <typename T> struct Bar                   T is a type
 * template <template <typename> class X> struct Zip  X is a template
 */

template<typename T1, class T2>
int compare(T1 t1, T2 t2) {
    if (t1 > t2) return 1;
    if (t1 == t2) return 0;
    return -1;
};

template <int a>
bool minu(int x) {
    return x - a;
};

int main() {
    cout << compare(1, 2) << endl;
    cout << minu<2>(3) << endl;
    return 0;
}