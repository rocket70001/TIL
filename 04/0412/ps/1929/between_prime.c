#include <stdio.h>

int is_prime(int num)
{
	if(num == 0 || num == 1)
		return -1;
	for(int i = 2; i*i <= num; i++)
	{
		if(num % i == 0 && num != 2)
			return -1;
	}
	return num;
}

int main()
{
	int from;
	int to;
	int prime[1000000];
	int idx = 0;

	scanf("%d %d", &from, &to);
	getchar();

	for(int i = from; i <= to; i++)
	{
		if(is_prime(i) != -1)
			prime[idx++] = i;
	}
	for(int i = 0; i < idx; i++)
	{
		printf("%d\n", prime[i]);
	}
}
