package com.inspektorat.kadelebak.view.kade_forum.data;

import com.inspektorat.kadelebak.data.MemoryCache;
import com.inspektorat.kadelebak.model.ListUser;
import com.inspektorat.kadelebak.model.User;

public class ForumCache extends MemoryCache<User> {
    @Override
    public void save(User data) {
        save(data.getName(), data);
    }
}
