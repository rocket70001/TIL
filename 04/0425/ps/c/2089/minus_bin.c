#include <stdio.h>
#include <stdlib.h>

int main()
{
	int decimal;
	int min_bin = 0;
	int bin = 1;
	int times = 1;

	scanf("%d", &decimal);

	while(bin <= abs(decimal))
	{
		bin *= 2; 
		times++;
		printf("%d\n", times);
	}
	if(decimal < 0)
		times++;
	for(int i = times; i > 0; i--)
	{
		int tmp = i;
		int exponetial = 1;
		while(tmp-- > 0)
			exponetial *= -2;
		min_bin = exponetial + decimal;
		if(
		
	}
}
