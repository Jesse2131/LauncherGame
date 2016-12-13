import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;




public class Screen extends JPanel implements ActionListener, KeyListener
{
    //Projectile
    Projectile p1;
    
    //Enemy
    Enemy[] enemyArray;
    Enemy[] enemyArray2;
    Enemy[] enemyArray3;
    
    //BOSS
    Boss[] bossArray;

    //Obstacles
    Obstacles[] obstaclesArrayL1;
    Obstacles[] obstaclesArrayL2;
    Obstacles[] obstaclesArrayL3;
    Obstacles[] obstaclesArrayL4;
    
    //Button for Launch
    JButton launchButton;

    //YES BUTTON(ENDGAME SCREEN)
    JButton yesPlayButton;
    //NO BOTTON
    JButton noPlayButton;
    
    //TextField for angle and magnitude
    JTextField setAngle;
    JButton setButton;
    JTextField setMag;
    JButton setMagButton;

    //BACKGROUNDS
    private BufferedImage backgroundL1;
    private BufferedImage backgroundL2;
    private BufferedImage backgroundL3;
    private BufferedImage backgroundBoss;
    private Image endGameBD;

    //LEVEL 1 AND 2 LAUNCHPAD
    private BufferedImage launchPad;

    //LAUNCHPAD FOR LEVEL 3
    private BufferedImage launchPadL3;
    private BufferedImage launchPadL3Pt2;

    //LAUNCHPAD FOR BOSS LEVEL
    private BufferedImage launchPadBoss;
    

    //For default angle/mag
    Double userAngle = 0.0;
    Double userMag = 0.0;

    int launchCount=10;
    int level = 1;
    int counter;

    //TIMER
    int timer;
        
    boolean moveball = false;




    public boolean checkEnemyVisible(Enemy e[])
    {
        boolean a = false;
        for(int i=0; i<e.length; i++)
        {
            a = a || e[i].getVisible();
        }
        return a;
    }




    public boolean checkBossVisible(Boss b[])
    {
        boolean a = false;
        for(int i=0; i<b.length; i++)
        {
            a = a || b[i].getVisible();
        }
        return a;
    }




    public void reloadLevel()
    {
        synchronized(this)
        {
            switch(level)
            {
                case 1:

                    add(setMag);
                    add(launchButton);
                    add(setAngle);
                    remove(yesPlayButton);
                    remove(noPlayButton);

                    //ENEMY
                    enemyArray = new Enemy[3];
                    for(int i=0; i<enemyArray.length; i++)
                    {
                        int x = 580+i*30;
                        int ranY = (int)(Math.random()*301+100 ) ;
                        enemyArray[i] = new Enemy(x,ranY);
                    }

                    //OBSTACLE
                    obstaclesArrayL1 = new Obstacles[1];
                    for(int k=0; k<obstaclesArrayL1.length; k++)
                    {
                        obstaclesArrayL1[k] = new Obstacles(300,250);
                    }
                    break;
                case 2: 

                    add(setMag);
                    add(launchButton);
                    add(setAngle);
                    remove(yesPlayButton);

                    //LEVEL 2 ENEMY                  
                    enemyArray2 = new Enemy[4];
                    for(int i=0; i<enemyArray2.length; i++)
                    {
                        int x = 580 + i*30;
                        int ranY = (int)(Math.random()*301+100 ) ;
                        enemyArray2[i] = new Enemy(x,ranY);
                    }
                    //LEVEL 2 OBSTACLES
                    obstaclesArrayL2 = new Obstacles[2];
                    for(int i=0; i<obstaclesArrayL2.length; i++)
                    {
                        int x = 210+i*180;
                        int y = 150;
                        obstaclesArrayL2[i] = new Obstacles(x,y);
                    }
                    break;
                case 3: 
                    add(setMag);
                    add(launchButton);
                    add(setAngle);
                    remove(yesPlayButton);
                    remove(noPlayButton);
                    //ENEMY                   
                    enemyArray3 = new Enemy[5];
                    for(int i=0; i<enemyArray3.length; i++)
                    {
                        int x = 550+i*30;
                        int ranY = (int)(Math.random()*301+100 ) ;
                        enemyArray3[i] = new Enemy(x,ranY);
                    }
                    //OBSTACLES
                    obstaclesArrayL3 = new Obstacles[2];
                    for(int i=0; i<obstaclesArrayL3.length; i++)
                    {
                        int x = 230+i*150;
                        int y = 220;
                        obstaclesArrayL3[i] = new Obstacles(x,y);
                    }
                    break;
                case 4: 
                    add(setMag);
                    add(launchButton);
                    add(setAngle);
                    remove(yesPlayButton);
                    remove(noPlayButton);
                    //BOSS LEVEL
                    bossArray = new Boss[1];
                    for(int i=0; i<bossArray.length; i++)
                    {
                        int x = 600;
                        int y = 100;
                        bossArray[i] = new Boss(x,y);
                    }


                    //OBSTACLES
                    obstaclesArrayL4 = new Obstacles[2];
                    for(int i=0; i<obstaclesArrayL4.length; i++)
                    {
                        int x = 230+i*150;
                        int y = 290;
                        obstaclesArrayL4[i] = new Obstacles(x,y);
                    }
                    break;    
                case 5: //ENDGAME SCREEN

                    remove(launchButton);
                    remove(setAngle);
                    remove(setMag);
                    add(yesPlayButton);
                    add(noPlayButton);

                    break;

                        
            }

        }   
    }




