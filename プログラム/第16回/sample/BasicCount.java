public class BasicCount {
    public static void main(String[] args) {
        int[] hps = {12, 0, 7};
        int aliveCount = 0;
        for (int i = 0; i < hps.length; i++) {
            if (hps[i] > 0) {
                aliveCount++;
            }
        }
        System.out.println(aliveCount);
    }
}
