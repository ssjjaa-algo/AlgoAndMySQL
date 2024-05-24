package Programmers.Algorithm;

import java.util.*;

class 불량사용자 {

    static Set<String> set = new HashSet<>();
    static Set<Set<String>> res = new HashSet<>();
    static int ans = 0;

    public int solution(String[] user_id, String[] banned_id) {

        for (int i = 0; i < banned_id.length; i++) {
            banned_id[i] = banned_id[i].replace("*", ".");
        }

        dfs(0, user_id.length, banned_id.length, user_id, banned_id);
        return res.size();
    }

    public void dfs(int cnt, int userLen, int bannedLen, String[] user_id, String[] banned_id) {

        if (cnt == bannedLen) {

            res.add(new HashSet<>(set));
            return;
        }

        for (int i = 0; i < userLen; i++) {

            if (set.contains(user_id[i])) continue;

            if (user_id[i].matches(banned_id[cnt])) {
                set.add(user_id[i]);
                dfs(cnt + 1, userLen, bannedLen, user_id, banned_id);
                set.remove(user_id[i]);
            }
        }
    }

}
