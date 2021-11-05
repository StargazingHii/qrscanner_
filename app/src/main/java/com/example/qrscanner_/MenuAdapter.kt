package com.example.qrscanner_

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter (val menuList: ArrayList<Menus>) : RecyclerView.Adapter<MenuAdapter.CustomViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_list,parent,false) //menulist를 가져와서 adapter에 붙혀줌
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuAdapter.CustomViewHolder, position: Int) {
        holder.menu_img.setImageResource(menuList.get(position).menu_img)
        holder.name.text = menuList.get(position).menu
        holder.price.text = menuList.get(position).price.toString()
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val menu_img = itemView.findViewById<ImageView>(R.id.menu_img_id) // 메뉴 이미지
        val name = itemView.findViewById<TextView>(R.id.menuname_id) // 메뉴 이름
        val price = itemView.findViewById<TextView>(R.id.price_id)// 메뉴 가
    }
}