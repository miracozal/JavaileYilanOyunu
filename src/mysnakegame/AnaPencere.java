/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysnakegame;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author mirac
 */
public class AnaPencere extends JFrame { //Pencere gibi davranabilmesi için pencere sınıfından kalıtım alıyor
    
    private int mGenislik = 600;
    private int mYukseklik = 600;
    private static AnaPencere mPencere = null;
    
    private AnaPencere()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Pencere kapatıldığında program kapatılır
        SetDimension(mGenislik, mYukseklik);
        setResizable(false); //Pencere boyutları fare ile değiştirilemez
        
        Snake snake = new Snake();
        add(snake);
    }
    
    public static AnaPencere PencereGetir() //Pencerenin yalnız 1 kez oluşturulması için static fonksiyonumuz
    {
        if(mPencere == null)
            mPencere = new AnaPencere();
        return mPencere;
    }
    
    public void SetDimension(int Genislik, int Yukseklik) //Bilgisayar ekran boyutunu yakalayacak fonksiyon
    {
        Dimension Dim = Toolkit.getDefaultToolkit().getScreenSize(); //Ekran çözünürlüğü almak için
        
        int PosX = 0;
        int PosY = 0;
        
        if(mGenislik+100>Dim.width)
        {
            mGenislik = Dim.width-100;
        }
        if(mYukseklik+100>Dim.height)
        {
            mYukseklik = Dim.height-100;
        }
        
        PosX = (Dim.width-mGenislik)/2;
        PosY = (Dim.height-mYukseklik)/2;
        
        setBounds(PosX,PosY,mGenislik,mYukseklik); //Pencere koordinatları
    }
}
