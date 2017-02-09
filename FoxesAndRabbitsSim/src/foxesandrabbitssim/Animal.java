/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foxesandrabbitssim;

import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public abstract class Animal extends Actor
{

    
    private static final Random rand = Randomizer.getRandom();
    
    private String gender;
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     */
    public Animal(Field field, Location location)
    {
        super(field, location);
        if(rand.nextBoolean())
        {
            gender = "Female";
        } else
        {
            gender = "Male";
        }
    }
    
    
    protected String getGender()
    {
        return gender;
    }

}
