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
public final class CardListDao_Impl implements CardListDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CardListEntity> __insertionAdapterOfCardListEntity;

  private final EntityDeletionOrUpdateAdapter<CardListEntity> __deletionAdapterOfCardListEntity;

  private final EntityDeletionOrUpdateAdapter<CardListEntity> __updateAdapterOfCardListEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCardList;

  public CardListDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCardListEntity = new EntityInsertionAdapter<CardListEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CardListEntity` (`cardListId`,`cardId`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CardListEntity value) {
        if (value.getCardListId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getCardListId());
        }
        if (value.getCardId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getCardId());
        }
      }
    };
    this.__deletionAdapterOfCardListEntity = new EntityDeletionOrUpdateAdapter<CardListEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `CardListEntity` WHERE `cardListId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CardListEntity value) {
        if (value.getCardListId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getCardListId());
        }
      }
    };
    this.__updateAdapterOfCardListEntity = new EntityDeletionOrUpdateAdapter<CardListEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `CardListEntity` SET `cardListId` = ?,`cardId` = ? WHERE `cardListId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CardListEntity value) {
        if (value.getCardListId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getCardListId());
        }
        if (value.getCardId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getCardId());
        }
        if (value.getCardListId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getCardListId());
        }
      }
    };
    this.__preparedStmtOfDeleteCardList = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM CardListEntity WHERE cardListId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertCardList(final CardListEntity... cardLists) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCardListEntity.insert(cardLists);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final CardListEntity... cardList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCardListEntity.handleMultiple(cardList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateCardList(final CardListEntity... cardLists) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCardListEntity.handleMultiple(cardLists);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCardList(final int cardListId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCardList.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, cardListId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteCardList.release(_stmt);
    }
  }

  @Override
  public CardListEntity getCardListById(final int cardListId) {
    final String _sql = "SELECT * FROM CardListEntity WHERE cardListId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cardListId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCardListId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardListId");
      final int _cursorIndexOfCardId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardId");
      final CardListEntity _result;
      if(_cursor.moveToFirst()) {
        _result = new CardListEntity();
        final Integer _tmpCardListId;
        if (_cursor.isNull(_cursorIndexOfCardListId)) {
          _tmpCardListId = null;
        } else {
          _tmpCardListId = _cursor.getInt(_cursorIndexOfCardListId);
        }
        _result.setCardListId(_tmpCardListId);
        final Integer _tmpCardId;
        if (_cursor.isNull(_cursorIndexOfCardId)) {
          _tmpCardId = null;
        } else {
          _tmpCardId = _cursor.getInt(_cursorIndexOfCardId);
        }
        _result.setCardId(_tmpCardId);
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
  public List<CardListEntity> getAllCardLists() {
    final String _sql = "SELECT * FROM CardListEntity ORDER BY cardListId DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCardListId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardListId");
      final int _cursorIndexOfCardId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardId");
      final List<CardListEntity> _result = new ArrayList<CardListEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CardListEntity _item;
        _item = new CardListEntity();
        final Integer _tmpCardListId;
        if (_cursor.isNull(_cursorIndexOfCardListId)) {
          _tmpCardListId = null;
        } else {
          _tmpCardListId = _cursor.getInt(_cursorIndexOfCardListId);
        }
        _item.setCardListId(_tmpCardListId);
        final Integer _tmpCardId;
        if (_cursor.isNull(_cursorIndexOfCardId)) {
          _tmpCardId = null;
        } else {
          _tmpCardId = _cursor.getInt(_cursorIndexOfCardId);
        }
        _item.setCardId(_tmpCardId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Boolean cardListExists(final int cardListId) {
    final String _sql = "SELECT EXISTS(SELECT * FROM CardListEntity WHERE cardListId = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cardListId);
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
