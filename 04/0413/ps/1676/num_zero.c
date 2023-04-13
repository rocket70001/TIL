#include <stdio.h>

long long cnt_zero_from_factorial(int num)
{
	if(num == 0)
		return 0;
	int sum_zero = 0;
	while(num != 1)
	{
		if(num % 125 == 0)
			sum_zero += 3;
		else if(num % 25 == 0)
			sum_zero += 2;
		else if(num % 5 == 0)
			sum_zero++;
		num--;
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
