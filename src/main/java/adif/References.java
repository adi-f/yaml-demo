package adif;

import adif.util.YamlUtil;

public class References {

    public static void main(String[] args) {
        YamlUtil yamlUtil = new YamlUtil();
        System.out.println(yamlUtil.normalize("References.yaml"));
    }
}
