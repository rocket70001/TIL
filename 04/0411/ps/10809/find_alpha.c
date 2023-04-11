#include <stdio.h>

int main()
{
	int i = 0;
	char word[102];
	int alpha_location[26];

	for(int j = 0; j < 26; j++)
		alpha_location[j] = -1;

	scanf("%s", word);
	getchar();

	while(word[i] != '\0')
	{
		if(alpha_location[word[i] - 97] == -1)
			alpha_location[word[i] - 97] = i;
		i++;
	}
	for(i = 0; i < 26; i++)
		printf("%d ", alpha_location[i]);
	return 0;
}
