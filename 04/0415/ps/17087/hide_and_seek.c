#include <stdio.h>
#include <string.h>
#include <stdlib.h>

unsigned int get_binary_gcd(unsigned int a, unsigned int b)
{
    if (a == 0)
        return b;
    if (b == 0)
        return a;

    unsigned int shift = __builtin_ctz(a | b);

    a >>= __builtin_ctz(a);
    b >>= __builtin_ctz(b);

    while (a != b) {
        if (a > b) {
            a -= b;
            a >>= __builtin_ctz(a);
        } else {
            b -= a;
            b >>= __builtin_ctz(b);
        }
    }

    return a << shift;
}

int main()
{
    int num_hider;
    unsigned int it_location;
    unsigned int hider_location[1000010] = { 0, };
    unsigned int distance[1000010] = { 0, };
    unsigned int shortcut = 1;

    scanf("%d %u", &num_hider, &it_location);
    getchar();

    int i = 0;
    while (i < num_hider) {
        if (scanf("%u", &hider_location[i]) != 1)
            return 1;
        distance[i] = abs((int)it_location - (int)hider_location[i]);
        i++;
    }

    shortcut = get_binary_gcd(distance[0], distance[1]);
    for(int i = 0; i < num_hider; i++) 
		shortcut = get_binary_gcd(shortcut, distance[i]); 
    printf("%u", shortcut);
    return 0;
}
