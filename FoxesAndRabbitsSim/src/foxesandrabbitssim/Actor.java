/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foxesandrabbitssim;

import java.util.List;

/**
 *
 * @author HaIIvard
 */
public abstract class Actor
{
        private Location location;
           
        
        private String causeOfDeath;
        private boolean alive;
        private Field field;
        private DeathLogger deathLogger;
    
    public Actor(Field field, Location location, DeathLogger deathLogger)
    {
        this.field = field;
        setLocation(location);
        alive = true;
        this.deathLogger = deathLogger;
    }

    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    public abstract void act(List<Actor> newActors);

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }
    
    public String getCauseOfDeath()
    {
        return causeOfDeath;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead(String causeOfDeath, Object object)
    {
        alive = false;
        this.causeOfDeath = causeOfDeath;
        if(object instanceof Animal)
        {
            deathLogger.addDeadAnimal((Animal) object);
        }
        if (location != null)
        {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    protected Location getLocation()
    {
        return location;
    }

    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if (location != null)
        {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }
    
}
