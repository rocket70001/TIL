#include <stdio.h>

long long cnt_zero_from_factorial(int num)
{
	if(num == 0)
		return 1;
	long long multiple = num;
	int sum_zero = 0;
	while(num != 1)
	{
		printf("multiple is -> %lld\n", multiple);
		multiple *= --num;
		while(multiple % 10 == 0)
		{
			sum_zero++;
			multiple /= 10;
		}
	}
	return sum_zero;
}

int main()
{
	int num_zero = 0;
	int N;
	scanf("%d", &N);
	getchar();
	num_zero = cnt_zero_from_factorial(N);
	printf("%d", num_zero);
}
