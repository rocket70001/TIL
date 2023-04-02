#include <stdio.h>
#include <string.h>

int main()
{
	char paren[100010];
	int stick = 0;
	int answer = 0;

	scanf("%s", paren);
	getchar();
	
	for(int i = 0; i < strlen(paren); i++) 
	{
		if(paren[i] == '(')
			stick++;
		if(paren[i] == ')')
		{
			stick--;	
			if(paren[i - 1] == '(')
				answer += stick;
			if(paren[i + 1] == ')')
				answer++;
		}
	}
	printf("%d\n", answer);
}

