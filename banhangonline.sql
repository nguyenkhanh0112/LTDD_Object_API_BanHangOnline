-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 25, 2023 at 01:30 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banhangonline`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `iddonhang` int(11) NOT NULL,
  `idsp` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `gia` varchar(255) NOT NULL,
  `tensp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`iddonhang`, `idsp`, `soluong`, `gia`, `tensp`) VALUES
(1, 15, 0, '50349000', ''),
(1, 14, 0, '13950000', ''),
(1, 10, 0, '14999000', ''),
(3, 15, 0, '50349000', ''),
(3, 14, 0, '13950000', ''),
(3, 10, 0, '14999000', ''),
(4, 15, 0, '50349000', ''),
(4, 14, 0, '13950000', ''),
(4, 10, 0, '14999000', ''),
(5, 13, 0, '12199000', ''),
(5, 12, 0, '17599000', ''),
(6, 15, 0, '50349000', ''),
(6, 14, 0, '13950000', ''),
(6, 10, 0, '14999000', ''),
(8, 15, 1, '50349000', ''),
(8, 8, 2, '17999000', ''),
(8, 9, 1, '17299000', ''),
(9, 14, 1, '13950000', ''),
(9, 12, 1, '17599000', ''),
(9, 8, 2, '17999000', ''),
(10, 8, 1, '17999000', ''),
(10, 7, 1, '17999000', ''),
(10, 4, 1, '17999000', ''),
(11, 15, 1, '50349000', ''),
(11, 14, 1, '13950000', ''),
(11, 13, 1, '12199000', ''),
(11, 12, 1, '17599000', ''),
(11, 11, 1, '27399000', ''),
(11, 10, 2, '14999000', ''),
(11, 8, 1, '17999000', ''),
(12, 8, 1, '17999000', ''),
(14, 14, 1, '13950000', ''),
(14, 15, 2, '50349000', ''),
(15, 14, 1, '13950000', ''),
(15, 15, 2, '50349000', ''),
(17, 12, 1, '17599000', ''),
(18, 13, 1, '12199000', ''),
(19, 15, 1, '50349000', ''),
(20, 12, 1, '17599000', ''),
(20, 14, 1, '13950000', ''),
(21, 14, 1, '13950000', ''),
(22, 12, 1, '17599000', ''),
(23, 15, 1, '50349000', ''),
(28, 21, 1, '46600000', ''),
(28, 15, 1, '50349001', ''),
(29, 15, 1, '50349001', ''),
(30, 15, 2, '50349001', ''),
(30, 13, 1, '12199000', ''),
(32, 21, 1, '46600000', 'Apple Macbook Pro 2020 Adonis Feed'),
(32, 15, 2, '50349001', 'Vivo Y02s 32GB'),
(33, 6, 1, '18099000', 'Laptop Acer Gaming Aspire 7 A715-43G-R8GA'),
(33, 8, 2, '17999000', 'Laptop LG Gram 16ZD90Q-G.AH52A5'),
(33, 2, 1, '24280000', 'Laptop HP Envy 13-ad074TU'),
(34, 14, 1, '13950000', 'Samsung-Galaxy-S21'),
(35, 3, 2, '21499000', 'Điện thoại Asus ROG Phone 6 S8+ G1/12/256 Đen'),
(35, 11, 1, '27399000', 'iPhone 13 Pro 128GB Vàng'),
(36, 15, 1, '50349001', 'Vivo Y02s 32GB'),
(37, 21, 1, '46600000', 'Apple Macbook Pro 2020 Adonis Feed'),
(37, 21, 1, '46600000', 'Apple Macbook Pro 2020 Adonis Feed'),
(37, 15, 1, '50349001', 'Vivo Y02s 32GB'),
(38, 11, 1, '27399000', 'iPhone 13 Pro 128GB Vàng'),
(38, 12, 1, '17599000', 'Samsung Galaxy A33 5G 6GB');

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `diachi` text NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `soluong` int(11) NOT NULL,
  `tongtien` varchar(255) NOT NULL,
  `thoigian` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`id`, `iduser`, `diachi`, `sdt`, `email`, `soluong`, `tongtien`, `thoigian`) VALUES
