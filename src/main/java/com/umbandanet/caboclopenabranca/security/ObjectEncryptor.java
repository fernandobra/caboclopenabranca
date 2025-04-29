package com.umbandanet.caboclopenabranca.security;

import com.umbandanet.caboclopenabranca.util.CryptoUtil;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.List;

public class ObjectEncryptor {

    public static <T> T encryptObject(T obj, String key) throws Exception {
        if (obj == null) return null;
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() == String.class) {
                String value = (String) field.get(obj);
                if (value != null) {
                    field.set(obj, CryptoUtil.encrypt(value, key));
                }
            }
        }
        return obj;
    }

    @SneakyThrows
    public static <T> List<T> encryptList(List<T> list, String key) {
        List<T> listReturn = null;
        if (list == null) return null;
        for (T t : list) {
          t= encryptObject(t,key);
        }
        return list;
    }
}
