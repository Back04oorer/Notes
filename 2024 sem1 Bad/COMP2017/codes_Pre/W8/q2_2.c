#include <stdio.h>
#include <unistd.h>

int main(void) {
	puts("about to launch sort");
	
	if (execl("/usr/bin/sort", "sort", "forkdemo.c", NULL) == -1) {
		perror("exec failed");
		return 1;
	}

	puts("finished sort");
	return 0;
}