(9, 10, 'thường tín', '1', 'khanh@gmail.com', 4, '67547000', ''),
(17, 10, 'ssss', '1', 'khanh@gmail.com', 1, '17599000', ''),
(18, 10, 'aaaaa', '1', 'khanh@gmail.com', 1, '12199000', ''),
(19, 10, 'ggggg', '1', 'khanh@gmail.com', 1, '50349000', ''),
(20, 10, 'kkkkas', '1', 'khanh@gmail.com', 2, '31549000', ''),
(21, 10, 'kkkkkkkko', '1', 'khanh@gmail.com', 1, '13950000', ''),
(22, 10, 'ppppp', '1', 'khanh@gmail.com', 1, '17599000', ''),
(23, 10, 'oooooo', '1', 'khanh@gmail.com', 1, '50349000', ''),
(24, 6, 'ngõ 1 bùi xương trạch', '1', 'vudoanha@gmail.com', 4, '193898002', '21-10-2023 00:55:57'),
(25, 6, 'ngõ 1 bùi xương trạch', '1', 'vudoanha@gmail.com', 4, '193898002', '21-10-2023 00:55:57'),
(26, 6, 'đasadsadsadas', '1', 'vudoanha@gmail.com', 1, '17599000', '21-10-2023 01:13:16'),
(27, 6, 'bùi xương trạch hà nội', '1', 'vudoanha@gmail.com', 4, '128598002', '21-10-2023 01:48:29'),
(28, 6, 'sssssss', '1', 'vudoanha@gmail.com', 2, '96949001', '21-10-2023 01:51:08'),
(29, 6, 'iiiiii', '1', 'vudoanha@gmail.com', 1, '50349001', '21-10-2023 02:26:32'),
(30, 6, 'ttttttt', '1', 'vudoanha@gmail.com', 3, '112897002', '21-10-2023 02:45:11'),
(31, 6, 'oooooo', '1', 'vudoanha@gmail.com', 3, '82798000', '21-10-2023 03:25:42'),
(32, 6, 'ppppp', '1', 'vudoanha@gmail.com', 3, '147298002', '21-10-2023 03:40:42'),
(33, 6, 'sssss', '1', 'vudoanha@gmail.com', 4, '78377000', '21-10-2023 03:46:50'),
(34, 6, 'sdasdsadsadsa', '1', 'vudoanha@gmail.com', 1, '13950000', '21-10-2023 04:06:44'),
(35, 6, 'asdadada', '1', 'vudoanha@gmail.com', 3, '70397000', '21-10-2023 04:57:53'),
(36, 6, 'Hà Nội', '123456', 'vudoanha@gmail.com', 1, '50349001', '21-10-2023 16:00:44'),
(37, 10, 'ngõ 1 bùi xương trạch', '1', 'khanh@gmail.com', 3, '143549001', '25-10-2023 07:19:12'),
(38, 10, 'ngõ 1 bùi xương trạch', '1', 'khanh@gmail.com', 2, '44998000', '25-10-2023 07:19:56');

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(50) NOT NULL,
  `hinhanhsanpham` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tensanpham`, `hinhanhsanpham`) VALUES
(1, 'Trang Chủ', 'https://cdn-icons-png.flaticon.com/128/4018/4018517.png'),
(2, 'Điện Thoại', 'https://cdn-icons-png.flaticon.com/128/3137/3137807.png'),
(3, 'Laptop', 'https://cdn-icons-png.flaticon.com/128/2888/2888704.png'),
(4, 'Thông tin', 'https://cdn-icons-png.flaticon.com/128/4310/4310155.png'),
(6, 'Đơn hàng ', 'https://cdn-icons-png.flaticon.com/128/11135/11135813.png');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` varchar(15) NOT NULL,
  `hinhanhsanpham` text NOT NULL,
  `motasanpham` text NOT NULL,
  `idloaisanpham` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idloaisanpham`) VALUES
(1, 'Apple Macbook Pro 2020', '46600000', 'https://cdn.tgdd.vn/Files/2020/12/21/1315201/macbook10_800x450.jpg', '\"Bộ xử lý: Intel Core i5 dual-core 3.1GHz, Turbo Boost up to 3.5GHz, with 64MB of eDRAM\r\nRAM: 8GB 2133MHz LPDDR3 memory\r\nỔ cứng SSD: 512GB\r\nCard đồ hoạ: Intel Iris Plus Graphics 650\r\n\"', 2),
(2, 'Laptop HP Envy 13-ad074TU', '24280000', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/hp-envy-13-01.jpg', '\"CPU Intel Core i7-7500U 2.7GHz up to 3.5GHz 4MB\r\nRAM 8GB LPDDR3 Onboard\r\nĐĩa cứng 256 GB PCIe® NVMe™ M.2 SSD\r\nCard đồ họa Intel® HD Graphics 620\r\nMàn hình 13.3 inch FHD (1920 x 1080) diagonal IPS BrightView micro-edge WLED-backlit\"', 2),
(3, 'Điện thoại Asus ROG Phone 6 S8+ G1/12/256 Đen', '21499000', 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:80/plain/https://cellphones.com.vn/media/catalog/product/b/g/bgf.png', '\"Màn hình 6.78 inch, AMOLED, FHD+, 2448 x 1080 Pixels\r\nCamera sau 50.0 MP + 13.0 MP + 5.0 MP\r\nCamera Selfie 12.0 MP\r\nRAM 12 GB\r\nBộ nhớ trong 256 GB\r\nCPU Snapdragon 8+ Gen 1\"', 1),
(4, 'iPhone 12 128GB Xanh lá', '17999000', 'https://hanoicomputercdn.com/media/product/64657_iphone_12_2.jpg', '', 1),
(6, 'Laptop Acer Gaming Aspire 7 A715-43G-R8GA', '18099000', 'https://hanoicomputercdn.com/media/product/66365_hacom_acer_gaming_aspire_7_a715_43g_19.png', '\"CPU: AMD R5 5625U\r\nRAM: 8GB\r\nỔ cứng: 512GB SSD\r\nVGA: NVIDIA RTX3050 4G\r\nMàn hình: 15.6 inch FHD 144Hz\r\nHĐH: Win 11\"', 2),
(8, 'Laptop LG Gram 16ZD90Q-G.AH52A5', '17999000', 'https://hanoicomputercdn.com/media/product/67142_16z90q_g_ah52a5.png', '\"CPU: Intel® Core™ i5-1240P (3.30 GHz upto 4.40 GHz, 12MB)\r\nRAM: 16GB LPDDR5 5200MHz\r\nỔ cứng: 256GB NVMe Gen.4\r\nVGA: Intel Iris Xe Graphics\r\nMàn hình: 16-inch WQXGA (2560x1600) Anti-glare IPS, DCI-P3 99% (Typ. / Min 95%), 350nit\r\nMàu sắc: Đen\"', 2),
(10, 'Laptop HP 15s-fq5080TU', '14999000', 'https://hanoicomputercdn.com/media/product/67862_hacom_hp_15_16.jpeg', '\"CPU: Intel Core i5 1235U\r\nRAM: 8GB\r\nỔ cứng: 256GB SSD\r\nVGA: Onboard\r\nMàn hình: 15.6 inch FHD\r\nHĐH: Win 11\"', 2),
(11, 'iPhone 13 Pro 128GB Vàng', '27399000', 'https://hanoicomputercdn.com/media/product/64690_iphone_13_pro_max_2.png', '\"Công nghệ màn hình: OLED\r\nĐộ phân giải: 1170 x 2532 Pixels, 2 camera 12 MP, 12 MP\r\nMàn hình rộng: 6.1\"\"\r\nHệ điều hành: iOS 14\r\nChip xử lý (CPU): Apple A14 Bionic 6 nhân\r\nBộ nhớ trong (ROM): 128GB\"', 1),
(12, 'Samsung Galaxy A33 5G 6GB', '17599000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/267211/Samsung-Galaxy-S21-FE-vang-1-2-600x600.jpg', '\"\"\"Điện thoại song công có màn hình B&W 2,8 inch có đèn nền\r\nCác linh kiện và CPU được chọn lọc để có hiệu suất mạnh mẽ\r\nTiêu thụ điện năng thấp\r\nNhiều phím dòng / có thể lập trình hơn và phím quay số nhanh\r\nCác cổng kết nối bên ngoài\r\nKhả năng tương thích phong phú có thể dễ dàng triển khai\"\"\"', 1),
(13, 'Samsung Galaxy S21 FE 5G', '12199000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/298377/samsung-galaxy-a34-5g-xanh-thumb-600x600.jpg', '\"\"\"Kích thước màn hình 6.1 inches\r\nCông nghệ màn hình OLED\r\nCamera sau Camera góc rộng: 12MP, f/1.6\r\nCamera góc siêu rộng: 12MP, ƒ/2.4\r\nCamera trước 12MP, f/2.2\r\nChipset Apple A15\r\nDung lượng RAM 4 GB\"\"\"', 1),
(14, 'Samsung-Galaxy-S21', '13950000', 'https://images.samsung.com/fr/smartphones/galaxy-note20/buy/carousel/mobile/005-galaxynote20-mysticbronze.jpg', 'Kích thước màn hình 6.4\" Dynamic AMOLED 2X (2340 x 1080)Kích thước tổng thể 155.7 x 74.5 x 7.9mmTrọng lượng177g. Tần số quét lớn nhất 120 HzCamera sau, Camera trước 32MP Pin 4,500mAh(typical) RAM6GB/8GBBộ nhớ 128GB/256GB', 1),
(15, 'Vivo Y02s 32GB', '50349001', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/245261/Xiaomi-redmi-note-11-blue-600x600.jpg', '\"\"\"\"\"\"\"Kích thước màn hình 6.1 inches\r\nCông nghệ màn hình OLED\r\nCamera sau Camera góc rộng: 12MP, f/1.6\r\nCamera góc siêu rộng: 12MP, ƒ/2.4\r\nCamera trước 12MP, f/2.2\r\nChipset Apple A15\r\nDung lượng RAM 4 GB\"\"\"\"\"\"\"', 1),
(21, 'Apple Macbook Pro 2020 Adonis Feed', '46600000', 'https://cdn.tgdd.vn/Files/2020/12/21/1315201/macbook10_800x450.jpg', '\"Bộ xử lý: Intel Core i5 dual-core 3.1GHz, Turbo Boost up to 3.5GHz, with 64MB of eDRAM\nRAM: 8GB 2133MHz LPDDR3 memory\nỔ cứng SSD: 512GB\nCard đồ hoạ: Intel Iris Plus Graphics 650\n\"', 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(250) NOT NULL,
  `username` varchar(250) NOT NULL,
  `pass` varchar(250) NOT NULL,
  `sodienthoai` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `username`, `pass`, `sodienthoai`) VALUES
(6, 'vudoanha@gmail.com', 'vu doãn hà12', '1', '123456'),
(10, 'khanh@gmail.com', 'khanh', '1', '1'),
(11, 'khanh2003@gmail.com', 'khanhdepzai', '1234', '1'),
(14, 'quanly@gmail.com', 'quanly', '1', '123456789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
