# 배열 기반 원형 큐

## 원형 큐 기본 개념  
선입 선출 큐 구현 위해 front와 rear을 인덱싱한다.  
일차원 배열이 환형으로 이어진 형태로 init시 front와 rear을 동일하게 0으로 설정해 큐가 비어있음을 드러나게 한다.  
isEmpty 함수로 확인해 front와 rear 값이 같으면 큐가 비어있는 것으로 간주한다.  
큐가 가득 찰 경우에도 front rear값이 같아지므로 큐의 인덱스 최댓값을 항상 n - 1로 설정해 비어있는 상태와 구분할 수 있도록 한다.  
Dequeue시 0인덱싱이 된 front를 한 칸 뒤로 옮기고 해당 인덱스가 가리키는 값을 반환한다.  
Enqueue시 rear을 한 칸 이동하고 인덱싱된 곳에 데이터를 저장한다.  
NextPosIdx 함수가 가장 중요한데 인덱스 값이 배열 최대 길이의 -1이면 인덱스 0을 반환해 rear을 회전시키고  
아닐 경우 1을 더해 다음 인덱스값을 반환하게 한다.