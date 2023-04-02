#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct _stack
{
	int top_num;
	int arr[100010];
} stack;

void init(stack *st)
{ 
	for(int i = 0; i < 100010; i++)
		st -> arr[i] = 0;
	st -> top_num = 0;
}

int main()
{
	char paren[100010];
	int laser = 0;
	int iter;
	int answer = 0;
	int max_topnum = 0;
	stack *st = (stack*)malloc(sizeof(stack));

	scanf("%s", paren);
	getchar();
	iter = strlen(paren);
	
	init(st);
	for(int i = 0; i < iter - 1; i++) 
	{
		if(max_topnum < st -> top_num)
			max_topnum = st -> top_num;
		if(paren[i] == '(' && paren[i + 1] == ')')
		{
			laser++;		
		}
		else if(paren[i] == '(')
		{
			st -> top_num++;
		}
		else if(paren[i] == ')')
		{
			int tmp = st -> top_num;
			while(tmp != 0)
			{
				st -> arr[tmp--] += laser;
			}
			st -> top_num--;
		}
	}
	for(int i = 1; i <= max_topnum - 1; i++)
	{
		answer += st -> arr[i] + 1;
	}
	printf("%d\n", answer);
}

