package adif.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;

public class YamlDeserializer {
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public <T> T readResource(String path, Class<T> type) {
        try (InputStream is = readFromClasspath(path)) {
            return mapper.readValue(is, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T readResource(String path, TypeReference<T> type) {
        try (InputStream is = readFromClasspath(path)) {
            return mapper.readValue(is, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream readFromClasspath(String path) {
        return this.getClass().getClassLoader().getResourceAsStream(path);
    }
}
