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
            Animal actor = deadAnimals.pop();

            String outputString = "";
            outputString = outputString + actor.getClass().toString() + ", " + actor.getCauseOfDeath() + ", " + "";
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
            Animal actor = deadAnimals.pop();

            List<String> list = new ArrayList<>();
            list.add(actor.getClass());
            list.add(actor.getCauseOfDeath());

            CSVUtils.writeLine(writer, list);
        }
        writer.flush();
        writer.close();
    }
}
