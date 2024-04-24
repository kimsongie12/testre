/*
백준 온라인 저지 16434번 드래곤 앤 던전(Gold IV)
 */

import java.io.*;

public class SonHM {
    static int n;
    static long atk, maxHP, left, right, mid;
    static long[][] dungeon = null;
    public static void main(String[] args) throws IOException{
        // 초기 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        n = Integer.parseInt(info[0]);
        atk = Long.parseLong(info[1]);
        dungeon = new long[n][3];

        for(int i=0; i<n; i++) {
            String[] room = br.readLine().split(" ");
            for(int j=0; j<3; j++) {
                dungeon[i][j] = Long.parseLong(room[j]);
            }
        }

        // 이분 탐색
        maxHP = (long)123456 * 1000000 * 1000000;
        binarySearch();
        System.out.println(maxHP);
    }

    static void binarySearch() {
        left = 1; right = maxHP;

        while(left < right) {
            mid = (left + right) / 2;
            boolean res = tryDungeon();

            if(res) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        maxHP = left;
    }

    static boolean tryDungeon() {
        long curAtk = atk;
        long curHP = mid;
        for(int i=0; i<n; i++) {
            if(dungeon[i][0] == 1) {
                // 몬스터의 방
                long monsterAtk = dungeon[i][1];
                long monsterHP = dungeon[i][2];
                long turn = monsterHP / curAtk;
                if(monsterHP % curAtk != 0) turn++;

                if(curHP <= monsterAtk * (turn-1)) return false;
                curHP -= monsterAtk * (turn-1);
            } else {
                // 포션의 방
                curAtk += dungeon[i][1];
                curHP += dungeon[i][2];
                if(curHP > mid) curHP = mid;
            }
        }

        return true;
    }
}