#include <stdio.h>

int main()
{
	char word[102];
	int i = 0;

	scanf("%s", word);
	while(word[i] != '\0')
		i++;
	printf("%d", i);
	return 0;
}
