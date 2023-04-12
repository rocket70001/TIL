#include <stdio.h>
#include <string.h>

int is_prime(int num)
{
	if(num == 0 || num == 1)
		return -1;
	for(int i = 2; i*i <= num; i++)
	{
		if(num % i == 0 && num != 2)
			return -1;
	}
	return 0;
}

int main()
{
	int n;
	int num_prime = 0;
	char num_str[100000];	
	char *ptr_numstr;

	scanf("%d", &n);
	getchar();
	int prime;

	scanf("%[^\n]s", num_str);
	getchar();
	ptr_numstr = num_str;
	for(int i = 0; i < n; i++)
	{
		sscanf(ptr_numstr,"%d", &prime);
		ptr_numstr = strchr(ptr_numstr, ' ');
		if(ptr_numstr != NULL)
			ptr_numstr++;
		if(is_prime(prime) == 0)
			num_prime++;
		else 
			continue;
	}
	printf("%d\n", num_prime);
}
