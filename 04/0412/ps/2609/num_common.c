#include <stdio.h>

int main()
{
	int a, b, divisor, multiple;
	int num_bigger, num_smaller;

	scanf("%d %d", &a, &b);

	if(a > b)
	{
		num_bigger = a;
		num_smaller = b;
	}
	else {
		num_bigger = b;
		num_smaller = a;
	}
	divisor = num_smaller;
	multiple = num_bigger;

	//Get greatest common divisor, least common multiple
	while(num_bigger % divisor != 0 || num_smaller % divisor != 0)
		divisor--;
	while(multiple % num_bigger != 0 || multiple % num_smaller != 0)
		multiple++;
	printf("%d\n%d", divisor, multiple);
}
