# python.py

# 선언 = 초기화 (자동 타입 배정, Type Hints로 타입을 
# 명시적으로 선언할 수도 있지만 필수는 아니다.)
a = 4
b = 6
print(a + b)
print()

# 문자열 선언과 슬라이싱
# 작은 따옴표와 큰 따옴표 모두 선언 가능

a = ''' "Hello World" '''
b = """ 'H'ello Worl'd' """
c = 'Life is too short'
d = "You need a Python"

print(a)
print(b)
print(c[:7] + ' ' + d[-8:])
print(d[:9] + c[-9:])

# 파이썬의 문자열은 immutable로 특정 인덱스만을 바꿀 수 없다.

# d[0] = 'T'

# print(d)
# 위 코드 실행시 에러 발생
print()
# 

# 문자열 포매팅

string = "Art is long, and Time is fleeting. -%d %s" %(1882, 'Henry Wadsworth Longfellow')

print(string)
print()
# 문자열 정렬

pi = 3.141592

print("pi = %f" %3.141592)
print("pi -> %%0.2f -> %0.2f" %pi)
print("pi + %%10.2f -> %10.2f" %pi)
print()

# if문

money = "can't buy everything"

if money == "can buy everything" :
    print("How Much?")
else:
    print("How?")

print()

# while문

num = 1
hello = "World"

while num <= 10:
    print(hello + "%s" %num)
    num += 1

print()

# for문

animals = ["Zebra", "Impala", "Rhino"]

for firstInitialized in animals:
    print(firstInitialized + " is running on the field")
    if firstInitialized == 'Rhino':
        print("All animals are free")
print()

# for range 구구단

for i in range(2, 10):
    for j in range(1, 10):
        print(i * j, end = " ")
    print('')

print()

# input

number = int(input("Enter the number of animals: "))
animals = list()

for i in range(0,number):
    animals.append(input("Enter the name of animal: "))
print()
for i in range(0, number):
    print(animals[i] + " is free")
