#include <iostream>

using namespace std;

/*
 * 运算符重载
 *
 * 在C++中进行运算符重载时，有以下问题需要注意：
 * - 重在后运算符的含义应该符合原有的用法习惯。例如，重载“+”运算符，完成的功能就应该类似于做加法，在重载的“+”运算符中做减法是不合适的。
 * - 运算符重载不能改变运算符原有的语义，包括运算符的优先级和结合性。
 * - 运算符重载不能改变运算符操作数的个数及语法结构。
 * - 不能创建新的运算符，即重载运算符不能超出C++语言允许重载的运算符范围。
 * - 重载运算符“()”“[]”“->”或者赋值运算符“=”时，只能将它们重载为成员函数，不能重载为全局函数。
 * - 运算符重载不能改变该运算符用于基本数据类型对象的含义。
 *
 * 不能重载的运算符：
 * - 成员访问运算符"."
 * - 成员指针访问运算符".*"或"->*"
 * - 域运算符"::"
 * - 长度运算符"sizeof"
 * - 条件运算符"?:"
 * - 预处理符号"#"
 *
 * 只能重载为成员函数的运算符：
 * - 赋值=
 * - 下标[]
 * - 调用()
 * - 成员访问箭头->
 * - 类型转换()
 *
 * 重载强制类型转换运算符：
 * - 不需要形参。
 * - 不需要指定返回值类型，因为返回值类型是确定的，就是运算符本身代表的类型。
 */


class Student {
public:
    int score;

    Student(int score) : score(score) {};

    /***********重载为成员函数***************/
    Student &operator=(const Student &s) { // 赋值
        cout << "call [Student &operator=(const Student &s)], " << s.score << endl;
        this->score = s.score;
        return *this;
    };

    ostream &operator<<(ostream &os) { // 流插入
        cout << "call [ostream &operator<<(ostream &os)], " << this->score << endl;
        cout << this->score << endl;
        return os;
    }

    istream &operator>>(istream &is) { // 流提取
        string str;
        is >> str;
        int old_score = this->score;
        this->score = atoi(str.c_str());
        cout << "call [istream &operator>>(istream &is)], old: " << old_score << ", new: " << this->score << endl;
        return is;
    }

    operator int() { // 强制类型转换
        cout << "call [operator int()]" << endl;
        return this->score;
    }

    Student &operator++() { // 重载前置自增（若需要重载后置，则加一个无用形参int）
        int old_score = score;
        ++score;
        cout << "call [Student &operator++()], old: " << old_score << ", new: " << score << endl;
        return *this;
    }
    /***********重载为成员函数***************/



    /***********重载为友元函数***************/
    friend Student operator-(const Student s1, const Student s2) { // 重载减法
        int diff = s1.score - s2.score;
        cout << "call [friend Student operator-(const Student s1, const Student s2)], " << s1.score << " - " << s2.score
             << " = " << diff << endl;
        return Student(diff);
    };

    friend ostream &operator<<(ostream &os, Student s) { // 重载流插入
        cout << "call [friend ostream &operator<<(ostream &os, Student s)], " << s.score << endl;
        return os;
    }

    friend istream &operator>>(istream &is, Student s) { // 重载流提取
        string str;
        is >> str;
        int old_score = s.score;
        s.score = atoi(str.c_str());
        cout << "call [istream &operator>>(istream &is)], old: " << old_score << ", new: " << s.score << endl;
        return is;
    }
    /***********重载为友元函数***************/
};

int main() {
    Student s1(99);
    Student s2(60);
    Student s3(59);

    Student s = s1 = s2 = s3; // 重载赋值运算符，从右往左，注意最后一步Student s = ?调用的是复制构造函数
    s << (cout << endl) << endl; // 重载流插入运算符（成员方式），注意调用形式
    s >> (cin); // 重载流提取运算符（成员方式）
    Student diff = s1 - s2; // 重载减法运算符（友元方式）
    cout << s << endl; // 重载流插入运算符（友元方式）
    cin >> s; // 重载流提取运算符（友元方式）
    int i = (int) s; // 重载强制类型转换
    ++s; // 重载前置自增

    return 0;
}