import com.sun.deploy.util.SessionState;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;
import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLBoundFault;
import javafx.scene.layout.BackgroundFill;
import jdk.jfr.events.ExceptionThrownEvent;
import sun.font.TrueTypeFont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.xml.crypto.Data;

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
    private JButton vsnet,vsnetc, outputstep, refresh,btn_nxt,btn_pre,btnfirst,readstep;
    private JPanel jp=new JPanel();
    private ChessPoint[] chessPoint = new ChessPoint[256];
    private int StepNum = 0;
    private ChessPoint currentPoint;
    private int[][] board=new int[17][17];
    //定义整个五子棋棋盘，使用已经写好的棋盘类
    private FirFormBoard firformboard;
    private int color=1;
    private String strIP;
    private int port=1234;
    private boolean isServer=true;
    private boolean isFirst=true;
    private boolean isvsmode=false;
    private boolean isEdit=true;

    ServerSocket server;
    Socket client;
    InputStream ipt;
    InputStreamReader isr;
    BufferedReader bf;


    Socket socket;
    OutputStream outputStream;
    PrintWriter printWriter;
    InputStream inputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    Thread ththth;

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
        btn_nxt=new JButton("下一步");
        btn_pre=new JButton("上一步");
        vsnet=new JButton("对战|服务端");
        vsnetc=new JButton("对战|客户端");
        btnfirst=new JButton("先手");
        outputstep=new JButton("输出棋谱");
        readstep=new JButton("读取棋谱");
        refresh.addActionListener((e)->firformboard.refresh());
        btn_nxt.addActionListener((e)->
        {
            if (chessPoint[StepNum+1]==null) return;
            StepNum++;
            board[chessPoint[StepNum].X][chessPoint[StepNum].Y]=2 - (StepNum % 2);
            firformboard.repaint();
        });
        btn_pre.addActionListener((e)->
        {
            if (StepNum<2) return;
            StepNum--;
            board[chessPoint[StepNum].X][chessPoint[StepNum].Y]=0;
            firformboard.repaint();
        });
        btnfirst.addActionListener(e->
        {
            if (btnfirst.getText().equals("先手"))
            {
                btnfirst.setText("后手");isFirst=false;
                if (JOptionPane.showConfirmDialog(null,"是否确认后手")==0)
                {
                    if (isServer && client != null)
                    {
                        try
                        {
                            OutputStream op = client.getOutputStream();
                            PrintWriter pw = new PrintWriter(op);
                            pw.println("1");
                            pw.flush();
                        } catch (Exception eee)
                        {
                        }
                    }
                    if (isFirst) return;
                    new servermode().start();
                }
//                if (isServer && client!=null)
//                {
//                    try
//                    {
//                        OutputStream op = client.getOutputStream();
//                        PrintWriter pw = new PrintWriter(op);
//                        pw.println("1");
//                        pw.flush();
////                        ththth=new servermode();
//                    }
//                    catch (Exception ex){}
//                }
            }
            else
            {
                btnfirst.setText("先手");isFirst=true;
                if (JOptionPane.showConfirmDialog(null,"是否确认先手")==0)
                {
                    if (isServer && client != null)
                    {
                        try
                        {
                            OutputStream op = client.getOutputStream();
                            PrintWriter pw = new PrintWriter(op);
                            pw.println("0");
                            pw.flush();
                        } catch (Exception eee)
                        {
                        }
                    }
                    if (isFirst) return;
                    new servermode().start();
                }
//                if (isServer && client!=null)
//                {
//                    try
//                    {
//                        OutputStream op = client.getOutputStream();
//                        PrintWriter pw = new PrintWriter(op);
//                        pw.println("0");
//                        pw.flush();
////                        ththth.stop();
//                    }
//                    catch (Exception ex){}
//                }
            }
        });
        vsnet.addActionListener((e)->
        {
            isvsmode=true;
            isServer = true;
            try
            {
                server = new ServerSocket(port);
                client = server.accept();
                ipt = client.getInputStream();
                isr = new InputStreamReader(ipt);
                bf = new BufferedReader(isr);
//                OutputStream op = client.getOutputStream();
//                PrintWriter pw = new PrintWriter(op);
//                if (isFirst) pw.println("0"); else pw.println("1");
//                pw.flush();
                if (isFirst)
                {
                    if (JOptionPane.showConfirmDialog(null,"是否确认先手")==0)
                    {
                        if (isServer && client != null)
                        {
                            try
                            {
                                OutputStream op = client.getOutputStream();
                                PrintWriter pw = new PrintWriter(op);
                                pw.println("0");
                                pw.flush();
                            } catch (Exception eee)
                            {
                            }
                        }
                        if (isFirst) return;
                        new servermode().start();
                    }
                }
                else
                {
                    if (JOptionPane.showConfirmDialog(null,"是否确认后手")==0)
                    {
                        if (isServer && client != null)
                        {
                            try
                            {
                                OutputStream op = client.getOutputStream();
                                PrintWriter pw = new PrintWriter(op);
                                pw.println("1");
                                pw.flush();
                            } catch (Exception eee)
                            {
                            }
                        }
                        if (isFirst) return;
                        new servermode().start();
                    }
                }
            } catch (Exception ex)
            {
            }
        });
        vsnetc.addActionListener(e->
        {
            isvsmode=true;
            isServer = false;
            btnfirst.setEnabled(false);
            try
            {
                socket = new Socket("127.0.0.1", 1234);
                outputStream = socket.getOutputStream();
                printWriter = new PrintWriter(outputStream);
                inputStream = socket.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
//                new Thread(()->
//                {
//                    while ( StepNum==0)
//                    {
                        try
                        {
//                            JOptionPane.showMessageDialog(null,"等待服务端选择先后手");
                            String stmp = bufferedReader.readLine();
                            if (stmp.equals("0"))
                            {
                                isFirst = false;
                                btnfirst.setText("后手");
//                                ththth=new clientmode();
                            } else
                            {
                                isFirst = true;
                                btnfirst.setText("先手");
//                                ththth.stop();
                            }
                        } catch (Exception exx)
                        {
                        }
//                    }
//                }).start();
            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }
            if (isFirst) return;
            new clientmode().start();
        });
        outputstep.addActionListener(e->
        {
            FileOutputStream fileOutputStream;
            File file=new File("D:\\IdeaProjects\\gui\\1.txt");
            try
            {
                fileOutputStream = new FileOutputStream(file);
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                int ii;
                dataOutputStream.writeInt(StepNum);
                for (ii = 1; ii <= StepNum; ii++)
                {
                    dataOutputStream.writeInt(chessPoint[ii].X*100+chessPoint[ii].Y);
                }
                dataOutputStream.close();
                fileOutputStream.close();
            } catch (Exception ex) {}
        });
        readstep.addActionListener(e->
        {
            new readchess().start();
        });



        jp.add(refresh);
        jp.add(btn_pre);
        jp.add(btn_nxt);
        jp.add(vsnet);
        jp.add(vsnetc);
        jp.add(btnfirst);
        jp.add(outputstep);
        jp.add(readstep);
        this.add(jp,BorderLayout.SOUTH);

        this.pack();
        this.setSize(25 * StaticFunc.bound, 18 * StaticFunc.bound);
        //实现按钮
        //实现界面
        //添加按钮监听        
    }

    class readchess extends  Thread
    {
        @Override
        public void run()
        {
            FileInputStream fileInputStream;
            File file=new File("D:\\IdeaProjects\\gui\\1.txt");
            try
            {
                fileInputStream=new FileInputStream(file);
                DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                int ii;
                StepNum=0;
                int tmp;
                tmp=dataInputStream.readInt();
                for(ii=1;ii<=tmp;ii++)
                {
                    tmp = dataInputStream.readInt();
                    chessPoint[ii]=new ChessPoint(tmp / 100,tmp % 100);
                    StepNum++;
                    firformboard.repaint();
                    Thread.sleep(1000);
                }
                dataInputStream.close();
                fileInputStream.close();
            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }
        }
    }

    class servermode extends Thread
    {
        @Override
        public void run()
        {
            isEdit = false;
            try
            {
                String tmp;
//            while (!IsWin())
//            {
                tmp = bf.readLine();
                setDown(StaticFunc.getXFromStr(tmp), StaticFunc.getYFromStr(tmp));
//            }
            } catch (Exception ex)
            {

            }
            isEdit = true;
        }
    }

    class  clientmode extends Thread
    {
        @Override
        public void run()
        {
            isEdit = false;
            try
            {
                String tmp;
//            while (!IsWin())
//            {
                tmp = bufferedReader.readLine();
                setDown(StaticFunc.getXFromStr(tmp), StaticFunc.getYFromStr(tmp));
//            }
            } catch (Exception ex)
            {

            }
            isEdit = true;
        }
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
                while (board[ii][jj] == xy)
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
            currentPoint = new ChessPoint(x, y);
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
            if (!isEdit) return;
            if (isvsmode)
            {
                if (isServer)
                {
//                    if (!isFirst && StepNum==0) return;
                    try
                    {
                        OutputStream op = client.getOutputStream();
                        PrintWriter pw = new PrintWriter(op);
                        pw.println(StaticFunc.getXY(e.getX())*100+StaticFunc.getXY(e.getY()));
                        pw.flush();
                        setDown(StaticFunc.getXY(e.getX()), StaticFunc.getXY(e.getY()));
                        if (IsWin()) firformboard.refresh();
                        new servermode().start();
                    }
                    catch (Exception ex)
                    {}

                }
                else
                {
//                    if (!isFirst && StepNum==0) return;
                    try
                    {
                        printWriter.println(StaticFunc.getXY(e.getX()) * 100 + StaticFunc.getXY(e.getY()));
                        printWriter.flush();
                        setDown(StaticFunc.getXY(e.getX()), StaticFunc.getXY(e.getY()));
                        if (IsWin()) firformboard.refresh();
                        new clientmode().start();
                    }
                    catch (Exception ex)
                    {}
                }
            }
            else
            {
                setDown(StaticFunc.getXY(e.getX()), StaticFunc.getXY(e.getY()));
                if (IsWin()) firformboard.refresh();
            }
        }

        public void mouseEntered(MouseEvent e)
        {
        }

        public void mouseExited(MouseEvent e)
        {
        }
    }

    public void setDown(int x,int y)
    {
        btnfirst.setEnabled(false);
        ChessPoint tmpChessPoint = new ChessPoint(x,y);
        if (!StaticFunc.isOnBoard(tmpChessPoint)) return;
        if (board[tmpChessPoint.X][tmpChessPoint.Y]!=0) return;
        StepNum++;
        chessPoint[StepNum] = tmpChessPoint;
        board[tmpChessPoint.X][tmpChessPoint.Y]=color;
        color=(color==1)?2:1;
        firformboard.repaint();
    }


    class FirFormBoard extends JPanel
    {

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
                    g.setColor(Color.white);
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
            btnfirst.setEnabled(true);

            repaint();
        }
    }
}



