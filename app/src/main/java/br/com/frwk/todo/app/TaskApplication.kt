package br.com.frwk.todo.app

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class ToDoApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        dbConfig()
    }

    fun dbConfig(){
        Realm.init(applicationContext)
        var c = RealmConfiguration.Builder()
        c.name("todo")
        c.schemaVersion(1)
        c.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(c.build())
    }
}