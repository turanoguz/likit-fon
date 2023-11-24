package com.kogus.comparators;

import com.kogus.events.EventHandler;
import com.kogus.misc.Pair;

import java.lang.reflect.Method;
import java.util.Comparator;

public class EventHandlerPriorityComparator implements Comparator<Pair<Object, Method>> {

    @Override
    public int compare(Pair<Object, Method> o1, Pair<Object, Method> o2) {
        return o1.getSecond().getAnnotation(EventHandler.class).priority()
                .compareTo(o2.getSecond().getAnnotation(EventHandler.class).priority());
    }

}
