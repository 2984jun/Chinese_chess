import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class ChessRule {
    public void armsRule(int Man, JLabel play, MouseEvent me, Vector Var) {
        //黑卒向下
        if (Man < 21) {
            //向下移动，得到终点的坐标模糊成合法坐标
            if ((me.getY() - play.getY()) > 27 && (me.getY() - play.getY()) < 86 && (me.getX() - play.getX()) < 55 && (me.getX() - play.getX()) > 0) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf((play.isVisible())));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(play.getX(), play.getY() + 57, 55, 55);
            }
            //向右移动，得到终点的坐标模糊成合法坐标、必须过河
            else if (play.getY() > 284 && (me.getX() - play.getX()) >= 57 && (me.getX() - play.getX()) <= 112) {
                play.setBounds(play.getX() + 57, play.getY(), 55, 55);
            }
            //向左移动，得到终点的坐标模糊成合法坐标、必须过河
            else if (play.getY() > 284 && (play.getX() - me.getX()) >= 2 && (play.getX() - me.getX()) <= 58) {
                play.setBounds(play.getX() - 57, play.getY(), 55, 55);
            }
        }
        //红兵向上
        else {
            Var.add(String.valueOf((play.isVisible())));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(Man));

            //向上移动，得到终点的坐标模糊成合法坐标
            if ((play.getY() - me.getY()) > 27 && (play.getY() - me.getY()) < 86 && (me.getX() - play.getX()) <= 55 && (me.getX() - play.getX()) >= 0) {

                play.setBounds(play.getX(), play.getY() - 57, 55, 55);
            }
            //向右移动，得到终点的坐标模糊成合法坐标、必须过河
            else if (play.getY() <= 341 && (me.getX() - play.getX()) >= 57 && (me.getX() - play.getX()) <= 112) {
                play.setBounds(play.getX() + 57, play.getY(), 55, 55);
            }
            //向左移动，得到终点的坐标模糊成合法坐标、必须过河
            else if (play.getY() <= 341 && (play.getX() - me.getX()) >= 3 && (play.getX() - me.getX()) <= 58) {
                play.setBounds(play.getX() - 57, play.getY(), 55, 55);
            }
        }
    }

    //卒、兵吃棋规则
    public void armsRule(int Man, JLabel play1, JLabel play2, int i, Vector Var) {
        //向右走
        if ((play2.getX() - play1.getX()) <= 112 && (play2.getX() - play1.getX()) >= 57 && (play1.getY() - play2.getY()) < 22 && (play1.getY() - play2.getY()) > -22 && play2.isVisible() &&
                play1.getName().charAt(1) != play2.getName().charAt(1)) {
            //黑棋要过河才能右吃棋
            if (play1.getName().charAt(1) == '1' && play1.getY() > 284 && play1.getName().charAt(1) != play2.getName().charAt(1)) {
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(), play2.getY(), 55, 55);
            }
            //红棋要过河才能左吃棋
            else if (play1.getName().charAt(1) == '2' && play1.getY() > 341 && play1.getName().charAt(1) != play2.getName().charAt(1)) {
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(), play2.getY(), 55, 55);
            }
        }
        //向左走
        else if ((play1.getX() - play2.getX()) <= 112 && (play1.getX() - play2.getX()) >= 57 && (play1.getY() - play2.getY()) < 22 && (play1.getY() - play2.getY()) > -22 && play2.isVisible() &&
                play1.getName().charAt(1) != play2.getName().charAt(1)) {
            //黑棋要过河才能左吃棋
            if (play1.getName().charAt(1) == '1' && play1.getY() > 284 && play1.getName().charAt(1) != play2.getName().charAt(1)) {
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(), play2.getY(), 55, 55);
            }
            //红棋要过河才能右吃棋
            else if (play1.getName().charAt(1) == '2' && play1.getY() > 341 && play1.getName().charAt(1) != play2.getName().charAt(1)) {
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(), play2.getY(), 55, 55);
            }
            //向上走
        } else if ((play1.getX() - play2.getX()) >= -22 && (play1.getX() - play2.getX()) <= 22 && (play1.getY() - play2.getY()) >= -122 && (play1.getY() - play2.getY()) <= 122 && play2.isVisible() &&
                play1.getName().charAt(1) != play2.getName().charAt(1)) {
            //黑棋不能向上吃棋
            if (play1.getName().charAt(1) == '1' && play1.getY() < play2.getY() && play1.getName().charAt(1) != play2.getName().charAt(1)) {
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(), play2.getY(), 55, 55);
            }
            //红棋不能向下吃棋
            else if (play1.getName().charAt(1) == '2' && play1.getY() > play2.getY() && play1.getName().charAt(1) != play2.getName().charAt(1)) {
                play2.setVisible(false);
                //把对方的位置给自己
                play1.setBounds(play2.getX(), play2.getY(), 55, 55);
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

    //炮、车移动规则
    public void cannonRule(int Man, JLabel play, JLabel playQ[], MouseEvent me, Vector Var) {
        //判断起点和终点之间是否右棋子
        boolean flag = true;

        //上下移动
        if (play.getX() - me.getX() <= 0 && play.getX() - me.getX() >= -55) {
            //指定所有模糊Y坐标
            for (int i = 56; i <= 571; i += 57) {
                //移动的Y坐标是否有指定坐标相近的
                if (i - me.getY() >= -27 && i - me.getY() <= 27) {
                    //所有棋子
                    for (int j = 0; j < 32; j++) {
                        //找出在同一条竖线的所有棋子、并不包括自己
                        if (playQ[j].getX() - play.getX() >= -27 && playQ[j].getX() <= 27 && playQ[j].getName() != play.getName() && playQ[j].isVisible()) {
                            //从起点到终点（从左到右）
                            for (int k = play.getY() + 57; k < i; k += 57) {
                                //大于起点、小于终点的坐标就可以知道中间是否有棋子
                                if (playQ[j].getY() < i && playQ[j].getY() > play.getY()) {
                                    //中间有一个棋子就不可以从这条线竖线过去
                                    flag = false;
                                    break;
                                }
                            }
                            //从起点到终点(从右到左）
                            for (int k = i + 57; k < play.getY(); k += 57) {
                                //找起点和终点的棋子
                                if (playQ[j].getY() < play.getY() && playQ[j].getY() > i) {
                                    //中间有一个棋子就不可以从这条线竖线过去
                                    flag = false;
                                    break;
                                }
                            }
                        }
                    }

                    //起点和终点没有棋子就可以移动了
                    if (flag) {
                        //记录当前添加到集合（用于悔棋）
                        Var.add(String.valueOf(play.isVisible()));
                        Var.add(String.valueOf(play.getX()));
                        Var.add(String.valueOf(play.getY()));
                        Var.add(String.valueOf(Man));
                        play.setBounds(play.getX(), i, 55, 55);
                        break;
                    }
                }
            }
        }

        //左右移动
        else if (play.getY() - me.getY() >= -27 && play.getY() - me.getY() <= 27) {
            //指定所有模糊X坐标
            for (int i = 24; i < 480; i += 57) {
                //移动的X坐标是否有指定坐标相近的
                if (i - me.getX() >= -55 && i - me.getX() <= 0) {
                    //所有棋子
                    for (int j = 0; j < 32; j++) {
                        //找出在同一条横线的所有棋子、并不包括自己
                        if (playQ[j].getY() - play.getY() >= -27 && playQ[j].getY() - play.getY() <= 27 && playQ[j].getName() != play.getName() && playQ[j].isVisible()) {
                            //从起点到终点（从上到下）
                            for (int k = play.getX() + 57; k < i; k += 57) {
                                //大于起点、小于终点的坐标就可以知道中间是否有棋子
                                if (playQ[j].getX() < i && playQ[j].getX() > play.getX()) {
                                    //中间有一个棋子就不可以从这条线横线过去
                                    flag = false;
                                    break;
                                }
                            }

                            //从起点到终点（从下到上）
                            for (int k = i + 57; k < play.getX(); k += 57) {
                                if (playQ[j].getX() < play.getX() && playQ[j].getX() > i) {
                                    flag = false;
                                    break;
                                }
                            }
                        }
                    }

                    //起点和终点没有棋子就可以移动了
                    if (flag) {
                        //记录当前添加到集合（用于悔棋）
                        Var.add(String.valueOf(play.isVisible()));
                        Var.add(String.valueOf(play.getX()));
                        Var.add(String.valueOf(play.getY()));
                        Var.add(String.valueOf(Man));
                        play.setBounds(i, play.getY(), 55, 55);
                        break;
                    }
                }
            }
        }
    }

    //炮、车吃棋规则
    public void cannonRule(int Chess, JLabel play, int Man, JLabel playTake, int i, JLabel playQ[], MouseEvent me, Vector Var) {
        //起点和终点之间是否有棋子
        int Count = 0;
        //所有棋子
        for (int j = 0; j < 32; j++) {
            //找出在同一条竖线的所有棋子、并不包括自己
            if (playQ[j].getX() - play.getX() >= -27 && playQ[j].getX() - play.getX() <= 27 && playQ[j].getName() != play.getName() && playQ[j].isVisible()) {
                //自己是起点，被吃的是终点（从上到下）
                for (int k = play.getY() + 57; k < playTake.getY(); k += 57) {
                    //大于起点、小于终点的坐标就可以知道中间是否有棋子
                    if (playQ[j].getY() < playTake.getY() && playQ[j].getY() > play.getY()) {
                        //计算起点和终点的棋子个数
                        Count++;
                        break;
                    }
                }

                //自己是起点，被吃是终点（从下到上）
                for (int k = playTake.getY(); k < play.getY(); k += 57) {
                    //找起点和终点的棋子
                    if (playQ[j].getY() < play.getY() && playQ[j].getY() > playTake.getY()) {
                        Count++;
                        break;
                    }
                }
            }
            //找出在同一条横线的所有棋子、并不包括自己
            else if (playQ[j].getY() - play.getY() >= -10 && playQ[j].getY() - play.getY() <= 10 && playQ[j].getName() != play.getName() && playQ[j].isVisible()) {
                //自己是起点，被吃是终点（从左到右）
                for (int k = play.getX() + 50; k < playTake.getX(); k += 57) {
                    //大于起点、小于终点的坐标就可以知道中间是否有棋子
                    if (playQ[j].getX() < playTake.getX() && playQ[j].getX() > play.getX()) {
                        //中间有一个棋子就不可以从这条线横线过去
                        Count++;
                        break;
                    }
                }

                //自己是起点，被吃的是终点（从右到左）
                for (int k = playTake.getX(); k < play.getX(); k += 57) {
                    //找起点和终点的棋子
                    if (playQ[j].getX() < play.getX() && playQ[j].getX() > playTake.getX()) {
                        Count++;
                        break;
                    }
                }
            }
        }

        //起点和终点之间要有一个棋子是炮的规则，并且不能吃自己的棋子
        if (Count == 1 && Chess == 0 && playTake.getName().charAt(1) != play.getName().charAt(1)) {
            //当前记录添加到集合（用于悔棋）
            Var.add(String.valueOf(play.isVisible()));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(Man));

            //当前记录添加到集合（用于悔棋）
            Var.add(String.valueOf(playTake.isVisible()));
            Var.add(String.valueOf(playTake.getX()));
            Var.add(String.valueOf(playTake.getY()));
            Var.add(String.valueOf(i));

            playTake.setVisible(false);
            play.setBounds(playTake.getX(), playTake.getY(), 55, 55);
        }
        //起点和终点之间没有棋子是车的规则，并且不能吃自己的棋子
        else if (Count == 0 && Chess == 1 && playTake.getName().charAt(1) != play.getName().charAt(1)) {
            //当前记录添加到集合（用于悔棋）
            Var.add(String.valueOf(play.isVisible()));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(Man));

            //当前记录添加到集合（用于悔棋）
            Var.add(String.valueOf(playTake.isVisible()));
            Var.add(String.valueOf(playTake.getX()));
            Var.add(String.valueOf(playTake.getY()));
            Var.add(String.valueOf(i));

            playTake.setVisible(false);
            play.setBounds(playTake.getX(), playTake.getY(), 55, 55);
        }
    }

    //马移动规则
    public void horseRule(int Man, JLabel play, JLabel playQ[], MouseEvent me, Vector Var) {
        //保存坐标和障碍
        int Ex = 0, Ey = 0;
        boolean Obstacles = true; //true 没有障碍  false 有障碍

        //上移、左边
        if (play.getX() - me.getX() >= 2 && play.getX() - me.getX() <= 57 && play.getY() - me.getY() >= 87 && play.getY() - me.getY() <= 141) {
            //Y坐标
            for (int i = 56; i <= 571; i += 57) {
                //移动到Y坐标是否有指定坐标相近的
                if (i - me.getY() >= -27 && i - me.getY() <= 27) {
                    Ey = i;
                    break;
                }
            }

            //Y坐标
            for (int i = 24; i <= 480; i += 57) {
                //移动到X坐标是否有指定坐标相近的
                if (i - me.getX() >= -55 && i - me.getX() <= 0) {
                    Ex = i;
                    break;
                }
            }

            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && play.getX() - playQ[i].getX() == 0 && play.getY() - playQ[i].getY() == 57) {
                    Obstacles = false;
                    break;
                }
            }

            //可以移动该棋子
            if (Obstacles) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex, Ey, 55, 55);
            }
        }
        //上移、右边
        else if (me.getX() - play.getX() >= 30 && me.getX() - play.getX() <= 90 && play.getY() - me.getY() >= 87 && play.getY() - me.getY() <= 141) {
            //Y坐标
            for (int i = 56; i <= 571; i += 57) {
                //移动到Y坐标是否有指定坐标相近的
                if (i - me.getY() >= -27 && i - me.getY() <= 27) {
                    Ey = i;
                    break;
                }
            }

            //Y坐标
            for (int i = 24; i <= 480; i += 57) {
                //移动到X坐标是否有指定坐标相近的
                if (i - me.getX() >= -55 && i - me.getX() <= 0) {
                    Ex = i;
                    break;
                }
            }

            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && play.getX() - playQ[i].getX() == 0 && play.getY() - playQ[i].getY() == 57) {
                    Obstacles = false;
                    break;
                }
            }

            //可以移动该棋子
            if (Obstacles) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex, Ey, 55, 55);
            }
        }
        //左移、上边
        else if (play.getX() - me.getX() >= 70 && play.getX() - me.getX() <= 130 && play.getY() - me.getY() >= 27 && play.getY() - me.getY() <= 86) {
            //Y
            for (int i = 56; i <= 571; i += 57) {
                //移动到Y坐标是否有指定坐标相近的
                if (i - me.getY() >= -27 && i - me.getY() <= 27) {
                    Ey = i;
                    break;
                }
            }

            //Y坐标
            for (int i = 24; i <= 480; i += 57) {
                //移动到X坐标是否有指定坐标相近的
                if (i - me.getX() >= -55 && i - me.getX() <= 0) {
                    Ex = i;
                    break;
                }
            }

            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && play.getX() - playQ[i].getX() == 57 && play.getY() - playQ[i].getY() == 0) {
                    Obstacles = false;
                    break;
                }
            }

            //可以移动该棋子
            if (Obstacles) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex, Ey, 55, 55);
            }
        }
        //左移、下边
        else if (play.getX() - me.getX() >= 70 && play.getX() - me.getX() <= 130 && me.getY() - play.getY() >= 27 && me.getY() - play.getY() <= 86) {
            //Y
            for (int i = 56; i <= 571; i += 57) {
                //移动到Y坐标是否有指定坐标相近的
                if (i - me.getY() >= -27 && i - me.getY() <= 27) {
                    Ey = i;
                    break;
                }
            }

            //Y坐标
            for (int i = 24; i <= 480; i += 57) {
                //移动到X坐标是否有指定坐标相近的
                if (i - me.getX() >= -55 && i - me.getX() <= 0) {
                    Ex = i;
                    break;
                }
            }

            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && play.getX() - playQ[i].getX() == 57 && play.getY() - playQ[i].getY() == 0) {
                    Obstacles = false;
                    break;
                }
            }

            //可以移动该棋子
            if (Obstacles) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex, Ey, 55, 55);
            }
        }
        //右移、上边
        else if (me.getX() - play.getX() >= 70 && me.getX() - play.getX() <= 150 && play.getY() - me.getY() > 30 && play.getY() - me.getY() <= 86) {
            //Y
            for (int i = 56; i <= 571; i += 57) {
                //移动到Y坐标是否有指定坐标相近的
                if (i - me.getY() >= -27 && i - me.getY() <= 27) {
                    Ey = i;
                    break;
                }
            }

            //Y坐标
            for (int i = 24; i <= 480; i += 57) {
                //移动到X坐标是否有指定坐标相近的
                if (i - me.getX() >= -55 && i - me.getX() <= 0) {
                    Ex = i;
                    break;
                }
            }

            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && playQ[i].getX() - play.getX() == 57 && play.getY() - playQ[i].getY() == 0) {
                    Obstacles = false;
                    break;
                }
            }

            //可以移动该棋子
            if (Obstacles) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex, Ey, 55, 55);
            }
        }
        //右移、下边
        else if (me.getX() - play.getX() >= 87 && me.getX() - play.getX() <= 140 && me.getY() - play.getY() > 30 && me.getY() - play.getY() <= 87) {
            //Y
            for (int i = 56; i <= 571; i += 57) {
                //移动到Y坐标是否有指定坐标相近的
                if (i - me.getY() >= -27 && i - me.getY() <= 27) {
                    Ey = i;
                    break;
                }
            }

            //Y坐标
            for (int i = 24; i <= 480; i += 57) {
                //移动到X坐标是否有指定坐标相近的
                if (i - me.getX() >= -55 && i - me.getX() <= 0) {
                    Ex = i;
                    break;
                }
            }

            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && playQ[i].getX() - play.getX() == 57 && play.getY() - playQ[i].getY() == 0) {
                    Obstacles = false;
                    break;
                }
            }

            //可以移动该棋子
            if (Obstacles) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex, Ey, 55, 55);
            }
        }
        //下移、左边
        else if (me.getY() - play.getY() > 87 && me.getY() - play.getY() < 141 && play.getX() - me.getX() > 10 && play.getX() - me.getX() < 86) {
            //Y
            for (int i = 56; i <= 571; i += 57) {
                //移动到Y坐标是否有指定坐标相近的
                if (i - me.getY() >= -27 && i - me.getY() <= 27) {
                    Ey = i;
                    break;
                }
            }

            //Y坐标
            for (int i = 24; i <= 480; i += 57) {
                //移动到X坐标是否有指定坐标相近的
                if (i - me.getX() >= -55 && i - me.getX() <= 0) {
                    Ex = i;
                    break;
                }
            }

            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && playQ[i].getX() - play.getX() == 0 && playQ[i].getY() - play.getY() == 57) {
                    Obstacles = false;
                    break;
                }
            }

            //可以移动该棋子
            if (Obstacles) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex, Ey, 55, 55);
            }
        }
        //下移、右边
        else if (me.getY() - play.getY() >= 87 && me.getY() - play.getY() <= 141 && me.getX() - play.getX() >=35 && me.getX() - play.getX() <= 87) {
            //Y
            for (int i = 56; i <= 571; i += 57) {
                //移动到Y坐标是否有指定坐标相近的
                if (i - me.getY() >= -27 && i - me.getY() <= 27) {
                    Ey = i;
                    break;
                }
            }

            //Y坐标
            for (int i = 24; i <= 480; i += 57) {
                //移动到X坐标是否有指定坐标相近的
                if (i - me.getX() >= -55 && i - me.getX() <= 0) {
                    Ex = i;
                    break;
                }
            }

            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && playQ[i].getX() - play.getX() == 0 && playQ[i].getY() - play.getY() == 57) {
                    Obstacles = false;
                    break;
                }
            }

            //可以移动该棋子
            if (Obstacles) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex, Ey, 55, 55);
            }
        }
    }

    //马吃棋规则
    public void horseRule(JLabel play, int Man, JLabel playTake, int take, JLabel playQ[], MouseEvent me, Vector Var) {
        boolean Obstacles = true; //true 没有障碍  false 有障碍
        boolean Chess = false; //true 吃棋 false 不吃棋

        //上移、左吃
        if(play.getName().charAt(1)!=playTake.getName().charAt(1) && play.getX()-playTake.getX()==57 && play.getY()-playTake.getY()==114){
            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && play.getX() - playQ[i].getX() == 0 && play.getY() - playQ[i].getY() == 57) {
                    Obstacles = false;
                    break;
                }
            }
            Chess=true;
        }
        //上移、右吃
        else if (play.getName().charAt(1)!=playTake.getName().charAt(1) && playTake.getX()-play.getX()==57 && play.getY()-playTake.getY()==114) {
            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && play.getX() - playQ[i].getX() == 0 && play.getY() - playQ[i].getY() == 57) {
                    Obstacles = false;
                    break;
                }
            }
            Chess=true;
        }
        //左移、上吃
        else if (play.getName().charAt(1)!=playTake.getName().charAt(1) && play.getY()-playTake.getY()==57 && play.getX()-playTake.getX()==114) {
            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && play.getX() - playQ[i].getX() == 57 && play.getY() - playQ[i].getY() == 0) {
                    Obstacles = false;
                    break;
                }
            }
            Chess=true;
        }
        //左移、下吃
        else if (play.getName().charAt(1)!=playTake.getName().charAt(1) && playTake.getY()-play.getY()==57 && play.getX()-playTake.getX()==114) {
            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && play.getX() - playQ[i].getX() == 57 && play.getY() - playQ[i].getY() == 0) {
                    Obstacles = false;
                    break;
                }
            }
            Chess=true;
        }
        //右移、上吃
        else if (play.getName().charAt(1)!=playTake.getName().charAt(1) && play.getY()-playTake.getY()==57 &&playTake.getX()-play.getX()==114) {
            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && playQ[i].getX() - play.getX() == 57 && play.getY() - playQ[i].getY() == 0) {
                    Obstacles = false;
                    break;
                }
            }
            Chess=true;
        }
        //右移、下吃
        else if (play.getName().charAt(1)!=playTake.getName().charAt(1) && playTake.getY()-play.getY()==57 &&playTake.getX()-play.getX()==114) {
            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && playQ[i].getX() - play.getX() == 57 && play.getY() - playQ[i].getY() == 0) {
                    Obstacles = false;
                    break;
                }
            }
            Chess=true;
        }
        //下移、左吃
        else if (play.getName().charAt(1)!=playTake.getName().charAt(1) && playTake.getY()-play.getY()==114 && play.getX()-playTake.getX()==57) {
            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && playQ[i].getX() - play.getX() == 0 && playQ[i].getY() - play.getY() == 57) {
                    Obstacles = false;
                    break;
                }
            }
            Chess=true;
        }
        //下移、右吃
        else if (play.getName().charAt(1)!=playTake.getName().charAt(1) && playTake.getY()-play.getY()==114 &&playTake.getX()-play.getX()==57) {
            //正前方是否有别的棋子
            for (int i = 0; i < 32; i++) {
                if (playQ[i].isVisible() && playQ[i].getX() - play.getX() == 0 && playQ[i].getY() - play.getY() == 57) {
                    Obstacles = false;
                    break;
                }
            }
            Chess=true;
        }

        //没有障碍、并且可以吃棋、不能吃自己颜色
        if (Chess && Obstacles && playTake.getName().charAt(1)!=play.getName().charAt(1)){
            //当前记录添加到集合（用于悔棋）
            Var.add(String.valueOf(play.isVisible()));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(Man));

            //当前记录添加到集合（用于悔棋）
            Var.add(String.valueOf(playTake.isVisible()));
            Var.add(String.valueOf(playTake.getX()));
            Var.add(String.valueOf(playTake.getY()));
            Var.add(String.valueOf(take));

            playTake.setVisible(false);
            play.setBounds(playTake.getX(), playTake.getY(), 55, 55);
        }
    }

    public void elephantRule(int Man,JLabel play,JLabel playQ[],MouseEvent me,Vector Var){
        //坐标和障碍
        int Ex=0,Ey=0;
        boolean Obstacles=true; //true 无障碍 false 有障碍

        //上左
        if(play.getX()-me.getX()<=141 && play.getX()-me.getX()>=87 && play.getY()-me.getY()>=87 && play.getY()-me.getY()<=141){
            //合法Y坐标
            for(int i=56;i<=571;i+=57){
                if(i-me.getY()>=-27 && i-me.getY()<=27){
                    Ey=i;
                    break;
                }
            }

            //合法X坐标
            for(int i=24;i<=480;i+=57){
                if(i-me.getX()>=-27 && i-me.getX()<=27){
                    Ex=i;
                    break;
                }
            }

            //左上方是否有棋子
            for(int i=0;i<32;i++){
                if(playQ[i].isVisible() && play.getX()-playQ[i].getX()==57 && play.getY()-playQ[i].getY()==57){
                    Obstacles=false;
                    break;
                }
            }

            //相（红棋）不能过河
            if(Obstacles && Ey>=341 && Man>9){
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex,Ey,55,55);
            }
            //象（黑棋）不能过河
            else if (Obstacles && Ey<=284 && Man<10) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex,Ey,55,55);
            }
        }
        //上右
        else if (me.getX()-play.getX()<=141 && me.getX()-play.getX()>=87 && play.getY()-me.getY()>=87 && play.getY()-me.getY()<=141) {
            //合法Y坐标
            for(int i=56;i<=571;i+=57){
                if(i-me.getY()>=-27 && i-me.getY()<=27){
                    Ey=i;
                    break;
                }
            }

            //合法X坐标
            for(int i=24;i<=480;i+=57){
                if(i-me.getX()>=-27 && i-me.getX()<=27){
                    Ex=i;
                    break;
                }
            }

            //右上方是否有棋子
            for(int i=0;i<32;i++){
                if(playQ[i].isVisible() && playQ[i].getX()-play.getX()==57 && play.getY()-playQ[i].getY()==57){
                    Obstacles=false;
                    break;
                }
            }

            //相（红棋）不能过河
            if(Obstacles && Ey>=341 && Man>9){
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex,Ey,55,55);
            }
            //象（黑棋）不能过河
            else if (Obstacles && Ey<=284 && Man<10) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex,Ey,55,55);
            }
        }
        //下左
        else if (play.getX()-me.getX()<=141 && play.getX()-me.getX()>=87 && me.getY()-play.getY()>=87 && me.getY()-play.getY()<=141) {
            //合法Y坐标
            for(int i=56;i<=571;i+=57){
                if(i-me.getY()>=-27 && i-me.getY()<=27){
                    Ey=i;
                    break;
                }
            }

            //合法X坐标
            for(int i=24;i<=480;i+=57){
                if(i-me.getX()>=-27 && i-me.getX()<=27){
                    Ex=i;
                    break;
                }
            }

            //左下方是否有棋子
            for(int i=0;i<32;i++){
                if(playQ[i].isVisible() && play.getX()-playQ[i].getX()==57 && playQ[i].getY()-play.getY()==57){
                    Obstacles=false;
                    break;
                }
            }

            //相（红棋）不能过河
            if(Obstacles && Ey>=341 && Man>9){
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex,Ey,55,55);
            }
            //象（黑棋）不能过河
            else if (Obstacles && Ey<=284 && Man<10) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex,Ey,55,55);
            }
        }
        //下右
        else if (me.getX()-play.getX()<=141 && me.getX()-play.getX()>=87 && me.getY()-play.getY()>=87 && me.getY()-play.getY()<=141) {
            //合法Y坐标
            for(int i=56;i<=571;i+=57){
                if(i-me.getY()>=-27 && i-me.getY()<=27){
                    Ey=i;
                    break;
                }
            }

            //合法X坐标
            for(int i=24;i<=480;i+=57){
                if(i-me.getX()>=-27 && i-me.getX()<=27){
                    Ex=i;
                    break;
                }
            }

            //右下方是否有棋子
            for(int i=0;i<32;i++){
                if(playQ[i].isVisible() && playQ[i].getX()-play.getX()==57 && playQ[i].getY()-play.getY()==57){
                    Obstacles=false;
                    break;
                }
            }

            //相（红棋）不能过河
            if(Obstacles && Ey>=341 && Man>9){
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex,Ey,55,55);
            }
            //象（黑棋）不能过河
            else if (Obstacles && Ey<=284 && Man<10) {
                //当前记录添加到集合（用于悔棋）
                Var.add(String.valueOf(play.isVisible()));
                Var.add(String.valueOf(play.getX()));
                Var.add(String.valueOf(play.getY()));
                Var.add(String.valueOf(Man));

                play.setBounds(Ex,Ey,55,55);
            }
        }
    }

    public void elephantRule(int Man,JLabel play,JLabel playTake,int Take,JLabel playQ[],MouseEvent me,Vector Var){
        //障碍
        boolean Obstacles=true; //true 无障碍 flase 有障碍

        //吃左上方的棋子
        if(play.getX()-playTake.getX()>=87 && play.getX()-playTake.getX()<=141 && play.getY()-playTake.getY()>=87 && play.getY()-playTake.getY()<=141){
            //左上方是否有棋子
            for(int i=0;i<32;i++){
                if(playQ[i].isVisible() && play.getX()-playQ[i].getX()==57 && play.getY()-playQ[i].getY()==57){
                    Obstacles=false;
                    break;
                }
            }
        }
        //吃右上方的棋子
        else if (playTake.getX()-play.getX()>=87 && playTake.getX()-play.getX()<=141 && play.getY()-playTake.getY()>=87 && play.getY()-playTake.getY()<=141) {
            //右上方是否有棋子
            for(int i=0;i<32;i++){
                if(playQ[i].isVisible() && playQ[i].getX()-play.getX()==57 && play.getY()-playQ[i].getY()==57){
                    Obstacles=false;
                    break;
                }
            }
        }
        //吃左下方的棋子
        else if (play.getX()-playTake.getX()>=87 && play.getX()-playTake.getX()<=141 && playTake.getY()-play.getY()>=87 && playTake.getY()-play.getY()<=141) {
            //左下方是否有棋子
            for(int i=0;i<32;i++){
                if(playQ[i].isVisible() && playQ[i].getY()-play.getY()==57 && play.getX()-playQ[i].getX()==57){
                    Obstacles=false;
                    break;
                }
            }
        }
        //吃右下方的棋子
        else if (playTake.getX()-play.getX()>=87 && playTake.getX()-play.getX()<=141 && playTake.getY()-play.getY()>=87 && playTake.getY()-play.getY()<=141) {
            //右下方是否有棋子
            for(int i=0;i<32;i++){
                if(playQ[i].isVisible() && playQ[i].getX()-play.getX()==57 && playQ[i].getY()-play.getY()==57){
                    Obstacles=false;
                    break;
                }
            }
        }

        //没有障碍、并且不能吃自己的棋子
        if(Obstacles && playTake.getName().charAt(1)!=play.getName().charAt(1)){
            //当前记录添加到集合（用于悔棋）
            Var.add(String.valueOf(play.isVisible()));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(Man));

            //当前记录添加到集合（用于悔棋）
            Var.add(String.valueOf(playTake.isVisible()));
            Var.add(String.valueOf(playTake.getX()));
            Var.add(String.valueOf(playTake.getY()));
            Var.add(String.valueOf(Take));

            playTake.setVisible(false);
            play.setBounds(playTake.getX(), playTake.getY(), 55, 55);
        }
    }

    //士、仕移动规则
    public void chapRule(int Man,JLabel play,JLabel playQ[],MouseEvent me,Vector Var){

    }

    //士、仕吃棋规则
    public void chapRule(int Man,JLabel play,JLabel playTake,int Take,JLabel playQ[],MouseEvent me,Vector Var){

    }

    //帅、将移动规则
    public void willRule(int Man,JLabel play,JLabel playQ[],MouseEvent me,Vector Var){

    }

    //帅、将吃棋规则
    public void willRule(int Man,JLabel play,JLabel playTake,int Take,JLabel playQ[],MouseEvent me,Vector Var){

    }
}

