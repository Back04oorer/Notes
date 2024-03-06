### Pointer

pointer holds a memory address

```C
// initialize a pointer points to 5
int * pointer = 5
```

```C
char str[] = "A"
// the length of str is 2 (due to a null (\0))
```

```C
char initialize = "A"
char * p = &initialize
```

how to implement a linked list in c ?

# Not cute : str[1] equals to *(str + 1)

`**num[i]**`实际上是`***(num + i)**` **的语法糖**

dereference * only works for pointer date type

# pointers are cute

`sizeof` operator returns the number of bytes used to represent the given type or expresstion.

`void` has no size , but `sizeof(void*)` will return the size of an address, can not

dereference `void`

pointers are `unsigned numbers`

  

  

### the type `char` will support value range from `CHAR_MIN` to `CHAR_MAX` as defined in file `<limit.h>`

![[/Untitled 30.png|Untitled 30.png]]

  

Most C implementations default types as `signed values`, but  
a warning that you should not assume this.  

  

## `const` stands for read only

```C
const int value = 1;
const int *x = &value;
// not allowed to modify the value "1"

int value = 10;
int * const x = &value;
// not allowed to modify the value of pointer,but can modify the value that x points to
```