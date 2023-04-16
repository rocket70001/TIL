#include <stdio.h>
#include <string.h>

int two_to_oct(int two_third, int two_sec, int two_fir)
{
	int num = two_third * 1 + two_sec * 2 + two_fir * 4;
	return num;
}

int main()
{
	char bin[1000010];
	int oct[400000];
	int i;
	int j = 0;
	int num;

	scanf("%s", bin);
	getchar();

	for(i = strlen(bin) - 1 ; i >= 2; i -= 3)
		oct[j++] = two_to_oct(bin[i] - '0', bin[i - 1] - '0', bin[i - 2] - '0');
	
	if(i == 0)
		oct[j++] = two_to_oct(bin[0] - '0', 0, 0);
	else if(i == 1)
		oct[j++] = two_to_oct(bin[1] - '0', bin[0] - '0', 0);
	
	for(int i = --j; i >= 0; i--)
		printf("%d", oct[i]);
}
