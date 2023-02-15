import java.util.*;
import java.util.concurrent.*;

class Service implements Runnable{
    int i;
    public Service(int i){this.i = i;}
    @Override
    public void run(){
        System.out.println(i+" ");
        try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
    }
}

public class Main{
    public static void main(String[] args){
        ExecutorService es = Executors.newFixedThreadPool(5);
        System.out.println(new Date());

        for(int i=0;i<25;i++)
        {
            es.execute(new Service(i));//this takes as input an object of type 'Runnable'
        }
        try {
            es.shutdown();
            es.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // Handle the exception
            e.printStackTrace();
        }

        System.out.println(new Date());
    }
}