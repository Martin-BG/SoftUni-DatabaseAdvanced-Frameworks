package app.retake.parser;

import app.retake.parser.interfaces.Parser;
import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(value = "JSONParser")
public class JSONParser implements Parser {

    private final Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
//                .setDateFormat("dd-MM-yyyy")
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .setPrettyPrinting()
                .create();
    }

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) {
        return this.gson.fromJson(fileContent, objectClass);
    }

    @Override
    public <T> String write(T object) {
        return this.gson.toJson(object);
    }

    private static class DateSerializer implements JsonSerializer<Date> {
        @Override
        public JsonElement serialize(final Date date, final Type typeOfSrc, final JsonSerializationContext context) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            return new JsonPrimitive(sdf.format(date));
        }
    }

    private static class DateDeserializer implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = null;
            try {
                date = sdf.parse(json.getAsJsonPrimitive().getAsString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
    }

    /*      With Lambda:

            .registerTypeAdapter(Date.class, dateJsonSerializer)
            .registerTypeAdapter(Date.class, dateJsonDeserializer)

        JsonSerializer<Date> dateJsonSerializer =
                (json, typeOfT, context) -> json == null ? null : new JsonPrimitive(new SimpleDateFormat("dd-MMM-yyyy").format(json));

        JsonDeserializer<Date> dateJsonDeserializer =
                (json, typeOfT, context) -> {
                    try {
                        return json == null ? null : new SimpleDateFormat("dd-MM-yyyy").parse(json.getAsJsonPrimitive().getAsString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                };*/
}
