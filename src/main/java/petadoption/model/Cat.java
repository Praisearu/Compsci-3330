/**
 * 
 */
package petadoption.model;

/**
 * 
 */
public class Cat extends Pet {
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

