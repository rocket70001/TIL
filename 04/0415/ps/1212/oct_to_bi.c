#include <stdio.h>
#include <string.h>

int main()
{
	char str_oct[333344];
	int binary[1100000];
	int i;
	int j = 0;

	scanf("%s", str_oct);
	for(i = strlen(str_oct) - 1; i >= 0; i--)
	{
		binary[j++] = (str_oct[i] - '0') % 2;
		if((str_oct[i] - '0') / 2 == 3)
		{
			binary[j++] = 1;
			binary[j++] = 1;
		}
		else if((str_oct[i] - '0') / 2 == 2)
		{
			binary[j++] = 0;
			binary[j++] = 1;
		}
		else if((str_oct[i] - '0') / 2 == 1){
			binary[j++] = 1;
			binary[j++] = 0;
		}
		else {
			binary[j++] = 0;
			binary[j++] = 0;
		}
	}
	for(int i = --j; i >= 0; i--)
	{
		if(i == j && binary[i] == 0)
		{
			i--;
			if(binary[i] == 0)
				i--;
		}
		printf("%d", binary[i]);
	}

}
