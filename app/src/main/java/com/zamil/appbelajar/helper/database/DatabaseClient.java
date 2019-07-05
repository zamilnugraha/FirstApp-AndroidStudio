package com.zamil.appbelajar.helper.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    /**
     *Initial AppDatabase
     **/
    private AppDatabase appDatabase;

    /**
     *Constructor untuk DatabaseClient
     **/
    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "App-belajar").build();
    }

    /**
     * Gets instance.
     *
     * @param mCtx the m ctx
     * @return the instance
     */
    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    /**
     * Gets app database.
     *
     * @return the app database
     */
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
