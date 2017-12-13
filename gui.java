import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
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
    private JButton vsnet, outputstep, refresh;
    //定义整个五子棋棋盘，使用已经写好的棋盘类
    private FirFormBoard firformboard;
    private int color=1;
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
        this.add(firformboard, BorderLayout.CENTER);

        refresh=new JButton("刷新");
        refresh.addActionListener((e)->firformboard.refresh());
        this.add(refresh,BorderLayout.SOUTH);

        this.pack();
        this.setSize(18 * StaticFunc.bound, 20 * StaticFunc.bound);
        //实现按钮
        //实现界面
        //添加按钮监听        
    }

    private boolean _IsWin(int x, int y, int xy)
    {
        int i, j;
        int ii, jj;
        int n;
        for (i = 1; i <= 15; i++)
            for (j = 1; j <= 15; j++)
            {
                n = 0;
                ii = i;
                jj = j;
                while (firformboard.board[ii][jj] == xy)
                {
                    ii += x;
                    jj += y;
                    n++;
                }
                if (n == 5) { return true; }
            }
        return false;
    }
    public boolean IsWin()
    {
        if ((_IsWin(1, 1, 1)) || (_IsWin(1, 0, 1)) || (_IsWin(0, 1, 1)) || (_IsWin(-1, 1, 1)))
        {
            JOptionPane.showMessageDialog(null,"黑棋胜利");
            return true;
        }
        if ((_IsWin(1, 1, 2)) || (_IsWin(1, 0, 2)) || (_IsWin(0, 1, 2)) || (_IsWin(-1, 1, 2)))
        {
            JOptionPane.showMessageDialog(null,"白棋胜利");
            return true;
        }
        return false;
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
            ChessPoint tmpChessPoint = new ChessPoint(StaticFunc.getXY(e.getX()), StaticFunc.getXY(e.getY()));
            if (!StaticFunc.isOnBoard(tmpChessPoint)) return;
            if (firformboard.board[tmpChessPoint.X][tmpChessPoint.Y]!=0) return;
            firformboard.StepNum++;
            firformboard.chessPoint[firformboard.StepNum] = tmpChessPoint;
            firformboard.board[tmpChessPoint.X][tmpChessPoint.Y]=color;
            color=(color==1)?2:1;
            firformboard.repaint();
            if (IsWin()) firformboard.refresh();
        }

        public void mouseEntered(MouseEvent e)
        {
        }

        public void mouseExited(MouseEvent e)
        {
        }
    }



    class FirFormBoard extends JPanel
    {
        public ChessPoint[] chessPoint = new ChessPoint[256];
        public int StepNum = 0;
        public ChessPoint currentPoint;
        public int[][] board=new int[17][17];

        public FirFormBoard()
        {
            int i,j;
            for(i=0;i<=16;i++)
                for(j=0;j<=16;j++)
                    board[i][j]=0;
        }

        public void paint(Graphics g)
        {
            super.paint(g);
            int i;
            for (i = 1; i <= 15; i++)
                g.drawLine(StaticFunc.bound, i * StaticFunc.bound, 15 * StaticFunc.bound, i * StaticFunc.bound);
            for (i = 1; i <= 15; i++)
                g.drawLine(i * StaticFunc.bound, StaticFunc.bound, i * StaticFunc.bound, 15 * StaticFunc.bound);
            if (currentPoint != null && StaticFunc.isOnBoard(currentPoint))
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
        }
        public void refresh()
        {
            int i,j;
            for(i=0;i<=16;i++)
                for(j=0;j<=16;j++)
                    board[i][j]=0;
            chessPoint = new ChessPoint[256];
            StepNum = 0;
            currentPoint=null;
            color=1;
        }
    }
}



