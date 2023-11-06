package com.example.storingdata

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.storingdata.databinding.ActivityStoredataBinding

class StoreDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoredataBinding
    lateinit var sharedPreferences: SharedPreferences
    var ageFromPref : Int? = null
    val KEY = "age"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoredataBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("com.example.storingdata", MODE_PRIVATE)

        ageFromPref = sharedPreferences.getInt(KEY,-1)

        if (ageFromPref != -1){
            binding.ageView.text = "Age : $ageFromPref"
        }

    }

    fun changeActivity(view: View){
        val intent : Intent = Intent(this@StoreDataActivity, ContextActivity::class.java)
        intent.putExtra("fromActivity","StoreDataActivity")
        startActivity(intent)
    }

    fun save(view: View) {
        /*
        * ?: -> Elvis operator
        *
        * x ?: y
        *
        * eğer x değeri null ise y kısmı işletiliyor, döndürülüyor
        * değilse x değeri dönüyor
        *
        * aşağıdaki örnek için açıklama şu:
        *
        *   eğer "binding.textInput.text.toString().toIntOrNull()" null döndürürse return yap
        *
        * */
        val userAge = binding.textInput.text.toString().toIntOrNull() ?: return

        sharedPreferences.edit().putInt(KEY, userAge).apply()
        binding.ageView.text = "Age : $userAge"
    }

    fun delete(view: View) {
        if (sharedPreferences.getInt("age",-1) == -1) return
        sharedPreferences.edit().remove("age").apply()
        binding.ageView.text = "Age : "
    }
}