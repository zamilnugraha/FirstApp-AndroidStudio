package com.zamil.appbelajar.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.zamil.appbelajar.model.ArticleEntity;

import java.util.List;

@Dao
public interface ArticleDao {

    @Insert
    long insert(ArticleEntity article);

    @Query("SELECT * FROM article WHERE id = :id")
    ArticleEntity getByUuid(String id);

    @Query("SELECT * FROM article")
    List<ArticleEntity> getAll();

    @Update
    void update(ArticleEntity article);

    @Delete
    void delete(ArticleEntity article);

}
