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
import java.awt.geom.Ellipse2D;
import javax.swing.JLabel;

/**
 *
 * @author mirac
 */
public class Forage extends JLabel{
    public int mGenislik = 20;
    
    Forage()
    {
        setPosition(20, 20);
    }
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g; //Java eski versiyonlarını desteklemek için
        
        Ellipse2D ellipce = new Ellipse2D.Double(0,0,mGenislik-2,mGenislik-2);
        
        g2.setColor(Color.red);
        
        g2.setStroke(new BasicStroke(2));
        
        g2.draw(ellipce);
        
        g2.fill(ellipce);
    }
    
    public void setPosition(int PosX, int PosY)
    {
        setBounds(PosX,PosY,mGenislik,mGenislik);
    }
}
