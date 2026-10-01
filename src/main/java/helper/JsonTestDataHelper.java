package helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/** Loads JSON rows for TestNG data providers. */
public class JsonTestDataHelper {
    private static JsonTestDataHelper instance;
    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    private JsonTestDataHelper() {
    }

    public static JsonTestDataHelper getInstance() {
        if (instance == null) {
            synchronized (JsonTestDataHelper.class) {
                if (instance == null) {
                    instance = new JsonTestDataHelper();
                }
            }
        }
        return instance;
    }

    public <T> Object[] getTestData(String filePath, Class<T> clazz) throws FileNotFoundException {
        logger.info("Leyendo datos de prueba de {}", filePath);
        try (JsonReader reader = new JsonReader(new FileReader(filePath))) {
            List<T> testData = new Gson().fromJson(
                    reader, TypeToken.getParameterized(ArrayList.class, clazz).getType());
            logger.info("Casos leidos: {}", testData.size());
            return testData.toArray();
        } catch (FileNotFoundException exception) {
            logger.error("No se encontro el archivo de datos {}", filePath);
            throw exception;
        } catch (Exception exception) {
            throw new IllegalArgumentException("No se pudieron leer los datos de " + filePath, exception);
        }
    }
}
