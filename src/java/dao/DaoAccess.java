package dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lavanya.basics.model.Person;

import java.util.List;

/**
 * Created by lavanya on 07/11/17.
 */
@Dao
public interface DaoAccess {


        @Insert
        void insertMultiple(List<Person> universities);

        @Insert
        void insertSingle(Person Person);

        @Query("SELECT * FROM Person")
        List<Person> fetchAllData();


        @Update
        void updateRecord(Person Person);

        @Delete
        void deleteRecord(Person Person);
    
}
