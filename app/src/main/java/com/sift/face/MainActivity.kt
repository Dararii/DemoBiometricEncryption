package com.sift.face

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity() {

    private val TAG = "MAIN_ACTIVITY"
    private val mFirestoreSetting: FirebaseFirestoreSettings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(false)
        .setSslEnabled(true)
        .build()
    private val mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirestore.firestoreSettings = mFirestoreSetting

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        val perms = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "Permission Needed", 2, *perms)
        }

        button.setOnClickListener {
            startActivity(Intent(this, DetectionActivity::class.java))
        }

    }

    //AES test
    /*val text = "this is data"
            val pass = "pass"
            val en = AesLib.encrypt(text, pass)
            Log.d(TAG, "encrypted $en")
            val de = AesLib.decrypt(en, pass)
            Log.d(TAG, "decrypted $de")*/
}
