package com.andef.myfinance.data.purpose.completed.repository

import com.andef.myfinance.data.purpose.completed.datasource.CompletedPurposeDao
import com.andef.myfinance.data.purpose.completed.mapper.CompletedPurposeModelListToCompletedPurposeListMapper
import com.andef.myfinance.data.purpose.completed.mapper.CompletedPurposeToCompletedPurposeModelMapper
import com.andef.myfinance.domain.purpose.completed.entities.CompletedPurpose
import com.andef.myfinance.domain.purpose.completed.repository.CompletedPurposeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CompletedPurposeRepositoryImpl @Inject constructor(
    private val dao: CompletedPurposeDao
) : CompletedPurposeRepository {
    override fun getCompletedPurposeList(): Flow<List<CompletedPurpose>> {
        return dao.getCompletedPurposeList().map {
            CompletedPurposeModelListToCompletedPurposeListMapper.map(it)
        }
    }

    override suspend fun removeCompletedPurpose(id: Int) {
        dao.removeCompletedPurpose(id)
    }

    override suspend fun addCompletedPurpose(completedPurpose: CompletedPurpose) {
        dao.addCompletedPurpose(CompletedPurposeToCompletedPurposeModelMapper.map(completedPurpose))
    }
}