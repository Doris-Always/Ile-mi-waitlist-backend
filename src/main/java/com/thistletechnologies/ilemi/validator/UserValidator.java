package com.thistletechnologies.ilemi.validator;

public class UserValidator {
    public static boolean isValidEmail(String email){
        return email.contains("@");
    }

    public static boolean isValidPhoneNumber(String phoneNumber){
        for (int i = 0; i < phoneNumber.length(); i++) {
            if(!Character.isDigit(phoneNumber.charAt(i))) throw new RuntimeException("phone number can only be digits");
        }
        return phoneNumber.length() == 11;
    }
    public static boolean isValidWhatsappNumber(String whatsappNumber){
        for (int i = 0; i < whatsappNumber.length(); i++) {
            if(!Character.isDigit(whatsappNumber.charAt(i))) throw new RuntimeException("phone number can only be digits");
        }
        return whatsappNumber.length() == 11;
    }

}
