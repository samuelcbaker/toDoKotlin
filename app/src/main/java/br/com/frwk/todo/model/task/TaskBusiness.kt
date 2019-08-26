package br.com.frwk.todo.model.task

import com.vicpin.krealmextensions.*
import io.realm.Sort
import java.lang.Exception

object TaskBusiness{

    fun saveTask(task: Task) : Boolean {
        try {
            task.save()
        }catch (e: Exception){
            e.printStackTrace()
            return false
        }
        return true
    }

    fun deleteTask(task: Task) : Boolean{
        try {
            Task().delete{ equalTo("id", task.id) }
        } catch (e: Exception){
            e.printStackTrace()
            return false
        }
        return true
    }

    fun getTask(id: Long) : Task? {
        try {
            return Task().queryFirst{equalTo("id", id)}!!
        }catch (e: Exception){
            e.printStackTrace()
            return null
        }
    }

    fun getTasks() : List<Task> {
        try {
            return Task().querySorted("date", Sort.DESCENDING)
        }catch (e: Exception){
            e.printStackTrace()
            return ArrayList()
        }
    }

    fun deleteTasks() : Boolean {
        try {
            Task().deleteAll()
        } catch (e: Exception){
            e.printStackTrace()
            return false
        }
        return true
    }

    fun getTasksByStatus(status: Boolean) : List<Task>{
        try {
            return Task().query { equalTo("status", status) }
        } catch (e: Exception){
            e.printStackTrace()
            return ArrayList()
        }
    }


}