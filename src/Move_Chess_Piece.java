import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Move_Chess_Piece{
    public void MoveChessPiece(Container con, MouseEvent me, JLabel play[], JLabel image, int Man, int i, int chessPlayClick, int Ex, int Ey, JLabel text, boolean chessManClick){
        //第一次单击棋子（移动棋子）
        if(me.getSource().equals(image)){
            ChessRule rule=new ChessRule();
            //该红棋走棋的时候
            if(chessPlayClick==2 && play[Man].getName().charAt(1)=='2'){
                Ex=play[Man].getX();
                Ey=play[Man].getY();

                //移动兵
                if(Man>15 && Man<26){
                    rule.armsRule(Man,play[Man],me);
                //移动炮
                } else if (Man>25 && Man<30) {
                    rule.cannonRule(play[Man],play,me);
                //移动车
                } else if (Man>=0 && Man<4) {
                    rule.cannonRule(play[Man],play,me);
                //移动马
                } else if (Man>3 && Man<8) {
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
                }

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
                    rule.armsRule(Man,play[Man],me);
                    //移动炮
                } else if (Man>25 && Man<30) {
                    rule.cannonRule(play[Man],play,me);
                    //移动车
                } else if (Man>=0 && Man<4) {
                    rule.cannonRule(play[Man],play,me);
                    //移动马
                } else if (Man>3 && Man<8) {
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
                }

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
                                rule.armsRule(play[Man],play[i]);
                            }

                            //炮吃棋规则
                            else if (Man>25 && Man<30) {
                                rule.cannonRule(0,play[Man],play[i],play,me);
                            }

                            //车吃棋规则
                            else if (Man>=0 && Man<4) {
                                rule.cannonRule(1,play[Man],play[i],play,me);

                            }

                            //马吃棋规则
                            else if (Man>3 && Man<8) {
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
                            }

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
                                rule.armsRule(play[Man],play[i]);
                            }

                            //炮吃棋规则
                            else if (Man>25 && Man<30) {
                                rule.cannonRule(0,play[Man],play[i],play,me);
                            }

                            //车吃棋规则
                            else if (Man>=0 && Man<4) {
                                rule.cannonRule(1,play[Man],play[i],play,me);

                            }

                            //马吃棋规则
                            else if (Man>3 && Man<8) {
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
                            }

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
                    JOptionPane.showConfirmDialog(con,"黑棋胜利","玩家一胜利",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
                    //双方都不可以再走棋了
                    chessPlayClick=3;
                    text.setText("黑棋胜利");
                } else if (!play[30].isVisible()) {
                    JOptionPane.showConfirmDialog(con,"红棋胜利","玩家二胜利",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}