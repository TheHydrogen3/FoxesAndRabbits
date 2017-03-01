
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
            Animal animal = deadAnimals.pop();
            
            String outputString = "";
            if (animal instanceof Rabbit)
            {
                Rabbit rabbit = (Rabbit) animal;
                outputString = outputString + "rabbit" + ", " + rabbit.getAge() + ", " + rabbit.getCauseOfDeath() + ", ";
            }
            else if (animal instanceof Fox)
            {
                Fox fox = (Fox) animal;
                outputString = outputString + "Fox" + ", " + fox.getAge() + ", " + animal.getCauseOfDeath() + ", " + "";
            }
            System.out.println(outputString);
        }
        
            
    }
}
