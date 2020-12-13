package D02.configurator;

import net.jqwik.api.*;
import net.jqwik.api.configurators.*;
import net.jqwik.api.providers.*;
import org.util.AoC2020.D02.PasswordPolicy;

public class ValidPasswordPolicyConfigurator extends ArbitraryConfiguratorBase {
    @Override
    protected boolean acceptTargetType(TypeUsage targetType) {
        return targetType.isAssignableFrom(PasswordPolicy.class);
    }

    public Arbitrary<PasswordPolicy> configure(Arbitrary<PasswordPolicy> arbitrary, ValidPasswordPolicy validPasswordPolicy) {
        return arbitrary.filter((passwordPolicy -> passwordPolicy.getRangeMin() <= passwordPolicy.getRangeMax()));
    }
}
