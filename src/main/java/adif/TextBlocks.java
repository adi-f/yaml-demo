package adif;

import adif.util.YamlDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.stream.Collectors;

public class TextBlocks {

    public static void main(String[] args) {

        List<Text> texts = new YamlDeserializer().readResource("TextBlocks.yaml", new TypeReference<>() {});

        String output = texts.stream()
                .map(Text::getText)
                .map(TextBlocks::markLines)
                .collect(Collectors.joining("\n\n----------\n\n"));
        System.out.println(output);
    }

    private static String markLines(String textBlock) {
        return '>' + textBlock.replaceAll("\n", "<\n>")  +'<';
    }

    public static class Text {
        private final String text;

        public Text(@JsonProperty("text") String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
