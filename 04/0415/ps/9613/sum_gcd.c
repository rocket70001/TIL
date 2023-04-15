#include <stdio.h>
#include <string.h>

int get_euclidean_gcd(int a, int b)
{
	int bigger, smaller;	
	if(a > b)
	{
		bigger = a;
		smaller = b;
	}
	else {
		bigger = b;
		smaller = a;
	}
	while(smaller != 0)
	{
		int r = bigger % smaller;
		bigger = smaller;
		smaller = r;
	}
	return bigger;
}

long long get_gcd_sum(int *arr)
{
	long long sum_gcd = 0;

	for(int i = 1; i < arr[0]; i++)
	{
		for(int j = i + 1; j <= arr[0]; j++)
			sum_gcd += get_euclidean_gcd(arr[i],arr[j]);
	}
	return sum_gcd;
}

int main()
{
	int num_test;
	char *ptr_str;
	
	scanf("%d", &num_test);
	getchar();

	for(int i = 0; i < num_test; i++)
	{
		char str_test[1000000];
		scanf("%[^\n]s", str_test);
		getchar();
		int numarr[110] = { 0, };
		ptr_str = str_test;
		sscanf(ptr_str, "%d", &numarr[0]);
		for(int j = 1; j <= numarr[0]; j++)
		{
			ptr_str = strchr(ptr_str, ' ');
			ptr_str++;
			sscanf(ptr_str, "%d", &numarr[j]);
		}
		printf("%lld\n",get_gcd_sum(numarr));
	}
	
}
