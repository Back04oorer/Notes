# Requirements

### 1.1 Package and Its File Format (.bpkg)

In this program, files are composed of several exceptional data blocks. These blocks are organized in a specific manner, and when combined, they can construct and present the full content of a file to the user. A package defines the information and resources needed to construct the content of a file. The package is represented by a unique identifier string "ident". The package file format is a text format that your program needs to parse. The package file format includes the following fields:

- **ident**: A hexadecimal string (up to 1024 characters) used to identify the same package on the network.
- **filename**: A string (up to 256 characters) used to help save and locate files when data needs to be updated.
- **size**: uint32_t, specifies the file size in bytes.
- **nhashes**: uint32_t, specifies the number of precomputed hashes. The number of hashes must be $2^{(h-1)} - 1$, corresponding to all non-leaf node hashes.
- **hashes**: Array of strings `string[2^{(h-1) - 1}]` (each string 64 characters), corresponding to the number of hashes in the nhashes field.
- **nchunks**: uint32_t, specifies the number of chunks. The number of chunks must be $2^{(h-1)} - 1$.
- **chunks**: Array of structures `struct[2^{(h-1)}]`, each chunk having the following fields:
    - **hash**: A string (64 characters), corresponding to the hash of the data block.
    - **offset**: uint32_t, offset in the file.
    - **size**: uint32_t, the byte size of the chunk.

### 1.2 Package Loading

The focus of this task is to load .bpkg files and store the details in a Merkle tree. Upon completion of this task, you should be able to execute the `bpkg_get_all_hashes` and `bpkg_get_all_chunk_hashes_from_hash` functions in the pkgchk.c file without needing to perform IO operations on the data file.

### 1.3 What is a Merkle Tree?

A Merkle tree is a variant of a binary tree where each node contains a hash value. The hash values of leaf nodes correspond to the hashes of data blocks, while all other non-leaf node hashes are obtained by combining their child nodes' hash values and hashing this combined value. Merkle trees are typically perfect or complete binary trees but can also be full binary trees. This structure allows for efficient verification of the integrity of data blocks and files.
### <mark style="background: #FF5582A6;">Tip</mark> how to build Merkle tree?
#### Step 1: Parse the `.bpkg` file

First, extract all relevant data from the `.bpkg` file, including:

- Data chunks
- Each data chunk's offset and size
- All pre-stored hash values (`expected_hash`) of non-leaf nodes

#### Step 2: Build the leaf nodes

For each data chunk, compute its hash value, which will serve as the `computed_hash` for the leaf nodes in the Merkle tree. Leaf nodes typically correspond directly to specific parts of the file.

#### Step 3: Build non-leaf nodes level by level

Starting from the leaf nodes, work upwards:

- Concatenate the `computed_hash` values of two child nodes (usually the left child followed by the right child).
- Apply a hash function to this concatenated string to generate the `computed_hash` for that node.
- Repeat this process until the root node’s `computed_hash` is computed.

#### Step 4: Verify data integrity

Finally, compare the root node's `computed_hash` with the root node `expected_hash` stored in the file:

- If they match, the data is complete and unaltered.
- If they do not match, it indicates that the data has been tampered with or corrupted during transmission.

### 1.4 Corrections, Variants, and Notes

- **Implementation flexibility**: There is no strict requirement that the Merkle tree be a perfect or complete binary tree. Theoretically, you could implement a Merkle tree with more than two child nodes per parent, or where not all leaf nodes are at the same level. However, these assumptions are made to simplify the implementation of the data structure.
- **Same block, different positions**: Through experimentation, you might find that if there are blocks with identical data, your implementation should either assume this does not happen or include necessary data to differentiate them. Refer to the REQ packet, particularly the offset section, to help solve searching issues. You could have a bit-field key accompanying it, as shown in the previous section’s illustration.
- **Data redundancy**: To aid in implementing this data structure and ensure that other parts remain unrestricted even in incomplete states, the provided file contains data beyond what is necessary.
- **Hexadecimal hash vs. byte hash**: Implementations by staff use hexadecimal hash, while computing a byte hash, although not incorrect, may produce different results from test cases. Ensure your implementation conforms to this requirement.

### 1.5 Checklist

- **Parse valid .bpkg files**, ensuring you can read every field within them.
- **Build a Merkle tree from the parsed bpkg file**.
- **Implement all functions in pkgchk.c**.
- **Compile and run**, using `make pkgchk.o` for compilation, which is necessary for the test cases.
- **You may modify the Makefile** freely to reference the c files you will use in your build targets.
- **Compile and run `make pkgchecker`**, and compile it together with pkgmain.c to locally test your program.'


# Notes
## Decisions for High-Performance Merkle Tree

#### 1. Understanding the Structure of `.bpkg` Files

Firstly, you need to fully understand the structure of `.bpkg` files, including how to parse out data chunks and hashes from the file. These data chunks will become the leaf nodes of the Merkle tree, while the hashes of non-leaf nodes will be computed from the hashes of these leaf nodes.

#### 2. Reading Data Blocks and Computing Hashes

- **Parallel Reading**: Use multithreading or asynchronous I/O to read data blocks in parallel. This can significantly reduce the time taken to read large files.
- **Parallel Hashing**: Each thread can independently compute the hash value of one or more data blocks. This can utilize multi-core processors to reduce the total time for hash computation.

#### 3. Building the Tree Structure

- **Bottom-Up Construction**: Start from the leaf nodes and gradually compute the hash values of parent nodes upwards. The hash of each parent node is computed from the hashes of its two child nodes using a specific hash function.
- **Parallel Tree Construction**: Where possible, multiple parent nodes' hash values can be computed in parallel. For example, different parts of the tree can be assigned to different threads for processing.

#### 4. Optimizing Storage Structures

- **Memory Management**: Manage memory efficiently, especially when dealing with large data, to ensure the memory efficiency and access speed of data structures.
- **Data Structure Selection**: Choose data structures suitable for fast access and modification to store tree nodes, such as balanced binary tree structures or hash tables.

#### 5. Testing and Verification

- **Integrity Verification**: Ensure that the hash of each node correctly reflects the hashes of its child nodes.
- **Performance Testing**: Evaluate the effectiveness of different parallel strategies through benchmark testing, adjusting the number of threads and task allocation for optimal performance.

#### 6. Reporting and Analysis

- **Performance Analysis**: Analyze and record the performance of different configurations, including processing time and resource utilization.
- **Results Visualization**: Create charts to display the impact of different numbers of threads on performance, as well as comparisons before and after optimization.