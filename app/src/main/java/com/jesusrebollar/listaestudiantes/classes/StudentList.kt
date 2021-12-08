package com.jesusrebollar.listaestudiantes.classes

class StudentList {
    object store {
        var students: MutableList<Alumno> = mutableListOf(
            Alumno("17920350", "Jaime de Jesús Pacheco Rebollar", "Ingeniería Informática", "I9A", "9")
        )
    }


}