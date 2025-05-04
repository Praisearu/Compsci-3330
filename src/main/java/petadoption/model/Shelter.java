package petadoption.model;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Shelter is a generic class that manages a list of pets.
 * It allows adding, removing, sorting, and adopting pets.
 * 
 * @param <T> any subclass of Pet
 */
public class Shelter<T extends Pet> {
    private List<T> pets;

    
    /**
     * Constructs an empty shelter.
     */
    public Shelter() {
        this.pets = new ArrayList<>();
    }

    
    /**
     * Marks a pet as adopted.
     * 
     * @param pet the pet to adopt
     */
    public void adoptPet(T pet) {
        pet.setAdopted(true);
    }  
    
    
    /**
     * Adds a pet to the shelter.
     * 
     * @param pet the pet to add
     */
    public void addPet(T pet) {
        pets.add(pet);
    }

    /**
     * Gets the list of all pets.
     * 
     * @return list of pets
     */
    public List<T> getPets() {
        return pets;
    }

    /**
     * Returns all pets in the shelter.
     * 
     * @return list of all pets
     */
    public List<T> getAllPets() {
        return pets;
    }

    /**
     * Removes a pet from the shelter based on ID.
     * 
     * @param id the ID of the pet to remove
     * @return true if the pet was removed, false if not found
     */
    public boolean removePetById(String id) {
        return pets.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }

    /**
     * Sorts pets by name alphabetically.
     */
    public void sortByName() {
        pets.sort(Comparator.comparing(Pet::getName));
    }

    /**
     * Sorts pets by age in ascending order.
     */
    public void sortByAge() {
        pets.sort(Comparator.comparingInt(Pet::getAge));
    }

    /**
     * Sorts pets by species alphabetically.
     */
    public void sortBySpecies() {
        pets.sort(Comparator.comparing(Pet::getSpecies));
    }
}