package adif;

import adif.util.YamlUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.stream.Collectors;

public class TextBlocks {

    public static void main(String[] args) {

        List<Text> texts = new YamlUtil().readResource("TextBlocks.yaml", new TypeReference<>() {});

        String output = texts.stream()
                .map(Text::getText)
                .map(TextBlocks::markLines)
                .collect(Collectors.joining("\n\n----------\n\n"));
        System.out.println(output);
    }

    private static String markLines(String textBlock) {
        return '>' + textBlock.replaceAll("\n", "<\n>")  +'<';
    }

    @Value
    @Builder
    @Jacksonized
    public static class Text {
        String text;

    }
}
