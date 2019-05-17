package com.corebyte.mob.kiipa.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.corebyte.mob.kiipa.model.Category;
import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.util.DateConverter;

@Database(entities =
        {Category.class, Stock.class, Measurement.class},
        version = 1,
        exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "kiipa-db";

    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (new Object()) {
                Log.i(AppDatabase.class.getSimpleName(), "Creating db connection");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DB_NAME)
                        .build();
            }
        }
        return sInstance;
    }

    public abstract CategoryDao categoryDao();

    public abstract StockDao stockDao();

    public abstract MeasurementDao measurementDao();

    public abstract TransactionSummaryDao transactionSummaryDao();

    public abstract TransactionBreakdownDao transactionBreakdownDao();
}
