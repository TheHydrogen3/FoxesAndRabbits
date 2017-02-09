package foxesandrabbitssim;

import java.util.List;
import java.util.Random;

/**
 * A simple model of fire. Fire spreads every day for a random amount of days.
 * The fire consumes all that it touches
 *
 * @author Alexander Eilert Berg
 */
public class Fire extends Actor
{

    //The maximum area the fire can spread to 
    private int burnArea;
    //The maximums size a fire can have.
    private static final int fireSize = 15;

    private Random rand;

    /**
     * Creates fire!
     *
     * @param field
     * @param location
     */
    public Fire(Field field, Location location)
    {
        super(field, location);
        this.rand = new Random(1919);
        this.burnArea = rand.nextInt(this.fireSize);
    }

    /**
     *
     * @param newFire
     */
    @Override
    public void act(List<Actor> newFire)
    {
        if (isAlive())
        {
            
            spread(newFire);
        }
    }

    protected void spread(List<Actor> newFire)
    {
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());

        if (burnArea != 0)
        {
            for (int c = 0; c <= this.burnArea; c++)
            {
                Location loc = free.remove(0);
                Fire spark = new Fire(field, loc);
                newFire.add(spark);
            }
        }

    }
}
