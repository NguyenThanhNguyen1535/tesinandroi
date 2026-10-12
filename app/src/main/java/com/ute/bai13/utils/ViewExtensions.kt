package com.ute.bai13.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast

// Extension Function lấy chuỗi đã cắt khoảng trắng thừa từ EditText
fun EditText.trimmedText(): String {
    return this.text.toString().trim()
}

// Extension Function hiển thị Toast nhanh gọn cho Context
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}