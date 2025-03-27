# 📱 Android App: Kiểm tra chính tả

Ứng dụng Android giúp giáo viên có thể chấm sửa lỗi và đưa ra kết quả đến học sinh

---
## 🎬 Chương trình ứng dụng và kiểm thử
<img src="images/demo.gif" width="200" alt="Demo GIF">
---

##  🎬 Xem tại Youtube
[![Watch the video](https://img.youtube.com/vi/DI3SbAJHxEQ/maxresdefault.jpg)](https://youtu.be/DI3SbAJHxEQ)



---

## 🚀 1. Tạo Project mới trong Android Studio
Tạo một dự án mới trong **Android Studio**:
![Create new project](images/1.png)

---

## 🏗️ 2. Chọn loại Activity
Chọn **Basic View Activity** để có giao diện cơ bản:
![Choose basic view](images/2.png)

---

## ✏️ 3. Đặt tên project và chọn cấu hình Android
Nhập tên ứng dụng, package name, và chọn ngôn ngữ **Java/Kotlin**:
![Project name](images/3.png)

---

## 🖥️ 4. Màn hình chính trong Android Studio
Khi mở dự án, bạn sẽ thấy giao diện chính gồm **XML Layouts** và **MainActivity**:
![Android Studio Coding](images/4.png)

---

## 🎨 5. Thiết kế giao diện (Layout)

### 🔹 Main Activity (Học sinh nhập câu)
Màn hình chính để nhập câu cần kiểm tra và gửi đến cho giáo viên:
![Main Activity UI](images/5.png)

### 🔹 Teacher Activity (Hiển thị kết quả)
Màn hình hiển thị **Kết quả đúng hay sai, nếu sai thì hiển thị thông tin giáo viên đã sửa lại câu đó**:
![Result Activity UI](images/6.png)

---

## ⚙️ 6. Cách hoạt động

1. **Học sinh nhập câu cần kiểm tra vào ô hộp thư**.
2. Sau khi nhập xong nhân nút**Gửi đến cho giáo viên**.
3. Giáo viên sẽ kiểm tra tại màn hình TeacherActivity và sửa lỗi nếu sai:
4. Hiển thị kết quả khi giáo viên đã kiểm tra được gửi đến lại cho Student trong **Main Activity**.
5. Luồng hoạt động của Activity Result Handling
# Activity Result Handling  

## 📌 Mô tả  
Ứng dụng sử dụng `setResult(...)` để trả kết quả từ một Activity con về Activity cha.  

## ⚡ Cách hoạt động  

### 1️⃣ Gọi Activity con từ Activity cha  
```java
Intent intent = new Intent(this, TeacherActivity.class);
startActivityForResult(intent, REQUEST_CODE);
```

### 2️⃣ Trả kết quả từ Activity con  
```java
Intent resultIntent = new Intent();
resultIntent.putExtra("teacherName", "Mr. John");
setResult(RESULT_OK, resultIntent);
finish();
```

### 3️⃣ Nhận kết quả ở Activity cha  
```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
        String teacherName = data.getStringExtra("teacherName");
        Toast.makeText(this, "Teacher: " + teacherName, Toast.LENGTH_SHORT).show();
    }
}
```
---

## 🛠️ 7. Cách chạy ứng dụng

1. Clone repo này về máy:
   ```sh
   git clone https://github.com/Trung78z/android-app_week_3.git
   ```
2. Mở **Android Studio** và import project.
3. Chạy ứng dụng trên **Emulator** hoặc **thiết bị thật**.
4. Nhập câu cần kiểm tra.


---

## 📩 8. Liên hệ
Nếu có thắc mắc hoặc góp ý, vui lòng liên hệ qua email: `quanh.0807@gmail.com` & `trungpspy@gmail.com`.