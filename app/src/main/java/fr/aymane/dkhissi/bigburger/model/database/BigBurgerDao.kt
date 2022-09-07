package fr.aymane.dkhissi.bigburger.model.database

import android.content.Context
import androidx.room.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.aymane.dkhissi.bigburger.entities.Product
import javax.inject.Singleton


@Dao
interface BigBurgerDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertAll ( list : List < Product >)

    @Query(" SELECT * FROM products_table ")
    fun getBasketList () : kotlinx.coroutines.flow.Flow<List<Product>>
}


@Database(
    entities = [ Product::class ],
    version = 1,
    exportSchema = false
)
abstract class BigBurgerRoomDatabase : RoomDatabase() {
    abstract fun bigBurgerDao () : BigBurgerDao
}


@InstallIn( SingletonComponent::class )
@Module
object BigBurgerRoomDatabaseModule {
    @Provides
    @Singleton
    fun provideBigBurgerDao(bigBurgerRoomDatabase: BigBurgerRoomDatabase): BigBurgerDao {
        return bigBurgerRoomDatabase.bigBurgerDao()
    }

    @Provides
    @Singleton
    fun provideBigBurgerRoomDatabase(@ApplicationContext appContext: Context): BigBurgerRoomDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            BigBurgerRoomDatabase::class.java,
            "products_table"
        ).build()
    }

}