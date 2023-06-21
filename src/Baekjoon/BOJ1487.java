package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ1487 {

    static class Product implements Comparable<Product>{
        int buyPrice;
        int deliveryPrice;

        public Product(int buyPrice, int deliveryPrice) {
            this.buyPrice = buyPrice;
            this.deliveryPrice = deliveryPrice;
        }

        @Override
        public int compareTo(Product o) {
            return Integer.compare(this.buyPrice,o.buyPrice);
        }
    }
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] input;
        Product[] arr = new Product[n];
        int a,b;
        for (int i=0; i < n; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);
            arr[i] = new Product(a,b);
        }

        Arrays.sort(arr);

        int minPrice = 0;
        int maximum = 0;
        for (int i=0; i< n; i++) {

            int productPrice = arr[i].buyPrice;
            int sum = 0;

            for (int j=0; j<n; j++) {

                if (productPrice > arr[j].buyPrice) continue;

                if (productPrice - arr[j].deliveryPrice > 0)
                    sum += productPrice -arr[j].deliveryPrice;

            }

            if (sum > maximum) {

                maximum = sum;
                minPrice = productPrice;
            }
        }

        System.out.println(minPrice);


    }

}
