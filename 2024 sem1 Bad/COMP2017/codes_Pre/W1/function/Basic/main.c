#include <stdio.h>
#include "calculation.h"

float float_sum(float num1, float num2){
	return num1 + num2;
}


int main(){
	/*
	invoke function that was defined in calculation.h
	*/
	printf("The sum of 10 and 8 is %d \n",int_sum(10,8));

	/*
	invoke function that was defined in .c file
	*/
	printf("The sum of 6.4 and 3.6 is %.1f \n",float_sum(6.4,3.6));

	return 0;

}