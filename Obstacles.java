import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
 
public class Obstacles extends JPanel
{
    int x;
    int y;
    int ydirect;
     
    int width;
    int height;
     
     
    Color brown;
    Color black;
    Color yellow;
    Color red;
	
	boolean visible;
	private BufferedImage treeImg;
	private BufferedImage pollImg;
	private BufferedImage coralImg;
    private BufferedImage cactusImg;
	
	
    public Obstacles(int x, int y) 
    {
         
        this.x = x;
        this.y = y;
        ydirect = 1;
         
        this.width = 20;
        this.height = 200;
         
 
        this.brown = new Color(102,51,00);


        //TOWER
        try
        {
            treeImg = ImageIO.read(new File("Level1Ob.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //POLL 
        try
        {
            pollImg = ImageIO.read(new File("Level2Ob.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Coral
        try
        {
            coralImg = ImageIO.read(new File("Level3Ob.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //CACTUS
        try
        {
            cactusImg = ImageIO.read(new File("BossLevelOb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }




		visible = true;
         
    }


    public void obstacleSound()
    {
        try
         {
            URL url = this.getClass().getClassLoader().getResource("ObHit.wav");
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
			g.drawImage(treeImg,x,y,null);
		}
         
    }




    public void drawMe2(Graphics g)
    {
    	if(visible)
    	{
    		g.drawImage(pollImg,x,y,null);
    	}
    }


    public void drawMe3(Graphics g)
    {
    	if( visible )
    	{
    		g.drawImage(coralImg,x,y,null);
    	}
    }




    public void drawMe4(Graphics g)
    {
        if( visible )
        {
            g.drawImage(cactusImg,x,y,null);
        }
    }
     
    public void checkCollision(Projectile p)
    {
        if( visible == true && p.getVisible() == true)
		{
			Double pX = p.getX();
			Double pY = p.getY();
			int pWidth = p.getWidth();
			int pHeight = p.getHeight();
			 
			if( pX + pWidth >= x && pX <= x + width 
				&& pY + pHeight >= y && pY <= y + height )
			{
				p.reset();
				this.obstacleSound();
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




