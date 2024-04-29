#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/select.h>
#include <sys/types.h>
#include <sys/wait.h>

#define str "This is a message from child"
int main() {
    int pipe_fd[2]; 
    pid_t pid;

    if (pipe(pipe_fd) == -1) { 
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    pid = fork(); 

    if (pid == -1) {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {
        sleep(6);
        close(pipe_fd[0]); 
        write(pipe_fd[1], str, sizeof(str)); 
        close(pipe_fd[1]); 
        exit(0);
    } else { // 父进程
        fd_set readfds;
        FD_ZERO(&readfds);
        FD_SET(pipe_fd[0], &readfds); 

        struct timeval timeout;
        timeout.tv_sec = 5; 
        timeout.tv_usec = 0;

        int retval = select(pipe_fd[0] + 1, &readfds, NULL, NULL, &timeout); //

        if (retval == -1) {
            perror("select");
            exit(EXIT_FAILURE);
        } else if (retval == 0) {
            printf("No message from child\n");
        } else {
            if (FD_ISSET(pipe_fd[0], &readfds)) { 
                char buffer[256];
                ssize_t len = read(pipe_fd[0], buffer, sizeof(buffer) - 1);
                if (len > 0) {
                    buffer[len] = '\0';
                    printf("message from child: %s\n", buffer);
                }
            }
        }

        close(pipe_fd[0]);
        wait(NULL); 
    }

    return 0;
}

