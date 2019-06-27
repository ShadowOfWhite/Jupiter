package com.example.latte_core.delegates.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * 作者：贪欢
 * 时间：2019/6/27
 * 描述：
 */
public class EventManager {

    private static final HashMap<String,Event> EVENTS = new HashMap<>();
    private EventManager(){
    }

    private static class Holder{
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance(){
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event){
        EVENTS.put(name,event);
        return this;
    }

    public Event createEvent(@NonNull String action){
        Event event = EVENTS.get(action);
        if (event == null){
            return new UndefindEvent();
        }
        return event;
    }


}
