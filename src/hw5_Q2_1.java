import java.util.concurrent.*;

class SOE4Prime implements Callable{
    int n;
    public SOE4Prime (int n){
        this.n = n;
    }
    @Override
    public Object call() throws Exception {
        long start = System.currentTimeMillis();

        boolean prime[] = new boolean[n+1];
        for(int i=0;i<n;i++)
            prime[i] = true;

        for(int p = 2; p*p <=n; p++)
        {
            if(prime[p] == true)
            {
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true)
                System.out.println(i + " ");
        }
        System.out.println(System.currentTimeMillis()-start);
        return System.currentTimeMillis()-start;
    }
}


public class hw5_Q2_1 {

    public static void main(String args[])
    {
        int n = 1000000;

        System.out.print("Following are the prime numbers ");
        System.out.println("smaller than or equal to " + n);
        SOE4Prime test = new SOE4Prime(1_000_000);
        Future result;
        ExecutorService pool = Executors.newFixedThreadPool(1);
        result = pool.submit(test);
        pool.shutdown();

        long escapedTime = 0;
        try {
            escapedTime = (long) result.get();
        } catch (InterruptedException|ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Time used: "+escapedTime+" milli-sec.");

    }


}




