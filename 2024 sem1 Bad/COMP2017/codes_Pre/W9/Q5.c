#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <signal.h>
#include <string.h>

#define SHARED_MEMORY_SIZE 128

void signal_handler(int signal) {
}

int main() {

    char* shared = mmap(
        NULL,
        SHARED_MEMORY_SIZE,
        PROT_READ | PROT_WRITE,
        MAP_ANON | MAP_SHARED,
        -1,
        0
    );

    if (shared == MAP_FAILED) {
        perror("mmap");
        exit(1);
    }

    // 注册信号处理程序
    signal(SIGUSR1, signal_handler);

    pid_t pid;
    
    if ((pid = fork()) == -1) {
        perror("fork");
        exit(1);
    }
    
    if (pid == 0) { 
        pause(); 

        time_t t = time(NULL);
        struct tm *tm_info = localtime(&t);
        strftime(shared, SHARED_MEMORY_SIZE, "%I:%M %p", tm_info);

        kill(getppid(), SIGUSR1);
        
        munmap(shared, SHARED_MEMORY_SIZE);
         
    } else {  // 父进程
        sleep(1);
        printf("Prent: Hi! Do you know what time it is?\n");

        kill(pid,SIGUSR1);

        pause();

        printf("Parent: %s\n", shared);

        printf("Parent: Thank you!\n");

        wait(NULL);
        
        munmap(shared, SHARED_MEMORY_SIZE);
    }
    
    return 0;
}

