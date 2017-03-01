/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foxesandrabbitssim;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Rabbit extends Animal
{
    // Characteristics shared by all rabbits (class variables).

    // The age at which a rabbit can start to breed.
    private static final int BREEDING_AGE = 130;
    // The age to which a rabbit can live.
    private static final int MAX_AGE = 1095;
    // The likelihood of a rabbit breeding.
    private int timeSinceLastPregnant = 0;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    
    private static final int GRASS_FOOD_VALUE = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // The rabbit's age.
    private int age;
    
    private int foodLevel;
    
    private DeathLogger deathLogger;
    

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location, DeathLogger deathLogger)
    {
        super(field, location, deathLogger);
        this.deathLogger = deathLogger; 
        if (randomAge)
        {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(GRASS_FOOD_VALUE);
        } else
        {
            age = 0;
            foodLevel = GRASS_FOOD_VALUE;
        }
        
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to return newly born rabbits.
     */
    public void act(List<Actor> newRabbits)
    {
        incrementAge();
        incrementTimeSincePregnant();
        incrementHunger();
        if(isAlive()) {
            if(getGender().equals("Female") && timeSinceLastPregnant > 31)
            {
                giveBirth(newRabbits);
            }            
            // Try to move into a free location.
             Location newLocation = findFood();
            if (newLocation == null)
            {
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            Location newLocation2 = getField().freeAdjacentLocation(getLocation());
            if(newLocation2 != null) {
                setLocation(newLocation2);
            }
            else {
                // Overcrowding.
                setDead("Overcrowding", this);
            }
        }
    }

    /**
     * Increase the age.
     * This could result in the rabbit's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead("Old age", this);
        }
    }
    
    private void incrementTimeSincePregnant()
    {
        timeSinceLastPregnant++;        
    }
    
    private void incrementHunger()
    {
        foodLevel--;
        if (foodLevel <= 0)
        {
            setDead("Starvation", this);
        }
    }
    
    /**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newRabbits A list to return newly born rabbits.
     */
    private void giveBirth(List<Actor> newRabbits)
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocationsIgnoreGrass(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Rabbit young = new Rabbit(false, field, loc, deathLogger);
            newRabbits.add(young);
            timeSinceLastPregnant = 0;
        }
    }
    
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext())
        {
            Location where = it.next();
            Object actor = field.getObjectAt(where);
            if (actor instanceof Grass)
            {
                Grass grass = (Grass) actor;
                if (grass.isAlive())
                {
                    grass.setDead("Eaten", grass);
                    foodLevel = GRASS_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed()) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
}
