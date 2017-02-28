
package foxesandrabbitssim;

import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author HaIIvard
 */
public class DeathLogger
{
    private Stack<Actor> deadAnimals;
    
    public DeathLogger()
    {
        deadAnimals = new Stack<>();
    }
    
    public void addDeadAnimal(Actor actor)
    {
        deadAnimals.add(actor);
    }
    
    public void printStats()
    {
        Iterator it = deadAnimals.iterator();
        while(it.hasNext())
        {
            Actor actor = (Actor) it.next();
            
            String outputString = "";
            outputString = outputString + actor.getClass().toString() + ", " + actor.getCauseOfDeath() + ", " + "";
            System.out.println(outputString);
        }
        
            
    }
}
