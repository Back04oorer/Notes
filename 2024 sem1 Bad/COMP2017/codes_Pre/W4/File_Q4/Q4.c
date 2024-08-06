#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
int main(int argc, char** argv) {
	// TODO: write to a file of your choice using fopen and fprintf
	FILE *f1 = fopen("example.txt","w");
	fprintf(f1, "nothing happends\nalright,i am actually doing something.");
	fclose(f1);

	// TODO: read from a file of your choice using fopen and fscanf
	char buffer[100];
	FILE *f2 = fopen("example.txt","r");

	//fscanf(f2,"%s",buffer);
	/*this shit stopped, when meet a space or '\0' or '\n'*/

	while(fgets(buffer,sizeof(buffer),f2)){
		printf("%s", buffer);
	}
	// TODO: write to stdout and stderr using fprintf
	return 0;
}