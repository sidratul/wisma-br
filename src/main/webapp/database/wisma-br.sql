-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Inang: 127.0.0.1
-- Waktu pembuatan: 11 Jun 2013 pada 15.46
-- Versi Server: 5.5.27
-- Versi PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Basis data: `wisma-br`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `inap`
--

CREATE TABLE IF NOT EXISTS `inap` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inapId` varchar(30) NOT NULL,
  `idTamu` int(11) NOT NULL,
  `waktuCheckin` datetime NOT NULL,
  `waktuCheckout` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CheckinId` (`inapId`),
  KEY `idTamu` (`idTamu`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=43 ;

--
-- Dumping data untuk tabel `inap`
--

INSERT INTO `inap` (`id`, `inapId`, `idTamu`, `waktuCheckin`, `waktuCheckout`) VALUES
(23, 'CK2013052801', 1, '2013-05-28 00:00:00', '2013-06-03 00:00:00'),
(25, 'CK2013052802', 10, '2013-05-28 00:00:00', '2013-06-03 00:00:00'),
(29, 'CK2013052901', 9, '2013-05-29 00:00:00', '2013-05-29 00:00:00'),
(30, 'CK2013052902', 9, '2013-05-29 00:00:00', '2013-06-02 00:00:00'),
(32, 'CK2013060701', 9, '2013-06-07 00:00:00', '2013-06-07 00:00:00'),
(35, 'CK2013060702', 1, '2013-06-07 00:00:00', '2013-06-07 00:00:00'),
(36, 'CK2013060703', 9, '2013-06-07 00:00:00', '2013-06-07 00:00:00'),
(38, 'CK2013060705', 9, '2013-06-07 00:00:00', '2013-06-07 00:00:00'),
(39, 'CK2013060901', 10, '2013-06-09 00:00:00', '2013-06-11 00:00:00'),
(40, 'CK2013061101', 11, '2013-06-11 00:00:00', '2013-06-11 00:00:00'),
(41, 'CK2013061102', 28, '2013-06-11 00:00:00', '2013-06-11 00:00:00'),
(42, 'CK2013061103', 25, '2013-06-11 00:00:00', '2013-06-11 00:00:00');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kamar`
--

CREATE TABLE IF NOT EXISTS `kamar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `noKamar` varchar(5) NOT NULL,
  `acTv` enum('0','1') NOT NULL,
  `kelasKamar` enum('a','b','c','d') NOT NULL COMMENT 'a=300,b=200,c=(90,120,150),d=(60,90,105)',
  `maxOrang` int(2) NOT NULL,
  `statIn` enum('0','1') DEFAULT NULL COMMENT '0 = kosong ; 1=terisi',
  `statReservasi` enum('0','1') DEFAULT NULL COMMENT '0=tidak terpesan ; 1 =terpesan',
  PRIMARY KEY (`id`),
  UNIQUE KEY `no_kamar` (`noKamar`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data untuk tabel `kamar`
--

INSERT INTO `kamar` (`id`, `noKamar`, `acTv`, `kelasKamar`, `maxOrang`, `statIn`, `statReservasi`) VALUES
(1, '101', '1', 'c', 3, '0', '0'),
(2, '102', '1', 'c', 3, '0', '1'),
(3, '103', '1', 'c', 3, '0', '0'),
(4, '104', '1', 'c', 3, '0', '0'),
(5, '105', '1', 'a', 6, '0', '0'),
(6, '106', '1', 'c', 3, '0', '0'),
(7, '107', '0', 'd', 3, '0', '0'),
(8, '108', '0', 'd', 3, '0', '0'),
(9, '201', '1', 'b', 4, '0', '0'),
(10, '202', '0', 'd', 3, '0', '0'),
(11, '203', '0', 'd', 3, '0', '0'),
(12, '204', '0', 'd', 3, '0', '0'),
(13, '205', '0', 'd', 3, '0', '0'),
(14, '206', '0', 'd', 3, '0', '0'),
(15, '207', '1', 'c', 3, '0', '0'),
(16, '208', '1', 'c', 3, '0', '0'),
(17, '209', '1', 'c', 3, '0', '0'),
(18, '210', '1', 'c', 3, '0', '0'),
(19, '211', '0', 'd', 3, '0', '0'),
(20, '301', '0', 'd', 3, '0', '0'),
(21, '302', '0', 'd', 3, '0', '0'),
(22, '303', '0', 'd', 3, '0', '0'),
(23, '304', '0', 'd', 3, '0', '0'),
(24, '305', '0', 'd', 3, '0', '0');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembayaran`
--

CREATE TABLE IF NOT EXISTS `pembayaran` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idInap` int(11) NOT NULL,
  `totalBiaya` decimal(11,2) NOT NULL,
  `sudahBayar` decimal(11,2) NOT NULL DEFAULT '0.00',
  `tglBayar` datetime DEFAULT NULL,
  `statusBayar` enum('0','1') NOT NULL DEFAULT '0' COMMENT '0=belum lunas ; 1= lunas',
  PRIMARY KEY (`id`),
  KEY `idInap` (`idInap`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data untuk tabel `pembayaran`
--

INSERT INTO `pembayaran` (`id`, `idInap`, `totalBiaya`, `sudahBayar`, `tglBayar`, `statusBayar`) VALUES
(2, 29, 120000.00, 120000.00, '2013-06-03 00:00:00', '1'),
(3, 30, 600000.00, 600000.00, '2013-06-07 00:00:00', '1'),
(4, 25, 540000.00, 540000.00, '2013-06-07 00:00:00', '1'),
(5, 23, 840000.00, 840000.00, '2013-06-07 00:00:00', '1'),
(6, 32, 90000.00, 90000.00, '2013-06-07 00:00:00', '1'),
(7, 35, 90000.00, 90000.00, '2013-06-07 00:00:00', '1'),
(8, 36, 90000.00, 90000.00, '2013-06-07 00:00:00', '1'),
(9, 38, 90000.00, 90000.00, '2013-06-07 00:00:00', '1'),
(10, 40, 120000.00, 120000.00, '2013-06-11 00:00:00', '1'),
(11, 39, 120000.00, 0.00, NULL, '0'),
(12, 41, 90000.00, 0.00, NULL, '0'),
(13, 42, 60000.00, 0.00, NULL, '0');

-- --------------------------------------------------------

--
-- Struktur dari tabel `reservasi`
--

CREATE TABLE IF NOT EXISTS `reservasi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reservasiId` varchar(30) NOT NULL,
  `idTamu` int(11) NOT NULL,
  `idKamar` int(11) NOT NULL,
  `tglReservasi` datetime NOT NULL,
  `ReservasiTgl` datetime NOT NULL,
  `statusReservasi` enum('0','1','2') NOT NULL COMMENT '0=tidak berlaku ;1=berlaku ; 2=jadi',
  PRIMARY KEY (`id`),
  KEY `idTamu` (`idTamu`),
  KEY `idKamar` (`idKamar`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data untuk tabel `reservasi`
--

INSERT INTO `reservasi` (`id`, `reservasiId`, `idTamu`, `idKamar`, `tglReservasi`, `ReservasiTgl`, `statusReservasi`) VALUES
(3, 'RS2013060702', 9, 1, '2013-06-07 00:00:00', '2013-06-09 00:00:00', '0'),
(4, 'RS2013060703', 1, 1, '2013-06-07 00:00:00', '2013-06-08 00:00:00', '2'),
(5, 'RS2013060704', 10, 1, '2013-06-07 00:00:00', '2013-06-16 00:00:00', '0'),
(7, 'RS2013060706', 1, 2, '2013-06-07 00:00:00', '2013-06-09 00:00:00', '0'),
(8, 'RS2013061101', 11, 1, '2013-06-11 00:00:00', '2013-06-12 00:00:00', '2'),
(9, 'RS2013061102', 11, 1, '2013-06-11 00:00:00', '2013-06-13 00:00:00', '1'),
(10, 'RS2013061103', 26, 2, '2013-06-11 00:00:00', '2013-06-13 00:00:00', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `status_inap`
--

CREATE TABLE IF NOT EXISTS `status_inap` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idInap` int(11) NOT NULL,
  `idKamar` int(11) NOT NULL,
  `jumOrang` int(1) NOT NULL,
  `biaya` decimal(11,2) NOT NULL,
  `waktuStatus` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idInap` (`idInap`),
  KEY `idKamar` (`idKamar`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=59 ;

--
-- Dumping data untuk tabel `status_inap`
--

INSERT INTO `status_inap` (`id`, `idInap`, `idKamar`, `jumOrang`, `biaya`, `waktuStatus`) VALUES
(10, 23, 1, 2, 120000.00, '2013-05-28 00:00:00'),
(15, 25, 2, 1, 90000.00, '2013-05-28 00:00:00'),
(19, 29, 3, 2, 120000.00, '2013-05-29 00:00:00'),
(20, 30, 3, 3, 150000.00, '2013-05-29 00:00:00'),
(21, 23, 1, 2, 120000.00, '2013-05-29 00:00:00'),
(22, 25, 2, 1, 90000.00, '2013-05-29 00:00:00'),
(23, 23, 1, 3, 150000.00, '2013-05-30 00:00:00'),
(25, 25, 2, 1, 90000.00, '2013-05-30 00:00:00'),
(26, 30, 3, 3, 150000.00, '2013-05-30 00:00:00'),
(27, 23, 1, 3, 150000.00, '2013-05-31 00:00:00'),
(28, 25, 2, 1, 90000.00, '2013-05-31 00:00:00'),
(29, 30, 3, 3, 150000.00, '2013-05-31 00:00:00'),
(42, 23, 1, 3, 150000.00, '2013-06-01 00:00:00'),
(43, 25, 2, 1, 90000.00, '2013-06-01 00:00:00'),
(44, 30, 3, 3, 150000.00, '2013-06-01 00:00:00'),
(45, 23, 1, 3, 150000.00, '2013-06-02 00:00:00'),
(46, 25, 2, 1, 90000.00, '2013-06-02 00:00:00'),
(48, 32, 2, 1, 90000.00, '2013-06-07 00:00:00'),
(51, 35, 1, 1, 90000.00, '2013-06-07 00:00:00'),
(52, 36, 2, 1, 90000.00, '2013-06-07 00:00:00'),
(54, 38, 2, 1, 90000.00, '2013-06-07 00:00:00'),
(55, 39, 2, 2, 120000.00, '2013-06-09 00:00:00'),
(56, 40, 1, 2, 120000.00, '2013-06-11 00:00:00'),
(57, 41, 1, 1, 90000.00, '2013-06-11 00:00:00'),
(58, 42, 13, 1, 60000.00, '2013-06-11 00:00:00');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tamu`
--

CREATE TABLE IF NOT EXISTS `tamu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `noId` varchar(30) NOT NULL,
  `jenisId` enum('ktp','sim') NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenisKelamin` enum('laki-laki','perempuan') NOT NULL,
  `kota` varchar(255) NOT NULL,
  `alamat` text,
  `telpon` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `no_id` (`noId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Dumping data untuk tabel `tamu`
--

INSERT INTO `tamu` (`id`, `noId`, `jenisId`, `nama`, `jenisKelamin`, `kota`, `alamat`, `telpon`) VALUES
(1, '4510210013', 'ktp', 'sidratul', 'laki-laki', 'tolitoli', 'jalan ra kartini no 47', '08999904741'),
(9, '11122222333', 'sim', 'nina', 'perempuan', 'bandung', 'jalan melati', '08993322333'),
(10, '45102100071', 'sim', 'Wina', 'perempuan', 'ambon', 'Jalan Merdeka', '08993322333'),
(11, '111111111111111', 'ktp', 'qqqqqqqqqqqqqqqq', 'laki-laki', 'qwqeqweq', '', ''),
(12, 'sdasdasdfsaaaa', 'ktp', 'sdfsdfsdfsdf', 'laki-laki', 'sdfsdf', '', ''),
(13, 'sdfsdfsaaa222', 'ktp', 'sdfsdfsdfsdfs', 'laki-laki', 'sdfsdfsdfs', '', ''),
(14, 'qqqqqqqqqqqq', 'sim', 'sdfsdfsdfsdfsdfsdf', 'laki-laki', 'dfsdfsdfsdfsd', '', ''),
(15, 'sdfdfdfdffs1111', 'ktp', 'sdfsdfsdfsdf', 'perempuan', 'sdfsdfsdfsdf', '', ''),
(16, 'ssssssssssssssssssss', 'ktp', 'ddddd', 'laki-laki', 'ssssssssss', 'dfsdfsdfs', ''),
(17, 'ssssss11111111', 'ktp', 'sidr', 'laki-laki', 'jakarta', '', ''),
(18, '2342342222', 'ktp', 'qqqwsssss', 'laki-laki', 'sasaq', '', ''),
(19, '1111111111111111wdeqe', 'ktp', 'werwerwerw', 'laki-laki', 'dsdaq', '', ''),
(20, 'saedfwsefrwefw', 'ktp', 'asdasdasda', 'laki-laki', 'asdasdas', '', ''),
(21, 'dfasdfasdfas', 'ktp', 'asasasas', 'laki-laki', 'asdasdasd', 'asdasda', ''),
(22, 'asdasdasdqqqq', 'ktp', 'asdasdasd', 'perempuan', 'asdasdasd', 'asdasdad', ''),
(23, 'ssssssssssssssas', 'ktp', 'asdasdasdasd', 'laki-laki', 'asdasasads', 'aasasss', ''),
(24, 'aaaaaaaaaaass', 'sim', 'asasasas', 'laki-laki', 'asasasd', 'asda', ''),
(25, 'asaaaa1111111', 'sim', 'asasdas', 'laki-laki', 'asdasda', 'sd', ''),
(26, 'asdasdaqws11111', 'ktp', 'asdasdasd', 'laki-laki', 'asdasdasda', '', ''),
(27, 'qwqw11111111111111', 'ktp', 'wwwwwwwwww', 'laki-laki', 'qqqqqqqqqqq', '', ''),
(28, '21232324234', 'ktp', 'qwqweqwe', 'laki-laki', 'qweqweq', '', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `aktif` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `aktif`) VALUES
(1, 'sid', '123', 1),
(2, 'sidra', '12345', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user_roles`
--

CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_roles` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data untuk tabel `user_roles`
--

INSERT INTO `user_roles` (`id`, `userId`, `authority`) VALUES
(1, 1, 'ROLE_RECEPTIONIS'),
(2, 2, 'ROLE_ADMIN'),
(3, 2, 'ROLE_RECEPTIONIS');

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `inap`
--
ALTER TABLE `inap`
  ADD CONSTRAINT `inap_ibfk_1` FOREIGN KEY (`idTamu`) REFERENCES `tamu` (`id`);

--
-- Ketidakleluasaan untuk tabel `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD CONSTRAINT `pembayaran_ibfk_1` FOREIGN KEY (`idInap`) REFERENCES `inap` (`id`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `reservasi`
--
ALTER TABLE `reservasi`
  ADD CONSTRAINT `reservasi_ibfk_1` FOREIGN KEY (`idTamu`) REFERENCES `tamu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservasi_ibfk_2` FOREIGN KEY (`idKamar`) REFERENCES `kamar` (`id`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `status_inap`
--
ALTER TABLE `status_inap`
  ADD CONSTRAINT `status_inap_ibfk_1` FOREIGN KEY (`idInap`) REFERENCES `inap` (`id`),
  ADD CONSTRAINT `status_inap_ibfk_2` FOREIGN KEY (`idKamar`) REFERENCES `kamar` (`id`);

--
-- Ketidakleluasaan untuk tabel `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FK_user_roles` FOREIGN KEY (`userId`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
