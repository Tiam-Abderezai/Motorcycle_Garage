package com.example.motorcyclegarage.motorcycle

import com.example.motorcyclegarage.R
import com.example.motorcyclegarage.data.model.motorcycle.Manufacturer
import com.example.motorcyclegarage.data.model.motorcycle.Model
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle

object MotorcycleProvider {
    val manufacturerList = initManufacturerList()
    val motorcycleList = initMotorcycleList()
    val modelList = initModelList()


    val bmwModels = listOf(
        Model(
            id = 1,
            name = "S 1000 RR",
            manufacturerId = 1,
            image = R.drawable.bmw_model_s1000rr,
            type = "Sport",
            power = "205 hp",
            year = "2023",
        ),
        Model(
            id = 2,
            name = "R 1250 GS",
            manufacturerId = 1,
            image = R.drawable.bmw_model_r1250gs,
            type = "Adventure",
            power = "136 hp",
            year = "2022",
        ),
        Model(
            id = 3,
            name = "K 1600 GT",
            manufacturerId = 1,
            image = R.drawable.bmw_model_k1600_gt,
            type = "Touring",
            power = "160 hp",
            year = "2021",
        ),
    )

    val suzukiModels = listOf(
        Model(
            id = 1,
            name = "GSX-R1000",
            manufacturerId = 2,
            image = R.drawable.suzuki_model_gsx_r1000,
            type = "Sport",
            power = "199 hp",
            year = "2023",
        ),
        Model(
            id = 2,
            name = "V Strom 1050",
            manufacturerId = 2,
            image = R.drawable.suzuki_model_v_strom_1050,
            type = "Adventure",
            power = "107 hp",
            year = "2022",
        ),
    )

    val BMW = Manufacturer(
        id = 1,
        name = "BMW",
        logo = R.drawable.manufact_logo_bmw,
        models = bmwModels
    )

    val SUZUKI = Manufacturer(
        id = 2,
        name = "SUZUKI",
        logo = R.drawable.manufact_logo_suzuki,
        models = suzukiModels
    )

    val yamahaModels = listOf(
        Model(
            id = 1,
            name = "MT-09",
            manufacturerId = 3,
            image = R.drawable.yamaha_model_mt_09,
            type = "Naked",
            power = "117 hp",
            year = "2022",
        ),
        Model(
            id = 2,
            name = "YZF-R1",
            manufacturerId = 3,
            image = R.drawable.yamaha_model_yzf_r1,
            type = "Sport",
            power = "200 hp",
            year = "2023",
        ),
    )
    val YAMAHA = Manufacturer(
        id = 3,
        name = "YAMAHA",
        logo = R.drawable.manufact_logo_ducati,
        models = yamahaModels
    )
    private fun initManufacturerList(): MutableList<Manufacturer> {
        val manufacturers = mutableListOf<Manufacturer>()
        manufacturers.add(
            Manufacturer(
                id = 1,
                name = "BMW",
                logo = R.drawable.manufact_logo_bmw,
                models = bmwModels
            )
        )
        manufacturers.add(
            Manufacturer(
                id = 2,
                name = "SUZUKI",
                logo = R.drawable.manufact_logo_suzuki,
                models = suzukiModels
            )
        )
        manufacturers.add(
            Manufacturer(
                id = 3,
                name = "YAMAHA",
                logo = R.drawable.manufact_logo_ducati,
                models = yamahaModels
            )
        )
        return manufacturers
    }

    private fun initModelList(): MutableList<Model> {
        val models = mutableListOf<Model>()
        models.add(
            Model(
                id = 1,
                name = "S 1000 RR",
                manufacturerId = 1,
                image = R.drawable.bmw_model_s1000rr,
                type = "Sport",
                power = "205 hp",
                year = "2023",
            )
        )
        models.add(
            Model(
                id = 2,
                name = "R 1250 GS",
                manufacturerId = 1,
                image = R.drawable.bmw_model_r1250gs,
                type = "Adventure",
                power = "136 hp",
                year = "2022",
            )
        )
        models.add(
            Model(
                id = 3,
                name = "K 1600 GT",
                manufacturerId = 1,
                image = R.drawable.bmw_model_k1600_gt,
                type = "Touring",
                power = "160 hp",
                year = "2021",
            ),
        )
        models.add(
            Model(
                id = 1,
                name = "GSX-R1000",
                manufacturerId = 2,
                image = R.drawable.suzuki_model_gsx_r1000,
                type = "Sport",
                power = "199 hp",
                year = "2023",
            )
        )
        models.add(
            Model(
                id = 2,
                name = "V Strom 1050",
                manufacturerId = 2,
                image = R.drawable.suzuki_model_v_strom_1050,
                type = "Adventure",
                power = "107 hp",
                year = "2022",
            )
        )
        models.add(
            Model(
                id = 1,
                name = "MT-09",
                manufacturerId = 3,
                image = R.drawable.yamaha_model_mt_09,
                type = "Naked",
                power = "117 hp",
                year = "2022",
            )
        )
        models.add(
            Model(
                id = 2,
                name = "YZF-R1",
                manufacturerId = 3,
                image = R.drawable.yamaha_model_yzf_r1,
                type = "Sport",
                power = "200 hp",
                year = "2023",
            ),
        )
        return models
    }


    private fun initMotorcycleList(): MutableList<Motorcycle> {
        val motorcycles = mutableListOf<Motorcycle>()
        motorcycles.add(
            Motorcycle(
                id = 1,
                manufacturer = BMW,
                logo = R.drawable.manufact_logo_bmw,
                image = bmwModels[0].image,
                model = bmwModels[0],
            )
        )
        motorcycles.add(
            Motorcycle(
                id = 2,
                manufacturer = BMW,
                logo = R.drawable.manufact_logo_bmw,
                image = bmwModels[1].image,
                model = bmwModels[1],
            )
        )
        motorcycles.add(
            Motorcycle(
                id = 3,
                manufacturer = BMW,
                logo = R.drawable.manufact_logo_bmw,
                image = bmwModels[2].image,
                model = bmwModels[2],
            )
        )
        motorcycles.add(
            Motorcycle(
                id = 4,
                manufacturer = SUZUKI,
                logo = R.drawable.manufact_logo_suzuki,
                image = suzukiModels[0].image,
                model = suzukiModels[0],
            )
        )
        motorcycles.add(
            Motorcycle(
                id = 5,
                manufacturer = SUZUKI,
                logo = R.drawable.manufact_logo_suzuki,
                image = suzukiModels[1].image,
                model = suzukiModels[1],
            )
        )
        motorcycles.add(
            Motorcycle(
                id = 6,
                manufacturer = YAMAHA,
                logo = R.drawable.manufact_logo_yamaha,
                image = yamahaModels[0].image,
                model = yamahaModels[0],
            )
        )
        motorcycles.add(
            Motorcycle(
                id = 7,
                manufacturer = YAMAHA,
                logo = R.drawable.manufact_logo_yamaha,
                image = yamahaModels[1].image,
                model = yamahaModels[1],
            )
        )
        return motorcycles
    }
}