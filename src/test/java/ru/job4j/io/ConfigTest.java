package ru.job4j.io;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("var5")).isEqualTo("Address=");
        assertThat(config.value("var1")).isEqualTo("Address=default");
    }

    @Test
    public void whenPairWithCommentsAndEmptyStrokes() {
        String path = "./data/pair_with_comments_And_Empty_Strokes.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("set")).isEqualTo("Set[]");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithPatternViolation() {
        String path = "./data/pair_with_pattern_violations.properties";
        Config config = new Config(path);
        config.load();
    }
}
