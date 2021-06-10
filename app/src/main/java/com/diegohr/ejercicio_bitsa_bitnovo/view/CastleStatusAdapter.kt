package com.diegohr.ejercicio_bitsa_bitnovo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegohr.ejercicio_bitsa_bitnovo.databinding.RowWindowStatusBinding
import com.diegohr.ejercicio_bitsa_bitnovo.model.WindowStatus

/**
 * Created by Diego Hernando on 10/6/21.
 */
class CastleStatusAdapter (private val castleStatus : Array<WindowStatus>) : RecyclerView.Adapter<CastleStatusAdapter.ViewHolder>() {

    class ViewHolder(val row: RowWindowStatusBinding) : RecyclerView.ViewHolder(row.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowWindowStatusBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.row.textviewWindowStatus.setText(castleStatus[position].status)
        holder.row.textviewPosition.setText(String.format("%02d", position+1))
    }

    override fun getItemCount(): Int {
        return castleStatus.size
    }


}