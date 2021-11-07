package com.example.qrscanner_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_detail.*

//상세 메뉴 VIEW
class Menu_detail_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_detail)

        tv_text.text = intent.getStringExtra("name") // Menus에서 선택한 메뉴 이름 get
    }
}