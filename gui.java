import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ChessPoint;


public class gui
{
    public static void main(String[] args) {
        FirForm f=new FirForm();
        f.setVisible(true);
    }
}


class FirForm extends JFrame
{
    //定义按钮：电脑先，本机对战，联机对战，输出棋谱
    private JButton computerfirst,vsmode,vsnet,outputstep;
    //定义整个五子棋棋盘，使用已经写好的棋盘类
    private FirFormBoard firformboard;
    int bound=60;
    //构造函数
    public FirForm()
    {
        super();
        firformboard=new FirFormBoard();
        firformboard.addMouseMotionListener(new FirFormBoard_MoveEvent());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(firformboard);
        this.pack();
        this.setSize(18*bound,20*bound);
        //实现按钮
        //实现界面
        //添加按钮监听        
    }
    class FirFormBoard_MoveEvent implements MouseMotionListener
    {
        public void mouseMoved(MouseEvent e)
        {
            int x,y;
            x=Math.round(e.getX()/bound);
            y=Math.round(e.getY()/bound);
            firformboard.currentPoint=new ChessPoint(x, y);
            repaint();
        }
        public void mouseDragged(MouseEvent e) {}
    }
}

class FirFormBoard extends JPanel
{
    ChessPoint[] chessPoint=new ChessPoint[256];
    ChessPoint currentPoint;
    int bound=60;
    public void paint(Graphics g)
    {
        super.paint(g);
        int i;
        for(i=1;i<=16;i++) g.drawLine(bound, i*bound, 16*bound, i*bound);
        for(i=1;i<=16;i++) g.drawLine(i*bound, bound, i*bound, 16*bound);
        i=1;
        if (currentPoint!=null) { g.setColor(Color.red); g.fillOval(currentPoint.X*bound-bound/6, currentPoint.Y*bound-bound/6, bound/3, bound/3);}
        chessPoint[1]=new ChessPoint(1, 1);
        while(chessPoint[i]!=null)
        {
            if (i % 2 == 1) g.setColor(Color.BLACK); else g.setColor(Color.GRAY);;
            g.fillOval(chessPoint[i].X*bound-bound/2, chessPoint[i].Y*bound-bound/2, bound, bound);
            i++;
        }
        //画五子棋盘
        //画横竖线
        //画点
        //遍历chessPoint画棋子
        //TODO:这是一个低效率的办法
    }
}

