#include <stdio.h>

void make_cieve(int* cieve, int number)
{
	cieve[0] = 1;
	cieve[1] = 1;
	for(int i = 0; i <= number; i++)
	{
		if(cieve[i] == 1)
			continue;
		for(int j = 2 * i; j <= number; j += i)
			cieve[j] = 1;
	}
}

int cieve[10000001] = { 0, };

int main()
{
	int number;
	int prime = 2;

	scanf("%d", &number);
	make_cieve(cieve, number);
	while(number > 0 || number % prime != 0)
	{
		if(number % prime == 0 && cieve[prime] == 0)
		{
			printf("%d\n", prime);
			number /= prime;
		}
		else if (number == 1) {
			break;
		}
		else 
			prime++;
	}
}
