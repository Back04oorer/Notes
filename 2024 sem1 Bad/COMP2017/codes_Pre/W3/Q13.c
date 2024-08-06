#include<stdio.h>

int main(int argc, char **argv) {
    if (argc == 1) {
        // No file arguments, read from stdin only
        char buffer[1024];
        while (fgets(buffer, sizeof(buffer), stdin)) {
            printf("%s", buffer);
        }
        return 1;
    }

    for (int i = 1; i < argc; i++) {
        if (argv[i][0] == '-' && argv[i][1] == '\0') {
            // Argument is "-", read from stdin
            char buffer[1024];
            while (fgets(buffer, sizeof(buffer), stdin)) {
                printf("%s", buffer);
            }
        } else {
            // Argument is a file path, read from the file
            char file_path[100];
            char buffer[1024];
            sscanf(argv[i], "%s", file_path);
            printf("file path: %s\n", file_path);
            FILE *f = fopen(file_path, "r");
            if (f == NULL) {
                printf("Error opening file: %s\n", file_path);
                continue; // Skip to next file if we can't open this one
            }
            while (fgets(buffer, sizeof(buffer), f)) {
                printf("%s", buffer);
            }
            fclose(f); // Don't forget to close the file
        }
    }
    return 1;
}
