#include<stdio.h>
#include<string.h>

int substring(const char *line,const char *word){
	for(int i=0; i < strlen(line)-strlen(word)+1; i++){
		if(strncmp(line + i, word,strlen(word)) == 0){
			return i;
		}
	}
	return -1;
}

int main(){
	printf("%d\n", substring("racecar", "car")); //4
	printf("%d\n", substring("telephone", "one")); //6
	printf("%d\n", substring("monkey", "cat")); //-1

	return 0;
}

