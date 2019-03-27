package com.ipc.aidl_client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.ipc.aidl_service.IMyAidlInterface
import com.ipc.aidl_service.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var iMyAidlInterface: IMyAidlInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            try {
                iMyAidlInterface.addPerson(Person("name", 111))
                val persons = iMyAidlInterface.personList
                Log.i(TAG, persons.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bindService()
    }

    private fun bindService() {
        val intent = Intent()
        intent.component = ComponentName("com.ipc.aidl_service", "com.ipc.aidl_service.MyAidlService")
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
        }

    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
}
