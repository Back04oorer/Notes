#include <stdio.h>
#include <string.h>
#include <pthread.h>

// 共享变量和互斥锁
char *message = "Chocolate microscopes?";
int mindex = 0;
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER; // 初始化互斥锁
size_t message_len = 0;

// 线程函数
void* threadF(void *arg) {
    while (1) {
        pthread_mutex_lock(&lock); // 锁定互斥锁
        if (mindex < message_len) { // 访问共享变量
            printf("%c", message[mindex]); // 输出字符
            mindex++; // 递增共享变量
        } else {
            pthread_mutex_unlock(&lock); // 解锁互斥锁
            break; // 退出循环
        }
        pthread_mutex_unlock(&lock); // 解锁互斥锁
    }
    printf("Thread end at %d.\n", mindex); // 打印线程结束时的索引
    return NULL; // 线程函数返回
}

int main(void) {
    pthread_t my_thread; // 线程变量
    message_len = strlen(message); // 获取字符串长度

    pthread_create(&my_thread, NULL, threadF, NULL); // 创建线程

    while (1) {
        pthread_mutex_lock(&lock); // 锁定互斥锁
        if (mindex < message_len) {
            printf("%c", message[mindex]);
            mindex++;
        } else {
            pthread_mutex_unlock(&lock); // 解锁互斥锁
            break;
        }
        pthread_mutex_unlock(&lock); // 解锁互斥锁
    }

    printf("main end at %d.\n", mindex); // 打印主线程结束时的索引
    pthread_join(my_thread, NULL); // 等待子线程结束
    printf("\n");
    printf("all threads ended: %d\n", mindex); // 所有线程结束时的索引
    return 0; // 主程序结束
}
