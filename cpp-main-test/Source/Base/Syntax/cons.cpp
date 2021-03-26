#include <iostream>

using namespace std;

/*
 * const
 */

/*
 * const修饰一般变量
 */
void cons_comm() {
    const int i = 1; // 等价于 int const j = 2;
    const int iarr[] = {0, 1, 2, 3}; // 等价于 int const iarr[] = {0, 1, 2, 3};
}

/*
 * const修饰指针变量时，基本含义如下：
 * - 如果唯一的const位于符号*的左侧，则表示指针所指数据是常量，数据不能通过本指针改变，但可以通过其他方式进行修改；指针本身是变量，可以指向其它的内存单元。
 * - 如果唯一的const位于符号*的右侧，则标识指针本身是常量，不能让该指针指向其他内存地址；指针所指的数据可以通过本指针进行修改。
 * - 在符号*的左右个一个const时，标识指针和指针所指的数据都是常量，既不能让指针指向其他地址，也不能通过指针修改所指向的内容。
 */
void cons_p() {
    int i = 0;
    const int *l = &i;
//    *l = 9; // 不能通过指针修改变量值

    int* const r = &i;
//    r = new int(1); // 不能将r指向其它地址
}

/*
 * const修饰函数返回的指针或引用，其作用为了防止将函数表达式作为左值
 */
const string* cons_frp() {
    return new string("cons_frp");
}

int main() {
    cout << *cons_frp() << endl;
    return 0;
}