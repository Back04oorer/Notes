#include<stdio.h>
#include<stdint.h>

enum TYPE { FIRE, WATER, FLYING, ROCK, ELECTRIC };


struct pokemon {
	const char* name;
	enum TYPE type;
};
void evolve(struct pokemon* mon) {
	(*mon).name = "Raichu";
	mon->type = ELECTRIC;
}

int main(){
	// pokemon pikachu = { "Pikachu", ELECTRIC }; where is your stuct?
	// struct pokemon pikachu = { ELECTRIC, "Pikachu" }; invalid

	// struct pokemon pikachu = { "Pikachu", ELECTRIC }; fine

	// struct pokemon pikachu = { .type = ELECTRIC, .name = "Pikachu" };

	printf("%zu\n",sizeof(struct pokemon));


	struct pokemon pikachu = { "Pikachu", ELECTRIC };
	struct pokemon* ptr = &pikachu;
	evolve(ptr);
	printf("%s\n", pikachu.name);
	return 1;
}
