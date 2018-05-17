package com.revinate.assertj.json;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static com.revinate.assertj.json.JsonPathAssert.assertThat;

public class JsonPathAssertTest {

    @BeforeClass
    public static void config() {
        TestConfig.setDefaults();
    }

    @Test
    public void jsonPathAsString_shouldReadStringValue() {
        DocumentContext documentContext = JsonPath.parse("{\"value\":\"foo\"}");

        assertThat(documentContext).jsonPathAsString("$.value").isEqualTo("foo");
    }

    @Test
    public void jsonPathAsInteger_shouldReadNumericValue() {
        DocumentContext documentContext = JsonPath.parse("{\"value\":10}");

        assertThat(documentContext).jsonPathAsInteger("$.value").isEqualTo(10);
    }

    @Test
    public void jsonPathAsList_shouldReadArray() {
        DocumentContext documentContext = JsonPath.parse("[1,2,3]");

        assertThat(documentContext).jsonPathAsListOf("$", Integer.class).containsExactly(1, 2, 3);
    }

    @Test
    public void jsonPathAsBigDecimal_shouldReadNumericValue() {
        DocumentContext documentContext = JsonPath.parse("0.3");

        assertThat(documentContext).jsonPathAsBigDecimal("$").isEqualTo(new BigDecimal("0.3"));
    }

    @Test
    public void jsonPathAsBoolean_shouldReadBoolean() {
        DocumentContext documentContext = JsonPath.parse("true");

        assertThat(documentContext).jsonPathAsBoolean("$").isTrue();
    }

    @Test
    public void jsonPath_shouldCheckForExistence() {
        DocumentContext documentContext = JsonPath.parse("{\"value\":1}");

        assertThat(documentContext).jsonPath("$.value").isNotNull();
    }

    @Test
    public void jsonPath_shouldCheckForNull() {
        DocumentContext documentContext = JsonPath.parse("{\"value\":null}");

        assertThat(documentContext).jsonPath("$.value").isNull();
    }
}
