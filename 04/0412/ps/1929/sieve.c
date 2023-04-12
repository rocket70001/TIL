#include <stdio.h>

int main()
{
	int from, to;
	scanf("%d %d", &from, &to);
	getchar();
	int sieve[1000001] = { 0, };

	for(int i = 2; i <= to; i++)
	{
		if(sieve[i] == 1)
			continue;
		for(int x = 2 * i; x <= to; x += i)
			sieve[x] = 1;
	}
	if(from < 2)
		from = 2;
	for(int i = from; i <= to; i++)
	{
		if(sieve[i] == 0)
			printf("%d\n", i);
	}
}
