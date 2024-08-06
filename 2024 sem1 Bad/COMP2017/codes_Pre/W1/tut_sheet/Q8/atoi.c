#include <stdio.h>

int atoi(const char s[]) {
    int i = 0; // 用于遍历字符串
    int n = 0; // 存储转换结果
    int sign = 1; // 标记正负数，1为正，-1为负

    while (s[i] == ' '){
    	i++;
    }

    if(s[i] == '-'){
    	sign = -1;
    	i++;
    }

    for (; s[i] >= '0' && s[i] <= '9'; i++)
    {
    	n = n * 10 + (s[i] - '0');
    }

    return sign * n;

}


int main(void) {
	printf("%d\n", atoi(""));
	printf("%d\n", atoi("0"));
	printf("%d\n", atoi("0123"));
	printf("%d\n", atoi("1234"));
	printf("%d\n", atoi("-1234"));
	return 0;
}