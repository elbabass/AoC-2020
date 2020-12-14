package org.util.AoC2020.D02;

public interface PasswordRule {
    boolean validate(PasswordPolicy passwordPolicy, String password);
}
