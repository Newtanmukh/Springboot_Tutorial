
class Mythread implements Runnable{

    public void run(){
        for(int i=1;i<11;i++)
        {
            System.out.println(i);
            try {
                Thread.sleep(1000);// milliseconds.
            }
            catch (Exception e)
            {

            }

        }
    }

    public static void main(String[] args)
    {
        Mythread t1 = new Mythread();
        Thread thr = new Thread(t1); //loading the Mythread object into Thread class type.
        thr.start();
    }

}