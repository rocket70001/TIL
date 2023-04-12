#include <stdio.h>
#include <string.h>

int main()
{
	while(1)
	{
		char str[102] = {0};
		scanf("%[^\n]s", str);
		getchar();
		if(str[0] == '\0')
			break;
		int low = 0, up = 0, num = 0, spc = 0;
		for(int i = 0; i < strlen(str); i++)
		{
			if(str[i] >= 'a' && str[i] <= 'z')
				low++;
			else if(str[i] >= 'A' && str[i] <= 'Z')
				up++;
			else if(str[i] >= '0' && str[i] <= '9')
				num++;
			else
				spc++;
		}
		printf("%d %d %d %d\n", low, up, num, spc);
	}
	return 0;
}
