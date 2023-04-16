#include <stdio.h>

void era_cieve(int* cieve)
{
	cieve[0] = 1;
	cieve[1] = 1;
	for(int i = 2; i <= 1000010; i++)
	{
		if(cieve[i] == 1)
			continue;
		for(int j = 2*i; j <= 1000010; j += i)
		{
			cieve[j] = 1;
		}
	}
}

int main()
{
	int cieve[1000010] = { 0, };
	int num_test;

	scanf("%d", &num_test);
	getchar();
	era_cieve(cieve);

	for(int i = 0; i < num_test; i++)
	{
		int gold;
		int num_partition = 0;
		scanf("%d", &gold);
		getchar();

		for(int j = 2; j <= gold / 2; j++)
		{
			if(cieve[j] == 0)
			{
				if(cieve[gold - j] == 0)
					num_partition++;
			}
		}
		printf("%d\n", num_partition);
	}
	return 0;
}
