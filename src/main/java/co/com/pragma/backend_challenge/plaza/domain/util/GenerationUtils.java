package co.com.pragma.backend_challenge.plaza.domain.util;

import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerationUtils {
    @Generated
    private GenerationUtils() {
        throw new IllegalStateException("Utility Class");
    }

    private static final String CANDIDATES ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String generateRandomSecurityPin(){
        ThreadLocalRandom random = ThreadLocalRandom.current();

        return IntStream.range(0, DomainConstants.PIN_LENGTH)
                .mapToObj(i -> String.valueOf(CANDIDATES.charAt(random.nextInt(CANDIDATES.length()))))
                .collect(Collectors.joining());
    }
}
