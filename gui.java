import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class gui
{
    public static void main(String[] args)
    {
        FirForm f = new FirForm();
        f.setVisible(true);
    }
}


class FirForm extends JFrame
{
    //定义按钮：电脑先，本机对战，联机对战，输出棋谱
    private JButton computerfirst, vsmode, vsnet, outputstep;
    //定义整个五子棋棋盘，使用已经写好的棋盘类
    private FirFormBoard firformboard;

    //构造函数
    public FirForm()
    {
        super();
        int i,j;

        for(i=0;i<=15;i++)
            for(j=0;j<=15;j++)
                StaticFunc.board[i][j]=0;

        firformboard = new FirFormBoard();
        firformboard.addMouseMotionListener(new FirFormBoard_MoveEvent());
        firformboard.addMouseListener(new FirFormBoard_ClickEvent());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(firformboard);
        this.pack();
        this.setSize(18 * StaticFunc.bound, 20 * StaticFunc.bound);
        //实现按钮
        //实现界面
        //添加按钮监听        
    }

    class FirFormBoard_MoveEvent implements MouseMotionListener
    {
        public void mouseMoved(MouseEvent e)
        {
            int x, y;
            x = StaticFunc.getXY(e.getX());
            y = StaticFunc.getXY(e.getY());
            firformboard.currentPoint = new ChessPoint(x, y);
            firformboard.repaint();
        }

        public void mouseDragged(MouseEvent e)
        {
        }
    }

    class FirFormBoard_ClickEvent implements MouseListener
    {
        public void mouseClicked(MouseEvent e)
        {

        }

        public void mousePressed(MouseEvent e)
        {
        }

        public void mouseReleased(MouseEvent e)
        {
            firformboard.StepNum++;
            ChessPoint tmpChessPoint = new ChessPoint(StaticFunc.getXY(e.getX()), StaticFunc.getXY(e.getY()));
            firformboard.chessPoint[firformboard.StepNum] = tmpChessPoint;
            firformboard.repaint();
        }

        public void mouseEntered(MouseEvent e)
        {
        }

        public void mouseExited(MouseEvent e)
        {
        }
    }
}

class FirFormBoard extends JPanel
{
    ChessPoint[] chessPoint = new ChessPoint[256];
    public int StepNum = 0;
    ChessPoint currentPoint;

    public void paint(Graphics g)
    {
        super.paint(g);
        int i;
        for (i = 1; i <= 16; i++)
            g.drawLine(StaticFunc.bound, i * StaticFunc.bound, 16 * StaticFunc.bound, i * StaticFunc.bound);
        for (i = 1; i <= 16; i++)
            g.drawLine(i * StaticFunc.bound, StaticFunc.bound, i * StaticFunc.bound, 16 * StaticFunc.bound);
        if (currentPoint != null)
        {
            g.setColor(Color.red);
            g.fillOval(currentPoint.X * StaticFunc.bound - StaticFunc.bound / 6, currentPoint.Y * StaticFunc.bound - StaticFunc.bound / 6, StaticFunc.bound / 3, StaticFunc.bound / 3);
        }
        for (i = 1; i <= StepNum; i++)
        {
            if (i % 2 == 1)
            {
                StaticFunc.board[chessPoint[i].X][chessPoint[i].Y]=1;
                g.setColor(Color.BLACK);
            }
            else
            {
                StaticFunc.board[chessPoint[i].X][chessPoint[i].Y]=2;
                g.setColor(Color.GRAY);
            }
            g.fillOval(chessPoint[i].X * StaticFunc.bound - StaticFunc.bound / 2, chessPoint[i].Y * StaticFunc.bound - StaticFunc.bound / 2, StaticFunc.bound, StaticFunc.bound);
        }
        //画五子棋盘
        //画横竖线
        //画点
        //遍历chessPoint画棋子
        //TODO:这是一个低效率的办法
    }
}

