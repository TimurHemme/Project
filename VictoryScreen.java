import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VictoryScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VictoryScreen extends World
{
    
    /**
     * Constructor for objects of class VictoryScreen.
     * 
     */
    public VictoryScreen()
    {    
        super(1000, 800, 1,false);
        this.setBackground("Schatkist.jpg");
        prepare();
    }
    private void prepare()
    {
    Replay space = new Replay();
        addObject(space, 200, 700);
    }
}
