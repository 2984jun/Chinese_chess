import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Chess {
    public static void main(String[] args){
        new ChessMainFrame("中国象棋；观棋不语真君子，棋死无悔大丈夫");
    }
}
class ChessMainFrame extends JFrame implements ActionListener,MouseListener,Runnable{
    //棋子
    JLabel play[]=new JLabel[32];
    //棋盘
    JLabel image;
    //窗格
    Container con;
    //工具栏
    JToolBar jmain;
    //重新开始
    JButton anew;
    //悔棋
    JButton repent;
    //退出
    JButton exit;
    //当前信息
    JLabel text;
    //保存当前操作
    Vector Var;
    //规则类对象
    ChessRule rule;

    /*单击棋子
        chessManClick=true 闪烁棋子 并给线程响应
        chessManClick=false 吃棋子 停止闪烁 并给线程响应
     */
    boolean chessManClick;

    /*控制玩家走棋
        chessPlayClick=1 黑棋走棋
        chessPlayClick=2 红棋走棋 默认红棋
        chessPlayClick=3 双方都不能走棋
     */
    int chessPlayClick=2;

    //控制棋子闪烁的线程
    Thread tmain;
    //把第一次的单击棋子给线程响应
    static int Man,i;

    ChessMainFrame(){
        new ChessMainFrame("中国象棋");
    }

    ChessMainFrame(String Title){
        Font font=new Font("Dialog",Font.PLAIN,12);
        java.util.Enumeration keys= UIManager.getDefaults().keys();
        while(keys.hasMoreElements()){
            Object key=keys.nextElement();
            Object value=UIManager.get(key);
            if(value instanceof javax.swing.plaf.FontUIResource){
                UIManager.put(key,font);
            }
        }
        //获行客格引用
        con=this.getContentPane();
        con.setLayout(null);

        //实例化规则类
        rule=new ChessRule();
        Var=new Vector();

        //创建工具栏
        jmain=new JToolBar();
        text=new JLabel("欢迎使用象棋对弈系统");

        //当鼠标放上显示信息
        text.setToolTipText("信息提示");
        anew=new JButton("新游戏");
        anew.setToolTipText("重新开始新的一局");
        exit=new JButton("退出");
        exit.setToolTipText("退出象棋程序");
        repent=new JButton("悔棋");
        repent.setToolTipText("返回到上次走棋的位置");

        //把组件添加到工具烂
        jmain.setLayout(new GridLayout(0,4));
        jmain.add(anew);
        jmain.add(repent);
        jmain.add(exit);
        jmain.add(text);
        jmain.setBounds(0,0,558,30);
        con.add(jmain);

        //添加棋子标签
        drewChessMan drewChessMan_=new drewChessMan();

        //注册按钮监听
        anew.addActionListener(this);
        repent.addActionListener(this);
        exit.addActionListener(this);

        //注册棋子移动监听
        for(int i=0;i<32;i++){
            con.add(play[i]);
            //play[i].addMouseListener(this);
        }

        //添加棋盘标签
        con.add(image=new JLabel(new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\R-C.jpg")));
        image.setBounds(0,30,558,620);
        //image.addMouseListener(this);

        //注册窗体关闭监听
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });

        //窗体居中及一些基本设置
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize=this.getSize();

        if(frameSize.height>screenSize.height){
            frameSize.height=screenSize.height;
        }
        if(frameSize.width>screenSize.width){
            frameSize.width=screenSize.width;
        }

        this.setLocation((screenSize.width- frameSize.width)/2-280,(screenSize.height-frameSize.height)/2-350);

        //设置
        this.setIconImage(new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\b151f8198618367afb3ba3a42d738bd4b31ce51d.jpg").getImage());
        this.setIconImage(new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\b151f8198618367afb3ba3a42d738bd4b31ce51d.jpg").getImage());
        this.setResizable(false);
        this.setTitle(Title);
        this.setSize(558,670);
        this.show();
    }

    public void run(){
        while(true){
            if(chessManClick){
                play[Man].setVisible(false);
                try{
                    tmain.sleep(200);
                }catch (Exception e){
                    play[Man].setVisible(true);
                }
            }else{
                text.setVisible(false);
                try{
                    tmain.sleep(250);
                }catch (Exception e){
                    text.setVisible(true);
                }
            }
            try{
                tmain.sleep(350);
            }catch (Exception e){
            }
        }
    }

    public void mouseClicked(MouseEvent me){
        System.out.println("Mouse");

        //当前坐标
        int Ex=0,Ey=0;
        //启动线程
        if(tmain==null){
            tmain=new Thread(this);
            tmain.start();
        }
    }
}
