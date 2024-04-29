#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>

int main() {
    int parent_to_child[2];
    int child_to_parent[2];
    pid_t pid;
    
    if (pipe(parent_to_child) == -1 || pipe(child_to_parent) == -1) {
        perror("pipe");
        exit(1);
    } 
    
    if ((pid = fork()) == -1) {
        perror("fork");
        exit(1);
    }
    
    if (pid == 0) { 
        close(parent_to_child[1]); 
        close(child_to_parent[0]); 

        char buffer[256];
        read(parent_to_child[0], buffer, sizeof(buffer)); 
        
        time_t t = time(NULL);
        struct tm *tm_info = localtime(&t);
        char time_str[256];
        strftime(time_str, sizeof(time_str), "%I:%M %p", tm_info);
        
        dprintf(child_to_parent[1], "The time is %s!\n", time_str);
        
        close(parent_to_child[0]); 
        close(child_to_parent[1]); 
    } else { 
        close(parent_to_child[0]);
        close(child_to_parent[1]); 
        
        printf("Hi! Do you know what time it is?\n");
        
        char buffer[256];
        read(child_to_parent[0], buffer, sizeof(buffer)); 
        
        printf("Parent: %s", buffer); 
        printf("Parent: Thank you!\n");
        
        close(parent_to_child[1]); 
        close(child_to_parent[0]); 
        
        wait(NULL); 
    }
    
    return 0;
}
