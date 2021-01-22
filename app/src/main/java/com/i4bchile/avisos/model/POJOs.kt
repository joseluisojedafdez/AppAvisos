package com.i4bchile.avisos.model


data class Ad(
    val namePublisher:String,
    val pyme:String,
    val category:String,
    val details: Detail,
    val active:Boolean,
    val rating:Int,
    val numEval:Int)

data class Detail(
    val title:String,
    val description:String,
    val email:String,
    val cellPhone:String,
    val webSite:String,
    val address:String,
    val city:String)

data class Evaluation(val namePublisher:String, val rating:Int, val comment:String)

data class User(val userName:String,val email:String)