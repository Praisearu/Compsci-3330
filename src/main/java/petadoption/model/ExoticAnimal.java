package petadoption.model;


public class ExoticAnimal extends Pet {
	

	String uniqueId;
	String animalName;
	String category;
	String subSpecies;
	int yearsOld;
	
	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}


	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}


	/**
	 * @return the animalName
	 */
	public String getAnimalName() {
		return animalName;
	}


	/**
	 * @param animalName the animalName to set
	 */
	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}


	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}


	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}


	/**
	 * @return the subSpecies
	 */
	public String getSubSpecies() {
		return subSpecies;
	}


	/**
	 * @param subSpecies the subSpecies to set
	 */
	public void setSubSpecies(String subSpecies) {
		this.subSpecies = subSpecies;
	}


	/**
	 * @return the yearsOld
	 */
	public int getYearsOld() {
		return yearsOld;
	}


	/**
	 * @param yearsOld the yearsOld to set
	 */
	public void setYearsOld(int yearsOld) {
		this.yearsOld = yearsOld;
	}
	

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
