package D02.provider;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.Provide;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;
import org.util.AoC2020.D02.PasswordPolicy;

import java.util.Collections;
import java.util.Set;

public class PasswordPolicyArbitraryProvider implements ArbitraryProvider {
    @Override
    public boolean canProvideFor(TypeUsage typeUsage) {
        return typeUsage.isOfType(PasswordPolicy.class);
    }

    @Provide
    public Arbitrary<PasswordPolicy> PasswordPolicy() {
        return getPasswordPolicyArbitrary();
    }

    @Override
    public Set<Arbitrary<?>> provideFor(TypeUsage typeUsage, SubtypeProvider subtypeProvider) {
        return Collections.singleton(getPasswordPolicyArbitrary());
    }

    private Arbitrary<PasswordPolicy> getPasswordPolicyArbitrary() {
        Arbitrary<Integer> min = Arbitraries.integers().between(1, 9);
        Arbitrary<Integer> max = Arbitraries.integers().between(1, 9);
        Arbitrary<Character> letter = Arbitraries.chars().range('a', 'z');

        return getAsPasswordPolicy(min, max, letter);
    }

    private Arbitrary<PasswordPolicy> getAsPasswordPolicy(Arbitrary<Integer> min, Arbitrary<Integer> max, Arbitrary<Character> letter) {
        return Combinators.combine(min, max, letter).as(PasswordPolicy::new);
    }
}
