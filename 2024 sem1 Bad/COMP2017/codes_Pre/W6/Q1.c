#include<stdio.h>
#include <unistd.h>

int main(){
	char s[] = "Hello World\n";
	int buffer_written = write(1,s,sizeof(s));
}