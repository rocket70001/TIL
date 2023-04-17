#include <stdio.h>
#include <string.h>

int power(int decimal, int times)
{
	int answer = decimal;
	if(times == 0)
		return 1;
	while(times > 1)
	{
		answer *= decimal;
		times--;
	}
	return answer;
}

int main()
{
	char str_num[50];
	int base;
	int decimal = 0;
	int i = 0;

	scanf("%s", str_num);
	scanf(" %d", &base);
	getchar();

	int digit = strlen(str_num) - 1;
	for(int i = digit; i >= 0; i--)
	{
		if(str_num[i] <= '9')
			decimal += (str_num[i] - '0') * power(base, digit - i);
		else
			decimal += (str_num[i] - 'A' + 10) * power(base, digit - i);
	}
	if(decimal > 1000000000 || decimal < 0)
		decimal = 1000000000;
	printf("%d", decimal);
}
