
# SkinSight - Ứng dụng phân tích làn da

## Nhóm thực hiện: [Title Card]
Trần Xuân Bảo - 23020332

Hà Xuân Huy - 23030375

Phan Hoàng Dũng - 23020346

## Mô tả dự án 
Dự án này là thành quả của nhóm chúng em trong môn học Kỹ thuật và công nghệ dữ liệu lớn tại Trường Đại học Công nghệ, Đại học Quốc Gia Hà Nội. Chúng em xin gửi lời cảm ơn chân thành đến các thầy đã tận tình hướng dẫn, cung cấp những phản hồi quý giá và luôn đồng hành với chúng em trong suốt quá trình thực hiện dự án.

Dự án này hướng tới việc triển khai một phiên bản song song của công cụ BLAST trên nền tảng Hadoop sử dụng mô hình MapReduce. Mục tiêu quan trọng nhất là tận dụng khả năng phân tán và xử lý song song của Hadoop để tăng tốc quá trình so khớp chuỗi sinh học, phục vụ cho các nghiên cứu về cell lines.

## Cấu trúc file 
- backend_ai/ # Chứa các mô hình AI (mụn, nám, nếp nhăn), mỗi model chạy container riêng
- backend_api/ # Backend chính viết bằng Flask (API server)
- database/ # Docker cấu hình cho cơ sở dữ liệu (MongoDB)
- frontend/ # Giao diện người dùng
- tests/ # Thư mục chứa script test hệ thống
- .gitignore # File cấu hình Git để bỏ qua các file không cần track
- README.md # Tài liệu mô tả dự án
- cookies.txt # Cookie file 
- docker-compose.yml # Triển khai toàn bộ hệ thống bằng Docker Compose
- requirements.txt # Liệt kê thư viện dùng chung toàn hệ thống