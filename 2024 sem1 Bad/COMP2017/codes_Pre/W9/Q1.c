#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<string.h>

int main(){

	int my_pipe[2];

	if (pipe(my_pipe) == -1){
		perror("pipe");
		return 1;
	}

	pid_t pid = fork();


	if (pid == -1){
		perror("wtf?");
		return 2;
	}

	if (pid == 0){// im in fucking child
		const char *str = "LinGaoYuan Moment";
		close(my_pipe[0]);
		printf("Start writing into my_pipe\n");
		write(my_pipe[1],str,strlen(str));
		close(my_pipe[1]);
	}else{ // im in parent process
		sleep(1);
		printf("I'm in parent process,with child's pid: %d\n", pid);
		close(my_pipe[1]);
		char buffer[64];

		ssize_t ReadedBuffer = read(my_pipe[0],buffer,sizeof(buffer));

		if (ReadedBuffer == -1){
			perror("Read unsuccess");
			return 3;
		}else if (ReadedBuffer == 0){
			perror("reach end of the pipe");
			return 4;
		}else{
			buffer[ReadedBuffer] = '\0';
			printf("buffer: %s\n", buffer);
		}
	}

	return 0;
}