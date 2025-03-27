package com.andef.myfinance.data.purpose.active.repository

import com.andef.myfinance.data.purpose.active.datasource.ActivePurposeDao
import com.andef.myfinance.data.purpose.active.mapper.ActivePurposeModelListToActivePurposeListMapper
import com.andef.myfinance.data.purpose.active.mapper.ActivePurposeToActivePurposeModelMapper
import com.andef.myfinance.data.purpose.active.mapper.ActivePurposeToCompletedPurposeMapper
import com.andef.myfinance.domain.purpose.active.entities.ActivePurpose
import com.andef.myfinance.domain.purpose.active.repository.ActivePurposeRepository
import com.andef.myfinance.domain.purpose.completed.repository.CompletedPurposeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ActivePurposeRepositoryImpl @Inject constructor(
    private val activePurposeDao: ActivePurposeDao,
    private val completedPurposeRepository: CompletedPurposeRepository
) : ActivePurposeRepository {
    override suspend fun addActivePurpose(activePurpose: ActivePurpose) {
        activePurposeDao.addActivePurpose(ActivePurposeToActivePurposeModelMapper.map(activePurpose))
    }

    override suspend fun changeActivePurpose(
        id: Int,
        name: String,
        targetSum: Double,
        currentSum: Double,
        photoUri: String
    ) {
        activePurposeDao.changeActivePurpose(id, name, targetSum, currentSum, photoUri)
    }

    override suspend fun addActivePurposeToCompletedPurpose(activePurpose: ActivePurpose) {
        val completedPurpose = ActivePurposeToCompletedPurposeMapper.map(activePurpose)
        completedPurposeRepository.addCompletedPurpose(completedPurpose)
    }

    override suspend fun removeActivePurpose(id: Int) {
        activePurposeDao.removeActivePurpose(id)
    }

    override suspend fun addToSum(id: Int, amount: Double) {
        activePurposeDao.addToSum(id, amount)
    }

    override suspend fun removeFromSum(id: Int, amount: Double) {
        activePurposeDao.removeFromSum(id, amount)
    }

    override fun getActivePurposeList(): Flow<List<ActivePurpose>> {
        return activePurposeDao.getActivePurposeList().map {
            ActivePurposeModelListToActivePurposeListMapper.map(it)
        }
    }
}