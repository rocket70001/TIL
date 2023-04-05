#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 600010

void do_order(char *order, char *sentence, int *cursor) {
    switch (order[0]) {
        case 'L':
            if (*cursor > 0) {
                (*cursor)--;
            }
            break;
        case 'D':
            if (sentence[*cursor] != '\0') {
                (*cursor)++;
            }
            break;
        case 'B':
            if (*cursor > 0) {
                memmove(sentence + *cursor - 1, sentence + *cursor, strlen(sentence + *cursor) + 1);
                (*cursor)--;
            }
            break;
        case 'P':
            memmove(sentence + *cursor + 1, sentence + *cursor, strlen(sentence + *cursor) + 1);
            sentence[*cursor] = order[2];
            (*cursor)++;
            break;
    }
}

int main() {
    char sentence[MAX] = {0};
    char order[10] = {0};
    int cursor = 0;
    int iter;

    scanf("%s", sentence);
    getchar();
    scanf("%d", &iter);
    getchar();

    cursor = strlen(sentence);

    for (int i = 0; i < iter; i++) {
        scanf("%[^\n]s", order);
        getchar();
        do_order(order, sentence, &cursor);
        memset(order, '\0', 10);
    }

    printf("%s", sentence);
    return 0;
}
