#include <stdio.h>

int main (int argc, char **argv)
{
	int ftemp = 0; /* god please no,idk what is ftemp*/ 
	printf("Please enter a fahrenheit temperature  ");
	scanf("%d", &ftemp);
	printf("%d fahrenheit is %d centigrade\n",ftemp,(ftemp - 32) * 5 / 9);
	return 0;
}