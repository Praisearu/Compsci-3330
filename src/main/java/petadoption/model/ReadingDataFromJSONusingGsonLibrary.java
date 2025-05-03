package petadoption.model;
 

import java.io.FileNotFoundException;



import com.google.gson.Gson;


public class ReadingDataFromJSONusingGsonLibrary {
    public static void main(String[] args) throws FileNotFoundException {
    	PetAdapter petAdapter = new PetAdapter("src/main/resources/pets.json");
    	 petAdapter.loadExoticPets("src/main/resources/exotic_animals.json");
    	 petAdapter.getShelter();
    	
    }
}

