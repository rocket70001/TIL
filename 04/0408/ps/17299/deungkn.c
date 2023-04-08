#include <stdio.h>
#include <stdlib.h>

typedef struct stack
{
	int top;
	int arr[1000001];
} stack;

void push(stack *st, int data)
{
	st -> arr[++st -> top] = data;
}

int pop(stack *st)
{
	int answer = st -> arr[st -> top--];
	return answer;
}

int main()
{
	int N;
	int comp[1000001];
	int value_count[1000001];
	int result[1000001];
	stack *st = (stack*)malloc(sizeof(stack));
	st -> top = -1;

	scanf("%d", &N);
	getchar();

	for(int i = 0; i < N; i++)
		scanf("%d", &comp[i]);
	for(int i = 0; i < N; i++)
	{
	}
	for(int i = N - 1; i > 0; i--)
	{
		push(st, comp[i]);
	}
	

		

}
