package hiberspring.serializers;

public interface Serializer {

    <T> T deserialize(Class<T> tClass, String fileName);

    <T> void serialize(T object, String fileName);
}
