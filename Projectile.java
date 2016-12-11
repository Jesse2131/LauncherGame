import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Projectile
{
    double x;
    double y;
	double initX;
	double initY;
	double xVel;
	double yVel;
	double gravity;
	double time; 
	
	double angle;
	double magnitude;




	boolean stopMove = false;
 	boolean visible = true;




 	int width;
 	int height;
     
    Color black;
 
 
     
    public Projectile(double x, double y)
    {
        this.x = x;
        this.y = y;
		
		this.initX = x;
		this.initY = y;
		
		this.angle = 0;
		this.magnitude = 0;
		this.time = 0;
		this.xVel = 0;
		this.yVel = 0;
		this.gravity = -1;



		this.width = 15;
		this.height = 15;
         
        black = new Color(0,0,0);
		
    }

    public void reset()
    {
    	time = 0;
    	x = initX;
    	y = initY;
    	stopMove = true;
    }
	
	public void move()
	{
		//x = vXt + x0
		x = xVel * time + initX;
		
		//y = -.5gt^2 - vYt + y0
		y = -0.5 *gravity*Math.pow(time,2) - yVel*time + initY;


	if(y>600 || x>800 || y<-400 || x<0)
		{
			reset();
		}

		//System.out.println(y);
		
		time = time + .1;


	}
	
	public void setVelocity(double angle, double magnitude)
	{
		this.angle = angle;
		this.magnitude = magnitude;
		this.xVel = this.magnitude * Math.cos(Math.toRadians(this.angle) );
		this.yVel = this.magnitude * Math.sin(Math.toRadians(this.angle) );
		




		//System.out.println("new angles"+angle);
	}	
     
 
 
    public void drawMe(Graphics g)
    {
		//DRAW PROJECTILE+LAUNCHER
		g.setColor(black);
        g.fillOval((int) Math.round(x), (int) Math.round(y), width, height);




		//ANGLE+MAG DISPLAY
        Font font = new Font("Arial", Font.PLAIN, 12);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("Angle " + angle, 7, 60);
		g.drawString("Magnitude " + magnitude, 100, 60);
    }
	






	public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public boolean getVisible()
    {
        return visible;
    }
}


