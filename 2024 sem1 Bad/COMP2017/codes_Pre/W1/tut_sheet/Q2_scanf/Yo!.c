#include <stdio.h>

int main()
{
	/*
	char * is a pointer,ponits to the first character of characters data's address
	%s expects char *
	variable "name" will be automatically converted to the pointer that points the first char's address
	*/
	char name[100];
	printf("What is your name?\n");
	scanf("%s",name);
	printf("Hi %s , your fuckin idiot!\n",name);
	return 0;
}