package foxesandrabbitssim;

import java.util.List;
import java.util.Random;

/**
 * A model of grass. Grass spreads every day in all directions depending on the
 * growth rate
 *
 * @author Alexander Eilert Berg
 */
public class Grass extends Actor
{

    private DeathLogger deathLogger;
    // The amount the grass spread each cycle 
    private static final int growthRate = 1;
    private static final int maxGrowthCycle = 50;
    // Time between each growth cycle 
    private int growthCycle;
    // Holds current step
    private int cycleStep;

    /**
     * Creates the grass
     *
     * @param field
     * @param location
     */
    public Grass(Field field, Location location, DeathLogger deathLogger)
    {
        super(field, location, deathLogger);
        Random random = new Random();
        growthCycle = random.nextInt(maxGrowthCycle);
    }

    /**
     * The grass grows
     *
     * @param newGrass
     */
    @Override
    public void act(List<Actor> newGrass)
    {
        if (isAlive())
        {
            grow(newGrass);
        }
    }

    /**
     * The grass checks the area around itself, then grows on all the free
     * spaces as many times as the growth rate allows.
     *
     * @param newGrass
     */
    protected void grow(List<Actor> newGrass)
    {
        cycleStep ++; 
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        if (cycleStep >= growthCycle)
        {
            for (int c = 0; c <= Grass.growthRate; c++)
            {
                if (free != null)
                {
                    Location loc = getField().freeAdjacentLocation(getLocation());
                    if (loc != null)
                    {
                        Grass sprout = new Grass(field, loc, deathLogger);
                        newGrass.add(sprout);
                    }
                }
            }
            cycleStep = 0; 
        }
    }
}
