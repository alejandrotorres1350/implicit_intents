package com.example.implicit_intent

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ShareCompat
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val website_Edit = findViewById<EditText>(R.id.website_editText)
        val location_Edit = findViewById<EditText>(R.id.location_editText)
        val sharetext_Edit = findViewById<EditText>(R.id.sharetext_editText)

        val website_button = findViewById<Button>(R.id.websiteButton)
        val location_button = findViewById<Button>(R.id.locationButton)
        val share_button = findViewById<Button>(R.id.shareButton)
        val picture_button = findViewById<Button>(R.id.pictureButton)


        website_button.setOnClickListener {
            var url: String = website_Edit.text.toString()
            var webpage: Uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            if (intent.resolveActivity(packageManager)!= null){
                startActivity(intent)
            }else{
                Log.d("ImplicitIntent", "Can't handle this!")
            }
        }

        location_button.setOnClickListener {
            var loc: String = location_Edit.text.toString()
            var addressU: Uri = Uri.parse("geo:0,0?q=" + loc)
            val intent = Intent(Intent.ACTION_VIEW, addressU)
            if (intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }else{
                Log.d("ImplicitIntent", "Can't handle this!")
            }
        }

        share_button.setOnClickListener {
            var texto:String = sharetext_Edit.text.toString()
            var tipo:String = "text/plain"
            ShareCompat.IntentBuilder
                .from(this)
                .setType(tipo)
                .setChooserTitle(R.string.share_text_with)
                .setText(texto)
                .startChooser()
        }

        picture_button.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }else{
                Log.d("ImplicitIntent", "Can't handle this!")
            }
        }
    }
}
