package petadoption.model;
 

import java.io.FileNotFoundException;



import com.google.gson.Gson;

/**
 * A simple utility class that demonstrates how to load pet data
 * from JSON files using the Gson library.
 */
public class ReadingDataFromJSONusingGsonLibrary {
	
	/**
     * Main method for testing pet data loading using PetAdapter.
     *
     * @param args command-line arguments (not used)
     * @throws FileNotFoundException if any of the JSON files are missing
     */
    public static void main(String[] args) throws FileNotFoundException {
    	// Initialize the adapter and load pets
    	PetAdapter petAdapter = new PetAdapter("src/main/resources/pets.json");
    	// Load additional exotic pets
    	 petAdapter.loadExoticPets("src/main/resources/exotic_animals.json");
    	 
    	 petAdapter.getShelter();
    	 
    	
    }
}

