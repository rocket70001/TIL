#include <stdio.h>

int get_euclidean_gcd(int bigger, int smaller)
{
	while(smaller != 0)
	{
		int r = bigger % smaller;
		bigger = smaller;
		smaller = r;
	}
	return bigger;
}

int main()
{
	int times;
	int a, b;
	int gcd;
	long long lcm;

	scanf("%d", &times);
	
	for(int i = 0; i < times; i++)
	{
		scanf("%d %d", &a, &b);
		if(a > b)
			gcd = get_euclidean_gcd(a, b);
		else
			gcd = get_euclidean_gcd(b, a);
		lcm = a*b/gcd;
		printf("%lld\n", lcm);
	}
}
