package foxesandrabbitssim;

import java.util.Iterator;
import java.util.Stack;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HaIIvard & Alexander
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
        while (it.hasNext())
        {
            Animal animal = deadAnimals.pop();

            String outputString = "";
            if (animal instanceof Rabbit)
            {
                Rabbit rabbit = (Rabbit) animal;
                outputString = outputString + "rabbit" + ", " + rabbit.getAge() + ", " + rabbit.getCauseOfDeath() + ", ";
            } else if (animal instanceof Fox)
            {
                Fox fox = (Fox) animal;
                outputString = outputString + "Fox" + ", " + fox.getAge() + ", " + animal.getCauseOfDeath() + ", " + "";
            }
            System.out.println(outputString);
        }
    }

    /**
     * Takes the information in the logger and exports it to a CSV file
     */
    public void createCSV() throws IOException
    {
        String csvFile = "/Users/Public/Documents/DeathLogger.csv";
        FileWriter writer = new FileWriter(csvFile);
        Iterator it = deadAnimals.iterator();
        while (it.hasNext())
        {
            Animal animal = deadAnimals.pop();

            List<String> list = new ArrayList<>();
            if (animal instanceof Rabbit)
            {               
                Rabbit rabbit = (Rabbit) animal;

                list.add(rabbit.getRace());
                list.add(rabbit.getCauseOfDeath());
            } else if (animal instanceof Fox)
            {
                Fox fox = (Fox) animal;

                list.add(fox.getRace());
                list.add(fox.getCauseOfDeath());
            }
            CSVUtils.writeLine(writer, list);
        }
        writer.flush();
        writer.close();
    }
}
