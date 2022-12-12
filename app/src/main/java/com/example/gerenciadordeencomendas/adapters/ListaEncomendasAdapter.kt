package com.example.gerenciadordeencomendas.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciadordeencomendas.R
import com.example.gerenciadordeencomendas.databinding.ItemEncomendaBinding
import com.example.gerenciadordeencomendas.model.Encomenda

class ListaEncomendasAdapter(
    private val context: Context,
    encomenda: List<Encomenda> = emptyList(),
    var quandoClicarNoItem: (encomenda: Encomenda) -> Unit = {},
    var quandoSegurarNoItem: (encomenda: Encomenda) -> Unit = {}
) : RecyclerView.Adapter<ListaEncomendasAdapter.ViewHolder>() {


    private val encomenda = encomenda.toMutableList()

    inner class ViewHolder(private val binding: ItemEncomendaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var encomenda: Encomenda

        init {
            itemView.setOnClickListener {
                if (::encomenda.isInitialized) {
                    quandoClicarNoItem(encomenda)
                }
            }

            itemView.setOnLongClickListener {
                if (::encomenda.isInitialized) {
                    quandoSegurarNoItem(encomenda)
                    binding.itemEncomendaCardview.setBackgroundColor(Color.parseColor("#b2b2b2"))
                }
                true
            }

        }

        fun vincula(encomenda: Encomenda) {
            this.encomenda = encomenda
            val nomePacote = binding.itemEncomendaNomePacote
            nomePacote.text = encomenda.nomePacote

            val status = binding.itemEncomendaStatus
            status.text = encomenda.status

            val dataAtualizado = binding.itemEncomendaData
            dataAtualizado.text = encomenda.dataAtualizado

            if (encomenda.status == "Objeto entregue ao destinatário") {
                binding.itemEncomendaIcon.setBackgroundResource(R.drawable.ic_packageentregueverde)
                nomePacote.setTextColor(Color.parseColor("#7E7E7E"))
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemEncomendaBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val encomenda = encomenda[position]
        holder.vincula(encomenda)
    }

    override fun getItemCount(): Int = encomenda.size

    @SuppressLint("NotifyDataSetChanged")
    fun atualiza(encomenda: List<Encomenda>) {
        this.encomenda.clear()
        this.encomenda.addAll(encomenda)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun remove(encomenda: Encomenda) {
        this.encomenda.remove(encomenda)
        notifyDataSetChanged()
    }

}


