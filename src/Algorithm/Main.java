package Algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        String salt = getSalt();
        System.out.println("salt : " + salt);
        System.out.println(getEncrypt("1234",salt));

    }

        // 암호화할 때 사용할 Salt (랜덤 바이트배열)
        public static String getSalt() {
            System.out.println("getSalt 진입");
            Random random = new Random();
            byte[] salt = new byte[10];

            random.nextBytes(salt); // 난수 생성

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < salt.length; i++) {
                sb.append(String.format("%02x", salt[i]));
            }
            System.out.println("getSalt 탈출");
            return sb.toString();
        }

        // Salt 사용해서 암호화
        public static String getEncrypt(String pw, String salt) {
            String result = null;
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256"); // sha 256알고리즘 객체 생성

                // pw + salt문자열에 sha 256 적용
                md.update((pw+salt).getBytes());
                byte[] b = md.digest();
                System.out.println("byte 배열의 길이 : " + b.length);
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < b.length; i++) { // 10진수 문자열로 변환
                    sb.append(String.format("%02x", b[i]));
                }

                result = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return result;
        }
}
