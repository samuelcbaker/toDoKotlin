package br.com.frwk.todo.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog

class AlertUtils{

    companion object {
        fun alertMessage(
            context: Context,
            title: String,
            message: String?,
            affirmativeButtonText: String,
            affirmativeButton: () -> Any,
            negativeButtonText: String,
            negativeButton: () -> Any
        ) {
            val builder = AlertDialog.Builder(context)

            builder.setTitle(title)

            builder.setMessage(message)

            builder.setPositiveButton(affirmativeButtonText) { dialog, which ->
                affirmativeButton()
            }

            builder.setNegativeButton(negativeButtonText) { dialog, which ->
                negativeButton()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

}