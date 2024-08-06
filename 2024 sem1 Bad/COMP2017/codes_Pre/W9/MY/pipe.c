#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main() {
    int pipefd[2];

    if (pipe(pipefd) == -1) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    printf("read file descriptor: %d\n", pipefd[0]);
    printf("write file descriptor: %d\n", pipefd[1]);

    printf("Start fork now \n =============================================\n");

    pid_t pid = fork();

    if (pid == -1){
        perror("something wrong with fork");
    }
    
    if(pid == 0){// child process
        const char *str = "I hate Comp2017\n";
        close(pipefd[0]);// read

        printf("I'm in child\n");
        write(pipefd[1],str,strlen(str));

        printf("I have written something in pipe\n");
        close(pipefd[1]);// write
    }else{
        wait(NULL);
        char buffer[128];

        close(pipefd[1]);// WRITE
        printf("I'm in parent with, and hold child's pid %d\n",pid);

        ssize_t bytesRead = read(pipefd[0], buffer, sizeof(buffer));

        if (bytesRead == -1) {
            perror("read");
        } else {
            printf("bytesRead: %d\n",bytesRead);
            buffer[bytesRead] = '\0';  // 确保缓冲区以 '\0' 结尾

            printf("I have: %s\n", buffer);
        }
        close(pipefd[0]);// read
    }


    return 0;
}
