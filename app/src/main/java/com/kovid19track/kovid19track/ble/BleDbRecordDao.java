package com.kovid19track.kovid19track.ble;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BleDbRecordDao {

    @Query("SELECT * FROM ble_record_table")
    List<BleRecord> getAllRecords();

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BleRecord record);

    @Query("DELETE FROM ble_record_table")
    void deleteAll();

    @Query("DELETE FROM ble_record_table WHERE ts <= :ts_thresh")
    void deleteEarlierThan(long ts_thresh);

    @Query("SELECT * FROM ble_record_table ORDER BY ts DESC")
    List<BleRecord> getSortedRecordsByTimestamp();

}
