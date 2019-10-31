public class ReceiveSynchronizer
{
    static int counter = -1;
    static int getCounter()
    {
        return counter;
    }
    synchronized static void setCounter(int c)
    {
        counter = c;
    }
}
