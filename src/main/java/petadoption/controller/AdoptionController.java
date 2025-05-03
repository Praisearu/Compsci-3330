package petadoption.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import petadoption.model.Pet;
import petadoption.model.PetAdapter;
import petadoption.model.Shelter;
import petadoption.view.AdoptFrame;
import petadoption.view.ManagePets;

public class AdoptionController {
    private Shelter<Pet> shelter;
    private AdoptFrame view;

    public AdoptionController() throws FileNotFoundException {
        PetAdapter adapter = new PetAdapter("src/main/resources/pets.json");
        adapter.loadExoticPets("src/main/resources/exotic_animals.json");
        this.shelter = adapter.getShelter();
    }

    public void setView(AdoptFrame view) {
        this.view = view;
        view.loadTable(shelter.getPets());  // now we can load the data once view is linked
    }

    public void sortPets(String option) {
        System.out.println("Sort selected: " + option);  // debug
        switch (option) {
            case "Sort by name":
                shelter.sortByName();
                break;
            case "Sort by age":
                shelter.sortByAge();
                break;
            case "Sort by species":
                shelter.sortBySpecies();
                break;
        }
        view.loadTable(shelter.getPets());
    }

    public void adoptPet(Pet pet) {
        if (!pet.isAdopted()) {
            shelter.adoptPet(pet);
            System.out.println("Adoption successful!");
        } else {
            System.out.println("This pet is already adopted.");
        }
        System.out.println("Pet adopted, table should be refreshed by the UI.");
    }

 // Temporary default... ENOBONG will do later
    public List<Pet> getNonAdoptedPets() {
        if (shelter == null) return new ArrayList<>(); // safety fallback
        return shelter.getAllPets().stream()
            .filter(p -> !p.isAdopted())
            .collect(Collectors.toList());
    }



	public void setManageView(ManagePets manageListFrame) {
		// TODO BY ENOBONG 
		
	}

	public List<Pet> getAllPets() {
    return shelter.getAllPets();
}
}
