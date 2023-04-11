#include <stdio.h>

int main()
{
	int i = 0;
	char word[102];
	int num_alphabet[26] = { 0, };

	scanf("%s", word);
	getchar();
	while(word[i] != '\0')
		num_alphabet[word[i++] - 97]++;
	for(int i = 0; i < 26; i++)
		printf("%d ", num_alphabet[i]);
	return 0;
}
