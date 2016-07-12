
public class RunPrimes {
    public static void main(String[] args) {
        Primes primes = new Primes();
        for (int i = 0; i < 1000; i++) {
            System.out.println(primes.next());
        }
    }
}
