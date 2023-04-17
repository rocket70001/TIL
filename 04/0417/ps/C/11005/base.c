#include <stdio.h>

int main()
{
	int deci;
	int base;
	char answer[100000];
	int i = 0;

	scanf("%d %d", &deci, &base);
	getchar();
	
	while(deci >= base)
	{
		if(deci % base < 10)
			answer[i++] = '0' + deci % base;
		else
			answer[i++] = 'A' + (deci % base - 10);
		deci /= base;
	}
	if(deci < 10)
		answer[i] = '0' + deci % base;
	else
		answer[i] = 'A' + (deci % base - 10);
	while(i >= 0)
		printf("%c", answer[i--]);
}
