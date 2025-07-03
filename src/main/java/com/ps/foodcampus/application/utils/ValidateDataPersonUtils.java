package com.ps.foodcampus.application.utils;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class ValidateDataPersonUtils {

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            return false;
        }

        try {
            int[] weightsFirstDigit = {10, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] weightsSecondDigit = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
            int sum = 0;

            // Calcula o primeiro dígito verificador
            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * weightsFirstDigit[i];
            }
            int mod = sum % 11;
            int firstDigit = (mod < 2) ? 0 : 11 - mod;

            // Calcula o segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * weightsSecondDigit[i];
            }
            mod = sum % 11;
            int secondDigit = (mod < 2) ? 0 : 11 - mod;

            // Verifica se os dígitos calculados são iguais aos fornecidos
            return firstDigit == Character.getNumericValue(cpf.charAt(9)) &&
                    secondDigit == Character.getNumericValue(cpf.charAt(10));
        } catch (InputMismatchException e) {
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Expressão regular para validar email
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }
}