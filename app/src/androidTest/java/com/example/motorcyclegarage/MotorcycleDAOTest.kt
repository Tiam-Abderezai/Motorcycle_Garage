package com.example.motorcyclegarage

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleDAO
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleDatabase
import com.example.motorcyclegarage.data.model.motorcycle.Manufacturer
import com.example.motorcyclegarage.data.model.motorcycle.Model
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import com.example.motorcyclegarage.motorcycle.MotorcycleProvider.YAMAHA
import com.example.motorcyclegarage.motorcycle.MotorcycleProvider.yamahaModels
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.theories.suppliers.TestedOn
import org.junit.rules.TestRule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MotorcycleDAOTest {

    private lateinit var motorcycleDatabase: MotorcycleDatabase
    private lateinit var motorcycleDao: MotorcycleDAO

    @Before
    fun setUp() {
        motorcycleDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MotorcycleDatabase::class.java
        ).build()
        motorcycleDao = motorcycleDatabase.motorcycleDAO()
    }

    @Test
    fun testInsertMotorcycle() = runTest {
        val model1 = Model(
            id = 1,
            name = "MT-09",
            manufacturerId = 3,
            image = R.drawable.yamaha_model_mt_09,
            type = "Naked",
            power = "117 hp",
            year = "2022",
        )
        val model2 = Model(
            id = 2,
            name = "YZF-R1",
            manufacturerId = 3,
            image = R.drawable.yamaha_model_yzf_r1,
            type = "Sport",
            power = "200 hp",
            year = "2023",
        )
        val manufacturer = Manufacturer(
            id = 3,
            name = "YAMAHA",
            logo = R.drawable.manufact_logo_ducati,
            models = listOf(model1, model2)
        )
        val motorcycle = Motorcycle(
            id = 7,
            manufacturer = manufacturer,
            logo = R.drawable.manufact_logo_yamaha,
            image = R.drawable.yamaha_model_yzf_r1,
            model = model1,
        )

        val oldNumberOfMotorcycles = motorcycleDao.getAllMotorcycles().size

        motorcycleDao.insertMotorcycle(motorcycle)

        val newNumberOfMotorcycles = motorcycleDao.getAllMotorcycles().size

        val changeInMotorcycles = newNumberOfMotorcycles - oldNumberOfMotorcycles

        Assert.assertEquals(1, changeInMotorcycles)
    }

    @After
    fun tearDown() {
        motorcycleDatabase.close()
    }
}