#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <time.h>

void child_saying_no() {
    puts("No!");
    return;
}

void check_time() {
    printf("%d\n", getpid());
    // i'm pretend to have the time format here
    puts("time");
    return;
}
 
int main() {
    
    puts("about to fork");

    pid_t og_pid = getpid(); // get current pid (parent)
    
    pid_t pid = fork();
    /*
    return 0 if in the child process
    or 
    return child's pid 
    */

    if (pid < 0) {
        perror("unable to fork");
        return 1;
    }

    /*
    pause()
    It suspends the calling process until any signal is caught by the process. 
    */
    
    if (pid == 0) {
        // Child process
        signal(SIGINT, child_saying_no); // it will replace the current handler function, but not in this case
        puts("The child wants to know the time");
        kill(og_pid, SIGINT);
        pause();// wait until child sent the SIGINT
        exit(0);
    } else {
        signal(SIGINT, check_time);
        // Parent process
        pause(); // wait until child sent the SIGINT
        puts("Have you finished your homework?");
        kill(pid,SIGINT);
        wait(NULL);// wait for child.In case of zombie process
    }
    
    return 0;
}