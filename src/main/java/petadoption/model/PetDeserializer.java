package petadoption.model;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * PetDeserializer is a custom deserializer for the Pet class hierarchy.
 * It uses the "type" field in the JSON to determine which subclass to instantiate.
 */
public class PetDeserializer implements JsonDeserializer<Pet> {

    /**
     * Deserializes a JSON element into the appropriate subclass of Pet
     * based on the "type" field in the JSON.
     *
     * @param json the JSON data to deserialize
     * @param typeOfT the type of the object to deserialize into
     * @param context the deserialization context
     * @return an instance of a Pet subclass (Dog, Cat, Rabbit, or ExoticAdapter)
     * @throws JsonParseException if the type is unknown or malformed
     */
    @Override
    public Pet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String petType = obj.get("type").getAsString();

        switch (petType) {
            case "Dog":
                return context.deserialize(json, Dog.class);
            case "Cat":
                return context.deserialize(json, Cat.class);
            case "Rabbit":
                return context.deserialize(json, Rabbit.class);
            case "ExoticAnimal":
                ExoticAnimal ea = context.deserialize(json, ExoticAnimal.class);
                return new ExoticAdapter(ea);
            default:
                throw new JsonParseException("Unknown pet type: " + petType);
        }
    }
}
