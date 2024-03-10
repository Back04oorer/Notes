  

### Virtual address space

- the set of all possible addresses are known as `virtual address space`
- virtual address space is just a `conceptual image` presented to the machine-level program.The actual implementation (presentedin Chapter 9) uses a combination of `dynamic random access memory (DRAM)`, `flash memory`, `disk storage`, `special hardware`, and `operating system software` to provide the program with what appears to be a monolithic byte array.

The C compiler also associates type information with each pointer, so that it can generate different machine-level code to access the value stored at the location designated by the pointer depending on the type of that value.

Although the C compiler maintains this `type` information, the actual  
machine-level program it generates has  
`no information` about data types

if tut quiz has this shit. …. ..


## Pointer …..

Casting from one type of pointer to another changes its `type` but `not its value`.  
One effect of casting is to change any scaling of pointer arithmetic. So, for  
example, if p is a pointer of type char * having value p, then the expression  
(int *) p+7 computes p + 28, while (int *) (p+7) computes p + 7. (Recall  
that casting has higher precedence than addition.)  

Pointers `can also point to functions`. This provides a powerful capability for  
storing and passing references to code, which can be invoked in some other  
part of the program. For example, if we have a function defined by the prototype  

`int fun(int x, int *p);`

then we can declare and assign a pointer `fp` to this function by the following  
code sequence:  

```C
int (*fp)(int, int *);
fp = fun;
```

We can then invoke the function using this pointer:

```C
int y = 1;
int result = fp(3, &y);
```

The value of a function pointer is the address of the `first instruction` in the  
machine-code representation of the function.