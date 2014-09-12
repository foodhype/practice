#include <iostream>
#include <stdlib.h>
#include <vector>
using namespace std;


template<class T>
vector<T> push(vector<T> &, T);

int main() {
    string x[10] = {};
    x[0] = "AAA";
    x[1] = "BBB";
    x[2] = "CCC";
    x[3] = "DDD";
    for (int i = 0; i < 10; i++) {
        cout << x[i] << endl;
    }

    cout << endl;
    int * blah = new int[10];
    for (int i = 0; i < 10; i++) {
        blah[i] = i + 1;
    }
    for (int i = 0; i < 10; i++) {
        cout << *(blah + i) << endl;
    }
    cout << endl;

    delete[] blah;

    vector<bool> arr;
    push(arr, false);
    push(arr, true);
    push(arr, false);
    for (int i = 0; i < arr.size(); i++) {
        cout << arr[i] << endl;
    }

    return 0;
}

template<class T>
vector<T> push(vector<T> & arr, T something) {
    arr.push_back(something);

    return arr;
}
