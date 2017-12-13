public class StaticFunc
{
    public static int bound = 60;
    public static int[][] board=new int[17][17];
    public static int getXY(int x)
    {
        return Math.round((x + StaticFunc.bound / 2) / StaticFunc.bound);
    }
    public  static int[][] CopyBoard()
    {
        int[][] x=new int[16][16];
        int i,j;
        for(i=0;i<=15;i++)
            for(j=0;j<=15;j++)
                x[i][j]=StaticFunc.board[i][j];
        return x;
    }
    public static boolean isOnBoard(int x,int y)
    {
        if (x>0 && x<16 && y>0 && y<16) return true; else return false;
    }
    public static boolean isOnBoard(ChessPoint chessPoint)
    {
        return isOnBoard(chessPoint.X,chessPoint.Y);
    }

}