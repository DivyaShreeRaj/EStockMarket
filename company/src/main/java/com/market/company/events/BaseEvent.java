package com.market.company.events;

public class BaseEvent<T> {

	public final T id;

    public BaseEvent(T id) {
        this.id = id;
    }
    
}
