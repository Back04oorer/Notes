#include <stdio.h>


int main()
{
	int x[] = { 1, 2, 3 };
	int * p1 = x;
	printf("p1 plus offset followed: %d\n", *(p1++));
}