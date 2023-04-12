#include <stdio.h>

long long multiply_digit(long long answer, long long digit)
{
	for(long long i = 0; i <= digit; i++)
		answer = 10 * answer;
	return answer;
}

long long get_digit(long long number)
{
	long long i = 0;
	while(number >= 10)
	{
		number /= 10;
		i++;
	}
	return i;
}

int main()
{
	long long abcd[4];
	long long A_digit;
	long long B_digit;
	long long answer = 0;

	scanf("%lld %lld %lld %lld", &abcd[0], &abcd[1], &abcd[2], &abcd[3]);
	A_digit = get_digit(abcd[1]);
	B_digit = get_digit(abcd[3]);
	answer += multiply_digit(abcd[0], A_digit);
	answer += multiply_digit(abcd[2], B_digit);
	printf("%lld", answer + abcd[1] + abcd[3]);
	return 0;
}
