#include<stdio.h>
#include<stdint.h>

int main(){
	int a;//4 bytes
	int* b = &a;  // 8bytes
	int* c = NULL; // 8bytes
	unsigned d; // equals to unsigned int 4bytes
	short e; // 2 bytes
	long f; // 8 bytes
	size_t g; //depends on the architecture 32bits - 4 bytes , 64 bits - 8 bytes
	long long h;// 8 bytes
	uint8_t i; // 1 bytes
	uint32_t j; // 4 bytes

	struct quoll{
		/* 20 + 1 = 21 bytes*/
		char name[20];
		uint8_t age;
	};

	struct quokka{
		/* 8 + 8 + 8 = 24 bytes*/
		char* name;
		struct quokka* quokka_father;
		struct quokka* quokka_mother;
	};

	union mammal{
		//24 bytes
		struct quoll l;// 21 bytes
		struct quokka a;// 24 bytes
	};

	struct example {
	    char a;    // 1 byte
	    // Padding 3 bytes
	    int b;     // 4 bytes
	    char c;    // 1 byte
	    // Padding 3 bytes
	};



	printf("%zu\n", sizeof(struct quoll));
	printf("%zu\n", sizeof(struct quokka));
	printf("%zu\n", sizeof(union mammal));
	printf("%zu\n", sizeof(struct example));


}


