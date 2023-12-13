package org.titans.util;

import java.util.UUID;

public class Utils {

    public static String GenerateId(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString().substring(0,5);
        return uuidAsString;
    }
}
