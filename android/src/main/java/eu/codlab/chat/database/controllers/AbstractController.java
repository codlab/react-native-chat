package eu.codlab.chat.database.controllers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractController<T extends BaseModel, KEY_TYPE> {

    private HashMap<KEY_TYPE, T> mCache;

    protected AbstractController() {
        flushCache();
    }

    public T getItemFrom(KEY_TYPE id) {
        T object = getItemFromCache(id);

        if (object == null) {
            object = getItemFromDatabase(id);
            if (object != null) putItemInCache(id, object);
        }

        Log.d(getTag(), "getItemFrom: id := " + id + " object := " + object);
        return object;
    }

    public void saveItem(KEY_TYPE id, T object) {
        Log.d(getTag(), "saveItem: id := " + id + " object := " + object);
        putItemInCache(id, object);
        object.save();
    }

    public void deleteObject(KEY_TYPE id, T object) {
        mCache.remove(id);
        object.delete();
    }

    protected abstract Property<KEY_TYPE> getColumnId();

    protected abstract Class<? extends BaseModel> getTableClass();

    protected List<T> listFromDatabaseThroughCache(@NonNull List<T> list) {
        List<T> new_list = new ArrayList<>();

        for (T item : list) {
            KEY_TYPE id = getId(item);
            T cache = getItemFromCache(id);
            if(null == cache) {
                putItemInCache(id, item);
                new_list.add(item);
            } else {
                new_list.add(cache);
            }
        }

        return new_list;
    }

    protected abstract KEY_TYPE getId(T object);

    protected void putItemInCache(KEY_TYPE id, T object) {
        mCache.put(id, object);
    }

    protected T getItemFromCache(KEY_TYPE id) {
        return mCache.get(id);
    }

    protected T getItemFromDatabase(KEY_TYPE id) {
        T result = (T) new Select()
                .from(getTableClass())
                .where(getColumnId().eq(id))
                .querySingle();

        Log.d(getTag(), "getItemFromDatabase: id := " + result);
        return result;
    }

    public abstract T createObject(@Nullable KEY_TYPE primary);

    public abstract boolean equals(@Nullable T left, @Nullable T right);

    public void flushCache() {
        if (null == mCache) {
            mCache = new HashMap<>(200);
        } else {
            mCache.clear();
        }
    }

    @NonNull
    protected String getTag() {
        return getClass().getSimpleName();
    }

    public void eraseAll() {
        new Delete().from(getTableClass()).execute();

    }
}
