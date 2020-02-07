package br.com.frwk.todo.view.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.frwk.todo.R
import br.com.frwk.todo.model.task.Task
import br.com.frwk.todo.presenter.TaskContract
import br.com.frwk.todo.presenter.TaskPresenter
import br.com.frwk.todo.utils.AlertUtils
import br.com.frwk.todo.utils.extensions.format
import br.com.frwk.todo.view.adapter.TaskAdapter
import br.com.frwk.todo.view.adapter.TaskListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_tasks.*
import java.util.*

class MainActivity : AppCompatActivity(), TaskContract, TaskListener {

    lateinit var presenter: TaskPresenter

    lateinit var adapter: TaskAdapter

    lateinit var selectedDate: Date


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        presenter.getTasks()

    }

    fun setupUI(){
        presenter = TaskPresenter()
        presenter.setContract(this)

        btnAdd.setOnClickListener{
            onButtonAddClick()
        }
    }

    override fun requestError(message: String) {

    }

    override fun taskLoaded(task: Task) {
    }

    override fun tasksLoaded(tasks: List<Task>) {
        if(tasks.isNotEmpty()){
            textViewNoTasks.visibility = View.GONE
            recyclerViewTasks.visibility = View.VISIBLE
            loadRecyclerTasks(tasks)
        } else {
            textViewNoTasks.visibility = View.VISIBLE
            recyclerViewTasks.visibility = View.GONE
        }
    }

    override fun taskSaved() {
    }

    override fun tasksDeleted() {
    }

    override fun taskDeleted() {
    }

    override fun onDeleteClick(task: Task) {
        AlertUtils.alertMessage(this,
            "Excluir tarefa",
            "Você tem certeza que deseja excluir essa tarefa?",
            "Sim",
            {
                presenter.deleteTask(task)
                presenter.getTasks()
            },
            "Não",
            { })
    }

    override fun onEditClick(task: Task) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.activity_task, null)
        dialogBuilder.setView(dialogView)

        val buttonDate = dialogView.findViewById<Button>(R.id.buttonDate)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextDescription)

        //buttonDate.text = task.date.toLong().form

        buttonDate.setOnClickListener{
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR)
            val minute = c.get(Calendar.MINUTE)
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view, y, m, d ->
                TimePickerDialog(this,TimePickerDialog.OnTimeSetListener{ view, h, min ->

                    val calendar = Calendar.getInstance()
                    calendar.set(y,m,d,h,min)
                    selectedDate = calendar.time
                    buttonDate.text =  selectedDate.format()

                },hour,minute,true).show()
            }, year, month, dayOfMonth).show()
        }

        dialogBuilder.setTitle(resources.getString(R.string.new_task_title))
        dialogBuilder.setPositiveButton(resources.getString(R.string.save)) { dialog, whichButton ->
            presenter.saveTask(Task(null, editTextDescription.text.toString(), selectedDate.time, false))
            presenter.getTasks()
        }

        dialogBuilder.setNegativeButton(resources.getString(R.string.cancel)) { dialog, whichButton -> }



        val b = dialogBuilder.create()
        b.show()
    }

    private fun loadRecyclerTasks(tasks: List<Task>){
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        recyclerViewTasks.adapter = TaskAdapter(this, tasks)
    }

    private fun onButtonAddClick(){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.activity_task, null)
        dialogBuilder.setView(dialogView)

        val buttonDate = dialogView.findViewById<Button>(R.id.buttonDate)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextDescription)

        buttonDate.setOnClickListener{
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR)
            val minute = c.get(Calendar.MINUTE)
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view, y, m, d ->
                TimePickerDialog(this,TimePickerDialog.OnTimeSetListener{ view, h, min ->

                    val calendar = Calendar.getInstance()
                    calendar.set(y,m,d,h,min)
                    selectedDate = calendar.time
                    buttonDate.text = selectedDate.format()

                },hour,minute,true).show()
            }, year, month, dayOfMonth).show()
        }

        dialogBuilder.setTitle(resources.getString(R.string.new_task_title))
        dialogBuilder.setPositiveButton(resources.getString(R.string.save)) { dialog, whichButton ->
            presenter.saveTask(Task(null, editTextDescription.text.toString(), selectedDate.time, false))
            presenter.getTasks()
        }

        dialogBuilder.setNegativeButton(resources.getString(R.string.cancel)) { dialog, whichButton -> }

        val b = dialogBuilder.create()
        b.show()
    }
}
