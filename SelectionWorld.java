import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectionWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionWorld extends World
{

    /**
     * Constructor for objects of class SelectionWorld.
     * 
     */
    public SelectionWorld()
    {    
        super(1000, 800, 1,false);
        this.setBackground("MenuBG.jpg");
        prepare();
    }
    private void prepare()
    {
    Player1 player = new Player1();  
    Player2 player2 = new Player2();
    Player3 player3 = new Player3();
    Selecteer selecteer = new Selecteer();
    Stats1 stats = new Stats1();
    Stats2 stats2 = new Stats2();
    Stats3 stats3 = new Stats3();
    addObject(stats3, 790, 350);
    addObject(stats2, 720, 470);
    addObject(stats, 511, 515);
    addObject(selecteer, getWidth()/2, 100);
    addObject(player, 250, 250);
    addObject(player2, 500, 250);
    addObject(player3, 750, 250);
    }
}
