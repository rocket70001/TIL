#include <stdio.h>

void make_sieve(int *sieve)
{
	for(int i = 2; i < 1000001; i++)
	{
		if(sieve[i] == 1)
			continue;
		for(int j = 2 * i; j < 1000001; j += i)
			sieve[j] = 1;
	}
}

void print_gold(int even, int *sieve)
{
	int fir_prime = 0, sec_prime = 0;
	for(int i = 2; i <= even/2; i++)
	{
		if(sieve[i] == 0)
		{
			fir_prime = i;
			if(sieve[even - i] == 0)
			{
				sec_prime = even - i;
				break;
			}
		}
	}
	if(fir_prime == 0 || sec_prime == 0)
		printf("Goldbach's conjecture is wrong\n");
	else
		printf("%d = %d + %d\n", even, fir_prime, sec_prime);
}

int main()
{
	int even[100001];
	char str_goldbach[100001][30];
	int sieve[1000001] = { 0, };
	int i = 0;

	make_sieve(sieve);
	while(1)
	{
		scanf("%d", &even[i]);
		getchar();
		if(even[i] == 0)
			break;
		print_gold(even[i], sieve);
		i++;
	}
}
