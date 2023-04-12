#include <stdio.h>

int main()
{
	while(1)
	{
		char str[102] = {0};
		printf("Hello\n");
		scanf("%[^\n]s", str);
		printf("this is string -> %s, and str[0] -> %c\n", str, str[0]);
		if(str[0] == '\n')
			break;
		int i, low, up, num, spc = 0;
		while(str[i] != '\0')
		{
			if(str[i] > 96 && str[i] < 123)
				low++;
			else if(str[i] > 64 && str[i] < 101)
				up++;
			else if(str[i] > 47 && str[i] < 58)
				num++;
			else 
				spc++;
			i++;
		}
		printf("%d %d %d %d\n", low, up, num, spc);
		scanf("%*c");
	}
	return 0;
}
