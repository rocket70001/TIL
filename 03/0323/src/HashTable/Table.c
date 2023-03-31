#include <stdio.h>
#include <stdlib.h>
#include "Table.h"

void TBLInit(Table * pt, HashFunc * f)
{
	int i;

	for(i = 0; i < MAX_TBL; i++)
		(pt -> tbl[i]).status = EMPTY;
	pt -> hf = f;
}

//주의! Hash Value 를 의미하는 변수 hv는 해쉬 함수에 해쉬 키를 대입해 얻은 결과라는 의미에서 일반적인 Hash Value와 다른 의미를 갖는다.
//해쉬 밸류가 해쉬 함수를 통해 얻어낸 해쉬 키라는 점에서 헷갈릴 수 있다.
void TBLInsert(Table * pt, Key k, Value v)
{
	int hv = pt -> hf(k);
	pt -> tbl[hv].val = v;
	pt -> tbl[hv].key = k;
	pt -> tbl[hv].status = INUSE;

}

Value TBLDelete(Table * pt, Key k)
{
	int hv = pt -> hf(k);
	
	if((pt -> tbl[hv]).status != INUSE)
	{
		return NULL;
	}
	else
	{
		(pt -> tbl[hv]).status = DELETED;
		return (pt -> tbl[hv]).val;
	}
}

Value TBLSearch(Table * pt, Key k)
{
	int hv = pt -> hf(k);

	if((pt -> tbl[hv]).status != INUSE)
	{
		return NULL;
	}
	else
		return (pt->tbl[hv]).val;
}
