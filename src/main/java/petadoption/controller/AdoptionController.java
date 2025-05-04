package petadoption.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import petadoption.model.Pet;
import petadoption.model.PetAdapter;
import petadoption.model.Shelter;
import petadoption.view.AdoptFrame;
import petadoption.view.ManagePets;

/**
 * AdoptionController acts as the intermediary between the UI and the data model.
 * It manages pet loading, sorting, adoption, and file persistence.
 */
public class AdoptionController {
    private Shelter<Pet> shelter;
    private AdoptFrame view;
    private ManagePets manageView;

    /**
     * Constructs the controller and loads pets from JSON files.
     * @throws FileNotFoundException if any required JSON file is not found
     */
    public AdoptionController() throws FileNotFoundException {
        PetAdapter adapter = new PetAdapter("src/main/resources/pets.json");
        adapter.loadExoticPets("src/main/resources/exotic_animals.json");
        this.shelter = adapter.getShelter();
    }

    /**
     * Returns a list of all pets that have not been adopted.
     * @return list of non-adopted pets
     */
    public List<Pet> getNonAdoptedPets() {
    	return shelter.getAllPets().stream()
                .filter(p -> !p.isAdopted())
                .collect(Collectors.toList());
    }


    /**
     * Sets the view for adoptable pets and loads the table.
     * @param view the AdoptFrame view to control
     */
    public void setView(AdoptFrame view) {
        this.view = view;
        view.loadTable(shelter.getPets());
    }

    
    /**
     * Sorts pets based on the selected option and refreshes views.
     * @param option sort type (e.g., "Sort by name", "Sort by age", "Sort by species")
     */
    public void sortPets(String option) {
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
        if (view != null) view.loadTable(shelter.getPets());
        if (manageView != null) manageView.loadTable(shelter.getAllPets());
    }

    /**
     * Marks a pet as adopted and refreshes the views.
     * @param pet the pet to adopt
     */
    public void adoptPet(Pet pet) {
        if (pet != null && !pet.isAdopted()) {
            pet.setAdopted(true);
            shelter.adoptPet(pet);

            
            if (view != null) view.loadTable(getNonAdoptedPets());
            if (manageView != null) manageView.loadTable(getAllPets());
        }
    }
    
    /**
     * Returns a list of all pets currently in the shelter.
     * @return list of all pets
     */
    public List<Pet> getAllPets() {
        return shelter.getAllPets();
    }

    /**
     * Finds a pet in the shelter by both its ID and name.
     * @param id the ID of the pet
     * @param name the name of the pet
     * @return the matching pet or null if not found
     */
    public Pet findPetByIdAndName(String id, String name) {
        return shelter.getAllPets().stream()
            .filter(p -> p.getId().equalsIgnoreCase(id) && p.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    /**
     * Saves the current list of pets to a specified JSON file.
     * @param filePath the path where the file will be saved
     * @return true if saving was successful, false otherwise
     */
    public boolean savePetsToFile(String filePath) {
        try {
            File file = new File(filePath);
            File dir = file.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs();  
            }

            try (FileWriter writer = new FileWriter(file)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(shelter.getAllPets(), writer);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Sets the view for managing pets (ManagePets) and allows table refresh.
     * @param manageListFrame the manage view to set
     */
    public void setManageView(ManagePets manageListFrame) {
        this.manageView = manageListFrame;
    }

    /**
     * Adds a new pet to the shelter if its ID is unique.
     * @param pet the pet to add
     * @return true if the pet was added, false if ID already exists
     */
    public boolean addPet(Pet pet) {
        boolean duplicate = shelter.getAllPets().stream()
            .anyMatch(p -> p.getId().equalsIgnoreCase(pet.getId()));
        if (duplicate) return false;
        shelter.addPet(pet);
        return true;
    }

    /**
     * Removes a pet from the shelter by its ID.
     * @param id the ID of the pet to remove
     * @return true if the pet was removed, false otherwise
     */
    public boolean removePetById(String id) {
        return shelter.removePetById(id);
    }

    /**
     * Retrieves a pet from the shelter by its ID.
     * @param id the ID of the pet
     * @return the pet if found, otherwise null
     */
    public Pet getPetById(String id) {
        return shelter.getAllPets().stream()
            .filter(p -> p.getId().equalsIgnoreCase(id))
            .findFirst()
            .orElse(null);
    }
}

