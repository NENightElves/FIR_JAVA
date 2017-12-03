import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


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
///����
///</summary>
class FirForm extends JFrame
{
    //���尴ť�������ȣ�������ս��������ս���������
    private JButton computerfirst,vsmode,vsnet,outputstep;
    //�����������������̣�ʹ���Ѿ�д�õ�������
    private FirFormBoard firformboard;
    //���캯��
    public FirForm()
    {
        //ʵ�ְ�ť
        //ʵ�ֽ���
        //��Ӱ�ť����        
    }
}

class FirFormBoard extends JPanel
{
    ChessPoint[] chessPoint=new ChessPoint[256];
    @Override
    public void paint(Graphics g)
    {
        //����������
        //��������
        //����
        //����chessPoint������
        //TODO:����һ����Ч�ʵİ취
    }
}

//��ť�ļ����¼�
//����ʹ��msgbox��inputbox
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
///���ĺ���
///</summary>
class ChessStaticFunc
{
    public static String getLine(File f)
    {
        //���ļ�f��ȡһ��
    }
    public static String getContant(File f)
    {
        //��ȡ�����ļ�����
    }
    public static void postChessPoint(ChessPoint cp,String ip,int port)
    {
        //��Ŀ��ip��������λ��
    }
    public static ChessPoint getChessPoint(String ip,int port)
    {
        //��Ŀ��ip��ȡ����λ��
        return null;
    }
}

///<summary>
///�����¼�༰ai
///</summary>
class ChessStep
{
    ChessPoint cp;
    int[][] board=new int[16][16];
    public ChessPoint getNextStep()
    {
        //��Ե�ǰ���̼�����һ��
        return null;
    }
}