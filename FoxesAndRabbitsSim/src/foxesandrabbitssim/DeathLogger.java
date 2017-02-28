
package foxesandrabbitssim;

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
}
