#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <unistd.h>

#define DATA_SIZE (6)

int* give_data() {
    int* data = malloc(sizeof(int) * DATA_SIZE);
    for (int i = 0; i < DATA_SIZE; i++) {
        data[i] = i;
    }
    return data;
}

void read_share(int* d) {
    for (int i = 0; i < DATA_SIZE; i++) {
        printf("%d\n", d[i]);
    }
}

int main() {
    // Get some initial data
    int* d = give_data();

    // Create shared memory for parent and child processes
    int* shared = mmap(
        NULL,
        DATA_SIZE * sizeof(int),
        PROT_READ | PROT_WRITE,
        MAP_ANON | MAP_SHARED,
        -1,
        0
    );

    // Copy data to shared memory
    memcpy(shared, d, DATA_SIZE * sizeof(int));

    // Free the original data
    free(d);

    // Fork a new process
    pid_t p = fork();

    if (p == 0) {  // Child process
        printf("Child\n");
        read_share(shared);  // Read data from shared memory

        // Modify the shared data
        for (int i = 0; i < DATA_SIZE; i++) {
            shared[i] = i + 10;
        }

        // Unmap the shared memory
        munmap(shared, DATA_SIZE * sizeof(int));

    } else if (p > 0) {  // Parent process
        sleep(2);  // Give the child process time to modify the data

        printf("Parent\n");
        read_share(shared);  // Read the updated data from shared memory

        // Unmap the shared memory
        munmap(shared, DATA_SIZE * sizeof(int));
    }

    return 0;
}
