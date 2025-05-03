package petadoption.model;

import com.google.gson.*;


import java.lang.reflect.Type;

public class PetDeserializer implements JsonDeserializer<Pet> {
    @Override
    public Pet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String petType = obj.get("type").getAsString();

        switch (petType) {
            case "Dog": return context.deserialize(json, Dog.class);
            case "Cat": return context.deserialize(json, Cat.class);
            case "Rabbit": return context.deserialize(json, Rabbit.class);
            case "ExoticAnimal":  ExoticAnimal ea = context.deserialize(json, ExoticAnimal.class);
            	return new ExoticAdapter(ea);
            default: throw new JsonParseException("Unknown pet type: " + petType);
        }
    }
}