package com.jesusrebollar.listaestudiantes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jesusrebollar.listaestudiantes.classes.Alumno
import com.jesusrebollar.listaestudiantes.classes.StudentList
import com.jesusrebollar.listaestudiantes.classes.StudentListAdapter

class list_students : AppCompatActivity() {
    val studentsList: List<Alumno> = StudentList.store.students

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_students)
        findViewById<Button>(R.id.addButton).setOnClickListener {
            openAddActivity()
        }
        initRecycler()
    }

    fun initRecycler() {
        val rv = findViewById<RecyclerView>(R.id.rvStudentList)
        val adapter = StudentListAdapter(studentsList)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }
    fun openAddActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}