    public Screen()
    {  

        setLayout(null);


        //END GAME SCREEN BUTTONS/BACKGROUND
        
        //YES BUTTON
        yesPlayButton = new JButton("YES!");
        yesPlayButton.setBounds(230,300,70,60);
        add(yesPlayButton);
        yesPlayButton.addActionListener(this);

        //NO BUTTON
        noPlayButton = new JButton("Nope");
        noPlayButton.setBounds(500,300,70,60);
        add(noPlayButton);
        noPlayButton.addActionListener(this);

        //BUTTONS AND TEXTFIELDS

        //LAUNCH BUTTON
        launchButton = new JButton("Launch");
        launchButton.setBounds(45,130,77,20);
        add(launchButton);
        launchButton.addActionListener(this);

        //ANGLE TEXTFIELD
        setAngle = new JTextField(20);
        setAngle.setBounds(5,70,60,25);
        add( setAngle );

        //MAG TEXTFIELD
        setMag = new JTextField(20);
        setMag.setBounds(103,70,60,25);
        add( setMag );
        
        timer = 5000;

        //ENDGAME BACKGROUND
        endGameBD = new ImageIcon("Endgame.gif").getImage();  

        p1 = new Projectile(59, 469);
        p1.setVelocity(userAngle,userMag);

        reloadLevel();
        addKeyListener(this);
        setFocusable(true);   
    }




