package petadoption.model;

/**
 * Dog is a subclass of Pet representing a dog in the shelter.
 * It overrides the show method to print its details.
 */
public class Dog extends Pet {

    /**
     * Displays the details of the dog.
     */
    @Override
    public void show() {
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Type: " + getType());
        System.out.println("Species: " + getSpecies());
        System.out.println("Age: " + getAge());
        String status = isAdopted() ? "Adopted" : "Not Adopted";
        System.out.println("Adopted: " + status);
        System.out.println();
    }
}