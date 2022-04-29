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
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserEntity> __insertionAdapterOfUserEntity;

  private final EntityDeletionOrUpdateAdapter<UserEntity> __deletionAdapterOfUserEntity;

  private final EntityDeletionOrUpdateAdapter<UserEntity> __updateAdapterOfUserEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUser;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `UserEntity` (`userId`,`username`,`password`,`admin`,`cardListId`,`userListId`,`bank`,`transactionListId`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserEntity value) {
        if (value.getUserId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getUserId());
        }
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPassword());
        }
        if (value.getAdmin() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getAdmin());
        }
        if (value.getCardListId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getCardListId());
        }
        if (value.getUserListId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getUserListId());
        }
        if (value.getBank() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindDouble(7, value.getBank());
        }
        if (value.getTransactionListId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getTransactionListId());
        }
      }
    };
    this.__deletionAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `UserEntity` WHERE `userId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserEntity value) {
        if (value.getUserId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getUserId());
        }
      }
    };
    this.__updateAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `UserEntity` SET `userId` = ?,`username` = ?,`password` = ?,`admin` = ?,`cardListId` = ?,`userListId` = ?,`bank` = ?,`transactionListId` = ? WHERE `userId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserEntity value) {
        if (value.getUserId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getUserId());
        }
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPassword());
        }
        if (value.getAdmin() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getAdmin());
        }
        if (value.getCardListId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getCardListId());
        }
        if (value.getUserListId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getUserListId());
        }
        if (value.getBank() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindDouble(7, value.getBank());
        }
        if (value.getTransactionListId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getTransactionListId());
        }
        if (value.getUserId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getUserId());
        }
      }
    };
    this.__preparedStmtOfDeleteUser = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM UserEntity WHERE userId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertUser(final UserEntity... users) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserEntity.insert(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final UserEntity... user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUserEntity.handleMultiple(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateUser(final UserEntity... users) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUserEntity.handleMultiple(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteUser(final int userId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUser.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, userId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteUser.release(_stmt);
    }
  }

  @Override
  public UserEntity getUserById(final int userId) {
    final String _sql = "SELECT * FROM UserEntity WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "admin");
      final int _cursorIndexOfCardListId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardListId");
      final int _cursorIndexOfUserListId = CursorUtil.getColumnIndexOrThrow(_cursor, "userListId");
      final int _cursorIndexOfBank = CursorUtil.getColumnIndexOrThrow(_cursor, "bank");
      final int _cursorIndexOfTransactionListId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionListId");
      final UserEntity _result;
      if(_cursor.moveToFirst()) {
        final Integer _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        }
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final Integer _tmpAdmin;
        if (_cursor.isNull(_cursorIndexOfAdmin)) {
          _tmpAdmin = null;
        } else {
          _tmpAdmin = _cursor.getInt(_cursorIndexOfAdmin);
        }
        final Integer _tmpCardListId;
        if (_cursor.isNull(_cursorIndexOfCardListId)) {
          _tmpCardListId = null;
        } else {
          _tmpCardListId = _cursor.getInt(_cursorIndexOfCardListId);
        }
        final Integer _tmpUserListId;
        if (_cursor.isNull(_cursorIndexOfUserListId)) {
          _tmpUserListId = null;
        } else {
          _tmpUserListId = _cursor.getInt(_cursorIndexOfUserListId);
        }
        final Double _tmpBank;
        if (_cursor.isNull(_cursorIndexOfBank)) {
          _tmpBank = null;
        } else {
          _tmpBank = _cursor.getDouble(_cursorIndexOfBank);
        }
        final Integer _tmpTransactionListId;
        if (_cursor.isNull(_cursorIndexOfTransactionListId)) {
          _tmpTransactionListId = null;
        } else {
          _tmpTransactionListId = _cursor.getInt(_cursorIndexOfTransactionListId);
        }
        _result = new UserEntity(_tmpUserId,_tmpUsername,_tmpPassword,_tmpAdmin,_tmpCardListId,_tmpUserListId,_tmpBank,_tmpTransactionListId);
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
  public List<UserEntity> getAllUsers() {
    final String _sql = "SELECT * FROM UserEntity ORDER BY userId DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "admin");
      final int _cursorIndexOfCardListId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardListId");
      final int _cursorIndexOfUserListId = CursorUtil.getColumnIndexOrThrow(_cursor, "userListId");
      final int _cursorIndexOfBank = CursorUtil.getColumnIndexOrThrow(_cursor, "bank");
      final int _cursorIndexOfTransactionListId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionListId");
      final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final UserEntity _item;
        final Integer _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        }
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final Integer _tmpAdmin;
        if (_cursor.isNull(_cursorIndexOfAdmin)) {
          _tmpAdmin = null;
        } else {
          _tmpAdmin = _cursor.getInt(_cursorIndexOfAdmin);
        }
        final Integer _tmpCardListId;
        if (_cursor.isNull(_cursorIndexOfCardListId)) {
          _tmpCardListId = null;
        } else {
          _tmpCardListId = _cursor.getInt(_cursorIndexOfCardListId);
        }
        final Integer _tmpUserListId;
        if (_cursor.isNull(_cursorIndexOfUserListId)) {
          _tmpUserListId = null;
        } else {
          _tmpUserListId = _cursor.getInt(_cursorIndexOfUserListId);
        }
        final Double _tmpBank;
        if (_cursor.isNull(_cursorIndexOfBank)) {
          _tmpBank = null;
        } else {
          _tmpBank = _cursor.getDouble(_cursorIndexOfBank);
        }
        final Integer _tmpTransactionListId;
        if (_cursor.isNull(_cursorIndexOfTransactionListId)) {
          _tmpTransactionListId = null;
        } else {
          _tmpTransactionListId = _cursor.getInt(_cursorIndexOfTransactionListId);
        }
        _item = new UserEntity(_tmpUserId,_tmpUsername,_tmpPassword,_tmpAdmin,_tmpCardListId,_tmpUserListId,_tmpBank,_tmpTransactionListId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Boolean userExists(final int userId) {
    final String _sql = "SELECT EXISTS(SELECT * FROM UserEntity WHERE userId = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
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
