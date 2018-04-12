package com.maoyihan.www.kobe.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.maoyihan.www.kobe.db.dao.UserDao;
import com.maoyihan.www.kobe.db.entity.UserEntity;

import java.util.List;

/**
 * @author MaoYiHan
 * @date 2018/4/12
 * @describe 数据库
 */
@Database(entities = {UserEntity.class},version = 1)
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase sInstance;
    @VisibleForTesting
    public static final String DATABASE_NAME = "Hu-db";
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    public abstract UserDao userDao();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            List<UserEntity> users = DataGenerator.generateUsers();
                            insertData(database, users);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    private static void insertData(final AppDatabase database, final List<UserEntity> users) {
        database.runInTransaction(() -> {
            database.userDao().insertAll(users);
        });
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }
}
