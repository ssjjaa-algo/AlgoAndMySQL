package Programmers.Algorithm;

class 가장긴팰린드롬
{
    public int solution(String s)
    {

        int answer = 1;
        char[] arr = s.toCharArray();

        int len = arr.length - 1;

        for (int i = len; i >= 0; i--) {
            for (int j = 0; j + i <= len; j++) {
                if (palindrome(j, j + i, arr)) return (i + 1);
            }
        }


        return answer;
    }

    public boolean palindrome(int start, int end, char[] arr) {

        while(start <= end) {
            if (arr[start++] != arr[end--]) return false;
        }

        return true;

    }
}
