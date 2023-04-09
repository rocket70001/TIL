
public class twoarr {
	public static void main(String args[]){
		int[][] arr = new int[10][20];
		int[][] another = new int[20][10];


		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				arr[i][j] = i + j;
			}

		}

		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				another[j][i] = arr[i][j];
			}

		}
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				System.out.println("arr[" + i +"][" + j +"]= "+ arr[i][j]);
			}
		}
		
		System.out.println("This is the end of arr");
		System.out.println("This is the end of arr");
		System.out.println("This is the end of arr");

		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				System.out.println("another[" + j +"][" + i +"]= "+ another[j][i]);
			}
		}
	}
}
