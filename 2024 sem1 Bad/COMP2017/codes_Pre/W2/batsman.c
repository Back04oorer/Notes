#include <stdio.h>
#include <string.h>

int main(int argc, char **argv){
	int MAX_BATSMEN = 10;
	int NAME_LENGTH = 10;
    int scores[MAX_BATSMEN]; 
    char fnames[10][100];
    char lnames[10][100];

    int i = 0;
    while(i < MAX_BATSMEN ){
    	char first_name[100];
    	char last_name[100];

    	printf("Enter Name and Score for batter %d: ", i + 1);
    	scanf("%99s %99s %d", first_name, last_name, &scores[i]);
    	
    	strcpy(fnames[i],first_name);
    	strcpy(lnames[i],last_name);
    	i++;
    }


    for (int j = 0; j < i; ++j)
    {
    	printf("%d. %c. %s,",j+1,fnames[j][0], lnames[j]);
    }
}