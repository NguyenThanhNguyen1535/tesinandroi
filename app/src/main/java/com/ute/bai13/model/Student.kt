package com.ute.bai13.model

import java.io.Serializable

// Data Class quản lý thực thể sinh viên, kế thừa Serializable để lưu Bundle
data class Student(
    val id: String,
    val name: String,
    val className: String,
    val email: String,
    val gpa: Double
) : Serializable {
    val isHonorStudent: Boolean
        get() = gpa >= 3.6
}

// Extension Function xếp loại học lực
fun Double.toAcademicRanking(): String {
    return when (this) {
        in 3.6..4.0 -> "Xuất"
        in 3.2..3.59 -> "Giỏi"
        in 2.5..3.19 -> "Khá"
        in 2.0..2.49 -> "Trung bình"
        else -> "Yếu"
    }
}