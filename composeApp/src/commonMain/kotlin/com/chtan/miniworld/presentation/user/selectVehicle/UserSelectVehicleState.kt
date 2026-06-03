package com.chtan.miniworld.presentation.user.selectVehicle

import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.car3
import miniworld.composeapp.generated.resources.car4
import miniworld.composeapp.generated.resources.car5
import miniworld.composeapp.generated.resources.car6


data class UserSelectVehicleState(
    val mapDetails: List<MapDetails> = listOf(
        MapDetails(
            "1",
            "https://skoda-motorsport.s3.amazonaws.com/com/2018/05/15891900363_3b37e115d5_k-1920x1280.jpg"
        ),
        MapDetails(
            "2",
            "https://c8.alamy.com/comp/2JJPT0W/a-closeup-of-an-off-road-race-car-in-the-desert-in-las-vegas-nv-usa-2JJPT0W.jpg"
        ),
        MapDetails(
            "3",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmuMHWWR7QBzSu3FM1970WoxajJYXVJdAOiw&s"
        ),
        MapDetails(
            "4",
            "https://www.cnet.com/a/img/hub/2016/01/26/8c4309b5-ec2f-42ca-a77d-d60efbbdbcc8/credit-kathy-durrett-koh-201527.jpg"
        ),

    ),
    val vehicleDetails : List<VehicleDetails> =
        listOf(
            VehicleDetails("1", img = Res.drawable.car3),
            VehicleDetails("1", img = Res.drawable.car4),
            VehicleDetails("1", img = Res.drawable.car5),
            VehicleDetails("1", img = Res.drawable.car6),

    )
)
