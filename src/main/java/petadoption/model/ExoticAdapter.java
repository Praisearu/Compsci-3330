package petadoption.model;

public class ExoticAdapter extends Pet {
	private ExoticAnimal exoticAnimal;

	public ExoticAdapter(ExoticAnimal exoticAnimal) {
		this.exoticAnimal = exoticAnimal;


		this.setId(exoticAnimal.uniqueId);
		this.setName(exoticAnimal.animalName);
		this.setType(Type.ExoticAnimal); 
		this.setSpecies(exoticAnimal.subSpecies);
		this.setAge(exoticAnimal.yearsOld);
		this.setAdopted(false); 
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
