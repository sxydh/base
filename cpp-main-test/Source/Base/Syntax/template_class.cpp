#include<iostream>
#include<math.h>
#include <cstring>

using namespace std;

template<typename T1, class T2>
class Point {
public:
    T1 x;
    T2 y;

    Point(T1 t1, T2 t2) : x(t1), y(t2) {}
};

int main() {
    Point<int, float> point(1, 3.1);
    cout << point.x << ", " << point.y << endl;
    return 0;
}