package com.example.motorcyclegarage.motorcycle.model

import com.example.motorcyclegarage.R

val BMW by lazy {
    Manufacturer(
        id = 1,
        name = "BMW",
        logo = R.drawable.manufact_logo_bmw,
        models = bmwModels
    )
}

val SUZUKI: Manufacturer by lazy {
    Manufacturer(
        id = 2,
        name = "SUZUKI",
        logo = R.drawable.manufact_logo_suzuki,
        models = suzukiModels
    )
}

val YAMAHA: Manufacturer by lazy {
    Manufacturer(
        id = 3,
        name = "YAMAHA",
        logo = R.drawable.manufact_logo_ducati,
        models = yamahaModels

    )
}

val bmwModels = listOf(
    Model(
        id = 1,
        name = "S 1000 RR",
        manufacturerName = "BMW",
        image = R.drawable.bmw_model_s1000rr,
        type = "Sport",
        power = "205 hp",
        year = "2023",
    ),
    Model(
        id = 2,
        name = "R 1250 GS",
        manufacturerName = "BMW",
        image = R.drawable.bmw_model_r1250gs,
        type = "Adventure",
        power = "136 hp",
        year = "2022",
    ),
    Model(
        id = 3,
        name = "K 1600 GT",
        manufacturerName = "BMW",
        image = R.drawable.bmw_model_k1600_gt,
        type = "Touring",
        power = "160 hp",
        year = "2021",
    ),
)

val suzukiModels = arrayListOf(
    Model(
        id = 1,
        name = "GSX-R1000",
        manufacturerName = "SUZUKI",
        image = R.drawable.suzuki_model_gsx_r1000,
        type = "Sport",
        power = "199 hp",
        year = "2023",
    ),
    Model(
        id = 2,
        name = "V Strom 1050",
        manufacturerName = "SUZUKI",
        image = R.drawable.suzuki_model_v_strom_1050,
        type = "Adventure",
        power = "107 hp",
        year = "2022",
    ),

    )

val yamahaModels = arrayListOf(
    Model(
        id = 1,
        name = "MT-09",
        manufacturerName = "YAMAHA",
        image = R.drawable.yamaha_model_mt_09,
        type = "Naked",
        power = "117 hp",
        year = "2022",
    ),
    Model(
        id = 2,
        name = "YZF-R1",
        manufacturerName = "YAMAHA",
        image = R.drawable.yamaha_model_yzf_r1,
        type = "Sport",
        power = "200 hp",
        year = "2023",
    ),
)


val manufacturerList = arrayListOf(
    BMW,
    SUZUKI,
    YAMAHA
)


val motorList = arrayListOf(
    Motorcycle(
        id = 1,
        manufacturer = BMW,
        logo = R.drawable.manufact_logo_bmw,
        image = bmwModels[0].image,
        model = bmwModels[0],
    ),
    Motorcycle(
        id = 2,
        manufacturer = BMW,
        logo = R.drawable.manufact_logo_bmw,
        image = bmwModels[1].image,
        model = bmwModels[1],
    ),
    Motorcycle(
        id = 3,
        manufacturer = BMW,
        logo = R.drawable.manufact_logo_bmw,
        image = bmwModels[2].image,
        model = bmwModels[2],
    ),
    Motorcycle(
        id = 4,
        manufacturer = SUZUKI,
        logo = R.drawable.manufact_logo_suzuki,
        image = suzukiModels[0].image,
        model = suzukiModels[0],
    ),
    Motorcycle(
        id = 5,
        manufacturer = SUZUKI,
        logo = R.drawable.manufact_logo_suzuki,
        image = suzukiModels[1].image,
        model = suzukiModels[1],
    ),
    Motorcycle(
        id = 6,
        manufacturer = YAMAHA,
        logo = R.drawable.manufact_logo_yamaha,
        image = yamahaModels[0].image,
        model = yamahaModels[0],
    ),
    Motorcycle(
        id = 7,
        manufacturer = YAMAHA,
        logo = R.drawable.manufact_logo_yamaha,
        image = yamahaModels[1].image,
        model = yamahaModels[1],
    ),
)