    public Dimension getPreferredSize() 
    {
        //Sets the size of the panel
        return new Dimension(800,600);
    }
    //LAUNCHPAD SOUDNS
    public void cannonsound()
    {
        try
        {
            URL url = this.getClass().getClassLoader().getResource("cannon.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }




    public void torpedosound()
    {
        try
        {
            URL url = this.getClass().getClassLoader().getResource("Torpedo.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }




    public void tanksound()
    {
        try
        {
            URL url = this.getClass().getClassLoader().getResource("Tanksound.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }




    public void paintComponent(Graphics g)
    {        
        super.paintComponent(g);
        
        if(level == 1) //LEVEL 1 BACKGROUND
        {
            //System.out.println("==========="+level);
            try
            {
                backgroundL1 = ImageIO.read(new File("Level1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }   
        else if(level == 2)//LEVEL 2 BACKGROUND
        {
            //System.out.println("==========="+level);
            try
            {
                backgroundL2 = ImageIO.read(new File("Level2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        }          
        else if(level == 3) //LEVEL 3 BACKGROUND
        {
            try
            {
                backgroundL3 = ImageIO.read(new File("Level3.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }       
        else if(level == 4) //BOSS BACKGROUND
        {
            try
            {
                backgroundBoss = ImageIO.read(new File("BossBackground.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        try  //LAUNCH PAD(LEVELS 1 AND 2)
        {
            launchPad = ImageIO.read(new File("LaunchPad.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }




        try  //LAUNCHPAD(LEVEL 3)
        {
            launchPadL3 = ImageIO.read(new File("LaunchPadL3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }




        try
        {
            launchPadL3Pt2 = ImageIO.read(new File("launchPadL3Pt2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }




        //LAUNCHPAD(BOSS LEVEL)
        try
        {
            launchPadBoss = ImageIO.read(new File("LaunchPadBoss.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }




        synchronized(this)
        {   
            if(level == 1)//LEVEL 1 OBJECTS
            {
                //BACKGROUND
                g.drawImage(backgroundL1,0,0,null);
                //LAUNCH PAD
                g.drawImage(launchPad,20,470,null);

                //ENEMY
                for(int i=0; i<enemyArray.length; i++)
                {
                    enemyArray[i].drawMe(g);
                }
                //OBSTACLES
                for(int i2=0; i2<obstaclesArrayL1.length; i2++)
                {
                    obstaclesArrayL1[i2].drawMe(g);
                }

                //TIMER
                timer--;
                if(timer == 0)
                {
                    level = 1;
                    launchCount = 10;
                    timer = 5000;
                }

                Font font = new Font("Impact", Font.BOLD, 17);
                g.setFont(font);
                g.setColor(Color.black);
                g.drawString("TIME LEFT: " + timer, 670, 20);
            }
            else if(level == 2) //LEVEL 2 OBJECTS
            {               
                //BACKGROUND 
                g.drawImage(backgroundL2,0,0,null);
                //LAUNCH PAD
                g.drawImage(launchPad,20,470,null);
          
                //ENEMY
                for(int i=0; i<enemyArray2.length; i++)
                {
                    enemyArray2[i].drawMe(g);
                }
     
                //OBSTACLES
                for(int i2=0; i2<obstaclesArrayL2.length; i2++)
                {
                    obstaclesArrayL2[i2].drawMe2(g);
                }

                //TIMER
                timer--;
                if(timer == 0)
                {
                    level = 1;
                    launchCount = 10;
                    timer = 5000;
                }
                
                Font font = new Font("Impact", Font.BOLD, 17);
                g.setFont(font);
                g.setColor(Color.black);
                g.drawString("TIME LEFT: " + timer, 670, 20);
            }   
            else if(level == 3)  //LEVEL 3 OBJECTS
            {
                //BACKGROUND
                g.drawImage(backgroundL3,0,0,null);
                //LAUNCH PAD
                g.drawImage(launchPadL3,-60,480,null);
                g.drawImage(launchPadL3Pt2,43,473,null);

                //ENEMY
                for(int i=0; i<enemyArray3.length; i++)
                {
                    enemyArray3[i].drawMe3(g);
                }


                //OBSTACLES
                for(int i2=0; i2<obstaclesArrayL3.length; i2++)
                {
                    obstaclesArrayL3[i2].drawMe3(g);
                }

                //TIMER
                timer--;
                if(timer == 0)
                {
                    level = 1;
                    launchCount = 10;
                    timer = 5000;
                }
                
                Font font = new Font("Impact", Font.BOLD, 17);
                g.setFont(font);
                g.setColor(Color.black);
                g.drawString("TIME LEFT: " + timer, 670, 20);
            }   
            else if(level == 4) //BOSS LEVEL OBJECTS
            {
                //BACKGROUND
                g.drawImage(backgroundBoss,0,0,null);
                //LAUNCHPAD
                g.drawImage(launchPadBoss,-70,470,null);

                //ENEMY
                for(int i=0; i<bossArray.length; i++)
                {
                    bossArray[i].drawMe(g);
                }

                //OBSTACLES
                for(int i2=0; i2<obstaclesArrayL4.length; i2++)
                {
                    obstaclesArrayL4[i2].drawMe4(g);
                }

                //TIMER
                timer--;
                if(timer == 0)
                {
                    level = 1;
                    launchCount = 10;
                    timer = 5000;
                }
                
                Font font = new Font("Impact", Font.BOLD, 17);
                g.setFont(font);
                g.setColor(Color.black);
                g.drawString("TIME LEFT: " + timer, 670, 20);
            }
            else if(level == 5)
            {
                g.drawImage(endGameBD,0,0,null);

                //BALL TEXT
                Font font = new Font("Impact", Font.BOLD, 80);
                g.setFont(font);
                g.setColor(Color.green);
                g.drawString("PLAY AGAIN?", 200, 250);
            }
        }
            




        if ( (level >= 1 ) &&  ( level <= 4 ) )
        {
            //Draw the ball
            p1.drawMe( g );
      
            //BALL TEXT
            Font font = new Font("Arial", Font.PLAIN, 20);
            g.setFont(font);
            g.setColor(Color.red);
            g.drawString("Balls Left: " + launchCount, 0, 20);




            Font font2 = new Font("Arial", Font.PLAIN, 24);
            g.setFont(font2);
            g.setColor(Color.blue);           
            
            if(level == 1 || level == 2 || level == 3)
            {
                g.drawString("Level " + level, 340,30);
            }
            else if(level == 4)
            {
                g.drawString("Boss Level", 340,30);
            }




            //ANGLE/MAG TEXT
            Font font3 = new Font("Arial", Font.PLAIN, 12);
            g.setFont(font3);
            g.setColor(Color.black);
            g.drawString("Set Angle",7,110);
            g.drawString("Set Mag",110,110);
        }   
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(moveball == false)
        {

        
            if(level >=1 && level <= 4)
            {
                    if( e.getActionCommand().equals("Launch") )
                    {  
                        launchCount--;

                        if(level == 1 || level == 2)
                        {
                            this.cannonsound();
                        }
                        else if(level == 3)
                        {
                            this.torpedosound();
                        }
                        else if(level == 4)
                        {
                            this.tanksound();
                        }


                    }

                    if(launchCount<=0)
                    {
                        //moveball = false;
                        level = 1;
                        launchCount = 10;
                        timer = 5000;
                    }
                    else
                    {
                        moveball = true;
                    }

                    //System.out.println(launchCount);




                    String getInput = setAngle.getText();
                    Double getInput2 = Double.parseDouble(getInput);




                    this.userAngle = getInput2;
                    p1.setVelocity(userAngle,userMag);




                    String getMagInput = setMag.getText();
                    Double getMagInput2 = Double.parseDouble(getMagInput);




                    this.userMag = getMagInput2;
                    p1.setVelocity(userAngle,userMag);
            }
        }
            
        








        if(e.getActionCommand().equals("YES!") )
        {
            level = 1;
            reloadLevel();
            launchCount = 10;
            timer = 5000;
            p1.reset();
        }

        if(e.getActionCommand().equals("Nope") )
        {
            System.exit(0);
        }
        
        
        requestFocus();
       
    }
     
    public void animate()
    {
        
        while( true )
        {
            //wait for .1 second
            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }




            synchronized(this)
            {
                
                
                if(moveball == true)
                {
                    p1.move();
                    //System.out.println("move");
                }




                if(p1.stopMove == true)
                {
                    moveball = false;
                    p1.stopMove = false;
                }                
                
                //LEVEL ONE ITEMS
                if(level == 1)
                {                      
                    //ENEMY
                    for(int k=0; k<enemyArray.length; k++)
                    {
                        enemyArray[k].checkCollision(p1);
                        //System.out.println(enemyArray[k].visible);
                    }

                    
                    //MOVING OBSTACLES
                    for(int k1=0; k1<obstaclesArrayL1.length; k1++)
                    {
                        obstaclesArrayL1[k1].move();
                    }
                    
                    //Level 1 obstacles
                    for(int k1=0; k1<obstaclesArrayL1.length; k1++)
                    {
                        obstaclesArrayL1[k1].checkCollision(p1);
                    }
                    
                    




                    boolean temp =  this.checkEnemyVisible(enemyArray);
                    //System.out.println("temp === " + temp);
                    if(temp == false)
                    {
                        level = 2;
                        reloadLevel();
                        p1.reset();
                        launchCount = 10;
                        timer = 5000;
                    }                        
                }
                else if(level == 2) 
                {    
                    //LEVEL2 ENEMY
                    for(int k2=0; k2<enemyArray2.length; k2++)
                    {
                        enemyArray2[k2].checkCollision(p1);
                    }

                    
                    //MOVING OBSTACLES
                    for(int k3=0; k3<obstaclesArrayL2.length; k3++)
                    {
                        obstaclesArrayL2[k3].move();    
                    }
                    
                    //LEVEL2 OBSTACLES
                    for(int k3=0; k3<obstaclesArrayL2.length; k3++)
                    {
                        obstaclesArrayL2[k3].checkCollisionL2(p1);    
                        //System.out.println(enemyArray[k].visible);
                    }




                    boolean temp =  this.checkEnemyVisible(enemyArray2);
                    //System.out.println("temp === " + temp);
                    if(temp == false)
                    {
                        level = 3;
                        reloadLevel();
                        p1.reset();
                        launchCount = 10;
                        timer = 5000;
                    }
                }                
                else if(level == 3) //LEVEL THREE ITEMS
                {
                    //System.out.println("======" +obstaclesArrayL3.length);
                    //ENEMY
                    for(int k2=0; k2<enemyArray3.length; k2++)
                    {
                        enemyArray3[k2].checkCollisionL3(p1);
                    }

                    
                    //MOVING OBSTACLES
                    for(int k3=0; k3<obstaclesArrayL3.length; k3++)
                    {
                        obstaclesArrayL3[k3].move();    


                    }
                    
                    //OBSTACLES
                    for(int k3=0; k3<obstaclesArrayL3.length; k3++)
                    {
                        obstaclesArrayL3[k3].checkCollisionL3(p1);    
                        //System.out.println("-----"+obstaclesArrayL3.length);
                    }




                    boolean temp =  this.checkEnemyVisible(enemyArray3);
                    if(temp == false)
                    {
                        level = 4;
                        reloadLevel();
                        p1.reset();
                        launchCount = 10;
                        timer = 5000;
                    }
                }
                else if(level == 4)
                {
                    /*
                    //MOVING BOSS
                    for(int k = 0; k<bossArray.length; k++)
                    {
                        bossArray[k].move();
                    }
                    */
                    //ENEMY
                    for(int k2=0; k2<bossArray.length; k2++)
                    {
                        bossArray[k2].checkCollisionBoss(p1);
                    }

                    /*
                    //MOVING OBSTACLES
                    for(int k3=0; k3<obstaclesArrayL4.length; k3++)
                    {
                        obstaclesArrayL4[k3].move();    
                    } 
                    

                    //OBSTACLES
                    for(int k3=0; k3<obstaclesArrayL4.length; k3++)
                    {
                        obstaclesArrayL4[k3].checkCollision(p1);    
                        //System.out.println(enemyArray[k].visible);
                    } 
                    */



                    boolean temp =  this.checkBossVisible(bossArray);
                    if(temp == false)
                    {
                        level = 5;
                        reloadLevel();
                        p1.reset();
                        launchCount = 10;
                        timer = 5000;
                    }  
                }  

            }
                        
            //reloadLevel();
            repaint();
        }
    }




    public void keyPressed(KeyEvent e)
    {
        synchronized(this)
        {
            if( e.getKeyCode() == 80) //"p"
            {
                if( (level >= 1) && ( level <= 4 ))
                {
                    level++;
                    //System.out.println("====reload======"+level);
                    launchCount = 10;
                    reloadLevel();
                    timer = 5000;
                }
                else if( level > 4  )
                {
                    level=1;
                    reloadLevel();
                }          
            }       
        }
        
    }
    
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
}






