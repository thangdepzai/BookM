# BookM
App quản lý đọc sách


# Flow dùng git

- clone project:
```
git clone https://github.com/thangdepzai/BookM.git
cd BookM
```
- Tạo nhánh mới từ develop
```
git checkout develop
git fetch
git reset --hard origin/develop
git checkout -b <ten-nhanh-moi>  // feature/ + tên tính năng
```
- Kiểm tra các file đã thay đổi
```
git status
```
- thêm các file đã thay đổi
```
git add .
```
// lưu ý không thêm các file cấu hình riêng của máy, chỉ thêm code

// để loại bỏ, thêm các thư mục đó vào .gitignore

- commit:
```
git commit -m "<message>"
```
- push thay đổi lên remote:
```
git push
```
- Tạo pull request để merge code mới vào develop







