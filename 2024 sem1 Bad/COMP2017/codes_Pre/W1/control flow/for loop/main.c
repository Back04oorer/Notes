#include <stdio.h>
#include "factorial.h"

int main(){
	// get input(int) from user
	int num = 0;
	printf("Please enter a positive integer:\n");
	/*
	this "&" sign is reading the memory address of variables
	*/
	scanf("%d",&num);
	printf("We are going to calculate the factorial of %d\n",num);
	int res = cal_factorial(num);
	if (res == -1){
		return -1;
	} 
	printf("the factorial of %d is %d\n", num, cal_factorial(num));

	return 0;
}
