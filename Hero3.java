
import greenfoot.*;

/**
 *
 * @author R. Springer
 */
public class Hero3 extends Mover {
    private final double gravity;
    private final double acc;
    private final double drag;
    private final double movespeed = 7;
    private boolean facingLeft=false;
    private int frame;
    private String teller;
    private int y = 01;
    private int ypos = 0;
    private int health = 1;
    private boolean hasBlueKey = false;
    private boolean hasYellowKey = false;

    private int totalCoins;

    public Hero3() {
        super();
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
        setImage("p3.png");
    }

    public void checkFall()
    {
        if (onGround())
        {
            Actor below = getOneObjectAtOffset (0, getImage().getHeight()/2, Tile.class);
            setLocation(below.getX(), below.getY());
        }
    }

    @Override
    public void act() {
        handleInput();

        velocityX *= drag;
        velocityY += acc;
        if (velocityY > gravity) {
            velocityY = gravity;
        }
        applyVelocity();
        lookForCoin();
        lookForGem();
        death();
        showHealth();
        gameover();
        hasBlueKey();
        nextLevel();
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        if (enemy != null &&
        getX() > enemy.getX()-enemy.getImage().getWidth()/2 &&
        getX() < enemy.getX()+enemy.getImage().getWidth()/2 &&
        getY() < enemy.getY()){
            removeTouching(Enemy.class);
            totalCoins++;
        }
        else if (isTouching(Enemy.class)) {
            health--;
            respawn();
        }
    }
    public void hasBlueKey(){
        if (isTouching(BlueKey.class)) {
            removeTouching(BlueKey.class);
            hasBlueKey=true;
        } else if (isTouching(YellowKey.class)) {
            removeTouching(YellowKey.class);
            hasYellowKey=true;
        }
    }
    public void nextLevel() {
        if (hasBlueKey = true && isTouching(Deur.class)){
            Greenfoot.setWorld(new VictoryScreen());
            hasBlueKey=false;
        } /*else if (hasYellowKey = true && isTouching(Deur.class)){
            Greenfoot.setWorld(new VictoryScreen());
        }*/
    }
    
    
    public void lookForCoin()
    {
        if(isTouching(SilverCoin.class)) {
            removeTouching(SilverCoin.class);
            totalCoins++;
        } else if(isTouching(GoldCoin.class)) {
            removeTouching(GoldCoin.class);
            totalCoins = totalCoins + 2;
        }
        getWorld().showText("Score: "+ Integer.toString(totalCoins), 60, 75);
    }

    public void showHealth() {
        getWorld().showText(""+ Integer.toString(health), 45, 45);
        getWorld().showText("X:" + Integer.toString(getX()), 47, 95);
        getWorld().showText("Y:" + Integer.toString(getY()), 53, 112);
    }

    public void death() {
        if (isTouching(Water.class)){
            health = health - 1;
            respawn();
        } 
    }

    public void gameover(){
        if (health == 0) {
            Greenfoot.setWorld(new GameOver());
        }
    }

    public void lookForGem(){
        if(isTouching(YellowGem.class)) {
            removeTouching(YellowGem.class);      
        } else if(isTouching(RedGem.class)) {
            removeTouching(RedGem.class);
        } else if(isTouching(GreenGem.class)) {
            removeTouching(GreenGem.class);
        } else if(isTouching(BlueGem.class)){
            removeTouching(BlueGem.class);
        }
    }

    public void animation()
    {
        String lopen = "images/p3_walk/PNG/p3_walk";
        if (y != 12){
            teller = Integer.toString(y);
            y++;
        } else if (y == 12) {
            y = 1;
        }

        setImage(lopen+teller+".png");
    }
    
    public void respawn()
    {
        setLocation(100, 2000);
        return;
    }

    public void handleInput() {
        if (Greenfoot.isKeyDown("w")) {
            velocityY = -15;
        }
        if (Greenfoot.isKeyDown("space") && onGround()) {

            velocityY = -13;
        } else if(isTouching(Ladder.class)) 
        {
            setImage("p3_jump.png");
            if(Greenfoot.isKeyDown("space"))
            {
                velocityY = -movespeed -1;
            } else if(isTouching(RopeAttached.class)) {
                velocityY = -movespeed;
            }
        }
        if (Greenfoot.isKeyDown("left")) {
            velocityX = -movespeed;
            if (velocityY != 0) {
                setImage("p3_jump.png");
                getImage().mirrorHorizontally();
            } else if (velocityX < 0) {
                animation();
                getImage().mirrorHorizontally();
            }
        }
        if (Greenfoot.isKeyDown("right")) {
            velocityX = movespeed;
            if (velocityY != 0) {
                setImage("p3_jump.png");
            } else if (velocityX > 0) {
                animation();
            }
        }
        if (Greenfoot.isKeyDown("down")) {
            if(isTouching(Ladder.class)) 
            {
                setImage("p3_jump.png");

                velocityX = 0;
            }
        } else if (velocityX == 0 && velocityY == 0) {
            setImage("p3_front.png");
        }
    }

    public int getWidth() {
        return getImage().getWidth();
    }

    public int getHeight() {
        return getImage().getHeight();
    }
}
