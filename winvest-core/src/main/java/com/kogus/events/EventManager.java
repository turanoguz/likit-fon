package com.kogus.events;

import com.kogus.comparators.EventHandlerPriorityComparator;
import com.kogus.exceptions.MustBe1ParameterException;
import com.kogus.misc.Pair;
import com.kogus.utils.ReflectionUtils;
import com.kogus.utils.SchedulerUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EventManager {
    final static Map<Class<?>, List<Pair<Object, Method>>> EVENTS = new HashMap<>();

    public static void registerAllListenerClass() {
        SchedulerUtils.runTask(() -> {
            ReflectionUtils.readAllClass().forEach((clazz) -> {
                if (!Arrays.asList(clazz.getInterfaces()).contains(EventListener.class)) return;
                try {
                    Object object = ReflectionUtils.findNoArgumentConstructor(clazz).newInstance((Object[]) null);
                    for (Method m : clazz.getMethods()) {
                        if (m.getAnnotation(EventHandler.class) != null) {
                            if (m.getParameterTypes().length != 1)
                                throw new MustBe1ParameterException("Event Listenerda Sadece 1 parametre bulunabilir.");
                            Class<?> firstParameterClass = m.getParameterTypes()[0];
                            List<Pair<Object, Method>> pairList = EVENTS.get(firstParameterClass);
                            if (pairList == null)
                                pairList = new ArrayList<>() {{
                                    add(new Pair<>(object, m));
                                }};
                            else
                                pairList.add(new Pair<>(object, m));

                            EVENTS.put(firstParameterClass, pairList);
                        }
                    }
                } catch (InstantiationException | IllegalAccessException |
                         InvocationTargetException | MustBe1ParameterException e) {
                    throw new RuntimeException(e);
                }
            });
            EVENTS.forEach((k, v) -> v.sort(new EventHandlerPriorityComparator()));
        });
    }

    public static void dispatchEvent(Class<?> type, Object o) {
        SchedulerUtils.runTaskAsync(() -> {
            List<Pair<Object, Method>> pairList = EVENTS.get(type);
            if (pairList == null) return;
            pairList.forEach((pair) -> {
                try {
                    pair.getSecond().invoke(pair.getFirst(), o);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }
}
