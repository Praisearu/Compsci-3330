package petadoption.model;

/**
 * ExoticAdapter adapts an ExoticAnimal object to the Pet interface
 * so that exotic animals can be treated like standard pets within the system.
 */
public class ExoticAdapter extends Pet {
	private final ExoticAnimal exoticAnimal;

	
	/**
     * Constructs an adapter for an exotic animal, mapping its data
     * to the standard Pet fields.
     *
     * @param exoticAnimal the exotic animal to wrap
     */
	public ExoticAdapter(ExoticAnimal exoticAnimal) {
		this.exoticAnimal = exoticAnimal;


		this.setId(exoticAnimal.uniqueId);
		this.setName(exoticAnimal.animalName);
		this.setType(Type.ExoticAnimal); 
		this.setSpecies(exoticAnimal.subSpecies);
		this.setAge(exoticAnimal.yearsOld);
		this.setAdopted(false); 
	}

	/**
     * Displays the adapted exotic animal's details.
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
