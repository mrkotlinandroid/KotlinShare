package android.com.sharedata

import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.content.Intent
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sharetext(view: View) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, "http://google.com/careers")
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, "send to "))

    }

    fun shareimage(view: View) {

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_STREAM, Utitlities.geturi("apple",this))
        shareIntent.type = "image/jpeg"
        startActivity(Intent.createChooser(shareIntent, ""))
    }
    fun sharemultipleimage(view: View) {
        val imageUris = ArrayList<Uri>()
            imageUris.add(Utitlities.geturi("android", this)) // Add your image URIs here
        imageUris.add(Utitlities.geturi("windows", this))
        imageUris.add(Utitlities.geturi("apple", this))

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND_MULTIPLE
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris)
        shareIntent.type = "image/*"
        startActivity(Intent.createChooser(shareIntent, "Share images to "))
    }

    class Utitlities {
        companion object {
            fun geturi(name: String, ctx: Context): Uri {
                var pname = ctx.packageName
                val uri1 = Uri.parse("android.resource://" + pname + "/drawable/" + name)
                val uri = Uri.parse("android.resource://"+pname+"/drawable/"+name)

                Log.d("uri", uri.toString())
                Toast.makeText(ctx,uri.toString(),Toast.LENGTH_SHORT).show()
                return uri
            }
        }
    }
}

