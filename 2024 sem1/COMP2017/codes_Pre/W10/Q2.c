#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define LENGTH 100000000
#define NTHREADS 20
#define NREPEATS 10
#define CHUNK (LENGTH / NTHREADS)

typedef struct {
    size_t id; // 8
    long* array; // 8
    long result; // 8
    // GOD!
    long a1;
    long a2;
    long a3;
    long a4;
    long a5;
} worker_args;

void* worker(void* args) {
    worker_args* wargs = (worker_args*) args;
    const size_t start = wargs->id * CHUNK;
    const size_t end = (wargs->id == NTHREADS - 1) ? LENGTH : (wargs->id + 1) * CHUNK;

    // Sum values from start to end
    for (size_t i = start; i < end; i++) {
        wargs->result += wargs->array[i];
    }
    return NULL;
}

int main(void) {
    long* numbers = malloc(sizeof(long) * LENGTH);
    if (numbers == NULL) {
        perror("Memory allocation failed");
        return 1;
    }

    // Initialize array with values 1 to LENGTH
    for (size_t i = 0; i < LENGTH; i++) {
        numbers[i] = i + 1;
    }

    worker_args* args = malloc(sizeof(worker_args) * NTHREADS);
    if (args == NULL) {
        free(numbers);
        perror("Memory allocation failed");
        return 1;
    }

    pthread_t thread_ids[NTHREADS];

    // Repeat the calculation NREPEATS times
    for (size_t n = 1; n <= NREPEATS; n++) {
        // Initialize worker arguments
        for (size_t i = 0; i < NTHREADS; i++) {
            args[i].id = i;
            args[i].array = numbers;
            args[i].result = 0;
        }

        // Launch threads
        for (size_t i = 0; i < NTHREADS; i++) {
            pthread_create(&thread_ids[i], NULL, worker, &args[i]);
        }

        // Wait for threads to finish
        for (size_t i = 0; i < NTHREADS; i++) {
            pthread_join(thread_ids[i], NULL);
        }

        // Calculate total sum
        long sum = 0;
        for (size_t i = 0; i < NTHREADS; i++) {
            sum += args[i].result;
        }

        printf("Run %2zu: total sum is %ld\n", n, sum);
    }

    // Free allocated memory
    free(args);
    free(numbers);

    return 0;
}
