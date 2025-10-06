package com.example.fierroscocharcas.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fierroscocharcas.data.local.entities.ProductEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ProductEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    private static volatile AppDatabase INSTANCE;
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(4);

    public static AppDatabase getInstance(Context ctx) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                                    AppDatabase.class, "fierros_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(CALLBACK)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final Callback CALLBACK = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            EXECUTOR.execute(() -> {
                ProductDao dao = INSTANCE.productDao();
                dao.clear();
                dao.insert(
                        new ProductEntity("Varilla 1/2\"", "Acero construcción", 28.50, 100, "ic_home"),
                        new ProductEntity("Cemento 42.5kg", "Portland", 32.90, 50, "ic_home"),
                        new ProductEntity("Clavo 2\"", "Caja 1kg", 12.00, 200, "ic_home")
                );
            });
        }
    };

    public static ExecutorService executor() { return EXECUTOR; }
}
