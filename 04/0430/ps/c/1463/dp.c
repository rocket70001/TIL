#include <stdio.h>

int main() 
{
	int arr[1000001];
	int num;
	scanf("%d", &num);

	arr[0] = 0;
	arr[1] = 0;
	arr[2] = 1;
	arr[3] = 1;

	for(int i = 4; i <= num; i++)
	{
		int min = arr[i - 1] + 1;
		if(i % 3 == 0 && min > arr[i / 3])
			min = arr[i / 3] + 1;
		if(i % 2 == 0 && min > arr[i / 2])
			min = arr[i / 2] + 1;
		arr[i] = min;
	}
	printf("%d", arr[num]);
}
