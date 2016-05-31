package net.numa08.realm_notification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.RealmBaseAdapter
import io.realm.RealmResults

class SimpleDataAdapter(context: Context, realmResults: RealmResults<SimpleData>)
    : RealmBaseAdapter<SimpleData>(context, realmResults) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
        = (convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false))?.apply {
        (findViewById(android.R.id.text1) as? TextView)?.text = getItem(position).date.time.toString()
    }
}