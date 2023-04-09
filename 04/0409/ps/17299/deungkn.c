#include <stdlib.h>
#include <stdio.h>

typedef struct _stack
{
	int top;
	int st_arr[1000001];
} stack;

void push(stack *st, int data)
{
	st -> st_arr[++st -> top] = data;
}

int pop(stack *st)
{
	int answer = st -> st_arr[st -> top--];
	return answer;
}

int main()
{
	int N;
	int arr[1000001];
	int cnt_arr[1000001] = { 0, };
	int cnt_max = 0;
	//int answer[1000001];

	stack *st = (stack*)malloc(sizeof(stack));	
	st -> top = -1;
	
	scanf("%d", &N);
	getchar();

	for(int i = 0; i < N; i++)
	{
		scanf("%d", &arr[i]);
		cnt_arr[arr[i]]++;
		if(cnt_max < arr[i])
			cnt_max = arr[i];
		printf("arr[%d] = %d, cnt_arr[%d] = %d, cnt_max = %d\n", i, arr[i], arr[i], cnt_arr[arr[i]], cnt_max);
	}

	for(int i = N - 1; i >= 0; i--)
	{
		int cnt_idx = 0;
		while(cnt_idx <= cnt_max && cnt_arr[cnt_idx] <= cnt_arr[arr[i]])
		{
			cnt_idx++;
		}
		if(cnt_idx == cnt_max + 1)
			push(st, -1);
		else
		{
			int j = i + 1;
			printf("before while, arr[%d] = %d\n", i, arr[i]);
			while(cnt_arr[arr[j++]] <= cnt_arr[arr[i]])
			{
				if(j == N)
				{
					push(st, -1);
					break;
				}
				if(cnt_arr[arr[j]] > cnt_arr[arr[i]])
				{
					push(st, arr[j]);
					break;
				}
			}
			printf("cnt_idx = %d\n", cnt_idx);
		}
	}
	while(st -> top >= 0)
		printf("%d ", pop(st));
}
