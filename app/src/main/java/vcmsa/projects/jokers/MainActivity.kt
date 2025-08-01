package vcmsa.projects.jokers

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Category
        val cbProgramming = findViewById<CheckBox>(R.id.cbProgramming)
        val cbMisc = findViewById<CheckBox>(R.id.cbMisc)
        val cbDark = findViewById<CheckBox>(R.id.cbDark)

        // Type
        val cbSingle = findViewById<CheckBox>(R.id.cbSingle)
        val cbTwoPart = findViewById<CheckBox>(R.id.cbTwoPart)

        // Filter
        val cbNsfw = findViewById<CheckBox>(R.id.cbNsfw)
        val cbReligious = findViewById<CheckBox>(R.id.cbReligious)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnClear = findViewById<Button>(R.id.btnClear)

        tvResult = findViewById(R.id.tvResult)

        btnSubmit.setOnClickListener {
            // Pick Joke Category
            val selectedCategories = mutableListOf<String>()
            if (cbProgramming.isChecked) selectedCategories.add("Programming")
            if (cbMisc.isChecked) selectedCategories.add("Misc")
            if (cbDark.isChecked) selectedCategories.add("Dark")

            if (selectedCategories.isEmpty()) {
                Toast.makeText(this, "Please select at least one category.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Pick joke type
            val jokeType = when {
                cbSingle.isChecked && !cbTwoPart.isChecked -> "single"
                !cbSingle.isChecked && cbTwoPart.isChecked -> "twopart"
                cbSingle.isChecked && cbTwoPart.isChecked -> null
                else -> null
            }

            // Pick Joke Filter
            val blacklistFlags = mutableListOf<String>()
            if (cbNsfw.isChecked) blacklistFlags.add("nsfw")
            if (cbReligious.isChecked) blacklistFlags.add("religious")

            val categoriesString = selectedCategories.joinToString(",")
            val blacklistString = if (blacklistFlags.isNotEmpty()) blacklistFlags.joinToString(",") else null

            fetchJoke(categoriesString, jokeType, blacklistString)
        }


        btnClear.setOnClickListener {
            cbProgramming.isChecked = false
            cbMisc.isChecked = false
            cbDark.isChecked = false
            cbSingle.isChecked = false
            cbTwoPart.isChecked = false
            cbNsfw.isChecked = false
            cbReligious.isChecked = false
            tvResult.text = ""
        }
    }

    private fun fetchJoke(categories: String?, type: String?, blacklist: String?) {
        val call = RetrofitInstance.api.getJoke(categories?.ifEmpty { null }, type, blacklist?.ifEmpty { null })
        call.enqueue(object : Callback<JokeResponse> {
            override fun onResponse(call: Call<JokeResponse>, response: Response<JokeResponse>) {
                val body = response.body()
                if (body != null) {
                    tvResult.text = when (body.type) {
                        "single" -> body.joke
                        "twopart" -> "${body.setup}\n\n${body.delivery}"
                        else -> "No joke found."
                    }
                } else {
                    tvResult.text = "No joke found, sorry."
                }
            }

            override fun onFailure(call: Call<JokeResponse>, t: Throwable) {
                tvResult.text = "Sorry no jokes for you: ${t.message}"
            }
        })
    }
}