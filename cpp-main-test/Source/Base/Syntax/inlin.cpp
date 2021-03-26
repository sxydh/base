#include <iostream>
using namespace std;

/*
 * 内联函数
 * 
 * - 内联函数不是在调用时发生控制转换，而是在编译时将函数体嵌入在每一个调用处。
 * - 如果成员函数定义在类体内部，则默认是内联函数。也可以在类体内部声明函数，并加上inline关键字，然后在类体外给出函数定义，这样的成员函数也是内联函数。
 * - 内联函数一般来说仅适用于只有几条语句的小函数。
 */
inline int Max(int x, int y)
{
	return (x > y) ? x : y;
}

int main()
{
	cout << "Max (20,10): " << Max(20, 10) << endl;
	return 0;
}
