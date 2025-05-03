package petadoption.model;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shelter<T extends Pet> {
    private List<T> pets;

    public Shelter() {
        this.pets = new ArrayList<>();
    }

    public void adoptPet(T pet) {
        pet.setAdopted(true);
    }  
    
    public void addPet(T pet) {
        pets.add(pet);
    }

    public List<T> getPets() {
        return pets;
    }

    public void sortByName() {
        pets.sort(Comparator.comparing(Pet::getName));
    }

    public void sortByAge() {
        pets.sort(Comparator.comparingInt(Pet::getAge));
    }

    public void sortBySpecies() {
        pets.sort(Comparator.comparing(Pet::getSpecies));
    }

    public List<T> getAllPets() {
        return pets; 
    }
}
