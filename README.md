
# Parallel BLAST on Hadoop for cellines analysis

## Nhóm thực hiện: 
Trần Xuân Bảo - 23020332

Hà Xuân Huy - 23030375

Phan Hoàng Dũng - 23020346

## Mô tả dự án 
Dự án này là thành quả của nhóm chúng em trong môn học Kỹ thuật và công nghệ dữ liệu lớn tại Trường Đại học Công nghệ, Đại học Quốc Gia Hà Nội. Chúng em xin gửi lời cảm ơn chân thành đến các thầy đã tận tình hướng dẫn, cung cấp những phản hồi quý giá và luôn đồng hành với chúng em trong suốt quá trình thực hiện dự án.

Dự án này hướng tới việc triển khai một phiên bản song song của công cụ BLAST trên nền tảng Hadoop sử dụng mô hình MapReduce. Mục tiêu quan trọng nhất là tận dụng khả năng phân tán và xử lý song song của Hadoop để tăng tốc quá trình so khớp chuỗi sinh học, phục vụ cho các nghiên cứu về cell lines.

## Cấu trúc file 
- BLAST_with_Hadoop/ # Chứa code và các bước chạy BLAST với Hadoop
- data_clustering/ # Các bước phân cụm dữ liệu
- data_collection/ # Code thu thập dữ liệu và tập dữ liệu thu được
- normal_BLAST/ # Các bước chạy BLAST truyền thống
