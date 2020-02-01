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
import java.awt.geom.Rectangle2D;
import javax.swing.*;

/**
 *
 * @author mirac
 */
public class SnakesBox extends JLabel{
    public int mGenislik = 20;
    
    public int mMove = MOVE.RIGHT;
    
    SnakesBox()
    {
        setBounds(100,100,mGenislik,mGenislik);
    }
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g; //Java eski versiyonlarını desteklemek için
        
        Rectangle2D rect = new Rectangle2D.Double(1,1,getWidth()-2,getHeight()-2);
        
        g2.setColor(Color.red);
        
        g2.setStroke(new BasicStroke(2));
        
        g2.draw(rect);
        
        g2.fill(rect);
        
    }
    
    public void GoLeft()
    {
        int PosX = getX();
        int PosY = getY();
                
        PosX-=mGenislik;
        setBounds(PosX, PosY, mGenislik, mGenislik);
    }
    
        public void GoRight()
    {
        int PosX = getX();
        int PosY = getY();
                
        PosX+=mGenislik;
        setBounds(PosX, PosY, mGenislik, mGenislik);
    }
        
    public void GoUp()
    {
        int PosX = getX();
        int PosY = getY();
                
        PosY-=mGenislik;
        setBounds(PosX, PosY, mGenislik, mGenislik);
    }
    
    public void GoDown()
    {
        int PosX = getX();
        int PosY = getY();
                
        PosY+=mGenislik;
        setBounds(PosX, PosY, mGenislik, mGenislik);
    }
    
    public SnakesBox CreateBox()
    {
        SnakesBox SB = new SnakesBox();
        int X = getX();
        int Y = getY();
        
        SB.setBounds(X, Y, mGenislik, mGenislik);
        
        SB.mMove = - mMove;
        SB.Move();
        SB.mMove = mMove;
        
        return SB;
    }
    
    public void Move()
    {
        if(mMove == MOVE.LEFT)
            GoLeft();
        else if(mMove == MOVE.RIGHT)
            GoRight();
        else if(mMove == MOVE.UP)
            GoUp();
        else
            GoDown();                           
    }
}
