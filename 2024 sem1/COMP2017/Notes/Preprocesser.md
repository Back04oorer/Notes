# C Compilation Process and Preprocessor Working Principles

## Compilation Process
In UNIX or UNIX-like systems, you can manually execute the compilation process of a C program using the following commands:

1. **Preprocessing (Preprocessor)**:
   ```bash
   gcc -E Name.c -o Name.i
   ```
   This command processes preprocessing directives such as `#include` and `#define`.

2. **Compiling (C Compiler)**:
   ```bash
   gcc -S Name.i -o Name.s
   ```
   Compiles preprocessed C code into assembly code.
   During this phase, the preprocessed code, which has all the directives expanded, is compiled into assembly language. The compiler translates the high-level C code into the low-level assembly instructions, <mark style="background: #ADCCFFA6;">only including what's necessary for the program to run</mark>, such as function calls used in the code.

3. **Assembling (Assembler)**:
   ```bash
   as Name.s -o Name.o
   ```
   Converts assembly code into machine code, generating an object file.
   The assembler takes the assembly code and converts it into object code, which is machine code in a format that can be linked. However, this code isn't yet ready to run because it might depend on external functions and libraries.

4. **Linking (Linker)**:
   ```bash
   ld Name.o -o a.out
   ```
   Links object code files to generate the final executable file.
   Finally, the linker takes the object code and any libraries it depends on, such as the C standard library for the `printf` function, and combines them to create a single executable file. This file can then be run on the computer.
   
## Preprocessor's Role
The preprocessor is primarily responsible for handling preprocessing directives in the source code, such as `#include` and `#define`.

- `#include <stdio.h>`: The preprocessor will find and insert the <mark style="background: #ADCCFFA6;">complete</mark> content of the `stdio.h` header file.
- `#define`: The preprocessor performs macro replacement, that is, replacing all instances of defined macros with their corresponding text.

#### Sample Code
Below is a simple C program, including its main operations after preprocessing:

```c
#include <stdio.h>

int main() {
    printf("huitouda,wangqianchong\n");
    return 0;
}
```

- The preprocessor replaces `#include <stdio.h>` with the entire content of that header file.
- The program outputs the string `"huitouda,wangqianchong\n"`, where `\n` is a newline character.

## Why do i need to use header files?

#### Project Structure

- **main.c**: Main program file that calls functions defined in `helper.c`.
- **helper.c**: Contains implementations of utility functions.
- **helper.h**: Contains declarations of the functions in `helper.c`.

#### File Contents

**helper.h**
```c
// helper.h
#ifndef HELPER_H
#define HELPER_H

// Function declaration
void print_hello();

#endif
```

**helper.c**

```c
// helper.c
#include "helper.h"
#include <stdio.h>

// Function implementation
void print_hello() {
    printf("Hello, world!\n");
}
```

**main.c**
```
// main.c
#include "helper.h"

int main() {
    print_hello();
    return 0;
}
```

Compile the entire project initially:

```c
gcc main.c helper.c -o program
```

Suppose we need to change the implementation of the `print_hello` function in `helper.c` to alter the printed message:

**Modified helper.c**

```c
// helper.c
#include "helper.h"
#include <stdio.h>

void print_hello() {
    printf("Hello, world! This is a new version.\n");
}

```

Since we only modified the implementation in `helper.c` and did not change any function declarations (no changes in `helper.h`), only `helper.c` needs to be recompiled:

```bash
gcc -c helper.c -o helper.o
```

Then, re-link to create the new executable:

```bash
gcc main.o helper.o -o program
```

By using header files to declare interfaces and keeping the implementation in source files, we only need to recompile and link **changed parts** when implementations are updated, without needing to touch other files using the same interfaces. This not only improves compilation efficiency but also streamlines project management.


