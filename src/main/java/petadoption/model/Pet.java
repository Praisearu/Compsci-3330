package petadoption.model;


/**
 * Abstract class representing a generic Pet.
 * All pets share common properties like ID, name, type, species, age, and adoption status.
 * Subclasses (Dog, Cat, Rabbit, ExoticAnimal) must implement the show() method.
 */
public abstract class Pet {

	String id;
	String name;
	public enum Type {Dog, Cat, Rabbit, ExoticAnimal};
	String species;
	int age;
	boolean adopted;

	private Type type;


	/**
     * Displays pet details — implemented by each subclass.
     */
	abstract void show();

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the species
	 */
	public String getSpecies() {
		return species;
	}


	/**
	 * @param species the species to set
	 */
	public void setSpecies(String species) {
		this.species = species;
	}


	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}


	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}


	/**
	 * @return the adopted
	 */
	public boolean isAdopted() {
		return adopted;
	}


	/**
	 * @param adopted the adopted to set
	 */
	public void setAdopted(boolean adopted) {
		this.adopted = adopted;
	}


	public Type getType() {
		return type;
	}

	public void setType( Type type) {
		this.type= type;
	}




}
