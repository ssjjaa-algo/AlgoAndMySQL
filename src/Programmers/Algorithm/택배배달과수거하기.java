package Programmers.Algorithm;

class 택배배달과수거하기 {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long dist = 0;

        for (int i = n - 1; i >= 0; i--) {

            if (deliveries[i] > 0 || pickups[i] > 0) {

                dist += (i + 1) * 2;
                int delivery = cap;
                int collection = cap;

                for (int j = i; j >= 0; j--) {

                    if (deliveries[j] <= delivery) {
                        delivery -= deliveries[j];
                        deliveries[j] = 0;
                    }
                    else {
                        deliveries[j] -= delivery;
                        break;
                    }
                }
                for (int j = i; j >= 0; j--) {

                    if (pickups[j] <= collection) {
                        collection -= pickups[j];
                        pickups[j] = 0;
                    }
                    else {
                        pickups[j] -= collection;
                        break;
                    }
                }

                i++;
            }
        }

        return dist;
    }
}
