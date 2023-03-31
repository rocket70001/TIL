#include <stdio.h>
#include <stdlib.h>
#include "Person.h"
#include "Table.h" 

int MyHashFunc(Key k)
{
	return k % 100;
}

int main(void)
{
	Table myTbl;
	Person * np;
	//Person * sp;
	//Person * rp;

	TBLInit(&myTbl, MyHashFunc);

	//입력
	np = MakePersonData(20120003, "Lee", "Seoul");
	TBLInsert(&myTbl, GetSSN(np), np);

	np = MakePersonData(20130012, "KIM", "Jeju");
	TBLInsert(&myTbl, GetSSN(np), np);

	np = MakePersonData(20170049, "HAN", "Kangwon");
	TBLInsert(&myTbl, GetSSN(np), np);

	//탐색 교재에는 변수 sp
	np = TBLSearch(&myTbl, 20120003);
	if(np != NULL)
		ShowPerInfo(np);

	np = TBLSearch(&myTbl, 20130012);
	if(np != NULL)
		ShowPerInfo(np);
	
	np = TBLSearch(&myTbl, 20170049);
	if(np != NULL)
		ShowPerInfo(np);
	
	//삭제 교재에는 변수 rp
	np = TBLDelete(&myTbl, 20120003);
	if(np != NULL)
		free(np);
	
	np = TBLDelete(&myTbl, 20130012);
	if(np != NULL)
		free(np);

	np = TBLDelete(&myTbl, 20170049);
	if(np != NULL)
		free(np);

	return 0;
}
