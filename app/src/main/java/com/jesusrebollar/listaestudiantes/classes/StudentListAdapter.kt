package com.jesusrebollar.listaestudiantes.classes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jesusrebollar.listaestudiantes.MainActivity
import com.jesusrebollar.listaestudiantes.R
import com.jesusrebollar.listaestudiantes.list_students


class StudentListAdapter (val students:List<Alumno>) : RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.student_list_element, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(students[position])
    }

    override fun getItemCount(): Int {
        return students.size
    }
    class ViewHolder(val view: View):RecyclerView.ViewHolder(view){

        fun render(student: Alumno) {
            view.findViewById<TextView>(R.id.student_name).text = student.nombre
            view.findViewById<TextView>(R.id.student_control_number).text = student.nControl
            view.findViewById<TextView>(R.id.student_carreer).text = student.carrera
            view.findViewById<TextView>(R.id.student_semester_info).text = student.semestre + "° semestre"
            view.findViewById<TextView>(R.id.student_group_info).text = student.grupo
            view.setOnClickListener {
                val intent = Intent(view.context,MainActivity::class.java)
                intent.putExtra("Alumno",student)
                view.context.startActivity(intent)
            }
        }
    }
}