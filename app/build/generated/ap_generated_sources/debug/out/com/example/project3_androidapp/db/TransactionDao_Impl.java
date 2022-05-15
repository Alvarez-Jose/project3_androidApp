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
import java.lang.Double;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TransactionEntity> __insertionAdapterOfTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<TransactionEntity> __deletionAdapterOfTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<TransactionEntity> __updateAdapterOfTransactionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTransaction;

  public TransactionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionEntity = new EntityInsertionAdapter<TransactionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `TransactionEntity` (`transactionId`,`amount`,`currency`,`isFinalized`,`sendingId`,`receivingId`,`description`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionEntity value) {
        if (value.getTransactionId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getTransactionId());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindDouble(2, value.getAmount());
        }
        if (value.getCurrency() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCurrency());
        }
        if (value.getIsFinalized() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getIsFinalized());
        }
        if (value.getSendingId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getSendingId());
        }
        if (value.getReceivingId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getReceivingId());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
      }
    };
    this.__deletionAdapterOfTransactionEntity = new EntityDeletionOrUpdateAdapter<TransactionEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `TransactionEntity` WHERE `transactionId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionEntity value) {
        if (value.getTransactionId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getTransactionId());
        }
      }
    };
    this.__updateAdapterOfTransactionEntity = new EntityDeletionOrUpdateAdapter<TransactionEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `TransactionEntity` SET `transactionId` = ?,`amount` = ?,`currency` = ?,`isFinalized` = ?,`sendingId` = ?,`receivingId` = ?,`description` = ? WHERE `transactionId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionEntity value) {
        if (value.getTransactionId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getTransactionId());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindDouble(2, value.getAmount());
        }
        if (value.getCurrency() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCurrency());
        }
        if (value.getIsFinalized() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getIsFinalized());
        }
        if (value.getSendingId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getSendingId());
        }
        if (value.getReceivingId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getReceivingId());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
        if (value.getTransactionId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getTransactionId());
        }
      }
    };
    this.__preparedStmtOfDeleteTransaction = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TransactionEntity WHERE transactionId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertTransaction(final TransactionEntity... transactions) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTransactionEntity.insert(transactions);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final TransactionEntity... transact) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTransactionEntity.handleMultiple(transact);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTransaction(final TransactionEntity... transactions) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTransactionEntity.handleMultiple(transactions);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTransaction(final int transactionId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTransaction.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, transactionId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteTransaction.release(_stmt);
    }
  }

  @Override
  public List<TransactionEntity> getTransactionById(final int transactionId) {
    final String _sql = "SELECT * FROM TransactionEntity WHERE transactionId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, transactionId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
      final int _cursorIndexOfIsFinalized = CursorUtil.getColumnIndexOrThrow(_cursor, "isFinalized");
      final int _cursorIndexOfSendingId = CursorUtil.getColumnIndexOrThrow(_cursor, "sendingId");
      final int _cursorIndexOfReceivingId = CursorUtil.getColumnIndexOrThrow(_cursor, "receivingId");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TransactionEntity _item;
        _item = new TransactionEntity();
        final Integer _tmpTransactionId;
        if (_cursor.isNull(_cursorIndexOfTransactionId)) {
          _tmpTransactionId = null;
        } else {
          _tmpTransactionId = _cursor.getInt(_cursorIndexOfTransactionId);
        }
        _item.setTransactionId(_tmpTransactionId);
        final Double _tmpAmount;
        if (_cursor.isNull(_cursorIndexOfAmount)) {
          _tmpAmount = null;
        } else {
          _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        }
        _item.setAmount(_tmpAmount);
        final String _tmpCurrency;
        if (_cursor.isNull(_cursorIndexOfCurrency)) {
          _tmpCurrency = null;
        } else {
          _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
        }
        _item.setCurrency(_tmpCurrency);
        final Integer _tmpIsFinalized;
        if (_cursor.isNull(_cursorIndexOfIsFinalized)) {
          _tmpIsFinalized = null;
        } else {
          _tmpIsFinalized = _cursor.getInt(_cursorIndexOfIsFinalized);
        }
        _item.setIsFinalized(_tmpIsFinalized);
        final Integer _tmpSendingId;
        if (_cursor.isNull(_cursorIndexOfSendingId)) {
          _tmpSendingId = null;
        } else {
          _tmpSendingId = _cursor.getInt(_cursorIndexOfSendingId);
        }
        _item.setSendingId(_tmpSendingId);
        final Integer _tmpReceivingId;
        if (_cursor.isNull(_cursorIndexOfReceivingId)) {
          _tmpReceivingId = null;
        } else {
          _tmpReceivingId = _cursor.getInt(_cursorIndexOfReceivingId);
        }
        _item.setReceivingId(_tmpReceivingId);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TransactionEntity> getAllTransactions() {
    final String _sql = "SELECT * FROM TransactionEntity ORDER BY transactionId DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
      final int _cursorIndexOfIsFinalized = CursorUtil.getColumnIndexOrThrow(_cursor, "isFinalized");
      final int _cursorIndexOfSendingId = CursorUtil.getColumnIndexOrThrow(_cursor, "sendingId");
      final int _cursorIndexOfReceivingId = CursorUtil.getColumnIndexOrThrow(_cursor, "receivingId");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TransactionEntity _item;
        _item = new TransactionEntity();
        final Integer _tmpTransactionId;
        if (_cursor.isNull(_cursorIndexOfTransactionId)) {
          _tmpTransactionId = null;
        } else {
          _tmpTransactionId = _cursor.getInt(_cursorIndexOfTransactionId);
        }
        _item.setTransactionId(_tmpTransactionId);
        final Double _tmpAmount;
        if (_cursor.isNull(_cursorIndexOfAmount)) {
          _tmpAmount = null;
        } else {
          _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        }
        _item.setAmount(_tmpAmount);
        final String _tmpCurrency;
        if (_cursor.isNull(_cursorIndexOfCurrency)) {
          _tmpCurrency = null;
        } else {
          _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
        }
        _item.setCurrency(_tmpCurrency);
        final Integer _tmpIsFinalized;
        if (_cursor.isNull(_cursorIndexOfIsFinalized)) {
          _tmpIsFinalized = null;
        } else {
          _tmpIsFinalized = _cursor.getInt(_cursorIndexOfIsFinalized);
        }
        _item.setIsFinalized(_tmpIsFinalized);
        final Integer _tmpSendingId;
        if (_cursor.isNull(_cursorIndexOfSendingId)) {
          _tmpSendingId = null;
        } else {
          _tmpSendingId = _cursor.getInt(_cursorIndexOfSendingId);
        }
        _item.setSendingId(_tmpSendingId);
        final Integer _tmpReceivingId;
        if (_cursor.isNull(_cursorIndexOfReceivingId)) {
          _tmpReceivingId = null;
        } else {
          _tmpReceivingId = _cursor.getInt(_cursorIndexOfReceivingId);
        }
        _item.setReceivingId(_tmpReceivingId);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TransactionEntity> getAllTransactionsById(final int id) {
    final String _sql = "SELECT * FROM TransactionEntity WHERE receivingId = ?  OR  sendingId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
      final int _cursorIndexOfIsFinalized = CursorUtil.getColumnIndexOrThrow(_cursor, "isFinalized");
      final int _cursorIndexOfSendingId = CursorUtil.getColumnIndexOrThrow(_cursor, "sendingId");
      final int _cursorIndexOfReceivingId = CursorUtil.getColumnIndexOrThrow(_cursor, "receivingId");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TransactionEntity _item;
        _item = new TransactionEntity();
        final Integer _tmpTransactionId;
        if (_cursor.isNull(_cursorIndexOfTransactionId)) {
          _tmpTransactionId = null;
        } else {
          _tmpTransactionId = _cursor.getInt(_cursorIndexOfTransactionId);
        }
        _item.setTransactionId(_tmpTransactionId);
        final Double _tmpAmount;
        if (_cursor.isNull(_cursorIndexOfAmount)) {
          _tmpAmount = null;
        } else {
          _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        }
        _item.setAmount(_tmpAmount);
        final String _tmpCurrency;
        if (_cursor.isNull(_cursorIndexOfCurrency)) {
          _tmpCurrency = null;
        } else {
          _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
        }
        _item.setCurrency(_tmpCurrency);
        final Integer _tmpIsFinalized;
        if (_cursor.isNull(_cursorIndexOfIsFinalized)) {
          _tmpIsFinalized = null;
        } else {
          _tmpIsFinalized = _cursor.getInt(_cursorIndexOfIsFinalized);
        }
        _item.setIsFinalized(_tmpIsFinalized);
        final Integer _tmpSendingId;
        if (_cursor.isNull(_cursorIndexOfSendingId)) {
          _tmpSendingId = null;
        } else {
          _tmpSendingId = _cursor.getInt(_cursorIndexOfSendingId);
        }
        _item.setSendingId(_tmpSendingId);
        final Integer _tmpReceivingId;
        if (_cursor.isNull(_cursorIndexOfReceivingId)) {
          _tmpReceivingId = null;
        } else {
          _tmpReceivingId = _cursor.getInt(_cursorIndexOfReceivingId);
        }
        _item.setReceivingId(_tmpReceivingId);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TransactionEntity> getAllTransactionsByName(final String name) {
    final String _sql = "SELECT * FROM TransactionEntity WHERE receivingId = (Select userId FROM UserEntity where username = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
      final int _cursorIndexOfIsFinalized = CursorUtil.getColumnIndexOrThrow(_cursor, "isFinalized");
      final int _cursorIndexOfSendingId = CursorUtil.getColumnIndexOrThrow(_cursor, "sendingId");
      final int _cursorIndexOfReceivingId = CursorUtil.getColumnIndexOrThrow(_cursor, "receivingId");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TransactionEntity _item;
        _item = new TransactionEntity();
        final Integer _tmpTransactionId;
        if (_cursor.isNull(_cursorIndexOfTransactionId)) {
          _tmpTransactionId = null;
        } else {
          _tmpTransactionId = _cursor.getInt(_cursorIndexOfTransactionId);
        }
        _item.setTransactionId(_tmpTransactionId);
        final Double _tmpAmount;
        if (_cursor.isNull(_cursorIndexOfAmount)) {
          _tmpAmount = null;
        } else {
          _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        }
        _item.setAmount(_tmpAmount);
        final String _tmpCurrency;
        if (_cursor.isNull(_cursorIndexOfCurrency)) {
          _tmpCurrency = null;
        } else {
          _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
        }
        _item.setCurrency(_tmpCurrency);
        final Integer _tmpIsFinalized;
        if (_cursor.isNull(_cursorIndexOfIsFinalized)) {
          _tmpIsFinalized = null;
        } else {
          _tmpIsFinalized = _cursor.getInt(_cursorIndexOfIsFinalized);
        }
        _item.setIsFinalized(_tmpIsFinalized);
        final Integer _tmpSendingId;
        if (_cursor.isNull(_cursorIndexOfSendingId)) {
          _tmpSendingId = null;
        } else {
          _tmpSendingId = _cursor.getInt(_cursorIndexOfSendingId);
        }
        _item.setSendingId(_tmpSendingId);
        final Integer _tmpReceivingId;
        if (_cursor.isNull(_cursorIndexOfReceivingId)) {
          _tmpReceivingId = null;
        } else {
          _tmpReceivingId = _cursor.getInt(_cursorIndexOfReceivingId);
        }
        _item.setReceivingId(_tmpReceivingId);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Boolean transactionExists(final int transactionId) {
    final String _sql = "SELECT EXISTS(SELECT * FROM TransactionEntity WHERE transactionId = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, transactionId);
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

  @Override
  public int getHighestId() {
    final String _sql = "SELECT MAX(transactionId) FROM TransactionEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
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
