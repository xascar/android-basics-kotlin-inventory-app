package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 2, exportSchema = false)
abstract class ItemRoomDatabase: RoomDatabase() {

    //Room database needs to know about DAOs
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile // The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory, are visible to all other threads immediately, is always up-to-date
        private var INSTANCE: ItemRoomDatabase? = null // Used to keep a reference to the database, when one has been created
        fun getDatabase(context: Context): ItemRoomDatabase { // Get only  one instance
            return INSTANCE ?: synchronized(this) {// Using singleton to get a unique database instances
                //Creating database instance
                val instance = Room.databaseBuilder(  context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }

}