package petadoption.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PetAdapter {

	private List<Pet> pets;

	public PetAdapter(String filename) throws FileNotFoundException {
		FileReader reader = new FileReader(filename);

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Pet.class, new PetDeserializer())
				.create();

		Type petListType = new TypeToken<List<Pet>>() {}.getType();
		this.pets = gson.fromJson(reader, petListType);
	}
	

	public void loadExoticPets(String filename) throws FileNotFoundException {
	    FileReader reader = new FileReader(filename);
	    Type listType = new TypeToken<List<ExoticAnimal>>() {}.getType();

	    List<ExoticAnimal> exoticAnimals = new Gson().fromJson(reader, listType);
	    for (ExoticAnimal ea : exoticAnimals) {
	        pets.add(new ExoticAdapter(ea));
	    }
	}
	    

	public void showAllPets() {
		for (Pet p : pets) {
			p.show();
		}
		
		
	}
}

