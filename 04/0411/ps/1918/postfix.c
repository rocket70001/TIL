#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct stack
{
	char element[102];
	int top;
}stack;

void push(stack *st, char thing)
{
	st -> element[++st -> top] = thing;
}

char pop(stack *st)
{
	char sign = st -> element[st -> top--];
	return sign;
}

char peep(stack *st)
{
	return st -> element[st -> top];
}
int main()
{
	char expression[102];
	stack *operator = (stack*)malloc(sizeof(stack));
	stack *answer = (stack*)malloc(sizeof(stack));
	operator -> top = -1;
	answer -> top = -1;

	scanf("%s", expression);
	getchar();

	for(int i = 0; i < strlen(expression); i++)
	{
		if(expression[i] >= 65 && expression[i] <= 90)
			push(answer, expression[i]);
		else if((expression[i] == '+' || expression[i] == '-') 
				&& (peep(operator) != '(' && peep(operator) != '\0'))
		{
			while(operator -> element[operator -> top] != '(' && operator -> top != -1)
				push(answer, pop(operator));
			push(operator, expression[i]);
		}
		else if((expression[i] == '*' || expression[i] == '/') 
				&& ((peep(operator) != '(' && peep(operator) != '+' 
						&& peep(operator) != '-' && peep(operator) != '\0')))
		{
			push(answer, pop(operator));
			push(operator, expression[i]);
		}
		else if (expression[i] == ')')
		{
			while(peep(operator) != '(')
				push(answer, pop(operator));
			pop(operator);
		}
		else
			push(operator, expression[i]);
			
	}

	while(operator -> top != -1)
		push(answer, pop(operator));

	int i = 0;
	while(answer -> element[i] != '\0')
		printf("%c", answer -> element[i++]);

	return 0;
}
