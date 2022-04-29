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
public final class UserListDao_Impl implements UserListDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserListEntity> __insertionAdapterOfUserListEntity;

  private final EntityDeletionOrUpdateAdapter<UserListEntity> __deletionAdapterOfUserListEntity;

  private final EntityDeletionOrUpdateAdapter<UserListEntity> __updateAdapterOfUserListEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUserList;

  public UserListDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserListEntity = new EntityInsertionAdapter<UserListEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `UserListEntity` (`ownerId`,`otherUserId`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserListEntity value) {
        if (value.getOwnerId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getOwnerId());
        }
        if (value.getOtherUserId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getOtherUserId());
        }
      }
    };
    this.__deletionAdapterOfUserListEntity = new EntityDeletionOrUpdateAdapter<UserListEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `UserListEntity` WHERE `ownerId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserListEntity value) {
        if (value.getOwnerId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getOwnerId());
        }
      }
    };
    this.__updateAdapterOfUserListEntity = new EntityDeletionOrUpdateAdapter<UserListEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `UserListEntity` SET `ownerId` = ?,`otherUserId` = ? WHERE `ownerId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserListEntity value) {
        if (value.getOwnerId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getOwnerId());
        }
        if (value.getOtherUserId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getOtherUserId());
        }
        if (value.getOwnerId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getOwnerId());
        }
      }
    };
    this.__preparedStmtOfDeleteUserList = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM UserListEntity WHERE ownerId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertUserList(final UserListEntity... users) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserListEntity.insert(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final UserListEntity... user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUserListEntity.handleMultiple(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateUserList(final UserListEntity... users) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUserListEntity.handleMultiple(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteUserList(final int userListId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUserList.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, userListId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteUserList.release(_stmt);
    }
  }

  @Override
  public UserListEntity getUserListById(final int userListId) {
    final String _sql = "SELECT * FROM UserListEntity WHERE ownerId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userListId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOwnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerId");
      final int _cursorIndexOfOtherUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "otherUserId");
      final UserListEntity _result;
      if(_cursor.moveToFirst()) {
        _result = new UserListEntity();
        final Integer _tmpOwnerId;
        if (_cursor.isNull(_cursorIndexOfOwnerId)) {
          _tmpOwnerId = null;
        } else {
          _tmpOwnerId = _cursor.getInt(_cursorIndexOfOwnerId);
        }
        _result.setOwnerId(_tmpOwnerId);
        final Integer _tmpOtherUserId;
        if (_cursor.isNull(_cursorIndexOfOtherUserId)) {
          _tmpOtherUserId = null;
        } else {
          _tmpOtherUserId = _cursor.getInt(_cursorIndexOfOtherUserId);
        }
        _result.setOtherUserId(_tmpOtherUserId);
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
  public List<UserListEntity> getAllUserLists() {
    final String _sql = "SELECT * FROM UserListEntity ORDER BY ownerId DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOwnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerId");
      final int _cursorIndexOfOtherUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "otherUserId");
      final List<UserListEntity> _result = new ArrayList<UserListEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final UserListEntity _item;
        _item = new UserListEntity();
        final Integer _tmpOwnerId;
        if (_cursor.isNull(_cursorIndexOfOwnerId)) {
          _tmpOwnerId = null;
        } else {
          _tmpOwnerId = _cursor.getInt(_cursorIndexOfOwnerId);
        }
        _item.setOwnerId(_tmpOwnerId);
        final Integer _tmpOtherUserId;
        if (_cursor.isNull(_cursorIndexOfOtherUserId)) {
          _tmpOtherUserId = null;
        } else {
          _tmpOtherUserId = _cursor.getInt(_cursorIndexOfOtherUserId);
        }
        _item.setOtherUserId(_tmpOtherUserId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Boolean userListExists(final int userListId) {
    final String _sql = "SELECT EXISTS(SELECT * FROM UserListEntity WHERE ownerId = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userListId);
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
