#include <stdio.h>

int main()
{
	int future_base;
	int present_base;
	int digit;
	int deciamal = 0;
	int answer[25];

	scanf("%d %d", &future_base, &present_base);
	getchar();
	scanf("%d", &digit);
	getchar();

	for(int i = digit; i > 0; i--)
	{
		int times = i;
		int tmp;
		int num_exponential = 1;
		scanf("%d", &tmp);
		while(times > 1)
		{
			num_exponential *= future_base;
			times--;
		}
		deciamal += tmp * num_exponential;
	}
	
	int i = 0;
	while(deciamal != 0 || deciamal % present_base != 0)
	{
		answer[i++] = deciamal % present_base;
		deciamal /= present_base;
	}
	while(--i >= 0)
		printf("%d ", answer[i]);
}
