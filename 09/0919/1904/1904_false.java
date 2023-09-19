import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final String one = "1";
        final String twoZero = "00";
        final int moddingNumber = 15746;
        int number = Integer.parseInt(st.nextToken());
        HashSet<String> tiles = new HashSet<>(Set.of(one));

        for (int i = 2; i <= number; i++) {
            HashSet<String> pastTiles = new HashSet<>(tiles);
            tiles.clear();
            for (String tile : pastTiles) {
                tiles.add(one + tile);
                tiles.add(tile + one);
            }
            if (i % 2 == 0) {
                String duplicatedZero = "";
                for (int zero = 0; zero < i / 2; zero++) {
                    duplicatedZero += twoZero;
                }
                tiles.add(duplicatedZero);
            } else {
                for (int interveningOne = 1; interveningOne <= i / 2 - 1; interveningOne++) {
                    String zeroIntervenedByOne = "";
                    for (int addTwoZero = 0; addTwoZero <= i / 2; addTwoZero++) {
                        if(addTwoZero == interveningOne) {
                            zeroIntervenedByOne += one;
                        } else {
                            zeroIntervenedByOne += twoZero;
                        }
                    }
                    tiles.add(zeroIntervenedByOne);
                }
            }
        }
        for (String tile : tiles) {
            System.out.print(tile + " ");
        }
        System.out.println("총 " + tiles.size() + "개");


        // n = 1 , 1
        // n = 2 , 11, 00
        // n = 3, 111, 100, 001
        // n = 4, 1111, 1001, 1100, 0011, 0000
        // n = 5, 11111, 10011, 11001, 11100, 00111, 00100, 00001, 10000
        // n = 6, 111111, 110011, 100111, 111001, 111100, 001111, 100100, 001001, 100001, 000011, 110000
        // 000000
        // n = 7, 0010000 0000100
        // n = 8, 00000000
        // n = 9, 001000000 000010000 000000100
        // n = 10, 0000000000
        // n == 11, 00100000000 00001000000 00000010000 00000000100

        // 5 - 1, 7 - 2, 9 - 3, 11 - 4 , 13 - 5
        // 1번 이후로 모두 동일한 규칙 적용
        // 기본 타일 1, 00
        // n이 증가할 때마다 이전 n의 모든 타일에 1을 앞뒤에 더한 새 타일을 만든다. + 1로만 구성된 새 타일을 추가한다.
        // 짝수 번째 추가는 새로운 n개의 00타일 집합이 생김.
        // 홀수 번째 타일이 새로운 00 타일 사이에 1이 삽입된 새 타일이 생김

        // 수로 표현해보자
        // 1, 2, 3, 5, 8, 12, 18, 25, 35, 46
        // 13 - 1, 20 - 2, 30 - 5, 43 - 8, 60 - 14

        // 중복되는 타일을 제거하는 방법이 필요하다.
        // 타일은 매 시기마다 (이전 타일 * 2) + 1(1로만 구성) + 00(00으로만 구성, 짝수시기)
        // + 00100(00사이에 낀 1, 홀수 시기)
        //
		//
		//전개가 잘못돼서 잘못된 가정, 구현으로 이어짐, 6번에 타일이 더 있음
    }
}

