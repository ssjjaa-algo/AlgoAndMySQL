import java.util.*;

class 베스트앨범 {
    
    static class Song implements Comparable<Song>{
        
        int idx;
        int count;
        
        public Song(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }
        
        @Override
        public int compareTo(Song o) {
            if (this.count == o.count) {
                return Integer.compare(this.idx, o.idx);
            }
            
            return Integer.compare(o.count, this.count);
        }
    }
    static Map<String, List<Song>> songMap = new HashMap<>();
    static Map<String, Integer> genreMap = new HashMap<>();
    
    public int[] solution(String[] genres, int[] plays) {
        
        init(genres, plays);
        
        List<String> genreSet = new ArrayList<>(genreMap.keySet());
        genreSet.sort((o1, o2) -> genreMap.get(o2).compareTo(genreMap.get(o1)));
        
        List<Integer> ans = new ArrayList<>();
        for (String key : genreSet) {
            
            List<Song> song = songMap.get(key);
            Collections.sort(song);
            
            for (int i = 0; i < 2 && i < song.size(); i++) {
                ans.add(song.get(i).idx);
            }
        }
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public void init(String[] genres, int[] plays) {
        
        int len = plays.length;
        
        for (int i = 0; i < len; i++) {
            
            genreMap.put(genres[i], genreMap.getOrDefault(genres[i], 0) + plays[i]);
            List<Song> songs = songMap.getOrDefault(genres[i], new ArrayList<>());
            songs.add(new Song(i, plays[i]));
            songMap.put(genres[i], songs);
        }
        
    }
}

/*
장르별로 해쉬를 설정한다.

1. 해쉬값이 가장 큰 장르를 가져온다.
2. 해당 장르에서 내림차순으로 정렬된 번호로 2개를 가져온다.

논리
장르별로 맵 1개, 인덱스와 재생 횟수를 기록하는 맵 1개 필요
Map<String, Integer> genre = new HashMap<>();
Map<String, List<Song>> map = new HashMap<>();

연산순서
1. 장르에 해당하는 키를 넣고, 재생 횟수를 추가한다 -> genre.put(genres[i], genre.getOrDefault(genres[i], 0) + plays[i]);

2. 장르의 인덱스와 재생 횟수를 기록한다.

3. 가장 많은 장르 횟수를 가진 key를 순서대로 -> genre의 integer를 기준으로
 - List<Song>을 재생횟수 내림차순, 인덱스 오름차순으로 정렬하여
 - 최대 2개만 뽑게 한다.
 

*/
