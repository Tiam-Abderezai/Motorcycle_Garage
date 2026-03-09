package com.example.motorcyclegarage.data

import com.example.motorcyclegarage.R
import com.example.motorcyclegarage.data.model.motorcycle.Manufacturer
import com.example.motorcyclegarage.data.model.motorcycle.Model
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import java.util.UUID
import java.util.concurrent.ThreadLocalRandom

object MotorcycleListTestData {

    private fun makeRandomString() = UUID.randomUUID().toString()
    private val randomInt = ThreadLocalRandom.current().nextInt(0, 1000 + 1)

    val modelBmwS100RR = Model(
        id = randomInt,
        name = "S 1000 RR",
        manufacturerId = 1,
        image = R.drawable.bmw_model_s1000rr,
        type = "Sport",
        power = "205 hp",
        year = "2023",
    )

    val modelBmwR1250GS = Model(
        id = randomInt,
        name = "R 1250 GS",
        manufacturerId = 1,
        image = R.drawable.bmw_model_r1250gs,
        type = "Adventure",
        power = "136 hp",
        year = "2022",
    )

    val modelSuzukiGsxR1000 = Model(
        id = randomInt,
        name = "GSX-R1000",
        manufacturerId = 2,
        image = R.drawable.suzuki_model_gsx_r1000,
        type = "Sport",
        power = "199 hp",
        year = "2023",
    )
    val modelSuzukiVStrom1050 = Model(
        id = randomInt,
        name = "V Strom 1050",
        manufacturerId = 2,
        image = R.drawable.suzuki_model_v_strom_1050,
        type = "Adventure",
        power = "107 hp",
        year = "2022",
    )

    val modelYamahaMt09 = Model(
        id = randomInt,
        name = "MT-09",
        manufacturerId = 3,
        image = R.drawable.yamaha_model_mt_09,
        type = "Naked",
        power = "117 hp",
        year = "2022",
    )
    val modelYamahaYzfR1 = Model(
        id = randomInt,
        name = "YZF-R1",
        manufacturerId = 3,
        image = R.drawable.yamaha_model_yzf_r1,
        type = "Sport",
        power = "200 hp",
        year = "2023",
    )


    val manufacturerBmw = Manufacturer(
        id = 1,
        name = "BMW",
        logo = R.drawable.manufact_logo_bmw,
        models = listOf(modelBmwS100RR, modelBmwR1250GS)
    )

    val manufacturerSuzuki = Manufacturer(
        id = 2,
        name = "SUZUKI",
        logo = R.drawable.manufact_logo_suzuki,
        models = listOf(modelSuzukiGsxR1000, modelSuzukiVStrom1050)
    )

    val manufacturerYamaha = Manufacturer(
        id = 3,
        name = "YAMAHA",
        logo = R.drawable.manufact_logo_yamaha,
        models = listOf(modelYamahaMt09, modelYamahaYzfR1)
    )


    val motorcycle1 = Motorcycle(
        id = randomInt,
        manufacturer = manufacturerBmw,
        logo = R.drawable.manufact_logo_bmw,
        image = R.drawable.bmw_model_s1000rr,
        model = modelBmwS100RR,
    )

    val motorcycle2 = Motorcycle(
        id = randomInt,
        manufacturer = manufacturerBmw,
        logo = R.drawable.manufact_logo_bmw,
        image = R.drawable.bmw_model_r1250gs,
        model = modelBmwR1250GS,
    )

    val motorcycle3 = Motorcycle(
        id = randomInt,
        manufacturer = manufacturerYamaha,
        logo = R.drawable.manufact_logo_yamaha,
        image = R.drawable.yamaha_model_yzf_r1,
        model = modelYamahaMt09,
    )

    val motorcycle4 = Motorcycle(
        id = randomInt,
        manufacturer = manufacturerYamaha,
        logo = R.drawable.manufact_logo_yamaha,
        image = R.drawable.yamaha_model_yzf_r1,
        model = modelYamahaYzfR1,
    )

    val motorcycle5 = Motorcycle(
        id = randomInt,
        manufacturer = manufacturerSuzuki,
        logo = R.drawable.manufact_logo_suzuki,
        image = R.drawable.suzuki_model_gsx_r1000,
        model = modelSuzukiGsxR1000,
    )

    val motorcycle6 = Motorcycle(
        id = randomInt,
        manufacturer = manufacturerSuzuki,
        logo = R.drawable.manufact_logo_suzuki,
        image = R.drawable.suzuki_model_v_strom_1050,
        model = modelSuzukiVStrom1050,
    )

    fun createMotorcycleListTestData(): List<Motorcycle> {
        return listOf(
            motorcycle1,
            motorcycle2,
            motorcycle3,
            motorcycle4,
            motorcycle5,
            motorcycle6
        )
    }

}