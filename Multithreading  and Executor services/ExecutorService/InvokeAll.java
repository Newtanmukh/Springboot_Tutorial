import java.util.*;
import java.util.concurrent.*;

class Service implements Callable<String>{
    int i;
    public Service(int i){this.i = i;}
    @Override
    public String call() throws Exception{

        System.out.println("In thread "+i);
        return "From Thread " + i;
    }


}

public class Main{
    public static void main(String[] args) throws InterruptedException,ExecutionException{
        ExecutorService es = Executors.newFixedThreadPool(3);
        System.out.println(new Date());


        List<Callable<String>> futureList = new ArrayList<>();
        for(int i=0;i<50;i++)
        {
            futureList.add(new Service(i));//this takes as input an object of type 'Runnable'
        }

        List<Future<String>> futures = es.invokeAll(futureList);


        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);

        for (Future<String> future : futures) {
            String result = future.get();
            System.out.println(result);
        }



        System.out.println(new Date());
    }
}