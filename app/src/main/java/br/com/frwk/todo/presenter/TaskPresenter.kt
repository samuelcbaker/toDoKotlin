package br.com.frwk.todo.presenter

import br.com.frwk.todo.model.task.Task
import br.com.frwk.todo.model.task.TaskBusiness

class TaskPresenter{
    lateinit var taskContract: TaskContract

    fun setContract(contract: TaskContract){
        this.taskContract = contract
    }

    fun saveTask(task: Task){
        if (TaskBusiness.saveTask(task)) {
            taskContract.taskSaved()
        } else {
            taskContract.requestError("Erro ao salvar tarefa.")
        }

    }

    fun deleteTask(task: Task){
        if (TaskBusiness.deleteTask(task)) {
            taskContract.taskDeleted()
        } else {
            taskContract.requestError("Erro ao deletar tarefa.")
        }
    }

    fun getTask(id: Long){

        val task = TaskBusiness.getTask(id)

        if (task != null) {
            taskContract.taskLoaded(task)
        } else {
            taskContract.requestError("Erro ao carregar tarefa.")
        }

    }

    fun getTasks() {

        val tasks = TaskBusiness.getTasks()

        taskContract.tasksLoaded(tasks)
    }

    fun deleteTasks() {
        if(TaskBusiness.deleteTasks()){
            taskContract.tasksDeleted()
        } else {
            taskContract.requestError("Erro ao deletar todas as tarefas")
        }
    }

    fun getTasksByStatus(status: Boolean) {
        val tasks = TaskBusiness.getTasksByStatus(status)

        if (tasks.isNotEmpty()) {
            taskContract.tasksLoaded(tasks)
        } else {
            taskContract.requestError("Erro ao carregar a lista de tarefas pelo status.")
        }
    }
}