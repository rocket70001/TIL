#include <stdlib.h>
#include <stdio.h>

typedef struct stack
{
    int top;
    int arg[1000010];
} stack;

void push(stack *st, int data)
{
    if (st->top == 1000000)
        return;
    st->arg[++st->top] = data;
}

int pop_okn(stack *st)
{
    int data = st->arg[st->top--];
    return data;
}


int traverse(stack *st, int comp_num)
{
    for (int i = st -> top; i >= 0; i--)
    {
        if (comp_num < st->arg[i])
            return st->arg[i];
    }
    return -1;
}

int main()
{
    int N;
    int arr[1000001];
    int answer[1000001];
    stack *okn = (stack*)malloc(sizeof(stack));
    okn -> top = -1;

    scanf("%d", &N);
	getchar();

    for (int i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    for (int i = N - 1; i >= 0; i--)
        push(okn, arr[i]);
    int i = 0;
    while (okn -> top >= 0)
	{
        int comp_num = pop_okn(okn);
        answer[i++] = traverse(okn, comp_num);
    }
	
    for (int i = 0; i < N; i++)
        printf("%d ", answer[i]);
    return 0;
}
