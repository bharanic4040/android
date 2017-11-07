package dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.lavanya.basics.model.Person;

/**
 * Created by lavanya on 07/11/17.
 */

@Database(entities = {Person.class}, version = 1)
public abstract class SampleDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}