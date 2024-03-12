#include<stdio.h>
#include<string.h>
#include <ctype.h>

int main(int argc, char **argv){

	if(argc == 1){
		char buffer[1024];
		int line = 0;
		int word_count = 0;
		int char_count = 0;
		int inWord = 0;
		while(fgets(buffer,sizeof(buffer),stdin)){
			line++;
			char_count = strlen(buffer) + char_count;

		    for (int i = 0; buffer[i] != '\0'; i++) {
		    	if (isspace(buffer[i])) {
		    		inWord = 0;
		    	} else if (!inWord) {
		            inWord = 1; 
		            word_count++; 
		        }
		    }
		}

		printf("%d %d %d\n",line,word_count,char_count);
		return 1;
	}


	for (int i = 1; i < argc; i++){
		if (argv[i][0] == '-' && argv[i][1] == '\0'){
			int inWord = 0;
			char buffer[1024];
			int line = 0;
			int word_count = 0;
			int char_count = 0;
			while(fgets(buffer,sizeof(buffer),stdin)){
				line++;
				char_count = strlen(buffer) + char_count;
			    for (int i = 0; buffer[i] != '\0'; i++) {
			    	if (isspace(buffer[i])) {
			    		inWord = 0;
			    	} else if (!inWord) {
			            inWord = 1; 
			            word_count++; 
			        }
			    }
			}
			printf("%d %d %d\n",line,word_count,char_count);
		}else{
			FILE *f = fopen(argv[i],"r");
			int inWord = 0;
			char buffer[1024];
			int line = 0;
			int word_count = 0;
			int char_count = 0;
			while(fgets(buffer,sizeof(buffer),f)){
				line++;
				char_count = strlen(buffer) + char_count;
			    for (int i = 0; buffer[i] != '\0'; i++) {
			    	if (isspace(buffer[i])) {
			    		inWord = 0;
			    	} else if (!inWord) {
			            inWord = 1; 
			            word_count++; 
			        }
			    }
			}		
			printf("%d %d %d\n",line,word_count,char_count);
			fclose(f);
		}
	}

}