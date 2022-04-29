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
public final class CardDao_Impl implements CardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CardEntity> __insertionAdapterOfCardEntity;

  private final EntityDeletionOrUpdateAdapter<CardEntity> __deletionAdapterOfCardEntity;

  private final EntityDeletionOrUpdateAdapter<CardEntity> __updateAdapterOfCardEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCard;

  public CardDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCardEntity = new EntityInsertionAdapter<CardEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CardEntity` (`cardId`,`cardNum`,`expiration`,`cvv`,`holderName`,`zip`,`cardNickname`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CardEntity value) {
        if (value.getCardId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getCardId());
        }
        if (value.getCardNum() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getCardNum());
        }
        if (value.getExpiration() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getExpiration());
        }
        if (value.getCvv() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getCvv());
        }
        if (value.getHolderName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getHolderName());
        }
        if (value.getZip() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getZip());
        }
        if (value.getCardNickname() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCardNickname());
        }
      }
    };
    this.__deletionAdapterOfCardEntity = new EntityDeletionOrUpdateAdapter<CardEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `CardEntity` WHERE `cardId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CardEntity value) {
        if (value.getCardId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getCardId());
        }
      }
    };
    this.__updateAdapterOfCardEntity = new EntityDeletionOrUpdateAdapter<CardEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `CardEntity` SET `cardId` = ?,`cardNum` = ?,`expiration` = ?,`cvv` = ?,`holderName` = ?,`zip` = ?,`cardNickname` = ? WHERE `cardId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CardEntity value) {
        if (value.getCardId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getCardId());
        }
        if (value.getCardNum() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getCardNum());
        }
        if (value.getExpiration() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getExpiration());
        }
        if (value.getCvv() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getCvv());
        }
        if (value.getHolderName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getHolderName());
        }
        if (value.getZip() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getZip());
        }
        if (value.getCardNickname() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCardNickname());
        }
        if (value.getCardId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getCardId());
        }
      }
    };
    this.__preparedStmtOfDeleteCard = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM CardEntity WHERE cardId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertCard(final CardEntity... cards) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCardEntity.insert(cards);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final CardEntity... card) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCardEntity.handleMultiple(card);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateCard(final CardEntity... cards) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCardEntity.handleMultiple(cards);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCard(final int cardId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCard.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, cardId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteCard.release(_stmt);
    }
  }

  @Override
  public CardEntity getCardById(final int cardId) {
    final String _sql = "SELECT * FROM CardEntity WHERE cardId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cardId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCardId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardId");
      final int _cursorIndexOfCardNum = CursorUtil.getColumnIndexOrThrow(_cursor, "cardNum");
      final int _cursorIndexOfExpiration = CursorUtil.getColumnIndexOrThrow(_cursor, "expiration");
      final int _cursorIndexOfCvv = CursorUtil.getColumnIndexOrThrow(_cursor, "cvv");
      final int _cursorIndexOfHolderName = CursorUtil.getColumnIndexOrThrow(_cursor, "holderName");
      final int _cursorIndexOfZip = CursorUtil.getColumnIndexOrThrow(_cursor, "zip");
      final int _cursorIndexOfCardNickname = CursorUtil.getColumnIndexOrThrow(_cursor, "cardNickname");
      final CardEntity _result;
      if(_cursor.moveToFirst()) {
        _result = new CardEntity();
        final Integer _tmpCardId;
        if (_cursor.isNull(_cursorIndexOfCardId)) {
          _tmpCardId = null;
        } else {
          _tmpCardId = _cursor.getInt(_cursorIndexOfCardId);
        }
        _result.setCardId(_tmpCardId);
        final Integer _tmpCardNum;
        if (_cursor.isNull(_cursorIndexOfCardNum)) {
          _tmpCardNum = null;
        } else {
          _tmpCardNum = _cursor.getInt(_cursorIndexOfCardNum);
        }
        _result.setCardNum(_tmpCardNum);
        final Integer _tmpExpiration;
        if (_cursor.isNull(_cursorIndexOfExpiration)) {
          _tmpExpiration = null;
        } else {
          _tmpExpiration = _cursor.getInt(_cursorIndexOfExpiration);
        }
        _result.setExpiration(_tmpExpiration);
        final Integer _tmpCvv;
        if (_cursor.isNull(_cursorIndexOfCvv)) {
          _tmpCvv = null;
        } else {
          _tmpCvv = _cursor.getInt(_cursorIndexOfCvv);
        }
        _result.setCvv(_tmpCvv);
        final String _tmpHolderName;
        if (_cursor.isNull(_cursorIndexOfHolderName)) {
          _tmpHolderName = null;
        } else {
          _tmpHolderName = _cursor.getString(_cursorIndexOfHolderName);
        }
        _result.setHolderName(_tmpHolderName);
        final Integer _tmpZip;
        if (_cursor.isNull(_cursorIndexOfZip)) {
          _tmpZip = null;
        } else {
          _tmpZip = _cursor.getInt(_cursorIndexOfZip);
        }
        _result.setZip(_tmpZip);
        final String _tmpCardNickname;
        if (_cursor.isNull(_cursorIndexOfCardNickname)) {
          _tmpCardNickname = null;
        } else {
          _tmpCardNickname = _cursor.getString(_cursorIndexOfCardNickname);
        }
        _result.setCardNickname(_tmpCardNickname);
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
  public List<CardEntity> getAllCards() {
    final String _sql = "SELECT * FROM CardEntity ORDER BY cardId DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCardId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardId");
      final int _cursorIndexOfCardNum = CursorUtil.getColumnIndexOrThrow(_cursor, "cardNum");
      final int _cursorIndexOfExpiration = CursorUtil.getColumnIndexOrThrow(_cursor, "expiration");
      final int _cursorIndexOfCvv = CursorUtil.getColumnIndexOrThrow(_cursor, "cvv");
      final int _cursorIndexOfHolderName = CursorUtil.getColumnIndexOrThrow(_cursor, "holderName");
      final int _cursorIndexOfZip = CursorUtil.getColumnIndexOrThrow(_cursor, "zip");
      final int _cursorIndexOfCardNickname = CursorUtil.getColumnIndexOrThrow(_cursor, "cardNickname");
      final List<CardEntity> _result = new ArrayList<CardEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CardEntity _item;
        _item = new CardEntity();
        final Integer _tmpCardId;
        if (_cursor.isNull(_cursorIndexOfCardId)) {
          _tmpCardId = null;
        } else {
          _tmpCardId = _cursor.getInt(_cursorIndexOfCardId);
        }
        _item.setCardId(_tmpCardId);
        final Integer _tmpCardNum;
        if (_cursor.isNull(_cursorIndexOfCardNum)) {
          _tmpCardNum = null;
        } else {
          _tmpCardNum = _cursor.getInt(_cursorIndexOfCardNum);
        }
        _item.setCardNum(_tmpCardNum);
        final Integer _tmpExpiration;
        if (_cursor.isNull(_cursorIndexOfExpiration)) {
          _tmpExpiration = null;
        } else {
          _tmpExpiration = _cursor.getInt(_cursorIndexOfExpiration);
        }
        _item.setExpiration(_tmpExpiration);
        final Integer _tmpCvv;
        if (_cursor.isNull(_cursorIndexOfCvv)) {
          _tmpCvv = null;
        } else {
          _tmpCvv = _cursor.getInt(_cursorIndexOfCvv);
        }
        _item.setCvv(_tmpCvv);
        final String _tmpHolderName;
        if (_cursor.isNull(_cursorIndexOfHolderName)) {
          _tmpHolderName = null;
        } else {
          _tmpHolderName = _cursor.getString(_cursorIndexOfHolderName);
        }
        _item.setHolderName(_tmpHolderName);
        final Integer _tmpZip;
        if (_cursor.isNull(_cursorIndexOfZip)) {
          _tmpZip = null;
        } else {
          _tmpZip = _cursor.getInt(_cursorIndexOfZip);
        }
        _item.setZip(_tmpZip);
        final String _tmpCardNickname;
        if (_cursor.isNull(_cursorIndexOfCardNickname)) {
          _tmpCardNickname = null;
        } else {
          _tmpCardNickname = _cursor.getString(_cursorIndexOfCardNickname);
        }
        _item.setCardNickname(_tmpCardNickname);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Boolean cardExists(final int cardId) {
    final String _sql = "SELECT EXISTS(SELECT * FROM CardEntity WHERE cardId = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cardId);
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
