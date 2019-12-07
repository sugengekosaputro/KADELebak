package com.inspektorat.kadelebak.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MemoryCache<T> {
    private Map<String, T> dataMap;

    public MemoryCache() {
        initialize(Collections.emptyList());
    }

    public void initialize(List<T> dataList) {
        dataMap = new HashMap<>();
        if (dataList != null && !dataList.isEmpty()) {
            for (T data : dataList) {
                save(data);
            }
        }
    }

    public abstract void save(T data);

    protected void save(String key, T data) {
        dataMap.put(key, data);
    }

    public List<T> getDataMapAsList() {
        return new ArrayList<>(dataMap.values());
    }
}
