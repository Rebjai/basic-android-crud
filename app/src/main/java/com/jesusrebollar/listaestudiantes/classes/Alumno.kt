package com.jesusrebollar.listaestudiantes.classes

import java.io.Serializable

data class Alumno (var nControl : String,
                   var nombre : String,
                   var carrera : String,
                   var grupo : String,
                   var semestre : String) : Serializable