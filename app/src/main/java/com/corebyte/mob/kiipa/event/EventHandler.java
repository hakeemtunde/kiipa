package com.corebyte.mob.kiipa.event;

import java.util.List;

public interface EventHandler<T> {

    void create(String... params);

    void update(T model);

    void delete(T model);

    List<T> fetchAll();

    void onEditButtonClicked(T model);

    boolean isValid(String... params);

}