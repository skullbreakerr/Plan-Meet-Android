package com.example.planmeet

import android.app.AlertDialog
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onShowClicked(view: View) {
        val itemCardLayout = view.parent as View
        val linkTextView: TextView = itemCardLayout.findViewById(R.id.linkTextView)

        linkTextView.visibility = View.VISIBLE

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Options")
        builder.setItems(arrayOf("Copy", "Share")) { _, position ->
            when (position) {
                0 -> copyToClipboard(linkTextView.text.toString())
                1 -> shareText(linkTextView.text.toString())
            }
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

    private fun copyToClipboard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.text = text
        Toast.makeText(this, "Link copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    private fun shareText(text: String) {
        val intent = android.content.Intent().apply {
            action = android.content.Intent.ACTION_SEND
            putExtra(android.content.Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(intent)
    }
}
