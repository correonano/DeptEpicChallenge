package com.dept.deptepicchallenge.domain.di

import com.dept.deptepicchallenge.data.DateRepository
import com.dept.deptepicchallenge.data.EpicDetailRepository
import com.dept.deptepicchallenge.domain.DatesDomain
import com.dept.deptepicchallenge.domain.EpicDetailDomain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideDatesDomain(dateRepository: DateRepository): DatesDomain =
        DatesDomain(dateRepository)

    @Singleton
    @Provides
    fun provideEpicDetailsDomain(epicDetailRepository: EpicDetailRepository) =
        EpicDetailDomain(epicDetailRepository)
}