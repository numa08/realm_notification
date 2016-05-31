package net.numa08.realm_notification

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.*

class MainActivity : AppCompatActivity() {

    val realmConfig by lazy { RealmConfiguration.Builder(this).inMemory().build() }
    val realm by lazy { Realm.getInstance(realmConfig) }
    var stop: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm.executeTransaction { r ->
            val d = (0..100).map { SimpleData(UUID.randomUUID().toString()) }
            r.copyToRealmOrUpdate(d)
        }

        setContentView(R.layout.activity_main)
        Thread({
            val realm = Realm.getInstance(realmConfig)
            while(!stop) {
                val data = SimpleData(UUID.randomUUID().toString())
                realm.executeTransaction { it.copyToRealmOrUpdate(data) }
                Thread.sleep(1000)
            }
            realm.close()
        }).start()
        val data = realm.where(SimpleData::class.java).findAll()
        val adapter = SimpleDataAdapter(this, data)
        (findViewById(android.R.id.list) as? ListView)?.adapter = adapter
    }

    override fun onDestroy() {
        stop = true
        realm.close()
        super.onDestroy()
    }
}
