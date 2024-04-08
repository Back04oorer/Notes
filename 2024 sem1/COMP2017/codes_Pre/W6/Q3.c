#include<stdio.h>
#include<unistd.h>
#include <string.h>
#include<stdlib.h>

int int_cmp(void* a, void* b){
	int* a_ptr = (int*) a;
	int* b_ptr = (int*) b;

	return (*a_ptr) - (*b_ptr);
}


void swap(void* a, void *b, size_t size_element){
	void* temp = malloc((int)size_element);
	memcpy(temp,a,size_element);
	memcpy(a,b,size_element);
	memcpy(b,temp,size_element);
	free(temp);
}

void bubble_sort(void* elements, size_t n_elements,size_t size_element, int (*cmp)(void*, void*)){

	for(int i = 0; i< n_elements-1 ; i++){
		for (int j = i; j < n_elements-1; j++){
			char* base = (char*)elements; 
			void* a = base + j * size_element; 
			void* b = base + (j + 1) * size_element; 
			if(cmp(a,b) > 0){
				swap(a,b,size_element);
			}

		}
	}

}


int main(){
	int x[5] = {1,9,10,2,5};
	bubble_sort(x,5,sizeof(int),int_cmp);

	for(int i =0 ; i < 5 ; i++){
		printf("%d\n", x[i]);
	}
}