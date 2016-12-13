import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Image;
 
public class Boss extends JPanel
{
    int x;
    int y;
    int ydirect;
     
    int width;
    int height;


    int bossHealth;
     
     
    Color green;
    Color black;
    Color yellow;
    Color red;
    
    boolean visible;
    private Image drag;


    public Boss(int x, int y)
    {
         
        this.x = x;
        this.y = y;
        ydirect = 1;
         
        this.width = 179;
        this.height = 172;

        this.green = new Color(0,255,00);


        this.bossHealth = 30;


        //BOSS
        drag = new ImageIcon("Dragon.gif").getImage();


        visible = true;
         
    }

    public void enemySound()
    {
        try
        {
            URL url = this.getClass().getClassLoader().getResource("enemyhit.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        }
         catch (Exception exc)
        {
             exc.printStackTrace(System.out);
        }
    }
    
    public boolean getVisible()
    {
        return visible;
    }
    
 
    public void drawMe(Graphics g)
    {
       if( visible )
        {
            g.drawImage(drag, x, y, null);
        }
    }


    public void checkCollisionBoss(Projectile p)
    {
            double pX = p.getX();
            double pY = p.getY();
            int pWidth = p.getWidth();
            int pHeight = p.getHeight();
            //System.out.println(pX);
            if( pX + pWidth >= x && pX <= x + width 
                && pY + pHeight >= y && pY <= y + height )
            {
                bossHealth --;
                //System.out.println("Collision");
                this.enemySound();


                if (bossHealth == 0)
                {
                    visible = false;
                }
            }


            
    }




    public void move() 
    {
        if(y<=0)
        {
            ydirect = 0;
        }


        if(y>=500)
        {
            ydirect = 1;
        }


        if(ydirect == 1)
        {
            y--;
            //System.out.println(y);
        }
        
        if(ydirect == 0)
        {
            y++;
            //System.out.println(y);
        }
 
    }
    public void animate()
    {
     
        while( true )
        {
            //Wait 
            try{
                Thread.sleep(100); //milliseconds
            } catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
   
            repaint();
        }
 
    }






}








