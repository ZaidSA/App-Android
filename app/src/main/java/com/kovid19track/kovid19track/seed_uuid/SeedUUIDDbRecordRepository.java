package com.kovid19track.kovid19track.seed_uuid;

import android.content.Context;

import java.util.List;

public class SeedUUIDDbRecordRepository {
    private SeedUUIDDbRecordDao mRecordDao;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public SeedUUIDDbRecordRepository(Context application) {
        SeedUUIDDbRecordRoomDatabase db = SeedUUIDDbRecordRoomDatabase.getDatabase(application);
        mRecordDao = db.recordDao();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public List<SeedUUIDRecord> getAllRecords() {
        return mRecordDao.getAllRecords();
    }

    public List<SeedUUIDRecord> getAllSortedRecords() {
        return mRecordDao.getSortedRecordsByTimestamp();
    }

    public SeedUUIDRecord getRecordBetween(long ts1, long ts2) {
        return mRecordDao.getRecordBetween(ts1, ts2);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(SeedUUIDRecord record) {
        SeedUUIDDbRecordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mRecordDao.insert(record);
        });
    }

    public void deleteEarlierThan(long ts_thresh) {
        mRecordDao.deleteEarlierThan(ts_thresh);
    }

    public void deleteAll() {
        mRecordDao.deleteAll();
    }
}