#include<stdio.h>

int main() {
    char ch;
    while((ch=getchar()) != EOF ){
    	if (ch >= 'a' && ch <= 'z'){
    		ch = ch - 32;
    		putchar(ch);
    	}else{
    		putchar(ch);
    	}
    }
    return 0;
}