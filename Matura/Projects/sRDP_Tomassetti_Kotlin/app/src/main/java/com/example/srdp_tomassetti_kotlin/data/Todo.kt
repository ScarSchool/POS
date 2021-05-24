package com.example.srdp_tomassetti_kotlin.data

data class Todo (
    var userId : Int,
    var id : Int,
    var title : String,
    var completed : Boolean
){
    constructor() : this(-1, -1, "pirni", false)
}

