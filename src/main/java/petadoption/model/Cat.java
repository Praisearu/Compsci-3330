package petadoption.model;

/**
 * Cat is a subclass of Pet representing a cat in the shelter.
 * It overrides the show method to display its details.
 */
public class Cat extends Pet {

    /**
     * Displays the details of the cat.
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
