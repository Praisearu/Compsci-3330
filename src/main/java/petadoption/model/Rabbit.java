/**
 * 
 */
package petadoption.model;

/**
 * Rabbit is a subclass of Pet representing rabbit-type animals in the shelter.
 * It overrides the show method to print rabbit-specific details.
 */
public class Rabbit extends Pet {
	 /**
     * Displays information about the rabbit.
     */
	@Override 
	public void show() {
			
		
		System.out.println("ID: " + getId());
		System.out.println("Name:" + getName());
		System.out.println("Type: " + getType() );
		System.out.println("Species: " + getSpecies());
		System.out.println("Age: " + getAge() );
		String status = isAdopted() ? "Adopted" : "Not Adopted";
		System.out.println("Adopted: " + status );
		System.out.println();
		System.out.println();
		}
	}


