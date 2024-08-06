#include<stdio.h>
#include<string.h>

struct customer
{
	unsigned int id;
	char name[64];
	unsigned int age;
	char look_for[64];
};


int main(){
	char buffer[128];
	struct customer customers[100];

	printf("Welcome to ShopaMocha,\n");

	unsigned int counts = 0;

	while(fgets(buffer,sizeof(buffer),stdin)){
		struct customer c;
		sscanf(buffer,"%s %d %s",c.name,&c.age,c.look_for);
		if (sscanf(buffer,"%s %d %s",c.name,&c.age,c.look_for) == 3){
			customers[counts].id = counts;
			strcpy(customers[counts].name, c.name);
			customers[counts].age = c.age;
			strcpy(customers[counts].look_for, c.look_for);
			counts++;
		}
	}


	for(int i=0 ; i < counts; i++){
		printf("Customer %d, Name: %s, Age: %d, Looking for: %s\n",customers[i].id,customers[i].name,customers[i].age,customers[i].look_for);
	}



	return 1;
}