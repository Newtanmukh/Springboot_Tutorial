//CALLABLE ALWAYS RETURNS THE DATA>

import java.util.*;
import java.util.concurrent.*;

class Service implements Callable<String>{
    int i;
    public Service(int i){this.i = i;}
    @Override
    public String call() throws Exception{
        try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
        return "From Thread " + i;
    }


}

public class Main{
    public static void main(String[] args) throws InterruptedException,ExecutionException{
        ExecutorService es = Executors.newFixedThreadPool(3);
        System.out.println(new Date());


        List<Future<String>> futureList = new ArrayList<>();
        for(int i=0;i<25;i++)
        {
            futureList.add((Future<String>)es.submit(new Service(i)));//this takes as input an object of type 'Runnable'
        }
        try {
            es.shutdown();
            es.awaitTermination(10, TimeUnit.SECONDS);
            for(Future<String> fut : futureList)
                System.out.println(fut.get());

        } catch (InterruptedException e) {
            // Handle the exception
            e.printStackTrace();
        }

        System.out.println(new Date());
    }
}