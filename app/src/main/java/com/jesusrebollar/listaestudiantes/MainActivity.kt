package com.jesusrebollar.listaestudiantes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.jesusrebollar.listaestudiantes.classes.Alumno
import com.jesusrebollar.listaestudiantes.classes.StudentList

class MainActivity : AppCompatActivity() {
    lateinit var buttonList: Button
    lateinit var buttonAdd: Button
    lateinit var nombre: TextView
    lateinit var nControl: TextView
    lateinit var carrera: TextView
    lateinit var semestre: TextView
    lateinit var grupo: TextView
    var alumno: Alumno? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
//        obtenemos los elementos de la vista y los asignamos a los atributos definidos
        setTextViews()


//        revisamos si se nos pasó un alumno a través del intent (editar / agregar)
        alumno = intent.getSerializableExtra("Alumno") as? Alumno
        if (alumno != null) {
            val actionBar = supportActionBar
            actionBar!!.setDisplayHomeAsUpEnabled(true)
            buttonAdd.text = "Editar Alumno"
            buttonList.text = "Eliminar Alumno"
//            obtener y mostrar los datos del alumno
            setStudentData()
//            definir los listeners para edición
            setEditButtonListeners()

        } else {
//        definimos los listeners del click de nuestros botones para agregar un nuevo registro
            setCreateButtonListeners()
            buttonList.visibility = View.INVISIBLE
        }


    }

    private fun setStudentData() {
        nombre.text = alumno!!.nombre
        nControl.text = alumno!!.nControl
        carrera.text = alumno!!.carrera
        semestre.text = alumno!!.semestre
        grupo.text = alumno!!.grupo
    }

    private fun setEditButtonListeners() {
        var studentIndex = StudentList.store.students.indexOf(alumno)
        buttonAdd.setOnClickListener {
            if (formIsValid()) {
//                buscar al estudiante para editar
                StudentList.store.students[studentIndex] = Alumno(
                    nControl.text.toString(), nombre.text.toString(),
                    carrera.text.toString(), grupo.text.toString(),
                    semestre.text.toString()
                )
                Toast.makeText(this, "Estudiante actualizado correctamente", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        buttonList.setOnClickListener {
            studentIndex = StudentList.store.students.indexOf(alumno)
            if (studentIndex != -1) {
                StudentList.store.students.removeAt(studentIndex)
                Toast.makeText(this, "Estudiante eliminado", Toast.LENGTH_SHORT)
                    .show()
                buttonAdd.visibility = View.INVISIBLE
                clearTextViews()
            }
            val intent = Intent(this, list_students::class.java)
            startActivity(intent)
//            finish the activity
            finish()
        }
    }

    private fun setCreateButtonListeners() {
//        buttonList.setOnClickListener {
//            val intent = Intent(this, list_students::class.java)
//            startActivity(intent)
//        }
        buttonAdd.setOnClickListener {
            Toast.makeText(this, "Adding", Toast.LENGTH_LONG)
            if (formIsValid()) {
                StudentList.store.students.add(
                    Alumno(
                        nControl.text.toString(), nombre.text.toString(),
                        carrera.text.toString(), grupo.text.toString(),
                        semestre.text.toString()
                    )
                )
                clearTextViews()
                Toast.makeText(this, "Estudiante añadido correctamente", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Por favor rellene todos los datos", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun clearTextViews() {
        nombre.text = ""
        nControl.text = ""
        carrera.text = ""
        semestre.text = ""
        grupo.text = ""
    }

    fun formIsValid(): Boolean {
        return !(nombre.text.isEmpty() ||
                nControl.text.isEmpty() ||
                carrera.text.isEmpty() ||
                semestre.text.isEmpty() ||
                grupo.text.isEmpty())
    }

    fun setTextViews() {
        buttonList = findViewById(R.id.openList);
        buttonAdd = findViewById(R.id.addStudentbtn);
        nombre = findViewById(R.id.studentName)
        nControl = findViewById(R.id.nControl)
        carrera = findViewById(R.id.carrera)
        semestre = findViewById(R.id.semester)
        grupo = findViewById(R.id.grupo)
    }
}