package br.com.frwk.todo.presenter

import br.com.frwk.todo.model.task.Task

interface TaskContract{

    fun requestError(message: String)

    fun taskLoaded(task: Task)

    fun tasksLoaded(tasks: List<Task>)

    fun taskSaved()

    fun tasksDeleted()

    fun taskDeleted()
}