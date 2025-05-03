package petadoption.model;

import java.io.FileNotFoundException;
import petadoption.*; 
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PetAdapter {
    private Shelter<Pet> shelter = new Shelter<Pet>();

    public PetAdapter(String filename) throws FileNotFoundException {
        FileReader reader = new FileReader(filename);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Pet.class, new PetDeserializer())
                .create();
        Type petListType = new TypeToken<List<Pet>>() {}.getType();
        List<Pet> petList = gson.fromJson(reader, petListType);

        for (Pet p : petList)
			try {
				{
					shelter.addPet(p);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    public void loadExoticPets(String filename) throws FileNotFoundException {
        FileReader reader = new FileReader(filename);
        Type listType = new TypeToken<List<ExoticAnimal>>() {}.getType();
        List<ExoticAnimal> exoticAnimals = new Gson().fromJson(reader, listType);
        for (ExoticAnimal ea : exoticAnimals)
			try {
				{
					shelter.addPet(new ExoticAdapter(ea));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    public Shelter<Pet> getShelter() {
        return shelter;
    }
}