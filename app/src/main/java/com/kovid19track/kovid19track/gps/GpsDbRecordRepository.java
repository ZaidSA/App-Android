package com.kovid19track.kovid19track.gps;

import android.content.Context;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GpsDbRecordRepository {
    private GpsDbRecordDao mRecordDao;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public GpsDbRecordRepository(Context application) {
        GpsDbRecordRoomDatabase db = GpsDbRecordRoomDatabase.getDatabase(application);
        mRecordDao = db.recordDao();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public List<GpsRecord> getAllRecords() {
        return mRecordDao.getAllRecords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<GpsRecord>> getSortedRecords() {
        return mRecordDao.getSortedRecords();
    }

    @WorkerThread
    public List<GpsRecord> getSortedRecordsSync() {
        return mRecordDao.getSortedRecordsSync();
    }

    public List<GpsRecord> getRecordsBetweenTimestamps(long ts1, long ts2) {
        return mRecordDao.getRecordsBetweenTimestamp(ts1, ts2);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(GpsRecord record) {
        GpsDbRecordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mRecordDao.insert(record);
        });
    }

    public void deleteAll() {
        mRecordDao.deleteAll();
    }

    public void delete(long ts) {
        mRecordDao.delete(ts);
    }

    public void deleteEarlierThan(long ts_thresh) {
        mRecordDao.deleteEarlierThan(ts_thresh);
    }
}