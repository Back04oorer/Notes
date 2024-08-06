#include <stdio.h>


int main()
{
	const char * ptr = "hello";
	const char array[] = "hello";
	const char array2[] = { 'h', 'e', 'l', 'l', 'o' };
	const char array3[] = { 'h', 'e', 'l', 'l', 'o', '\0' };
	const char array4[5] = { 'h', 'e', 'l', 'l', 'o' };
	const char array5[6] = { 'h', 'e', 'l', 'l', 'o', 0 };
	const char array6[20] = { 'h', 'e', 'l', 'l', 'o' };
	const char array7[20] = { 0 };
	const char array8[20] = "hello";

	printf("%lu\n", sizeof(array));
	printf("%lu\n", sizeof(array5));
}