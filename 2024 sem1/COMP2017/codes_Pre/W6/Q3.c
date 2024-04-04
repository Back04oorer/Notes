#include<stdio.h>
#include<unistd.h>
#include <string.h>

int int_cmp(void* a, void* b){
	if (){
		return 0;
	}
	return 1;
}


void swap(void* a, void *b, size_t size_element){
	void* temp = malloc(size);
	memcpy(temp,a,size_element);
	memcpy(a,b,size_element);
	memcpy(b,temp,size_element);
	free(temp);
}

void bubble_sort(void* elements, size_t n_elements,size_t size_element, int (*cmp)(void*, void*)){
	char temp[size_element];
	for(int i = 0; i< n_elements-1 ; i++){
		for (int j = i; j < n_elements-1; j++){
			char* base = (char*)elements; 
			void* a = base + j * size_element; 
			void* b = base + (j + 1) * size_element; 
			if(elements_cmp(a,b) == 0){
				swap(a,b,size_element);
			}

		}
	}

}


int main(){
	int x[5] = {1,9,10,2,5};

}