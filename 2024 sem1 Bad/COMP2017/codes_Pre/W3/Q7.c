#include<stdio.h>
#include<math.h>

int main(){
	
	float num ;
	printf("Specify the radius of the sphere: ");
	scanf("%f",&num);

	double result = pow(num,3) * 3.14 * 4 / 3;

	printf("Volume is %lf\n", result);
	return 1;
}