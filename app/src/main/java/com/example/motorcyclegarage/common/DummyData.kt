package com.example.motorcyclegarage.common

import com.example.motorcyclegarage.R
import com.example.motorcyclegarage.data.model.motorcycle.Manufacturer
import com.example.motorcyclegarage.data.model.motorcycle.Model
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import com.example.motorcyclegarage.motorcycle.MotorcycleProvider.modelBmwR1250GS
import com.example.motorcyclegarage.motorcycle.MotorcycleProvider.modelBmwS100RR
import com.example.motorcyclegarage.motorcycle.MotorcycleProvider.modelSuzukiGsxR1000
import com.example.motorcyclegarage.motorcycle.MotorcycleProvider.modelSuzukiVStrom1050
import com.example.motorcyclegarage.motorcycle.MotorcycleProvider.modelYamahaMt09
import com.example.motorcyclegarage.motorcycle.MotorcycleProvider.modelYamahaYzfR1

val modelSuzukiGsxR1000Dummy = Model(
    id = 1,
    name = "GSX-R1000",
    manufacturerId = 2,
    image = R.drawable.suzuki_model_gsx_r1000,
    type = "Sport",
    power = "199 hp",
    year = "2023",
)
val modelSuzukiVStrom1050Dummy = Model(
    id = 2,
    name = "V Strom 1050",
    manufacturerId = 2,
    image = R.drawable.suzuki_model_v_strom_1050,
    type = "Adventure",
    power = "107 hp",
    year = "2022",
)
val manufacturerSuzukiDummy = Manufacturer(
    id = 2,
    name = "SUZUKI",
    logo = R.drawable.manufact_logo_suzuki,
    models = listOf(modelSuzukiGsxR1000Dummy, modelSuzukiVStrom1050Dummy)
)
val motorcycleDummy = Motorcycle(
    id = 1,
    manufacturer = manufacturerSuzukiDummy,
    logo = R.drawable.manufact_logo_suzuki,
    image = R.drawable.suzuki_model_v_strom_1050,
    model = modelSuzukiVStrom1050Dummy,
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
    id = 1,
    manufacturer = manufacturerBmw,
    logo = R.drawable.manufact_logo_bmw,
    image = R.drawable.bmw_model_s1000rr,
    model = modelBmwS100RR,
)

val motorcycle2 = Motorcycle(
    id = 2,
    manufacturer = manufacturerBmw,
    logo = R.drawable.manufact_logo_bmw,
    image = R.drawable.bmw_model_r1250gs,
    model = modelSuzukiGsxR1000Dummy,
)

val motorcycle3 = Motorcycle(
    id = 3,
    manufacturer = manufacturerYamaha,
    logo = R.drawable.manufact_logo_yamaha,
    image = R.drawable.yamaha_model_yzf_r1,
    model = modelYamahaMt09,
)

val motorcycle4 = Motorcycle(
    id = 4,
    manufacturer = manufacturerYamaha,
    logo = R.drawable.manufact_logo_yamaha,
    image = R.drawable.yamaha_model_yzf_r1,
    model = modelYamahaYzfR1,
)

val motorcycle5 = Motorcycle(
    id = 5,
    manufacturer = manufacturerSuzuki,
    logo = R.drawable.manufact_logo_suzuki,
    image = R.drawable.suzuki_model_gsx_r1000,
    model = modelSuzukiGsxR1000,
)

val motorcycle6 = Motorcycle(
    id = 6,
    manufacturer = manufacturerSuzuki,
    logo = R.drawable.manufact_logo_suzuki,
    image = R.drawable.suzuki_model_v_strom_1050,
    model = modelSuzukiVStrom1050,
)