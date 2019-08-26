package br.com.frwk.todo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.frwk.todo.R

class TaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
    }
}
