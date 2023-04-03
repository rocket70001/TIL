#include <stdio.h>

#define MAX 600010

void move_curL(char *sentence)
{

}

void move_curR(char *sentence)
{}

void remove_curL(char *sentence)
{}

void add_curL(char *sentence, char alphabet)
{}
	
void do_order(char *order, char *sentence)
{
	switch (order[0]) {
		case 'L' :
			move_curL(sentence);
			break;
		case 'D' :
			move_curR(sentence);
			break;
		case 'B' :
			remove_curL(sentence);
			break;
		case 'P' :
			add_curL(sentence, order[2]);
			break;
	}
}

int main()
{
	char sentence[MAX];
	char order[10];
	int iter;	
	
	scanf("%s", sentence);
	getchar();
	scanf("%d", &iter);
	getchar();

	for(int i = 0; i < iter; i++)
	{
		scanf("%s", order);
		getchar();
	}
	printf("%s", sentence);
	return 0;
}
