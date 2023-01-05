package adif.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

public class YamlUtil {
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory())
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

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

    public String writeString(Object dto) {
        try {
            return mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String normalize(String path) {

        try(InputStream is = readFromClasspath(path)) {
            Yaml yaml = new Yaml();
            Object structure = yaml.load(is);
            return writeString(structure);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T readResourceDirectWithSnakeYaml(String path) {
        try (InputStream is = readFromClasspath(path)) {
            return new Yaml().load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream readFromClasspath(String path) {
        return this.getClass().getClassLoader().getResourceAsStream(path);
    }
}
