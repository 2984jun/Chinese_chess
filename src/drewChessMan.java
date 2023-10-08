import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class drewChessMan extends JFrame{
    public void AdddrewChessMan(JLabel play[],Container con){
        //流程控制
        int i,k;
        //图标
        Icon in;

        //黑色棋子

        //车
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\a7af275077614853a7b552db580e8ee4.png");
        for(i=0,k=24;i<2;i++,k+=456){
            play[i]=new JLabel(in);
            play[i].setBounds(k,56,55,55);
            play[i].setName("黑车");
        }

        //马
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\10218cb37ccc42e58c0ea1bbdec503fd.png");
        for(i=4,k=81;i<6;i++,k+=342){
            play[i]=new JLabel(in);
            play[i].setBounds(k,56,55,55);
            play[i].setName("黑马");
        }

        //象
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\0fbe8b946eca4351b6929d0c46517e8f.png");
        for(i=8,k=138;i<10;i++,k+=228){
            play[i]=new JLabel(in);
            play[i].setBounds(k,56,55,55);
            play[i].setName("象");
        }

        //士
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\0b43a15126a7454ea92f852a135c9e41 (1).png");
        for(i=12,k=195;i<14;i++,k+=114){
            play[i]=new JLabel(in);
            play[i].setBounds(k,56,55,55);
            play[i].setName("士");
        }

        //卒
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\27f55f020f9440e7873e0074d3aac7cd.png");
        for(i=16,k=24;i<21;i++,k+=114){
            play[i]=new JLabel(in);
            play[i].setBounds(k,227,55,55);
            play[i].setName("卒");
        }

        //炮
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\bd8f4b1a88934f8086acc9ba2e56d2a7.png");
        for(i=26,k=81;i<28;i++,k+=342){
            play[i]=new JLabel(in);
            play[i].setBounds(k,170,55,55);
            play[i].setName("黑炮");
        }

        //将
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\be8c1ad4c2904b51b91ec33c17922f1c.png");
        play[30]=new JLabel(in);
        play[30].setBounds(252,68,55,55);
        play[30].setName("将");

        //红色棋子
        //车
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\屏幕截图 2023-10-08 140110.png");
        for(i=2,k=24;i<4;i++,k+=456){
            play[i]=new JLabel(in);
            play[i].setBounds(k,569,55,55);
            play[i].setName("红车");
        }

        //马
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\屏幕截图 2023-10-08 140102.png");
        for(i=6,k=81;i<8;i++,k+=342){
            play[i]=new JLabel(in);
            play[i].setBounds(k,569,55,55);
            play[i].setName("红马");
        }

        //相
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\屏幕截图 2023-10-08 140052.png");
        for(i=10,k=138;i<12;i++,k+=228){
            play[i]=new JLabel(in);
            play[i].setBounds(k,569,55,55);
            play[i].setName("相");
        }

        //仕
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\ba799e0475b949b8b454b0a054b8d17b.png");
        for(i=14,k=195;i<16;i++,k+=114){
            play[i]=new JLabel(in);
            play[i].setBounds(k,569,55,55);
            play[i].setName("仕");
        }

        //兵
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\17ca8c1963964d1a91cbe47a6e2784a8.png");
        for(i=21,k=24;i<26;i++,k+=114){
            play[i]=new JLabel(in);
            play[i].setBounds(k,398,55,55);
            play[i].setName("兵");
        }

        //炮
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\3519df795efe46cd9226a1908625db35.png");
        for(i=28,k=81;i<30;i++,k+=342){
            play[i]=new JLabel(in);
            play[i].setBounds(k,455,55,55);
            play[i].setName("红炮");
        }

        //帅
        in=new ImageIcon("C:\\Users\\fuwujun\\Pictures\\Camera Roll\\97d92ef4acac4b678b6d9170f149b023.png");
        play[31]=new JLabel(in);
        play[31].setBounds(252,560,55,55);
        play[31].setName("帅");

        for(int n=0;n<32;n++){
            con.add(play[n]);
        }
    }
}
