class UserThread extends Thread{
    public void run(){
        System.out.println("user thread is going to run now.");
    }
}


class Threadop{
    public static void main(String[] args)
    {
        System.out.println("Program Started");
        int x = 56+44;

        System.out.println("Sum is : " + String.valueOf(x));
        Thread t = Thread.currentThread();//will return you the object of the current thread type.
        String Tname = t.getName();//getting the name
        t.setName("Other");
        String name = t.getName();
        System.out.println(Tname);
        try{
            t.sleep(4000);//or Thread.sleep(4000); is also fine.

        }catch(Exception e){

        }
        System.out.println(t.getId());
        System.out.println(name);

        UserThread userThread = new UserThread();
        userThread.start();
        System.out.println("Program Ended");
    }
}