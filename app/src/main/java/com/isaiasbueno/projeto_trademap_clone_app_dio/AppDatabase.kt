package com.isaiasbueno.projeto_trademap_clone_app_dio

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.isaiasbueno.projeto_trademap_clone_app_dio.domain.Acao
import com.isaiasbueno.projeto_trademap_clone_app_dio.domain.AcaoDAO
@Database(
    version = 1,
    entities = [
        Acao::class
    ],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun acaoDAO(): AcaoDAO
    companion object {
        private const val NOME_BANCO_DE_DADOS = "picpayclone.db"
        @Volatile
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, NOME_BANCO_DE_DADOS)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}