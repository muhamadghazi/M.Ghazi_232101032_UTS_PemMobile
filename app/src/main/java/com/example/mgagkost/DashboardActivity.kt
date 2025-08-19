package com.example.mgagkost

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mgagkost.R


class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val ivBack = findViewById<ImageView>(R.id.ivBack)
        ivBack.setOnClickListener {
            finish() // kembali ke activity sebelumnya
        }

        val kostList = listOf(
            Kost("Kamar Standard", "Rp 1.500.000/bulan", R.drawable.kost_standard,
                "Kasur single, Lemari, Meja belajar, Kamar mandi dalam"),
            Kost("Kamar Deluxe", "Rp 2.500.000/bulan", R.drawable.kost_deluxe,
                "Kasur double, Lemari besar, Meja belajar, AC, Kamar mandi dalam"),
            Kost("Kamar Executive", "Rp 3.500.000/bulan", R.drawable.kost_executive,
                "Kasur double premium, Lemari besar, Meja kerja, AC, TV, Kamar mandi dalam dengan water heater")
        )

        val lvKosts = findViewById<ListView>(R.id.lvKosts)
        val adapter = KostAdapter(this, kostList)
        lvKosts.adapter = adapter

        lvKosts.setOnItemClickListener { _, _, position, _ ->
            val selectedKost = kostList[position]
            Toast.makeText(this, "Anda memilih: ${selectedKost.name}", Toast.LENGTH_SHORT).show()
        }
    }
}

data class Kost(
    val name: String,
    val price: String,
    val image: Int,
    val description: String
)

class KostAdapter(context: Context, private val kostList: List<Kost>) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = kostList.size
    override fun getItem(position: Int): Any = kostList[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(R.layout.item_kost, parent, false)

        val ivKost = view.findViewById<ImageView>(R.id.ivKost)
        val tvName = view.findViewById<TextView>(R.id.tvKostName)
        val tvPrice = view.findViewById<TextView>(R.id.tvKostPrice)
        val tvDesc = view.findViewById<TextView>(R.id.tvKostDesc)

        val kost = kostList[position]

        ivKost.setImageResource(kost.image)
        tvName.text = kost.name
        tvPrice.text = kost.price
        tvDesc.text = kost.description

        return view
    }
}
