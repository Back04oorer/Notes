#include <stdio.h>
#include "my_functions.h"

int factorial(int num){
	if(num < 0){
		printf("Please enter a positive integer\n");
		return -1;
	}
	int res = 1;
	for(int i=num; i > 0 ;i--){
		res = res * i;
	}
	return res;
}
