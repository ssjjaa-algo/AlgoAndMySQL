package Programmers.Algorithm;

class 풍선터트리기 {
    public int solution(int[] a) {

        int[] left = new int[a.length];
        int[] right = new int[a.length];

        left[0] = a[0];
        right[a.length - 1] = a[a.length - 1];

        int lMin = left[0];
        int rMin = right[a.length - 1];
        int answer = 0;

        for (int i = 1; i < a.length; i++) {
            if (lMin > a[i]) lMin = a[i];
            left[i] = lMin;
        }

        for (int i = a.length - 2; i >= 0; i--) {
            if (rMin > a[i]) rMin = a[i];
            right[i] = rMin;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] > left[i] && a[i] > right[i]) continue;
            answer++;
        }


        return answer;
    }
}

/*

큰 값은 얼마든지 터트릴 수 있다.
단 작은 값은 한 번만 터트릴 수 있다.

따라서 어떤 값이 양 옆으로 작은 값을 2번 이상 가지면 안된다.

*/
