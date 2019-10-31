public class SendSynchronizer
{
    private static int counter=0;
    static int getCounter()
    {
        return counter;
    }
    synchronized static void  increaseCounter()
    {
        counter++;
    }

}
