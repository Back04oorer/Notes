#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>


void handle_signal(int signal) {
    time_t now;
    char* time_str;

    time(&now); // 获取当前时间
    time_str = ctime(&now); // 转换为可读的字符串

    // 移除时间字符串的换行符
    time_str[strcspn(time_str, "\n")] = 0;

    if (signal == SIGUSR1 || signal == SIGUSR2) {
        printf("%s\n", time_str);
    } else if (signal == SIGINT) {
        printf("Received SIGINT, exiting gracefully at %s\n", time_str);
        exit(0); // 优雅地退出
    }
}


int main() {
	struct sigaction sa;
	sa.sa_handler = handle_signal;

	sigemptyset(&sa.sa_mask);

	sa.sa_flags = 0;

    // 使用sigaction设置SIGINT的处理函数
    /*
    int sigaction(int signum, const struct sigaction *act,struct sigaction *oldact);
    */
    sigaction(SIGINT, &sa, NULL);
    sigaction(SIGUSR1, &sa, NULL);
    sigaction(SIGUSR2, &sa, NULL);

    while (1) {
    	sleep(1); // 暂停，直到收到信号
    }

    return 0;
}
