package com.dept.deptepicchallenge.data.di

import android.content.Context
import androidx.room.Room
import com.dept.deptepicchallenge.data.DateRepository
import com.dept.deptepicchallenge.data.EpicDetailRepository
import com.dept.deptepicchallenge.data.db.DatesDao
import com.dept.deptepicchallenge.data.db.EpicDB
import com.dept.deptepicchallenge.data.db.EpicDetailDao
import com.dept.deptepicchallenge.data.net.NasaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val URL = "https://epic.gsfc.nasa.gov/api/enhanced/"


    @Singleton
    @Provides
    fun provideEpicDB(@ApplicationContext context: Context) : EpicDB {
        return Room.databaseBuilder(
            context,
            EpicDB::class.java, "database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDateRepository(datesDao: DatesDao, nasaApi: NasaApi): DateRepository {
        return DateRepository(datesDao, nasaApi)
    }

    @Singleton
    @Provides
    fun provideEpicDetailRepository(epicDetailDao: EpicDetailDao, nasaApi: NasaApi): EpicDetailRepository {
        return EpicDetailRepository(epicDetailDao, nasaApi)
    }

    @Singleton
    @Provides
    fun provideEpicDetailDao(epicDB: EpicDB): EpicDetailDao {
        return epicDB.epicDetailsDao()
    }

    @Singleton
    @Provides
    fun provideDateDao(epicDB: EpicDB): DatesDao {
        return epicDB.datesDao()
    }

    @Singleton
    @Provides
    fun provideNasaApi(retrofit: Retrofit): NasaApi {
        return retrofit.create(NasaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
}