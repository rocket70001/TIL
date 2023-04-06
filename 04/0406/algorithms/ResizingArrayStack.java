import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item>
{
	private Item[] a = (Item[]) new Object[1]; //스택 항목
	private int N = 0;						   //항목 개수
						
	public boolean isEmpty() { return N == 0; }
	public int size() { return N; }

	private void resize(int max)
	{//크기가 max인 새로운 배열로 스택을 이동
	 Item[] temp = (Item[]) new Object[max];
	 for(int i = 0; i < N; i++)
		 temp[i] = a[i];
	 a = temp;
	}

	public void push(Item item)
	{//스택의 위에 새로운 항목 추가
		if (N == a.length) resize(2 * a.length);
		a[N++] = item;
	}

	public Item pop()
	{//스택의 위에서 항목 꺼내기(제거)
		Item item = a[--N];
		a[N] = null; //로이터링 방지
		if (N > 0 && N == a.length / 4) resize(a.length / 2);
		return item;
	}

	public Iterator<Item> iterator()
	{ return new ReverseArrayIterator(); }

	private class ReverseArrayIterator implements Iterator<Item>
	{//LIFO 반복자 지원
		private int  i = N;
		public boolean hasNext() {return i > 0;}
		public Item next() {return a[--i];}
		public void remove() {}
	}
}
