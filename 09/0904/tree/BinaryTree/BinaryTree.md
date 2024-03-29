# 이진트리

## 포화이진트리, 완전이진트리
정의에 따라 다르지만 공집합을 포함하는 일반 트리를 이진트리로 분류하기도 한다.  
모든 레벨이 꽉 찬 상태의 포화 이진트리와 레벨에 빈 곳은 존재할 수 있지만 노드가 꽉 찬 완전이진트리가 중요하다.  
루트 노드부터 레벨0,1,2,3... 순으로 증가하며 전체의 길이를 높이라고 한다.  
가장 말단에서 더이상 노드를 가지지 않는 노드를 단말 노드라고 한다.  

## 배열 기반 구현과 링크드 리스트 기반 구현
일반적으로는 유연한 링크드 리스트 기반 구현이 낫다.  
하지만 완전이진트리를 구현하고 빈번하게 탐색이 이뤄지는 경우 배열로 만드는 게 더 빠르다.  
이 배열기반의 완전이진트리가 힙이다.  
이진트리 구현시 편의를 위해 관례적으로 0번 인덱스는 사용하지 않는다. 루트노드는 1번이 된다.  

## 공집합 노드를 가진 노드도 이진트리라는 말의 의미
트리 노드 구조체를 선언할 때 별도로 노드를 표현하는 구조체를 선언하지 않는다.  
데이터와 왼, 오른편 포인터를 갖는 트리 노드 자체가 재귀적으로 트리 노드 포인터를 담는 구조체이기 때문이다.  
따라서 트리노드 포인터가 모두 NULL을 가리킬 때 해당 노드는 공집합 노드를 가지고 있다고 표현하고 이진 트리로 간주한다.  

## 서브 트리 삭제시 주의점
둘 이상의 노드로 이루어진 서브 트리를 삭제하려면 서브 트리를 구성하는 모든 노드를 대상으로 free()를 호출해야 한다.  
모든 노드를 방문하는 걸 순회라고 하는데 연결 리스트와 달리 별도의 방법이 필요하다.

## 이진 트리의 순회(Traversal)
루트 노드를 방문하는 순서에 따라 전위, 중위, 후위 순회로 구분한다.  
루트가 1이고 왼쪽 노드가 2 오른쪽 노드가 3이면 차례로 123, 213, 312로 순회한다.  
순회는 이진 트리 자체가 재귀적인 형태의 구조체로 구성된 것을 이용해 재귀함수를 이용한다.  
재귀함수가 단말 노드에 도달할 시 단말 노드의 서브 트리 포인터는 NULL을 가리키므로 탈출 조건으로 사용할 수 있다.
```c
void InorderTraverse(BTreeNode* bt) {
	if(bt == NULL) {
		return ;
	}

	InorderTraverse(bt -> left);
	printf("%d \n", bt -> data); //함수의 순회 조건을 단순히 루트 노드값의 출력이라고 전제한다.
	InorderTraverse(bt -> right);
}
```
전위, 중위, 후위는 함수 호출 순서만 바꿔주면 된다.
예시에서는 printf를 사용했지만 함수 포인터를 사용해 순회 시 취할 함수를 선택할 수도 있다.

