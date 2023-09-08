package Programmers.Algorithm;

import java.io.*;

class JadenCase문자열만들기 {

    static String[] input;
    public String solution(String s) throws IOException {

        String answer = init(s);
        return answer;
    }

    public String init(String s){
        input = s.split(" ");


        String temp="";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            temp = input[i];

            if(temp.length() == 0) {
                sb.append(" ");
                continue;
            }

            if (temp.length() == 1) {
                sb.append(temp.substring(0,1).toUpperCase()).append(" ");
                continue;
            }

            sb.append(temp.substring(0,1).toUpperCase())
                    .append(temp.substring(1,temp.length()).toLowerCase())
                    .append(" ");

        }


        return sb.toString().substring(0,s.length());
    }
}
