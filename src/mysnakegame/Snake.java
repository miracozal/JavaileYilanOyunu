/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysnakegame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author mirac
 */
public class Snake extends JLabel{

    SnakesBox SB = new SnakesBox(); //Global değişken olması için buraya yerleştirdik
    
    public Timer mTimer = null;
    
    public Forage mForage = new Forage();
    
    public Random mRandom = null;
            
    public ArrayList<SnakesBox> List = new ArrayList<SnakesBox>();
        
    @Override //Paint override ettik
    public void paint(Graphics g) {
        super.paint(g); //super.paint(g) ile default çizim sağlanıyor
            
        Graphics2D g2 = (Graphics2D)g; //Java eski versiyonlarını desteklemek için
        
        Rectangle2D rect = new Rectangle2D.Double(5,5,getWidth()-10,getHeight()-10);
        
        g2.setColor(Color.red);
        
        g2.setStroke(new BasicStroke(10));
        
        g2.draw(rect);
    }    
    
    Snake()
    {
        mRandom = new Random(System.currentTimeMillis());
        addKeyListener(new KeyboardControl());
        setFocusable(true); //Tuşa basılınca aktif pencere belirlemiş oluruz
        
        mTimer = new Timer(100,new TimerControl());
        mTimer.start();
        
        List.add(SB);
        for (int i = 1; i < 10; i++) {
            AddQueue();
        }   
        
        add(mForage);
        add(SB);  
    }
    
    public void AddQueue()
    {
        SnakesBox SB = List.get(List.size()-1).CreateBox();
        List.add(SB);
        add(SB);
    }
    
    public void AddForage()
    {
        int Width = getWidth()-30-mForage.mGenislik;
        int Height = getHeight()-30-mForage.mGenislik;
        
        int PosX = 10 + Math.abs(mRandom.nextInt())%Width;
        int PosY = 10 + Math.abs(mRandom.nextInt())%Height;
        
        PosX = PosX - PosX%20;
        PosY = PosY - PosY%20;
            
        for(int i = 0; i < List.size(); i++)
        {
            if((PosX==List.get(i).getX())&&(PosY==List.get(i).getY()))
            {
                AddForage();
                return;
            }    
        }
        
        mForage.setPosition(PosX, PosY);
    }
    
    public void AllMove()
    {
        for (int i = List.size()-1; i > 0; i--) 
        {
            SnakesBox Before = List.get(i-1);
            SnakesBox After = List.get(i);
            
            List.get(i).Move();
            After.mMove = Before.mMove;
        }
        SB.Move();
    }
    
    public boolean Collision()
    {
        int line = 10;        
        int height = getHeight();
        int width = getWidth();
        
        if(SB.getX()<=10)
            return true;
        if(SB.getX()+SB.mGenislik>=width-line)
            return true;
        if(SB.getY()<=10)
            return true;
        if(SB.getY()+SB.mGenislik>=height-line)
            return true;
        
        for (int i = 1; i < List.size(); i++) {
            int X = List.get(i).getX();
            int Y = List.get(i).getY();
            
            if((X==SB.getX())&&(Y==SB.getY()))
                return true;
        }
        
        if(mForage.getX()==SB.getX()&&(mForage.getY()==SB.getY()))
        {
            AddQueue();
            AddForage();
        }
        return false;
    }
    
    class TimerControl implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            AllMove();
            if(Collision())
                mTimer.stop();
        }
    }

    class KeyboardControl implements KeyListener //Kalıtım alınacak interface olduğundan implements
    {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                if(SB.mMove != MOVE.RIGHT)
                    SB.mMove = MOVE.LEFT;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                if(SB.mMove != MOVE.LEFT)
                    SB.mMove = MOVE.RIGHT;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP)
            {
                if(SB.mMove != MOVE.DOWN)
                    SB.mMove = MOVE.UP;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                if(SB.mMove != MOVE.UP)
                    SB.mMove = MOVE.DOWN;
            }        
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
