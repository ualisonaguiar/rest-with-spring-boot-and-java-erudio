package br.github.serializer;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GenderSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String gender, JsonGenerator gen, SerializerProvider arg2) throws IOException {

        String formattedGender;
        switch (gender.toUpperCase()) {
            case "M":
            case "F":
                formattedGender = gender.toUpperCase();
                break;
            case "MALE":
                formattedGender = "M";
                break;
            case "FEMALE":
                formattedGender = "F";
                break;
            default:
                formattedGender = gender; // fallback
                break;
        }

        gen.writeString(formattedGender);
    }

}
