package com.jdev.course.utils;

import java.util.UUID;

public class GenerateRegister {

    public static String newRegister() {
        return UUID.randomUUID().toString().substring(0,10);
    }
}
