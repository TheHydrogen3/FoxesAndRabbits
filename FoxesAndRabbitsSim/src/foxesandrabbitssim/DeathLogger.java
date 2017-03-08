package foxesandrabbitssim;

import java.util.Iterator;
import java.util.Stack;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author HaIIvard & Alexander
 */
public class DeathLogger
{

    private Stack<Animal> deadAnimals;

    private HashMap<Integer, Integer> rabbitPop;
    private HashMap<Integer, Integer> foxPop;
    private HashMap<Integer, Integer> grassPop;
    private Integer stepCount = 1;

    public DeathLogger()
    {
        deadAnimals = new Stack<>();
        rabbitPop = new HashMap<>();
        foxPop = new HashMap<>();
        grassPop = new HashMap<>();
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
     * Adds the current step number as a key and the population number
     * @param pop 
     */
    public void addRabbitPop(int pop)
    {
        rabbitPop.put(this.stepCount, pop);        
    }
    
    /**
     * Adds the current step number as a key and the population number
     * @param pop 
     */
    public void addFoxPop(int pop)
    {
        foxPop.put(this.stepCount, pop);
    }
    
    /**
     * Adds the current step number as a key and the population number
     * @param pop 
     */
    public void addGrassPop(int pop)
    {
        grassPop.put(this.stepCount, pop);
    }
    
    /**
     * Increases the step counter
     */
    public void increaseStep()
    {
        this.stepCount++;
    }
    
    
    /**
     * Takes the animals age and death in the logger and exports it to a CSV file
     */
    public void createDeathCSV() throws IOException
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
                list.add(Integer.toString(rabbit.getAge()));
            }else if (animal instanceof Fox)
            {
                Fox fox = (Fox) animal;

                list.add(fox.getRace());
                list.add(fox.getCauseOfDeath());
                list.add(Integer.toString(fox.getAge()));
            }
            CSVUtils.writeLine(writer, list);
        }
        writer.close();
    }
    /**
     * Takes the rabbit age and death in the logger and exports it to a CSV file
     */
    public void createDeathRabbitsCSV() throws IOException
    {
        String csvFile = "/Users/Public/Documents/DeathLoggerRabbits.csv";
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
                list.add(Integer.toString(rabbit.getAge()));
            }
            CSVUtils.writeLine(writer, list);
        }
        writer.close();
    }
    
    /**
     * Takes the fox age and death in the logger and exports it to a CSV file
     */
    public void createDeathFoxCSV() throws IOException
    {
        String csvFile = "/Users/Public/Documents/DeathLoggerFox.csv";
        FileWriter writer = new FileWriter(csvFile);
        
        Iterator it = deadAnimals.iterator();
        while (it.hasNext())
        {
            Animal animal = deadAnimals.pop();
            List<String> list = new ArrayList<>();

            if (animal instanceof Fox)
            {
                Fox fox = (Fox) animal;

                list.add(fox.getRace());
                list.add(fox.getCauseOfDeath());
                list.add(Integer.toString(fox.getAge()));
            }
            CSVUtils.writeLine(writer, list);
        }
        writer.close();
    }
    
    
    
    
    /**
     * Creates a CSV with the population and step 
     */
    public void createRabbitPopCSV() throws IOException
    {
        String csvFile = "/Users/Public/Documents/RabbitPop.csv";
        FileWriter writer = new FileWriter(csvFile);
        
        Iterator it = rabbitPop.entrySet().iterator();
        while(it.hasNext())
        {
            List<String> list = new ArrayList<>();
            Entry thisEntry = (Entry) it.next();
            
            String key = Integer.toString((int) thisEntry.getKey());
            String pop = Integer.toString((int) thisEntry.getValue());
            
            list.add(key);
            list.add(pop);
                    
            CSVUtils.writeLine(writer, list);            
        }
    }
    
    /**
     * Creates a CSV with the population and step 
     */
    public void createGrassPopCSV() throws IOException
    {
        String csvFile = "/Users/Public/Documents/GrassPop.csv";
        FileWriter writer = new FileWriter(csvFile);
        
        Iterator it = grassPop.entrySet().iterator();
        while(it.hasNext())
        {
            List<String> list = new ArrayList<>();
            Entry thisEntry = (Entry) it.next();
            
            String key = Integer.toString((int) thisEntry.getKey());
            String pop = Integer.toString((int) thisEntry.getValue());
            
            list.add(key);
            list.add(pop);
                    
            CSVUtils.writeLine(writer, list);            
        }
    }
    
    /**
     * Creates a CSV with the population and step 
     */
    public void createFoxPopCSV() throws IOException
    {
        String csvFile = "/Users/Public/Documents/FoxPop.csv";
        FileWriter writer = new FileWriter(csvFile);
        
        Iterator it = foxPop.entrySet().iterator();
        while(it.hasNext())
        {
            List<String> list = new ArrayList<>();
            Entry thisEntry = (Entry) it.next();
            
            String key = Integer.toString((int) thisEntry.getKey());
            String pop = Integer.toString((int) thisEntry.getValue());
            
            list.add(key);
            list.add(pop);
                    
            CSVUtils.writeLine(writer, list);            
        }
    }

    /**
     * Creates all CSVs
     */
    void createCSVs() throws IOException
    {
        createDeathCSV();
        createDeathRabbitsCSV();
        createDeathFoxCSV();
        createFoxPopCSV();
        createGrassPopCSV();
        createRabbitPopCSV();
    }
        
    
}
