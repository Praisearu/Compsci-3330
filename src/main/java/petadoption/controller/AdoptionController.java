package petadoption.controller;

//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import petadoption.model.Pet;
//import petadoption.model.PetAdapter;
//import petadoption.model.Shelter;
//import petadoption.view.AdoptFrame;
//import petadoption.view.ManagePets;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class AdoptionController {
    private Shelter<Pet> shelter;
    private AdoptFrame view;
    private ManagePets manageView;

    public AdoptionController() throws FileNotFoundException {
        PetAdapter adapter = new PetAdapter("src/main/resources/pets.json");
        adapter.loadExoticPets("src/main/resources/exotic_animals.json");
        this.shelter = adapter.getShelter();
    }

//    public void setView(AdoptFrame view) {
//        this.view = view;
//        view.loadTable(shelter.getPets());  // now we can load the data once view is linked
//    }
//
//    public void sortPets(String option) {
//        System.out.println("Sort selected: " + option);  // debug
//        switch (option) {
//            case "Sort by name":
//                shelter.sortByName();
//                break;
//            case "Sort by age":
//                shelter.sortByAge();
//                break;
//            case "Sort by species":
//                shelter.sortBySpecies();
//                break;
//        }
//        view.loadTable(shelter.getPets());
//    }
//
//    public void adoptPet(Pet pet) {
//        if (!pet.isAdopted()) {
//            shelter.adoptPet(pet);
//            System.out.println("Adoption successful!");
//        } else {
//            System.out.println("This pet is already adopted.");
//        }
//        System.out.println("Pet adopted, table should be refreshed by the UI.");
//    }
//
// // Temporary default... ENOBONG will do later
    public List<Pet> getNonAdoptedPets() {
    	return shelter.getAllPets().stream()
                .filter(p -> !p.isAdopted())
                .collect(Collectors.toList());
    }



    public void setView(AdoptFrame view) {
        this.view = view;
        view.loadTable(shelter.getPets());
    }

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

    public void adoptPet(Pet pet) {
        if (pet != null && !pet.isAdopted()) {
            pet.setAdopted(true);
            shelter.adoptPet(pet);
        }
    }
    
    public List<Pet> getAllPets() {
        return shelter.getAllPets();
    }

    public Pet findPetByIdAndName(String id, String name) {
        return shelter.getAllPets().stream()
            .filter(p -> p.getId().equalsIgnoreCase(id) && p.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    public boolean savePetsToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(shelter.getAllPets(), writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setManageView(ManagePets manageListFrame) {
        this.manageView = manageListFrame;
    }

    public boolean addPet(Pet pet) {
        boolean duplicate = shelter.getAllPets().stream()
            .anyMatch(p -> p.getId().equalsIgnoreCase(pet.getId()));
        if (duplicate) return false;
        shelter.addPet(pet);
        return true;
    }

    public boolean removePetById(String id) {
        return shelter.removePetById(id);
    }

    public Pet getPetById(String id) {
        return shelter.getAllPets().stream()
            .filter(p -> p.getId().equalsIgnoreCase(id))
            .findFirst()
            .orElse(null);
    }
}


//	public void setManageView(ManagePets manageListFrame) {
//		// TODO BY ENOBONG 
//		
//	}
//
//	public List<Pet> getAllPets() {
//    return shelter.getAllPets();
}
}
