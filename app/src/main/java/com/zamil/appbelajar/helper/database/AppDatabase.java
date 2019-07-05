package com.zamil.appbelajar.helper.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.zamil.appbelajar.dao.ArticleDao;
import com.zamil.appbelajar.model.ArticleEntity;

@Database(entities = {ArticleEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ArticleDao articleDao();

}
