import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Set;
import org.reflections.Reflections;


@CustomAnnotation
public class Entity {
    private String name;
    private int id;

    public Entity(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Reflections reflections = new Reflections();
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(CustomAnnotation.class);
        for(Class<?> clazz : set) {
            Properties properties = new Properties();
            try (FileReader fileReader = new FileReader(clazz.getAnnotation(CustomAnnotation.class).source())){
                properties.load(fileReader);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clazz.getConstructor(String.class, int.class).newInstance(properties.getProperty("name", null), Integer.parseInt(properties.getProperty("id", "0")));
        }

    }
}
