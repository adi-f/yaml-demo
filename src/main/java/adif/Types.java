package adif;

import adif.util.YamlUtil;

import java.util.List;

public class Types {

    public static void main(String[] args) {
        YamlUtil yamlUtil = new YamlUtil();
        List<Object> listJackson = yamlUtil.readResource("Types.yaml", List.class);
        List<Object> listSnakeYaml = yamlUtil.readResourceDirectWithSnakeYaml("Types.yaml");

        System.out.println("Deserialized by Jackson");
        printList(listJackson);

        System.out.println("\nDeserialized by SnakeYAML");
        printList(listSnakeYaml);
    }

    private static void printList(List<Object> list) {
        list.forEach(entry -> System.out.printf(
                " - %s: %s\n",
                entry == null ? "null" : entry.getClass().getSimpleName(),
                entry
        ));
    }
}
