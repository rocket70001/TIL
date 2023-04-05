#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define sent_MAX 100010
#define MAX 600010

typedef struct _node
{
	char data;
	struct _node *next;
	struct _node *before;
}node;

typedef struct _Llist
{
	int num_node;
	node *cur;
	node *head;
	node *tail;
}list;

void init(list* li)
{
	node *pad_head = (node*)malloc(sizeof(node));
	node *pad_tail = (node*)malloc(sizeof(node));

	pad_head -> data = '\0';
	pad_head -> before = NULL;
	pad_head -> next = li -> cur;

	pad_tail -> data = '\0';
	pad_tail -> before = li -> cur;
	pad_tail -> next = NULL;

	li -> num_node = 2;
	li -> cur = pad_tail;
	li -> head = pad_head;
	li -> tail = pad_tail;
}

void move_curL(list *li)
{
	if(li -> cur == li -> head || li -> cur -> before == li -> head)
		return ;
	li -> cur = li -> cur -> before;
}

void move_curR(list *li)
{
	if(li -> cur == li -> tail)
		return ;
	li -> cur = li -> cur -> next;
}

void remove_curL(list *li)
{
	if(li -> cur == li -> head || li -> cur -> before == li -> head)
		return ;
	node *del_node = li -> cur -> before;

	li -> cur -> before = li -> cur -> before -> before;
	li -> cur -> before -> next = li -> cur;
	li -> num_node--;

	free(del_node);
}

void add_curL(list *li, char alphabet)
{
	node *new_node = (node*)malloc(sizeof(node));

	new_node -> data = alphabet;
	
	if(li -> num_node == 2)
	{
		new_node -> before = li -> head;
		new_node -> next = li -> tail;
		li -> cur = new_node;
		li -> head -> next = new_node;
		li -> tail -> before = new_node;	
		move_curR(li);
	}
	else
	{
		new_node -> before = li -> cur -> before;
		new_node -> next = li -> cur;
		li -> cur -> before -> next = new_node;
		li -> cur -> before = new_node;
	}

	li -> num_node++;
}
	
void do_order(char *order, list *li)
{
	switch (order[0]) {
		case 'L' :
			move_curL(li);
			break;
		case 'D' :
			move_curR(li);
			break;
		case 'B' :
			remove_curL(li);
			break;
		case 'P' :
			add_curL(li, order[2]);
			break;
	}
}

int main()
{
	char sentence[sent_MAX];
	char answer[MAX];
	char order[10];
	list *li = (list*)malloc(sizeof(list));
	int iter;	
	
	scanf("%s", sentence);
	getchar();
	scanf("%d", &iter);
	getchar();

	init(li);
	
	for(int i = 0; i < strlen(sentence); i++)
		add_curL(li, sentence[i]);

	for(int i = 0; i < iter; i++)
	{
		scanf("%[^\n]s", order);
		getchar();
		do_order(order, li);
		memset(order, '\0', 10);
	}

	li -> cur = li -> tail;
	for(int i = li -> num_node - 2 ; i >=0; i--)
	{
		if(li -> cur != li -> head || li -> cur != li -> tail)
			answer[i] = li -> cur -> data;
		move_curL(li);
	}

	printf("%s", answer);
	return 0;
}
