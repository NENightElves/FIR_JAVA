import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.org.apache.regexp.internal.REUtil;

class ChessPoint
{
    public int X;
    public int Y;
    public ChessPoint(int x,int y)
    {
        X=x;
        Y=y;
    }
}

///<summary>
///界面
///</summary>
class FirForm extends JFrame
{
    //定义按钮：电脑先，本机对战，联机对战，输出棋谱
    private JButton computerfirst,vsmode,vsnet,outputstep;
    //定义整个五子棋棋盘，使用已经写好的棋盘类
    private FirFormBoard firformboard;
    //构造函数
    public FirForm()
    {
        //实现按钮
        //实现界面
        //添加按钮监听        
    }
}

class FirFormBoard extends JPanel
{
    ChessPoint[] chessPoint=new ChessPoint[256];
    @Override
    public void paint(Graphics g)
    {
        //画五子棋盘
        //画横竖线
        //画点
        //遍历chessPoint画棋子
        //TODO:这是一个低效率的办法
    }
}

//按钮的监听事件
//可以使用msgbox和inputbox
class Button_computerfirst_Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
    }
}
class Button_vsmode_Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
    }
}
class Button_vsnet_Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
    }
}
class Button_outputstep_Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
    }
}

///<summary>
///核心函数
///</summary>
class ChessStaticFunc
{
    public static String getLine(File f)
    {
        //从文件f读取一行
    }
    public static String getContant(File f)
    {
        //读取整个文件内容
    }
    public static void postChessPoint(ChessPoint cp,String ip,int port)
    {
        //向目标ip发送棋子位置
    }
    public static ChessPoint getChessPoint(String ip,int port)
    {
        //从目标ip获取棋子位置
        return null;
    }
}

///<summary>
///步骤记录类及ai
///</summary>
class ChessStep
{
    ChessPoint cp;
    int[][] board=new int[16][16];
    public ChessPoint getNextStep()
    {
        //针对当前棋盘计算下一步
        return null;
    }
}