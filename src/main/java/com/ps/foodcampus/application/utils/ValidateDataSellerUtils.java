package com.ps.foodcampus.application.utils;
import java.util.InputMismatchException;
import org.springframework.stereotype.Component;

@Component
public class ValidateDataSellerUtils extends ValidateDataPersonUtils {

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || !cnpj.matches("\\d+")) {
            return false;
        }

        try {
            int[] weights = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int sum = 0;

            // Calcula o primeiro dígito verificador
            for (int i = 0; i < 12; i++) {
                sum += Character.getNumericValue(cnpj.charAt(i)) * weights[i];
            }
            int mod = sum % 11;
            int firstDigit = (mod < 2) ? 0 : 11 - mod;

            // Calcula o segundo dígito verificador
            sum = 0;
            int[] weightsSecondDigit = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < 13; i++) {
                sum += Character.getNumericValue(cnpj.charAt(i)) * weightsSecondDigit[i];
            }
            mod = sum % 11;
            int secondDigit = (mod < 2) ? 0 : 11 - mod;

            // Verifica se os dígitos calculados são iguais aos fornecidos
            return firstDigit == Character.getNumericValue(cnpj.charAt(12)) &&
                    secondDigit == Character.getNumericValue(cnpj.charAt(13));
        } catch (InputMismatchException e) {
            return false;
        }
    }
}