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
 
public class Enemy extends JPanel
{
    int x;
    int y;
    int ydirect;
     
    int width;
    int height;
     
     
    Color green;
    Color black;
    Color yellow;
    Color red;
	
	boolean visible;
	private BufferedImage birdImage;
	private BufferedImage fishImage;
    private Image drag;

    public Enemy(int x, int y)
    {
         
        this.x = x;
        this.y = y;
        ydirect = 1;
         
        this.width = 30;
        this.height = 30;
         
 
        this.green = new Color(0,255,00);

        //ENEMY BIRD LEVEL 1-2
        try
        {
            birdImage = ImageIO.read(new File("Level1Enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }




        //FISH LEVEL 3
        try
        {
            fishImage = ImageIO.read(new File("Level3Enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //BOSS
        drag = new ImageIcon("Dragon.gif").getImage();




		visible = true;
         
    }




    public void enemySound()
    {
        try
         {
            URL url = this.getClass().getClassLoader().getResource("BirdHit.wav");
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
			g.drawImage(birdImage, x, y, null);
		}   
    }




    public void drawMe3(Graphics g)
    {
    	if( visible )
    	{
    		g.drawImage(fishImage,x,y,null);
    	}
    }

    public void drawMe4(Graphics g)
    {
        if( visible )
        {
            g.drawImage(drag, x, y, null);
        }
    }
     
    public void checkCollision(Projectile p)
    {




        if( this.visible == true && p.getVisible() == true)
		{
			
			double pX = p.getX();
			double pY = p.getY();
			int pWidth = p.getWidth();
			int pHeight = p.getHeight();
			//System.out.println(pX);
			if( pX + pWidth >= x && pX <= x + width 
				&& pY + pHeight >= y && pY <= y + height )
			{
				//System.out.println("Collision");
				visible = false;
				this.enemySound();
			}
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








































































































