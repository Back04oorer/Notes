#include<stdio.h>
#include<stdlib.h>



int main(int argc, char** argv){
	int len = 0 ;,
	sscanf(argv[1],"%d",&len);

	srand(2);

	for (int i=0; i < len; i++){
		char x = rand() % (128 - 32 + 1) + 32;
		putchar(x);
	}

	return 1;
}