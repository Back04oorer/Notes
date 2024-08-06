#include <stdio.h>

int main() {
    char buf[100];
    int number = 12;

    buf[0] = (char)number; 

    printf("buf[0] contains: %d\n", (unsigned char)buf[0]);

    return 0;
}