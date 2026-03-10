package com.example.motorcyclegarage.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.motorcyclegarage.data.MotorcycleListTestData.motorcycle1
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleListDAO
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleListDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MotorcycleListDAOTest {
    private lateinit var db: MotorcycleListDatabase
    private lateinit var dao: MotorcycleListDAO


    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            MotorcycleListDatabase::class.java
        ).allowMainThreadQueries() // Only for tests
            .build()
        dao = db.motorcycleListDAO()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertMotorcycle_and_allMotorcycles_returns_inserted_motorcycle() = runTest {
        // Arrange
        val testMotorcycle = motorcycle1

        // Act - Insert motorcycle
        dao.insertMotorcycle(testMotorcycle)

        // Assert - Verify it's in the database
        val allMotorcycles = dao.getAllMotorcycles().first()
        assertThat(allMotorcycles).hasSize(1)
        assertThat(allMotorcycles[0]).isEqualTo(testMotorcycle.copy(id = motorcycle1.id))
    }

    @Test
    fun getAllMotorcycles_whenEmpty_returnsEmptyList() = runTest {
        // Act & Assert
        val allMotorcycles = dao.getAllMotorcycles().first()
        assertThat(allMotorcycles).isEmpty()
    }

//    @Test
//    fun deleteMotorcycle_removesItFromDatabase() = runTest {
//        // Arrange - Insert a motorcycle first
//        val motorcycle = motorcycle1
//        dao.insertMotorcycle(motorcycle)
//        var allMotorcycles = dao.getAllMotorcycles().first()
//        assertThat(allMotorcycles).hasSize(1)
//
////        // Act - Delete the motorcycle
////        dao.delete(allMotorcycles[0])
//
//        // Assert - Verify it's removed
//        allMotorcycles = dao.getAllMotorcycles().first()
//        assertThat(allMotorcycles).isEmpty()
//    }
}