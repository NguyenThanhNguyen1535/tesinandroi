package com.ute.bai13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.bai13.databinding.ActivityMainBinding
import com.ute.bai13.model.Student
import com.ute.bai13.model.toAcademicRanking
import com.ute.bai13.utils.toast
import com.ute.bai13.utils.trimmedText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val KEY_STUDENT = "KEY_STUDENT"
    }

    private val defaultStudent = Student(
        id = "2415053122331",
        name = "Nguyen Thanh Nguyen",
        className = "24T3",
        email = "nguyen.nv@ute.udn.vn",
        gpa = 3.75
    )

    private var currentStudent = defaultStudent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khôi phục dữ liệu nếu Activity bị hủy do xoay màn hình
        savedInstanceState?.getSerializable(KEY_STUDENT)?.let {
            currentStudent = it as Student
        }

        // Bind dữ liệu ban đầu lên giao diện
        bindStudentData(currentStudent)

        // Bắt sự kiện bấm nút Cập nhật GPA
        binding.btnUpdateGpa.setOnClickListener {
            val gpaStr = binding.edtGpaInput.trimmedText()
            val gpa = gpaStr.toDoubleOrNull()

            // Validate dữ liệu phòng thủ
            if (gpa == null || gpa !in 0.0..4.0) {
                binding.edtGpaInput.error = "GPA phải từ 0.0 đến 4.0"
                return@setOnClickListener
            }

            // Cập nhật bất biến bằng phương thức copy()
            currentStudent = currentStudent.copy(gpa = gpa)
            bindStudentData(currentStudent)
            toast("Đã cập nhật GPA thành công!")
        }

        // Bắt sự kiện bấm nút Khôi phục mặc định
        binding.btnReset.setOnClickListener {
            currentStudent = defaultStudent
            bindStudentData(currentStudent)
            binding.edtGpaInput.text.clear()
            toast("Đã khôi phục dữ liệu mặc định!")
        }
    }

    // Lưu trạng thái hiện tại vào Bundle trước khi xoay màn hình
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_STUDENT, currentStudent)
    }

    // Hàm gán toàn bộ thông tin từ model lên các Views
    private fun bindStudentData(student: Student) {
        with(binding) {
            tvStudentName.text = student.name
            tvStudentDetails.text = "MSSV: ${student.id} • Lớp: ${student.className}"
            tvStudentEmail.text = "Email: ${student.email}"
            tvGpaBadge.text = "${student.gpa} GPA • ${student.gpa.toAcademicRanking()}"
        }
    }
}