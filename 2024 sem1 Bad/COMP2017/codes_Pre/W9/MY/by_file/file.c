#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <string.h>

#define FILENAME "shared.txt"
#define MESSAGE "Hello from the child process!"

// 信号处理器
void signal_handler(int signum) {
    if (signum == SIGUSR1) {
        printf("Signal received from child process.\n");
    }
}

int main() {
    // 安装信号处理器
    signal(SIGUSR1, signal_handler);

    pid_t pid = fork();

    if (pid < 0) {
        // fork 失败
        perror("fork failed");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {
        // 子进程
        sleep(1); // 等待父进程准备

        // 打开文件并写入数据
        int fd = open(FILENAME, O_WRONLY | O_CREAT, 0644);
        if (fd < 0) {
            perror("open failed");
            exit(EXIT_FAILURE);
        }

        // 写入消息
        ssize_t bytes_written = write(fd, MESSAGE, strlen(MESSAGE));
        if (bytes_written < (ssize_t)strlen(MESSAGE)) {
            perror("write incomplete");
            close(fd);
            exit(EXIT_FAILURE);
        }

        // 刷新缓冲区，确保数据写入磁盘
        if (fsync(fd) < 0) {
            perror("fsync failed");
            close(fd);
            exit(EXIT_FAILURE);
        }

        close(fd);

        // 发送信号通知父进程
        kill(getppid(), SIGUSR1);
        exit(EXIT_SUCCESS);
    } else {
        // 父进程
        printf("Parent process waiting for signal...\n");

        // 等待子进程的信号
        pause();

        // 打开文件并读取数据
        int fd = open(FILENAME, O_RDONLY);
        if (fd < 0) {
            perror("open failed");
            exit(EXIT_FAILURE);
        }

        char buffer[256];
        ssize_t bytes_read = read(fd, buffer, sizeof(buffer) - 1);
        if (bytes_read > 0) {
            buffer[bytes_read] = '\0'; // 确保字符串终止
            printf("Data read from file: %s\n", buffer);
        } else {
            printf("No data read from file.\n");
        }

        close(fd);

        // 等待子进程结束
        wait(NULL);
    }

    return 0;
}
