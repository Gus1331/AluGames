package com.gus1331.models.cheapsharkapi

data class JogoPesquisa(val external:String, val thumb:String){
    override fun toString(): String {
        return "JogoPesquisa(external='$external', thumb='$thumb')"
    }
}

