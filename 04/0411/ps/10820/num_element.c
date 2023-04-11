#include <stdio.h>

int main()
{
	int num_str;
	char str[102];
	int low_up_num_spc[10110] = { 0,};

	scanf("%d", &num_str);
	getchar();

	for(int i = 0; i < num_str; i++)
	{
		scanf("%[^\n]s", str);
		getchar();
		int j = 0;
		while(str[j] != '\0')
		{
			if(str[j] > 96 && str[j] < 123)
				low_up_num_spc[(i*4) + 0]++;
			else if(str[j] > 64 && str[j] < 101)
				low_up_num_spc[(i*4) + 1]++;
			else if(str[j] > 47 && str[j] < 58)
				low_up_num_spc[(i*4) + 2]++;
			else 
				low_up_num_spc[(i*4) + 3]++;
			j++;
		}
	}

	for(int i = 0; i < num_str; i++)
	{
		printf("%d ", low_up_num_spc[(i*4) + 0]);
		printf("%d ", low_up_num_spc[(i*4) + 1]);
		printf("%d ", low_up_num_spc[(i*4) + 2]);
		printf("%d\n", low_up_num_spc[(i*4) + 3]);
	}
}
