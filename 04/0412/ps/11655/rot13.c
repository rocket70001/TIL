#include <stdio.h>
#include <string.h>

int main()
{
	char str[102];

	scanf("%[^\n]s", str);
	getchar();

	for(int i = 0; i < strlen(str); i++)
	{
		if(str[i] >= 'a' && str[i] <= 'z')
		{
			if(str[i] + 13 > 'z')
				str[i] = 96 + str[i] + 13 - 'z';
			else
				str[i] += 13;
		}
		else if(str[i] >= 'A' && str[i] <= 'Z')
		{
			if(str[i] + 13 > 'Z')
				str[i] = 64 + str[i] + 13 - 'Z';
			else
				str[i] += 13;
		}
	}
	printf("%s", str);
	return 0; 
}
