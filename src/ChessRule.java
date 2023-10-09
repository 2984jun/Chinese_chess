import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class ChessRule {
    public void armsRule(int Man,JLabel play,MouseEvent me,Vector Var){
        //黑卒向下
        if(Man<21){
            //向下移动，得到终点的坐标模糊成合法坐标
            if((me.getY()-play.getY())>27 && (me.getY()-play.getY())<86 && (me.getX()-play.getX())<55 && (me.getX()-play.getX())>0){
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf((play.isVisible())));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(play.getX(),play.getY()+57,55,55);
            }
            //向右移动，得到终点的坐标模糊成合法坐标、必须过河
            else if (play.getY()>284 && (me.getX()-play.getX())>=57 && (me.getX()-play.getX())<=112) {
                play.setBounds(play.getX()+57,play.getY(),55,55);
            }
            //向左移动，得到终点的坐标模糊成合法坐标、必须过河
            else if (play.getY()>284 && (play.getX()-me.getX())>=2 && (play.getX()-me.getX())<=58) {
                play.setBounds(play.getX()-57,play.getY(),55,55);
            }
        }
        //红兵向上
        else{
            Var.add(String.valueOf((play.isVisible())));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(Man));

            //向上移动，得到终点的坐标模糊成合法坐标
            if((play.getY()-me.getY())>27 && (play.getY()-me.getY())<86 && (me.getX()-play.getX())<=55 && (me.getX()-play.getX())>=0){

                play.setBounds(play.getX(),play.getY()-57,55,55);
            }
            //向右移动，得到终点的坐标模糊成合法坐标、必须过河
            else if (play.getY()<=341 && (me.getX()-play.getX())>=57 && (me.getX()-play.getX())<=112) {
                play.setBounds(play.getX()+57,play.getY(),55,55);
            }
            //向左移动，得到终点的坐标模糊成合法坐标、必须过河
            else if (play.getY()<=341 && (play.getX()-me.getX())>=3 && (play.getX()-me.getX())<=58) {
                play.setBounds(play.getX()-57,play.getY(),55,55);
            }
        }
    }

    //卒、兵吃棋规则
    public void armsRule(int Man,JLabel play1,JLabel play2,int i,Vector Var){
        //向右走
        if((play2.getX()-play1.getX()) <=112 && (play2.getX()-play1.getX()) >=57 && (play1.getY()-play2.getY()) <22 && (play1.getY()-play2.getY()) >-22 && play2.isVisible() &&
        play1.getName().charAt(1)!=play2.getName().charAt(1)){
            //黑棋要过河才能右吃棋
            if(play1.getName().charAt(1)=='1' && play1.getY()>284 && play1.getName().charAt(1)!=play2.getName().charAt(1)){
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(),play2.getY(),55,55);
            }
            //红棋要过河才能左吃棋
            else if (play1.getName().charAt(1)=='2' && play1.getY()>341 && play1.getName().charAt(1)!=play2.getName().charAt(1)) {
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(),play2.getY(),55,55);
            }
        }
        //向左走
        else if ((play1.getX()-play2.getX()) <=112 && (play1.getX()-play2.getX()) >=57 && (play1.getY()-play2.getY()) <22 && (play1.getY()-play2.getY()) >-22 && play2.isVisible() &&
                play1.getName().charAt(1)!=play2.getName().charAt(1)) {
            //黑棋要过河才能左吃棋
            if(play1.getName().charAt(1)=='1' && play1.getY()>284 && play1.getName().charAt(1)!=play2.getName().charAt(1)){
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(),play2.getY(),55,55);
            }
            //红棋要过河才能右吃棋
            else if (play1.getName().charAt(1)=='2' && play1.getY()>341 && play1.getName().charAt(1)!=play2.getName().charAt(1)) {
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(),play2.getY(),55,55);
            }
        //向上走
        } else if ((play1.getX()-play2.getX()) >=-22 && (play1.getX()-play2.getX()) <=22 && (play1.getY()-play2.getY()) >=-122 && (play1.getY()-play2.getY()) <=122 && play2.isVisible() &&
                play1.getName().charAt(1)!=play2.getName().charAt(1)) {
            //黑棋不能向上吃棋
            if(play1.getName().charAt(1)=='1' && play1.getY()<play2.getY() && play1.getName().charAt(1)!=play2.getName().charAt(1)){
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(),play2.getY(),55,55);
            }
            //红棋不能向下吃棋
            else if (play1.getName().charAt(1)=='2' && play1.getY()>play2.getY() && play1.getName().charAt(1)!=play2.getName().charAt(1)) {
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(),play2.getY(),55,55);
            }
        }

        //当前记录添加到集合（用于悔棋）
        Var.add(String.valueOf((play1.isVisible())));
        Var.add(String.valueOf(play1.getX()));
        Var.add(String.valueOf(play1.getY()));
        Var.add(String.valueOf(Man));

        //当前记录添加到集合（用于悔棋）
        Var.add(String.valueOf((play2.isVisible())));
        Var.add(String.valueOf(play2.getX()));
        Var.add(String.valueOf(play2.getY()));
        Var.add(String.valueOf(i));
    }


}
