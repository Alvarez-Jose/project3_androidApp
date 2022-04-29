package com.example.project3_androidapp.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDao _userDao;

  private volatile UserListDao _userListDao;

  private volatile CardDao _cardDao;

  private volatile CardListDao _cardListDao;

  private volatile TransactionDao _transactionDao;

  private volatile TransactionListDao _transactionListDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `UserEntity` (`userId` INTEGER, `username` TEXT, `password` TEXT, `admin` INTEGER, `cardListId` INTEGER, `userListId` INTEGER, `bank` REAL, `transactionListId` INTEGER, PRIMARY KEY(`userId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `UserListEntity` (`ownerId` INTEGER, `otherUserId` INTEGER, PRIMARY KEY(`ownerId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CardEntity` (`cardId` INTEGER, `cardNum` INTEGER, `expiration` INTEGER, `cvv` INTEGER, `holderName` TEXT, `zip` INTEGER, `cardNickname` TEXT, PRIMARY KEY(`cardId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CardListEntity` (`cardListId` INTEGER, `cardId` INTEGER, PRIMARY KEY(`cardListId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TransactionEntity` (`transactionId` INTEGER, `amount` INTEGER, `currency` TEXT, `isFinalized` INTEGER, `sendingId` INTEGER, `receivingId` INTEGER, `description` TEXT, PRIMARY KEY(`transactionId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TransactionListEntity` (`transactionListId` INTEGER, `userId` INTEGER, `transactionId` INTEGER, PRIMARY KEY(`transactionListId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a7d5911005fb2016c8e1b1d3593a5d36')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `UserEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `UserListEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `CardEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `CardListEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `TransactionEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `TransactionListEntity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUserEntity = new HashMap<String, TableInfo.Column>(8);
        _columnsUserEntity.put("userId", new TableInfo.Column("userId", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserEntity.put("username", new TableInfo.Column("username", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserEntity.put("password", new TableInfo.Column("password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserEntity.put("admin", new TableInfo.Column("admin", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserEntity.put("cardListId", new TableInfo.Column("cardListId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserEntity.put("userListId", new TableInfo.Column("userListId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserEntity.put("bank", new TableInfo.Column("bank", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserEntity.put("transactionListId", new TableInfo.Column("transactionListId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserEntity = new TableInfo("UserEntity", _columnsUserEntity, _foreignKeysUserEntity, _indicesUserEntity);
        final TableInfo _existingUserEntity = TableInfo.read(_db, "UserEntity");
        if (! _infoUserEntity.equals(_existingUserEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "UserEntity(com.example.project3_androidapp.db.UserEntity).\n"
                  + " Expected:\n" + _infoUserEntity + "\n"
                  + " Found:\n" + _existingUserEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsUserListEntity = new HashMap<String, TableInfo.Column>(2);
        _columnsUserListEntity.put("ownerId", new TableInfo.Column("ownerId", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserListEntity.put("otherUserId", new TableInfo.Column("otherUserId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserListEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserListEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserListEntity = new TableInfo("UserListEntity", _columnsUserListEntity, _foreignKeysUserListEntity, _indicesUserListEntity);
        final TableInfo _existingUserListEntity = TableInfo.read(_db, "UserListEntity");
        if (! _infoUserListEntity.equals(_existingUserListEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "UserListEntity(com.example.project3_androidapp.db.UserListEntity).\n"
                  + " Expected:\n" + _infoUserListEntity + "\n"
                  + " Found:\n" + _existingUserListEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsCardEntity = new HashMap<String, TableInfo.Column>(7);
        _columnsCardEntity.put("cardId", new TableInfo.Column("cardId", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardEntity.put("cardNum", new TableInfo.Column("cardNum", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardEntity.put("expiration", new TableInfo.Column("expiration", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardEntity.put("cvv", new TableInfo.Column("cvv", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardEntity.put("holderName", new TableInfo.Column("holderName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardEntity.put("zip", new TableInfo.Column("zip", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardEntity.put("cardNickname", new TableInfo.Column("cardNickname", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCardEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCardEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCardEntity = new TableInfo("CardEntity", _columnsCardEntity, _foreignKeysCardEntity, _indicesCardEntity);
        final TableInfo _existingCardEntity = TableInfo.read(_db, "CardEntity");
        if (! _infoCardEntity.equals(_existingCardEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "CardEntity(com.example.project3_androidapp.db.CardEntity).\n"
                  + " Expected:\n" + _infoCardEntity + "\n"
                  + " Found:\n" + _existingCardEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsCardListEntity = new HashMap<String, TableInfo.Column>(2);
        _columnsCardListEntity.put("cardListId", new TableInfo.Column("cardListId", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardListEntity.put("cardId", new TableInfo.Column("cardId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCardListEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCardListEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCardListEntity = new TableInfo("CardListEntity", _columnsCardListEntity, _foreignKeysCardListEntity, _indicesCardListEntity);
        final TableInfo _existingCardListEntity = TableInfo.read(_db, "CardListEntity");
        if (! _infoCardListEntity.equals(_existingCardListEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "CardListEntity(com.example.project3_androidapp.db.CardListEntity).\n"
                  + " Expected:\n" + _infoCardListEntity + "\n"
                  + " Found:\n" + _existingCardListEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsTransactionEntity = new HashMap<String, TableInfo.Column>(7);
        _columnsTransactionEntity.put("transactionId", new TableInfo.Column("transactionId", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("amount", new TableInfo.Column("amount", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("currency", new TableInfo.Column("currency", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("isFinalized", new TableInfo.Column("isFinalized", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("sendingId", new TableInfo.Column("sendingId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("receivingId", new TableInfo.Column("receivingId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransactionEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransactionEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransactionEntity = new TableInfo("TransactionEntity", _columnsTransactionEntity, _foreignKeysTransactionEntity, _indicesTransactionEntity);
        final TableInfo _existingTransactionEntity = TableInfo.read(_db, "TransactionEntity");
        if (! _infoTransactionEntity.equals(_existingTransactionEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "TransactionEntity(com.example.project3_androidapp.db.TransactionEntity).\n"
                  + " Expected:\n" + _infoTransactionEntity + "\n"
                  + " Found:\n" + _existingTransactionEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsTransactionListEntity = new HashMap<String, TableInfo.Column>(3);
        _columnsTransactionListEntity.put("transactionListId", new TableInfo.Column("transactionListId", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionListEntity.put("userId", new TableInfo.Column("userId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionListEntity.put("transactionId", new TableInfo.Column("transactionId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransactionListEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransactionListEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransactionListEntity = new TableInfo("TransactionListEntity", _columnsTransactionListEntity, _foreignKeysTransactionListEntity, _indicesTransactionListEntity);
        final TableInfo _existingTransactionListEntity = TableInfo.read(_db, "TransactionListEntity");
        if (! _infoTransactionListEntity.equals(_existingTransactionListEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "TransactionListEntity(com.example.project3_androidapp.db.TransactionListEntity).\n"
                  + " Expected:\n" + _infoTransactionListEntity + "\n"
                  + " Found:\n" + _existingTransactionListEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "a7d5911005fb2016c8e1b1d3593a5d36", "a3deb947da43eb51cc9caee34e9ca53b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "UserEntity","UserListEntity","CardEntity","CardListEntity","TransactionEntity","TransactionListEntity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `UserEntity`");
      _db.execSQL("DELETE FROM `UserListEntity`");
      _db.execSQL("DELETE FROM `CardEntity`");
      _db.execSQL("DELETE FROM `CardListEntity`");
      _db.execSQL("DELETE FROM `TransactionEntity`");
      _db.execSQL("DELETE FROM `TransactionListEntity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserListDao.class, UserListDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CardDao.class, CardDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CardListDao.class, CardListDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TransactionDao.class, TransactionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TransactionListDao.class, TransactionListDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public UserListDao userListDao() {
    if (_userListDao != null) {
      return _userListDao;
    } else {
      synchronized(this) {
        if(_userListDao == null) {
          _userListDao = new UserListDao_Impl(this);
        }
        return _userListDao;
      }
    }
  }

  @Override
  public CardDao cardDao() {
    if (_cardDao != null) {
      return _cardDao;
    } else {
      synchronized(this) {
        if(_cardDao == null) {
          _cardDao = new CardDao_Impl(this);
        }
        return _cardDao;
      }
    }
  }

  @Override
  public CardListDao cardListDao() {
    if (_cardListDao != null) {
      return _cardListDao;
    } else {
      synchronized(this) {
        if(_cardListDao == null) {
          _cardListDao = new CardListDao_Impl(this);
        }
        return _cardListDao;
      }
    }
  }

  @Override
  public TransactionDao transactionDao() {
    if (_transactionDao != null) {
      return _transactionDao;
    } else {
      synchronized(this) {
        if(_transactionDao == null) {
          _transactionDao = new TransactionDao_Impl(this);
        }
        return _transactionDao;
      }
    }
  }

  @Override
  public TransactionListDao transactionListDao() {
    if (_transactionListDao != null) {
      return _transactionListDao;
    } else {
      synchronized(this) {
        if(_transactionListDao == null) {
          _transactionListDao = new TransactionListDao_Impl(this);
        }
        return _transactionListDao;
      }
    }
  }
}
