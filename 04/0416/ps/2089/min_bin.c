#include <stdio.h>
#include <stdlib.h>

int power(int num)
{
	int two = 1;

	while(num)
	{
		two *= 2;
		num--;
	}
	return two;
}

int min_bin(int deci)
{
	int bin = 1;
	int i = 0;

	if(deci > 0)
	{
		while(bin < deci)
		{
			bin *= -2;
			i++;
		}
		return i;
	}
	else if(deci < 0)
	{
		while(bin > deci)
		{
			bin *= -2;
			i++;
		}
		return i;
	}
	return 0;
}

int main()
{
	int deci;
	int leftover;
	int len_bin;
	unsigned int *min_binary;

	scanf("%d", &deci);
	getchar();

	printf("%d\n",min_bin(deci));

	len_bin = min_bin(deci);
	min_binary = (unsigned int*)malloc(sizeof(unsigned int) * len_bin);
	if(deci >= 0)
		leftover = power(len_bin) - deci;
	else
		leftover = power(len_bin - 1) - deci;
	printf("%d\n", leftover);
	for(int i = 0; i < len_bin; i++)
	{
		
	}

}
