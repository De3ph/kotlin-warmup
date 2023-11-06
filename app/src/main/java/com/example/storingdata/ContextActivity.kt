package com.example.storingdata

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.storingdata.databinding.ActivityContextBinding

class ContextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContextBinding.inflate(layoutInflater);
        val root = binding.root
        setContentView(root)

        Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()

        val fromActivityIntent = intent.getStringExtra("fromActivity")?.toString()

        if (fromActivityIntent != null) {
            Toast.makeText(this@ContextActivity, "Camed from $fromActivityIntent", Toast.LENGTH_LONG)
                .show()

        }
    }

    fun showDialog(view: View) {
//        Toast.makeText(this@ContextActivity, "Clicked!", Toast.LENGTH_SHORT).show()
        val alert = AlertDialog.Builder(this@ContextActivity).setTitle("Alert Title")
            .setMessage("Alert Message")
            .setPositiveButton("Positive", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Toast.makeText(this@ContextActivity, "Positive!", Toast.LENGTH_SHORT).show();
                }
            })
            // her iki türlü de onClick tanımlanabiliyor
            .setNegativeButton(
                "Negative"
                // aşağıdaki _ (kullanılmayan parametreler böyle isimlendiriliyor) dialog, which parametreleri
            ) { _, _ ->
                Toast.makeText(
                    this@ContextActivity,
                    "Negative!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .show();
    }

    fun changeActivity(view: View) {
        // StoreDataActivity::class.java ile referans veriliyor
        val nextIntent = Intent(this@ContextActivity, StoreDataActivity::class.java)
        startActivity(nextIntent)
    }
}