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
    char line[200];
    char h[] = "sad";
    while(fgets(line, sizeof(line), stdin)){
        //starts with 
        for (int i=0; i < strlen(line)-strlen(h) + 1; i++)
        {
            if(string_compare(&line[i],h,strlen(h)) == 1){
                printf("Found: %s", line);
            }
        }
    }
}