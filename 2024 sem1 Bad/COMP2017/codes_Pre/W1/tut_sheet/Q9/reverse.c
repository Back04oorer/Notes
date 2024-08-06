#include <stdio.h>
#include <stdbool.h>
#include <string.h> 

int main(void) {
    while(true){
        char str[100];
        fgets(str, sizeof(str),stdin);

        int len = strlen(str);
        printf("length is %d", len);
        for(int i = len - 1; i >=0 ; i--){
            if(str[i] == '\n')continue;
             printf("%c", str[i]);
        }
        printf("\n");
    }
}