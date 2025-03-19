package co.com.pragma.backend_challenge.plaza.domain.util;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GenerationUtilsTest {
    private static final int PIN_LENGTH = DomainConstants.PIN_LENGTH;
    private static final String CANDIDATES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static final String GENERATED_PIN_SHOULD_NOT_BE_NULL = "Generated PIN should not be null";
    public static final String GENERATED_PIN_SHOULD_HAVE_THE_CORRECT_LENGTH = "Generated PIN should have the correct length";
    public static final String GENERATED_PIN_CONTAINS_INVALID_CHARACTER = "Generated PIN contains invalid character: ";
    public static final String GENERATED_PINS_SHOULD_NOT_ALL_BE_IDENTICAL = "Generated PINs should not all be identical";

    @Test
    void shouldGeneratePinOfCorrectLength() {
        String pin = GenerationUtils.generateRandomSecurityPin();
        assertNotNull(pin, GENERATED_PIN_SHOULD_NOT_BE_NULL);
        assertEquals(PIN_LENGTH, pin.length(), GENERATED_PIN_SHOULD_HAVE_THE_CORRECT_LENGTH);
    }

    @Test
    void shouldOnlyContainValidCharacters() {
        String pin = GenerationUtils.generateRandomSecurityPin();
        for (char c : pin.toCharArray()) {
            assertTrue(CANDIDATES.indexOf(c) >= 0, GENERATED_PIN_CONTAINS_INVALID_CHARACTER + c);
        }
    }

    @RepeatedTest(5)
    void shouldGenerateDifferentPins() {
        Set<String> generatedPins = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            generatedPins.add(GenerationUtils.generateRandomSecurityPin());
        }
        assertTrue(generatedPins.size() > 1, GENERATED_PINS_SHOULD_NOT_ALL_BE_IDENTICAL);
    }
}