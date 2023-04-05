#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 600010

typedef struct Stack {
    char arr[MAX];
    int top;
} Stack;

void push(Stack *stack, char c) {
    stack->arr[++stack->top] = c;
}

char pop(Stack *stack) {
    if (stack->top == -1) {
        return '\0';
    }
    return stack->arr[stack->top--];
}

void do_order(char *order, Stack *left_stack, Stack *right_stack) {
    char temp;
    switch (order[0]) {
        case 'L':
            temp = pop(left_stack);
            if (temp != '\0') {
                push(right_stack, temp);
            }
            break;
        case 'D':
            temp = pop(right_stack);
            if (temp != '\0') {
                push(left_stack, temp);
            }
            break;
        case 'B':
            pop(left_stack);
            break;
        case 'P':
            push(left_stack, order[2]);
            break;
    }
}

int main() {
    Stack left_stack, right_stack;
    left_stack.top = -1;
    right_stack.top = -1;

    char sentence[MAX];
    int iter;
    char order[10];

    scanf("%s", sentence);
    getchar();
    scanf("%d", &iter);
    getchar();

    for (int i = 0; i < strlen(sentence); i++) {
        push(&left_stack, sentence[i]);
    }

    for (int i = 0; i < iter; i++) {
        scanf("%[^\n]s", order);
        getchar();
        do_order(order, &left_stack, &right_stack);
        memset(order, '\0', 10);
    }

    while (left_stack.top != -1) {
        push(&right_stack, pop(&left_stack));
    }

    int index = 0;
    while (right_stack.top != -1) {
        sentence[index++] = pop(&right_stack);
    }
    sentence[index] = '\0';

    printf("%s", sentence);
    return 0;
}
