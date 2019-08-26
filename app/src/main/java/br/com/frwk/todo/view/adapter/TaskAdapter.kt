package br.com.frwk.todo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.frwk.todo.R
import br.com.frwk.todo.model.task.Task
import br.com.frwk.todo.utils.extensions.format
import kotlinx.android.synthetic.main.row_tasks.view.*
import java.util.*

interface TaskListener{
    fun onDeleteClick(task: Task)

    fun onEditClick(task: Task)
}

class TaskAdapter(private var context: Context,private var list: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    private var listener = context as TaskListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_tasks, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {return list.size}

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var task = list.get(position)

        holder.description.setText(task.description)
        holder.date.setText(Date(task.date).format())
        holder.status.setImageResource(if (task.status) R.drawable.ic_check_circle else R.drawable.ic_unchecked_circle)

        holder.status.setOnClickListener {
            task.status = !task.status
            notifyItemChanged(position)
        }

        holder.btnDelete.setOnClickListener{
            listener.onDeleteClick(task)
        }

        holder.btnEdit.setOnClickListener{
            listener.onEditClick(task)
        }
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description = view.taskDescription
        val date = view.taskDate
        val status = view.taskStatus
        val btnDelete = view.btnDelete
        val btnEdit = view.btnEdit
    }
}