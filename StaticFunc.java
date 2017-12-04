public class StaticFunc
{
    public static int bound = 60;

    public static int getXY(int x)
    {
        return Math.round((x + StaticFunc.bound / 2) / StaticFunc.bound);
    }
}