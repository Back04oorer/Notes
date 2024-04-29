
## Process Creation
- **Cloning Existing Process**: The operating system creates a new memory image for the process by cloning an existing process. This typically involves copying the memory state of a parent process into a new process.
- **Memory Image and Permissions**: The new process's memory image includes permissions related to read (r), write (w), and execute (x) actions, configured according to user and group settings. These permissions determine what operations the process can perform on its memory spaces.

## Process Virtual Memory Layout

![[Pasted image 20240415211804.png]]

The diagram shows a typical layout of a process's virtual memory. In operating systems, a process's virtual memory is divided into different sections, each with a specific purpose:

- `Code`: Contains the executable machine code of the process.
- `Data`: Holds initialized data for global and static variables.
- `BSS` (Block Started by Symbol): Used for uninitialized global and static variables.
- `Heap`: Area for dynamically allocated memory, often allocated by the program at runtime using `malloc` or `new`.
- `Stack`: Memory area where function parameters, return addresses, and local variables are stored. It has a downward-growing pointer (upward in the diagram).

In addition to the application's own memory layout, there is a section for the shared C library (`Shared C library *so`), like `libc.so`. This allows multiple programs to share the same library without needing a copy in each program's memory space.

The diagram also marks several special addresses:

- `0x0`: Usually represents the start of the virtual memory.
- `0x40000000`: This may be the starting position of the shared library in virtual memory.
- `0xC0000000`: Below this address is the user mode process virtual address space, with kernel mode address space above it, often reserved for the operating system's kernel components. In many 32-bit operating systems, the user space is the top 3GB, and kernel space is the bottom 1GB (though the diagram shows 1GB of kernel space, which can vary by system).

This layout helps protect different types of data from interfering with each other and is a critical feature of modern operating systems in managing memory.

## Shared Libraries and Threads

The shared C library refers to Dynamic Link Libraries (DLLs) which are typically `.so` (shared object) files on Unix-like systems. Different processes, and even different threads, can load and use the same copy of a dynamic library.

Here are some key points to understand about the relationship between multithreading and shared libraries:

1. **Virtual Memory Space**: Each process has its own virtual memory space managed by the operating system. The same dynamic library in multiple processes can be mapped to different locations in their respective virtual memory spaces, but those mappings will point to the same location in physical memory.

2. **Physical Memory Sharing**: When a dynamic library is loaded, the operating system attempts to keep only one copy of that library in physical memory and maps that copy into the virtual memory spaces of all requesting processes.
3. 
## Processes and Memory

The slide from the University of Sydney explains the relationship between processes and memory, specifically the mapping between virtual memory and physical memory. Key points include:

- **Mapping Virtual Memory to Physical Memory**: Each process's virtual memory is mapped to physical memory. Virtual memory is an abstraction of memory seen by a process, while physical memory corresponds to the actual hardware memory.

- **Composition of Physical Memory**: Physical memory consists of various types of storage, such as cache, Random Access Memory (RAM), disks, tapes, network storage, etc. These storages collectively constitute the physical memory available to a process.

- **Sharing Finite Memory Resources Among Multiple Processes**: Multiple processes share limited physical memory resources, such as Dynamic Random Access Memory (DRAM) and Static Random Access Memory (SRAM), which can be quickly exhausted.

- **Secondary Storage**: Data that cannot fit in primary storage is kept in secondary storage, such as a hard drive. This hierarchical memory management extends the available storage space.

- **The Role of the Operating System**: The operating system is responsible for managing the translation from virtual memory to physical memory, and this process typically requires hardware support, such as a Memory Management Unit (MMU).

The left side of the slide shows a schematic of virtual memory, with each process's instructions and data mapped to different parts of the physical memory. The right side is a schematic of physical memory, which includes areas specific to the operating system and mapped areas for different processes. These processes may represent different programs in the actual physical memory, sharing the physical memory while each process sees its own separate virtual memory space.

![[Pasted image 20240415215750.png]]

