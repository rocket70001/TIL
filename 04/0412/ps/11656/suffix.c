#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct str_list 
{
	char str[1001];
} str_list;

int compare_str(const void *str1, const void *str2)
{
	if(strcmp((char*)str1, (char*)str2) > 0)
		return 1;
	else if(strcmp((char*)str1, (char*)str2) < 0)
		return -1;
	else
		return 0;
}

int main()
{
	char str_input[1001];
	int len_input;

	scanf("%s", str_input);
	getchar();
	len_input = strlen(str_input);
	str_list list[len_input];
	
	for(int i = 0; i < strlen(str_input); i++)
	{
		int x = i;
		int j = 0;
		while(str_input[x] != '\0')
			list[i].str[j++] = str_input[x++];
		list[i].str[j] = '\0';
	}
	
	qsort(list, len_input, sizeof(list->str), compare_str);
	
	for(int i = 0; i < len_input; i++)
	{
		printf("%s\n", list[i].str);
	}
}
