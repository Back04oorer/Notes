void swap(int *a, int *b) {
	int tmp = 0;
	tmp = *a;
	*a = *b;
	*b = tmp;
}
int main(void) {
	int a = 2;
	int b = 3;
	swap(a, b); //Specify the variables to swap
	printf("%d %d\n", a, b); // should print 3 2
	return 0;
}