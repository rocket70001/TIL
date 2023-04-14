#include <stdio.h>

int cnt_permu_zero(int num_case, int num_choice)
{
	int sum_two = 0;
	int sum_five = 0;

	for(int i = 2; i <= num_case; i *= 2)
		sum_two += num_case / i;
	for(int i = 2; i <= num_choice; i *=2)
		sum_two -= num_choice / i;
	for(int i = 2; i <= num_case - num_choice; i *=2)
		sum_two -= (num_case - num_choice) / i;
	for(int i = 5; i <= num_case; i *= 5)
		sum_five += num_case / i;
	for(int i = 5; i <= num_choice; i *= 5)
		sum_five -= num_choice / i;
	for(int i = 5; i <= num_case - num_choice; i *= 5)
		sum_five -= (num_case - num_choice) / i;
	
	return ((sum_two > sum_five) ? sum_five : sum_two);
}

int main()
{
	int num_case;
	int num_choice;
	int num_zero = 0;

	scanf("%d %d", &num_case, &num_choice);
	num_zero = cnt_permu_zero(num_case,num_choice);
	if(num_zero < 0)
		num_zero = 0;
	printf("%d", num_zero);
	return 0;
}
