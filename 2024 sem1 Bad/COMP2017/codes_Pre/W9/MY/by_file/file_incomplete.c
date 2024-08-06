#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <string.h>

#define FILENAME "shared.txt"

void signal_handler(int signum) {
    if (signum == SIGUSR1) {
        printf("Signal received from child process.\n");
    }
}

int main() {

    signal(SIGUSR1, signal_handler);


    pid_t pid = fork();

    if (pid < 0) {
        // fork 失败
        perror("fork failed");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {

        sleep(1); 

        int fd = open(FILENAME, O_WRONLY | O_CREAT,0644);
        if (fd < 0) {
            perror("open failedZZZZZZZ");
            exit(EXIT_FAILURE);
        }

        const char *message = "Hello from the child process!";

        ssize_t bytes_written = write(fd, message, strlen(message));

        // // 刷新缓冲区，确保数据写入磁盘
        // if (fsync(fd) < 0) {
        //     perror("fsync failed");
        //     close(fd);
        //     exit(EXIT_FAILURE);
        // }

        close(fd);

        kill(getppid(), SIGUSR1);

        exit(EXIT_SUCCESS);

    } else {

        printf("Parent process waiting for signal...\n");
        
        pause();

        int fd = open(FILENAME, O_RDONLY);
        if (fd < 0) {
            perror("open failed SSSSSS");
            exit(EXIT_FAILURE);
        }

        char buffer[256];
        ssize_t bytes_read = read(fd, buffer, sizeof(buffer) - 1);
        if (bytes_read > 0) {
            buffer[bytes_read] = '\0';
            printf("Data read from file: %s\n", buffer);
        }else {
            printf("No data read from file.\n");
        }

        close(fd);

        wait(NULL);
    }

    return 0;
}
