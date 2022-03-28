package edu.itsco.miagendaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.itsco.miagendaapp.adapters.ContactosAdapter
import edu.itsco.miagendaapp.modelos.Contacto

class MainActivity : AppCompatActivity() {

    private  val adaptadorContactos: ContactosAdapter = ContactosAdapter(ArrayList<Contacto>())

    private val startForResult =registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            val intent = it.data
            val contacto: Contacto? = intent?.getParcelableExtra<Contacto>("CONTACTO")
            adaptadorContactos.addcontacto(contacto!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerContactos:RecyclerView = findViewById(R.id.recycler_contacto)
        recyclerContactos.adapter = adaptadorContactos

        recyclerContactos.layoutManager = LinearLayoutManager(this)
        recyclerContactos.setHasFixedSize(true)

        val fabAddContacto: FloatingActionButton = findViewById(R.id.fab_add_contacto)
        fabAddContacto.setOnClickListener{
            val intent = Intent(this, NuevoContactoActivity::class.java)
            startForResult.launch(intent)
        }
    }
}