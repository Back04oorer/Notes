
# File I/O in C using `fwrite` and `fread`

In C programming, `fwrite` and `fread` functions facilitate binary file input and output operations, working with `FILE*` type objects.

## FILE* Object

- A `FILE*` object represents an open file in C. 
- Obtained via file opening functions such as `fopen`.

## fwrite Function

- Used to write data to a file.
- Operates directly on memory through pointers and buffers.

## fread Function

- Used to read data from a file.
- Similar to `fwrite`, it requires direct memory operation.

## Usage

Both functions require specifying:
- The buffer's location.
- The size of the data to operate on.
- The number of elements to read/write.

These parameters determine the amount of data to be read from or written to the file.

## Binary Data

- Since these functions deal with binary data, they can handle any data type (e.g., integers, structures) as long as the buffer is sufficiently sized and not exceeded.
- Understanding the structure of binary data is crucial as these functions perform no formatting.

## Example

```c
#include <stdio.h>

struct Data {
    int id;
    char name[20];
};

int main() {
    struct Data dataToWrite = {1, "John Doe"};
    struct Data dataToRead;

    FILE *file = fopen("example.bin", "wb+");
    // write | binary | + -> update
    if (file != NULL) {
        // Write data to file
        fwrite(&dataToWrite, sizeof(struct Data), 1, file);

        // Move file pointer to the beginning
        fseek(file, 0, SEEK_SET);

        // Read data from file
        fread(&dataToRead, sizeof(struct Data), 1, file);

        printf("ID: %d, Name: %s\n", dataToRead.id, dataToRead.name);

        fclose(file);
    }
    return 0;
}

```










# Blocking vs Non-blocking I/O in C

Understanding blocking and non-blocking I/O operations is essential in C programming for efficient I/O handling. Below are explanations and examples in C.

## Blocking I/O in C

Blocking I/O operations in C wait until the operation completes before the program can continue. This is common with standard I/O operations.

### Example: Blocking I/O

```c
#include <stdio.h>
#include <unistd.h>

int main() {
    char buffer[128];
    printf("Enter something: "); // Prompt for input
    ssize_t bytesRead = read(STDIN_FILENO, buffer, sizeof(buffer)); // Blocking read
    if (bytesRead > 0) {
        printf("You entered: %s", buffer);
    }
    return 0;
}
```

## Non-blocking I/O in C

Non-blocking I/O operations allow the program to continue running without waiting for the I/O operation to complete. This requires setting the file descriptor to non-blocking mode.

### Example: Non-blocking I/O

To use non-blocking I/O in C, you may need to work with system calls and flags that adjust the behavior of file descriptors. Here's a conceptual example:

```c
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>

int main() {
    char buffer[128];
    // Set STDIN (usually file descriptor 0) to non-blocking
    fcntl(STDIN_F
    ILENO, F_SETFL, O_NONBLOCK);

    printf("Enter something: "); // Attempt to read immediately
    ssize_t bytesRead = read(STDIN_FILENO, buffer, sizeof(buffer));
    if (bytesRead > 0) {
        printf("You entered: %s", buffer);
    } else if (bytesRead == -1 && errno == EAGAIN) {
        printf("No input available.\n");
    }

    // Reset STDIN to blocking mode if necessary
    // fcntl(STDIN_FILENO, F_SETFL, ~O_NONBLOCK);

    return 0;
}

```





# Signals in UNIX/Linux Systems

When interacting with running programs in a UNIX/Linux environment, signals are a fundamental aspect of communication between the shell and those programs. Two common user-initiated signals are:

- **SIGINT**: Sent by pressing `Ctrl+C`, it interrupts a program.
- **SIGSTOP**: Sent by pressing `Ctrl+Z`, it pauses the program's execution.

Signals provide a mechanism to control the behavior of processes running in the foreground or background. A common tool for sending signals to processes is the `kill` command, which, despite its name, is used for more than just terminating programs.

## Sending Signals with the `kill` Command

To send a signal to a process, you need to know the process's ID (PID). The general format for using the `kill` command is as follows:

```bash
kill -<signal type> <pid>
```

## Commonly Used Signals

- **SIGTERM** (`-SIGTERM`): Politely asks the process to terminate. It's a gentle stop signal that allows the process to clean up resources.
- **SIGKILL** (`-SIGKILL`): Immediately terminates the process. It cannot be caught or ignored by the process, making it a sure way to stop a program.
- **SIGSTOP** (`-SIGSTOP`): Pauses the process's execution. This signal cannot be handled by the process, ensuring it stops.

## Handling Signals

Most signals, with the exception of `SIGKILL` and `SIGSTOP`, can be intercepted by the process through defined signal handlers. This allows a program to perform specific actions or clean up resources before terminating or when certain events occur.
