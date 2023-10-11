import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
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
        drewChessMan_.AdddrewChessMan(play,con);

        //注册按钮监听
        anew.addActionListener(this);
        repent.addActionListener(this);
        exit.addActionListener(this);

        //注册棋子移动监听
        for(int i=0;i<32;i++){
            con.add(play[i]);
            play[i].addMouseListener(this);
        }

        //添加棋盘标签
        con.add(image=new JLabel(new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\R-C.jpg")));
        image.setBounds(0,30,558,620);
        image.addMouseListener(this);

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

        this.setLocation((screenSize.width- frameSize.width)/2-270,(screenSize.height-frameSize.height)/2-350);

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
                }
                play[Man].setVisible(true);
            }else{
                text.setVisible(false);
                try{
                    tmain.sleep(250);
                }catch (Exception e){
                }
                play[Man].setVisible(true);
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
        //第一次点击棋子（移动棋子）
        if(me.getSource().equals(image)){
            ChessRule rule=new ChessRule();
            //该红棋走棋的时候
            if(chessPlayClick==2 && play[Man].getName().charAt(1)=='2'){
                Ex=play[Man].getX();
                Ey=play[Man].getY();

                //移动兵
                if(Man>15 && Man<26){
                    rule.armsRule(Man,play[Man],me,Var);
                    //移动炮
                } else if (Man>25 && Man<30) {
                    rule.cannonRule(Man,play[Man],play,me,Var);
                    //移动车
                } else if (Man>=0 && Man<4) {
                    rule.cannonRule(Man,play[Man],play,me,Var);
                    //移动马
                } /*else if (Man>3 && Man<8) {
                    rule.horseRule(play[Man],play,me);
                    //移动相
                } else if (Man>7 && Man<12) {
                    rule.elephantRule(Man,play[Man],play,me);
                    //移动仕
                } else if (Man>11 && Man<16) {
                    rule.chapRule(Man,play[Man],play,me);
                }
                //移动帅
                else if (Man==30 || Man==31) {
                    rule.willRule(Man,play[Man],play,me);
                }*/

                //是否走棋错误(是否在原地没有动
                if(Ex==play[Man].getX() && Ey==play[Man].getY()){
                    text.setText("红棋走棋");
                    chessPlayClick=2;
                }else{
                    text.setText("黑棋走棋");
                    chessPlayClick=1;
                }

                //该黑棋走棋的时候
            } else if (chessPlayClick==1 && play[Man].getName().charAt(1)=='1') {
                Ex=play[Man].getX();
                Ey=play[Man].getY();

                //移动卒
                if(Man>15 && Man<26){
                    rule.armsRule(Man,play[Man],me,Var);
                    //移动炮
                } else if (Man>25 && Man<30) {
                    rule.cannonRule(Man,play[Man],play,me,Var);
                    //移动车
                } else if (Man>=0 && Man<4) {
                    rule.cannonRule(Man,play[Man],play,me,Var);
                    //移动马
                } /*else if (Man>3 && Man<8) {
                    rule.horseRule(play[Man],play,me);
                    //移动象
                } else if (Man>7 && Man<12) {
                    rule.elephantRule(Man,play[Man],play,me);
                    //移动士
                } else if (Man>11 && Man<16) {
                    rule.chapRule(Man,play[Man],play,me);
                }
                //移动将
                else if (Man==30 || Man==31) {
                    rule.willRule(Man,play[Man],play,me);
                }*/

                //是否走棋错误(是否在原地没有动
                if(Ex==play[Man].getX() && Ey==play[Man].getY()){
                    text.setText("黑棋走棋");
                    chessPlayClick=1;
                }else{
                    text.setText("红棋走棋");
                    chessPlayClick=2;
                }
            }
            chessManClick=false;
        }else {
            //第一次单击棋子（闪烁棋子）
            if(!chessManClick){
                for(i=0;i<32;i++){
                    //被单击的棋子
                    if(me.getSource().equals(play[i])){
                        Man=i;
                        chessManClick=true;
                        break;
                    }
                }

                //第二次单击棋子（吃棋子规则）
            } else if (chessManClick) {
                //当前没有操作（停止闪烁）
                chessManClick=false;

                for(i=0;i<32;i++){
                    //找到被吃的棋子
                    if (me.getSource().equals(play[i])){
                        //该红棋吃棋的时候
                        if(chessPlayClick==2 && play[Man].getName().charAt(1)=='2'){
                            Ex=play[Man].getX();
                            Ey=play[Man].getY();

                            //卒、兵吃棋规则
                            if(Man>15 && Man<26){
                                rule.armsRule(Man,play[Man],play[i],i,Var);
                            }

                            //炮吃棋规则
                            else if (Man>25 && Man<30) {
                                rule.cannonRule(0,play[Man],Man,play[i],i,play,me,Var);
                            }

                            //车吃棋规则
                            else if (Man>=0 && Man<4) {
                                rule.cannonRule(1,play[Man],Man,play[i],i,play,me,Var);

                            }

                            //马吃棋规则
                           /* else if (Man>3 && Man<8) {
                                rule.horseRule(play[Man],play[i],play,me);
                            }

                            //相、象吃棋规则
                            else if (Man>7 && Man<12) {
                                rule.elephantRule(play[Man],play[i],play);
                            }

                            //士、仕吃棋规则
                            else if (Man>11 && Man<16) {
                                rule.chapRule(Man,play[Man],play[i],play);
                            }

                            //将、帅吃棋规则
                            else if (Man==30 || Man==31) {
                                rule.willRule(Man,play[Man],play[i],play);
                                play[Man].setVisible(true);
                            }*/

                            //是否走棋错误（是否在原地没有动）
                            if(Ex==play[Man].getX() && Ey==play[Man].getY()){
                                text.setText("红棋走棋");
                                chessPlayClick=2;
                                break;
                            }else{
                                text.setText("黑棋走棋");
                                chessPlayClick=1;
                                break;
                            }
                            //该黑棋吃棋的时候
                        }else if(chessPlayClick==1 && play[Man].getName().charAt(1)=='1'){
                            Ex=play[Man].getX();
                            Ey=play[Man].getY();

                            //卒、兵吃棋规则
                            if(Man>15 && Man<26){
                                rule.armsRule(Man,play[Man],play[i],i,Var);
                            }

                            //炮吃棋规则
                            else if (Man>25 && Man<30) {
                                rule.cannonRule(0,play[Man],Man,play[i],i,play,me,Var);
                            }

                            //车吃棋规则
                            else if (Man>=0 && Man<4) {
                                rule.cannonRule(1,play[Man],Man,play[i],i,play,me,Var);

                            }

                            //马吃棋规则
                            /*else if (Man>3 && Man<8) {
                                rule.horseRule(play[Man],play[i],play,me);
                            }

                            //相、象吃棋规则
                            else if (Man>7 && Man<12) {
                                rule.elephantRule(play[Man],play[i],play);
                            }

                            //士、仕吃棋规则
                            else if (Man>11 && Man<16) {
                                rule.chapRule(Man,play[Man],play[i],play);
                            }

                            //将、帅吃棋规则
                            else if (Man==30 || Man==31) {
                                rule.willRule(Man,play[Man],play[i],play);
                                play[Man].setVisible(true);
                            }*/

                            //是否走棋错误（是否在原地没有动）
                            if(Ex==play[Man].getX() && Ey==play[Man].getY()){
                                text.setText("黑棋走棋");
                                chessPlayClick=1;
                                break;
                            }else{
                                text.setText("红棋走棋");
                                chessPlayClick=2;
                                break;
                            }
                        }
                    }
                }

                //是否胜利
                if(!play[31].isVisible()){
                    JOptionPane.showConfirmDialog(this,"黑棋胜利","玩家一胜利",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
                    //双方都不可以再走棋了
                    chessPlayClick=3;
                    text.setText("黑棋胜利");
                } else if (!play[30].isVisible()) {
                    JOptionPane.showConfirmDialog(this,"红棋胜利","玩家二胜利",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    public void mousePressed(MouseEvent me){
    }
    public void mouseReleased(MouseEvent me){
    }
    public void mouseEntered(MouseEvent me){
    }
    public void mouseExited(MouseEvent me){
    }

    public void actionPerformed(ActionEvent ae){
        //重新开始按钮
        if(ae.getSource().equals(anew)) {
            int i, k;
            //重新排列每个棋子的位置
            //黑色棋子

            //车
            for (i = 0, k = 24; i < 2; i++, k += 456) {
                play[i].setBounds(k, 56, 55, 55);
                play[i].setVisible(true);
            }
            //马
            for (i = 4, k = 81; i < 6; i++, k += 342) {
                play[i].setBounds(k, 56, 55, 55);
                play[i].setVisible(true);
            }
            //象
            for (i = 8, k = 138; i < 10; i++, k += 228) {
                play[i].setBounds(k, 56, 55, 55);
                play[i].setVisible(true);
            }
            //士
            for (i = 12, k = 195; i < 14; i++, k += 114) {
                play[i].setBounds(k, 56, 55, 55);
                play[i].setVisible(true);
            }
            //卒
            for (i = 16, k = 24; i < 21; i++, k += 114) {
                play[i].setBounds(k, 227, 55, 55);
                play[i].setVisible(true);
            }
            //炮
            for (i = 26, k = 81; i < 28; i++, k += 342) {
                play[i].setBounds(k, 170, 55, 55);
                play[i].setVisible(true);
            }
            //将
            play[30].setBounds(252, 68, 55, 55);
            play[30].setVisible(true);

            //红色棋子
            //车
            for (i = 2, k = 24; i < 4; i++, k += 456) {
                play[i].setBounds(k, 569, 55, 55);
                play[i].setVisible(true);
            }
            //马
            for (i = 6, k = 81; i < 8; i++, k += 342) {
                play[i].setBounds(k, 569, 55, 55);
                play[i].setVisible(true);
            }
            //相
            for (i = 10, k = 138; i < 12; i++, k += 228) {
                play[i].setBounds(k, 569, 55, 55);
                play[i].setVisible(true);
            }
            //士
            for (i = 14, k = 195; i < 16; i++, k += 114) {
                play[i].setBounds(k, 569, 55, 55);
                play[i].setVisible(true);
            }
            //兵
            for (i = 21, k = 24; i < 26; i++, k += 114) {
                play[i].setBounds(k, 398, 55, 55);
                play[i].setVisible(true);
            }
            //炮
            for (i = 28, k = 81; i < 30; i++, k += 342) {
                play[i].setBounds(k, 455, 55, 55);
                play[i].setVisible(true);
            }
            //帅
            play[31].setBounds(252, 560, 55, 55);
            play[31].setVisible(true);
        }
        //悔棋按钮
        else if (ae.getSource().equals(repent)) {
            try{
                //获得setVisible属性值
                String S=(String) Var.get(Var.size()-4);
                //获得X坐标
                int x=Integer.parseInt((String) Var.get(Var.size()-3));
                //获得Y坐标
                int y=Integer.parseInt((String) Var.get(Var.size()-2));
                //获得索引
                int M=Integer.parseInt((String) Var.get(Var.size()-1));

                //赋给棋子
                play[M].setVisible(true);
                play[M].setBounds(x,y,55,55);
                if(play[M].getName().charAt(1)=='1'){
                    text.setText("黑棋走棋");
                    chessPlayClick=1;
                }else{
                    text.setText("红棋走棋");
                    chessPlayClick=2;
                }

                //删除用过的坐标
                Var.remove(Var.size()-4);
                Var.remove(Var.size()-3);
                Var.remove(Var.size()-2);
                Var.remove(Var.size()-1);

                //停止棋子闪烁
                chessManClick=false;
            }catch (Exception e){
            }
        } else if (ae.getSource().equals(exit)) {
            int j=JOptionPane.showConfirmDialog(this,"真的要退出吗？","退出",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE);

            if(j==JOptionPane.YES_OPTION){
             System.exit(0);
            }
        }
    }
}
