#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct stack
{
	double value[102];
	int top;
} stack;

void push(stack *st, double num)
{
	st -> value[++st -> top] = num;
}

double pop(stack *st)
{
	return st -> value[st -> top--];
}

double pick_operator(char operator, double A, double B)
{
	switch (operator) {
		case '*':
			return A * B;
		case '/':
			return A / B;
		case '+':
			return A + B;
		case '-':
			return A - B;
	}
	return 0;
}

int main()
{
	int num_operand;
	char expression[110];
	int val_operand[26];
	stack *answer = (stack*)malloc(sizeof(stack));
	answer -> top = -1;

	scanf("%d", &num_operand);
	getchar();
	scanf("%s", expression);

	for(int i = 0; i < num_operand; i++)
	{
		scanf("%d", &val_operand[i]);
		getchar();
	}
	for(int i = 0; i < strlen(expression); i++)
	{
		if(expression[i] < 65 || expression[i] > 100)
		{
			double B = pop(answer);
			double A = pop(answer);
			double C = pick_operator(expression[i], A, B);
			push(answer, C);
		}
		else {
			push(answer, val_operand[expression[i] - 65]);
		}
	}
	printf("%.2f", pop(answer));
}
