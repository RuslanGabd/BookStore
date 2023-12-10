package java.com.ruslan.DI.context;

import java.com.ruslan.DI.ObjectFactory;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    @Setter
    private ObjectFactory objectFactory;
    private final Map<Class, Object> objectMap = new ConcurrentHashMap<>();

    public ApplicationContext() {
    }

    public <T> T getObject(Class<T> clazz) {
        if (objectMap.containsKey(clazz)) {
            return (T) objectMap.get(clazz);
        }

        T object = objectFactory.getObject(clazz);
        objectMap.put(clazz, object);
        return object;
    }
}
