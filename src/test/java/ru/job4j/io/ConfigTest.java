package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentsAndEmptyStrokes() {
        String path = "./data/pair_with_comments_And_Empty_Strokes.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("set")).isEqualTo(null);
    }

    @Test
    void whenPairWithPatternViolation() {
        String path = "./data/pair_with_pattern_violations.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("var5")).isEqualTo("Address=");
        assertThat(config.value("var1")).isEqualTo("Address=default");
        assertThat(config.value("name")).isEqualTo(null);
    }
}