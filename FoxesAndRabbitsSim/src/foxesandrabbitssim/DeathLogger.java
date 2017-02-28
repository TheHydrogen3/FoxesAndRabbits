
package foxesandrabbitssim;

import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author HaIIvard
 */
public class DeathLogger
{
    private Stack<Animal> deadAnimals;
    
    public DeathLogger()
    {
        deadAnimals = new Stack<>();
    }
    
    public void addDeadAnimal(Animal actor)
    {
        deadAnimals.add(actor);
    }
    
    public void printStats()
    {
        Iterator it = deadAnimals.iterator();
        while(it.hasNext())
        {
            Animal actor = deadAnimals.pop();
            
            String outputString = "";
            outputString = outputString + actor.getClass().toString() + ", " + actor.getCauseOfDeath() + ", " + "";
            System.out.println(outputString);
        }
        
            
    }
}
