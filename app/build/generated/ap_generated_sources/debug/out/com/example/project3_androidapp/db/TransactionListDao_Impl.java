package com.example.project3_androidapp.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionListDao_Impl implements TransactionListDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TransactionListEntity> __insertionAdapterOfTransactionListEntity;

  private final EntityDeletionOrUpdateAdapter<TransactionListEntity> __deletionAdapterOfTransactionListEntity;

  private final EntityDeletionOrUpdateAdapter<TransactionListEntity> __updateAdapterOfTransactionListEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTransactionList;

  public TransactionListDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionListEntity = new EntityInsertionAdapter<TransactionListEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `TransactionListEntity` (`transactionListId`,`userId`,`transactionId`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionListEntity value) {
        if (value.getTransactionListId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getTransactionListId());
        }
        if (value.getUserId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getUserId());
        }
        if (value.getTransactionId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getTransactionId());
        }
      }
    };
    this.__deletionAdapterOfTransactionListEntity = new EntityDeletionOrUpdateAdapter<TransactionListEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `TransactionListEntity` WHERE `transactionListId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionListEntity value) {
        if (value.getTransactionListId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getTransactionListId());
        }
      }
    };
    this.__updateAdapterOfTransactionListEntity = new EntityDeletionOrUpdateAdapter<TransactionListEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `TransactionListEntity` SET `transactionListId` = ?,`userId` = ?,`transactionId` = ? WHERE `transactionListId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionListEntity value) {
        if (value.getTransactionListId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getTransactionListId());
        }
        if (value.getUserId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getUserId());
        }
        if (value.getTransactionId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getTransactionId());
        }
        if (value.getTransactionListId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getTransactionListId());
        }
      }
    };
    this.__preparedStmtOfDeleteTransactionList = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TransactionListEntity WHERE transactionListId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertTransactionList(final TransactionListEntity... transactionLists) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTransactionListEntity.insert(transactionLists);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final TransactionListEntity... transactionList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTransactionListEntity.handleMultiple(transactionList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTransactionList(final TransactionListEntity... transactionLists) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTransactionListEntity.handleMultiple(transactionLists);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTransactionList(final int transactionListId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTransactionList.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, transactionListId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteTransactionList.release(_stmt);
    }
  }

  @Override
  public TransactionListEntity getTransactionListById(final int transactionListId) {
    final String _sql = "SELECT * FROM TransactionListEntity WHERE transactionListId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, transactionListId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTransactionListId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionListId");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
      final TransactionListEntity _result;
      if(_cursor.moveToFirst()) {
        _result = new TransactionListEntity();
        final Integer _tmpTransactionListId;
        if (_cursor.isNull(_cursorIndexOfTransactionListId)) {
          _tmpTransactionListId = null;
        } else {
          _tmpTransactionListId = _cursor.getInt(_cursorIndexOfTransactionListId);
        }
        _result.setTransactionListId(_tmpTransactionListId);
        final Integer _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        }
        _result.setUserId(_tmpUserId);
        final Integer _tmpTransactionId;
        if (_cursor.isNull(_cursorIndexOfTransactionId)) {
          _tmpTransactionId = null;
        } else {
          _tmpTransactionId = _cursor.getInt(_cursorIndexOfTransactionId);
        }
        _result.setTransactionId(_tmpTransactionId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TransactionListEntity> getAllTransactionLists() {
    final String _sql = "SELECT * FROM TransactionListEntity ORDER BY transactionListId DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTransactionListId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionListId");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
      final List<TransactionListEntity> _result = new ArrayList<TransactionListEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TransactionListEntity _item;
        _item = new TransactionListEntity();
        final Integer _tmpTransactionListId;
        if (_cursor.isNull(_cursorIndexOfTransactionListId)) {
          _tmpTransactionListId = null;
        } else {
          _tmpTransactionListId = _cursor.getInt(_cursorIndexOfTransactionListId);
        }
        _item.setTransactionListId(_tmpTransactionListId);
        final Integer _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        }
        _item.setUserId(_tmpUserId);
        final Integer _tmpTransactionId;
        if (_cursor.isNull(_cursorIndexOfTransactionId)) {
          _tmpTransactionId = null;
        } else {
          _tmpTransactionId = _cursor.getInt(_cursorIndexOfTransactionId);
        }
        _item.setTransactionId(_tmpTransactionId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Boolean transactionListExists(final int transactionListId) {
    final String _sql = "SELECT EXISTS(SELECT * FROM TransactionListEntity WHERE transactionListId = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, transactionListId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final Boolean _result;
      if(_cursor.moveToFirst()) {
        final Integer _tmp;
        if (_cursor.isNull(0)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(0);
        }
        _result = _tmp == null ? null : _tmp != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
