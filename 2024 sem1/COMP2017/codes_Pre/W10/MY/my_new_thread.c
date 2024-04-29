#include <stdio.h>
#include <unistd.h>
#include <pthread.h>


pthread_t my_thread;

void* threadF (void* arg){
	printf("Fuck you! From pthread\n");
	sleep(20);
	return 0;
}

int main(void){
	pthread_create(&my_thread,NULL,threadF,NULL);
	printf("Fuck you main!\n");
	pthread_join(my_thread,NULL);
	return 0;
}