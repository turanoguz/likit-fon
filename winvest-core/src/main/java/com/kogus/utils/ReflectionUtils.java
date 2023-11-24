package com.kogus.utils;

import com.kogus.exceptions.NoFoundNoArgumentConstructor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {
    

    public static void recursiveExtractPackage(String packageName, List<Class<?>> classList) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        reader.lines().forEach(name -> {
            if (name.endsWith((".class")))
                classList.add(getClass(name, packageName));
            else
                recursiveExtractPackage(packageName + "." + name, classList);
        });
    }

    public static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            //handle
        }
        return null;
    }

    public static Constructor<?> findNoArgumentConstructor(Class<?> clazz) {
        Constructor<?> noArgumentConstructor;
        try {
            noArgumentConstructor = clazz.getDeclaredConstructor((Class<?>[]) null);
        } catch (NoSuchMethodException e) {
            throw new NoFoundNoArgumentConstructor(clazz.getSimpleName() + ".class için parametresiz yapıcı metot bulunamadı");
        }
        return noArgumentConstructor;
    }

    public static <FROM, TO> TO cast(FROM from, Class<TO> to) {
        try {
            TO pwm = (TO) findNoArgumentConstructor(to).newInstance();
            List<Field> toFieldList = new ArrayList<>(Arrays.asList(pwm.getClass().getDeclaredFields()));
            Class<?> superClassOfTo = pwm.getClass().getSuperclass();
            if(superClassOfTo != Object.class && superClassOfTo != null)
                toFieldList.addAll(Arrays.asList(superClassOfTo.getDeclaredFields()));
            for (Field toField : toFieldList) {
                for (Field fromField : from.getClass().getDeclaredFields()) {
                    if (!toField.getName().equals(fromField.getName())) continue;
                    toField.trySetAccessible();
                    fromField.trySetAccessible();
                    toField.set(pwm, fromField.get(from));
                    break;
                }
            }
            return pwm;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
