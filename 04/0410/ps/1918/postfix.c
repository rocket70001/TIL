#include <stdlib.h>
#include <stdio.h>
#include <string.h>
/*
typedef struct stack
{
	int 
} stack;
*/
void make_postfix(char *arr, int i)
{
	}

int main(){
	char expression[102];
	char answer[102];

	scanf("%s", expression);

	for(int i = 0; i < strlen(expression); i++)
	{
		if(expression[i] < 65 || expression[i] > 100)
		{
			char tmp = expression[i];
			expression[i] = expression[i + 1];
			expression[i + 1] = tmp; 
		i += 1;
		}
	}

	printf("%s", expression);
}
