package com.jdev.course.utils;

import java.util.UUID;

public class GenerateRegister {

    public static String newRegister() {
        String codec = UUID.randomUUID().toString().substring(0,10);
        return codec.replace("-", "0");
    }
}