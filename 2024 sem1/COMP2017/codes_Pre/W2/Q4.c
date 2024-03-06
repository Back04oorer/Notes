#include <stdio.h>
#include <string.h>

int main(int argc, char **argv){
	char z[100] ;
	printf("%p %p %td\n", &z[1], z + 5, &z[12] - &z[9]);
	// %td to decimal?

	
}