package com.nicolabs.notificationsoreo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //get an instance of notification manager
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        createNotificationChannel()

        fab.setOnClickListener { notifyUser() }
    }

    private fun createNotificationChannel() {
        //create a notification channel only if SDK version is 26 above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //set the ID of the channel
            val id = "my_channel_01"

            //set the user-visible name of the channel
            val name = "This is my channel"

            //set the channel's importance using IMPORTANCE* constants from NotificationManager
            val importance = NotificationManager.IMPORTANCE_HIGH

            //you can now instantiate of NotificationChannel using channel ID, channel name, and
            // channel importance
            val channel = NotificationChannel(id, name, importance)

            //configure the notification channel
            channel.description = "This is a test description"
            channel.enableLights(true) //use the notification led in your phone, if there's one
            channel.lightColor = Color.RED //set the notification light color to red, if there's one
            channel.enableVibration(true)

            //create the notification channel
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun notifyUser() {

        //set the channel ID you'll use
        val CHANNEL_ID = "my_channel_01"

        //create a notification builder
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.abc_ic_star_black_16dp)
                .setContentTitle("This is my content title")
                .setContentText("This is my content text")

        //set a notification id. it is a unique integer your app uses to identify the notification
        val notifId = 1
        notificationManager.notify(notifId, builder.build())

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
