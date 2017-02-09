package foxesandrabbitssim;

import java.util.List;

/**
 * A model of grass. Grass spreads every day in all directions depending on the growth rate
 *
 * @author Alexander Eilert Berg
 */
public class Grass extends Actor
{
    private static final int growthRate = 4;
    
    /**
     * Creates the grass
     * @param field
     * @param location 
     */
    public Grass(Field field, Location location)
    {
      super(field, location);
    }

    /**
     * The grass grows
     * @param newGrass 
     */
    @Override
    public void act(List<Actor> newGrass)
    {
        if(isAlive())
        {
            grow(newGrass);
        }
    }
    /**
     * The grass checks the area around itself, then grows on all the free spaces as many times as the growth rate allows.
     * @param newGrass 
     */
    protected void grow(List<Actor> newGrass)
    {
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        
        for (int c = 0; c <= Grass.growthRate; c++)
        {
            if (free != null)
            {
                Location loc = free.remove(0);
                Grass sprout = new Grass(field, loc);
                newGrass.add(sprout);
            }
        }
    }
}