#include <stdio.h>
#include <string.h>

int main(int argc, char **argv){
	char *input1 = "tofu island";
	char *input2 = "tofu island";
	char *input3 = "Tofu island";
	char *input4 = "tofu islanD";

	if (! strcmp(input1, input2)) {
		printf("input 1 and 2 are OK\n");
	}

	if (strcmp(input1, input2)) {
		printf("input 1 and 2 are NOT OK\n");
	}

	if (! strcmp(input2, input3)) {
		printf("input 2 and 3 are OK\n");
	}

	if (! strncmp(input1, input2, 5)) {
		printf("input 3 and 4 are OK\n");
	}
	if (! strcmp(input4, input1)) {
		printf("input 4 and 1 are OK\n");
	}
	return 0;
}