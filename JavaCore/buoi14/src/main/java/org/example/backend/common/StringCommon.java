package org.example.backend.common;

public class StringCommon {
//    public static final String DEPARTMENT_NAME_REGEX = "^[0-9a-zA-z\\s]+$";
    public static final String DEPARTMENT_NAME_REGEX = "[\\p{L}0-9 ]+$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
}
