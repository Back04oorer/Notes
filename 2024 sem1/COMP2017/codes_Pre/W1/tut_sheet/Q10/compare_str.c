#include <stdio.h>
#include <string.h> 

int string_compare(const char* w1, const char* w2, unsigned int len){
    for (int i = 0 ; i < len; i++){
        if( w1[i] != w2[i]){
            return 0;
        }
    }
    return 1;
}


int main(void) {
    char str1[] = "cnm";
    char str2[] = "cnm";
    printf("%d\n", string_compare(str1,str2,3));
}
