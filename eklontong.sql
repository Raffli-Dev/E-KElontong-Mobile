-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: database:3306
-- Generation Time: Jun 16, 2024 at 12:32 PM
-- Server version: 5.7.41
-- PHP Version: 8.0.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eklontong`
--

-- --------------------------------------------------------

--
-- Table structure for table `app_version`
--

CREATE TABLE `app_version` (
  `id` bigint(20) NOT NULL,
  `version_code` int(11) NOT NULL,
  `version_name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `app_version`
--

INSERT INTO `app_version` (`id`, `version_code`, `version_name`, `type`, `status`, `created_at`, `updated_at`) VALUES
(1, 1, '1.0', 'ANDROID', 'ACTIVE', '2021-11-23 15:16:40', '2022-01-21 10:52:28'),
(5, 66, '667', 'ANDROID', 'ACTIVE', '2021-12-11 11:34:29', '2022-01-21 10:51:15');

-- --------------------------------------------------------

--
-- Table structure for table `ci_sessions`
--

CREATE TABLE `ci_sessions` (
  `id` varchar(128) NOT NULL,
  `ip_address` varchar(45) NOT NULL,
  `timestamp` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `data` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ci_sessions`
--

INSERT INTO `ci_sessions` (`id`, `ip_address`, `timestamp`, `data`) VALUES
('d00daa01652f0bd0904c3eb976c612be442138e0', '192.168.169.211', 1718534261, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533343236313b),
('9ad778a40bb2024181b19a9b99629849489ff4ad', '192.168.169.211', 1718534261, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533343236313b),
('33218bb95634e673e074f5d06215e54d6207961f', '192.168.169.211', 1718535565, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533353536353b),
('fc1fae0e34ca5ccad739a966b61523b563cc9f77', '192.168.169.211', 1718535565, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533353536353b),
('00405dc526cab563e0d15c86f8b51ebf470cedbd', '192.168.169.211', 1718535645, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533353634353b),
('37f520f24a8c49bb60ee6b3a279c2783edf5cccc', '192.168.169.211', 1718535645, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533353634353b),
('15497e37485d61fbdc539700fd137a638c1f4c1b', '192.168.169.211', 1718535705, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533353730353b),
('fa1e90f8cea3acf96363f6ab2a7450a26bb77d94', '192.168.169.211', 1718535705, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533353730353b),
('6b6ba9c618045bccd508a832066580141adddd4a', '192.168.169.211', 1718539217, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393231373b),
('e7dc9f81cc41f06495ebd82cf71d90143aaf7abd', '192.168.169.211', 1718539217, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393231373b),
('db243b5e7a169eb57a6c070d754f385b059db300', '192.168.169.211', 1718539258, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393235383b),
('7448e3ac9e501bd9fa8948c51f36cb21e5fcf23a', '192.168.169.211', 1718539258, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393235383b),
('657b2fe52d6c923f2d33a25b0d9401dd190aa808', '192.168.169.211', 1718539278, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393237383b),
('68a0b334b141cdd347ff917c4c3798e3d5928821', '192.168.169.211', 1718539278, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393237383b),
('3e196177c657f0dcaa6094a2f2a6f465308d94fd', '192.168.169.211', 1718539379, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393337393b),
('07077e25274bd6d7b1b4a0b59a419e2142aeec5d', '192.168.169.211', 1718539379, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393337393b),
('3a95ab8b13c46ac5f654662c77b379e4927cb435', '192.168.169.211', 1718539403, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393430333b),
('7fff341b317ccf59df5087993fafd9b718b1e6d2', '192.168.169.211', 1718539403, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393430333b),
('5327bffe971ad18f3fcd4b53ea882c32e5872ece', '192.168.169.211', 1718539559, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393535393b),
('886d57bbc78e9667d3c2668dd2f57358527f7686', '192.168.169.211', 1718539559, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393535393b),
('23894f03b9e6db620234cdc6fc6b2222098dfbaf', '192.168.169.211', 1718539564, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393536343b),
('b1dc126f557e612a4b96a789ec682e8ab0246acd', '192.168.169.211', 1718539564, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393536343b),
('fe76ade08f493e0ab072f1ba102c304836532ff2', '192.168.169.211', 1718539565, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393536353b),
('2183ccd01491f7038031364444be0bd9c7471592', '192.168.169.211', 1718539565, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393536353b),
('ddb8f28768307fa0862c462e79448546e01cf47b', '192.168.169.211', 1718539570, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393537303b),
('6beb434509739f64e552128d2bc2c98b9c7847c3', '192.168.169.211', 1718539570, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393537303b),
('851c95ae3e6e7de73065d66ceaa1edf44fc94dc4', '192.168.169.211', 1718539818, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393831383b),
('63c9ca4c4261e7499d71c094c2d8a3afff678176', '192.168.169.211', 1718539818, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383533393831383b),
('8ed3870f0fef08e432b6b9e87f9107ac1a8eacf8', '192.168.169.211', 1718540574, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303537343b),
('88e937020ebc4960b792df12ca810068d0f99bdc', '192.168.169.211', 1718540575, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303537343b),
('ae1e27f6892f49a78313d61545411d11632b78e3', '192.168.169.211', 1718540955, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303935353b),
('4839aa67d31b38a1e04268648c3a15ca57c159cc', '192.168.169.211', 1718540955, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303935353b),
('048e6091bb0f88ae6c3568fc1275eebf88d149fa', '192.168.169.211', 1718540976, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303937363b),
('e6407ffee6aea46efa3741527e78eefcaeb274f5', '192.168.169.211', 1718540976, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303937363b),
('4d91fad1d1cda4fedc76377998d0d484f2830550', '192.168.169.211', 1718540978, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303937383b),
('df5a25e992ed5f39e0503218d5c5e390b3a9f7fb', '192.168.169.211', 1718540979, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303937393b),
('61a6dbe91b7d1344a9a5966cbf0f2f31be5fe7d6', '192.168.169.211', 1718540981, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303938313b),
('cc5c81e7a44cfea7be9274a6042179d2238b0866', '192.168.169.211', 1718540981, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303938313b),
('1fb2b13719c500a2da8a7a255be40feeba373f73', '192.168.169.211', 1718540985, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303938353b),
('46143a2daa929d4eebf334219160c67ea4dd58fa', '192.168.169.211', 1718540985, 0x5f5f63695f6c6173745f726567656e65726174657c693a313731383534303938353b);

-- --------------------------------------------------------

--
-- Table structure for table `db_brands`
--

CREATE TABLE `db_brands` (
  `id` int(50) NOT NULL,
  `brand_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `brand_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` mediumtext COLLATE utf8mb4_unicode_ci,
  `company_id` int(5) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_brands`
--

INSERT INTO `db_brands` (`id`, `brand_code`, `brand_name`, `description`, `company_id`, `status`) VALUES
(1, 'CT0001', 'Accer', 'semua produk dengan merk accer', NULL, 1),
(2, 'CT0002', 'SAMFLASH', 'SSD', NULL, 1),
(3, 'CT0003', 'TENDA', 'ROUTER 301', NULL, 1),
(4, 'CT0004', 'NETLINK', 'CONVERTER FIBER OPTIK', NULL, 1),
(5, 'CT0005', 'EYOTA', '', NULL, 1),
(6, 'CT0006', 'NYK', '', NULL, 1),
(7, 'CT0007', 'CADY DRIVE', '', NULL, 1),
(8, 'CT0008', '2ND CADY', '', NULL, 1),
(9, 'CT0009', 'TP-LINK', '', NULL, 1),
(10, 'CT0010', 'TARMOC', '', NULL, 1),
(11, 'CT0011', 'ETERLINK', '', NULL, 1),
(12, 'CT0012', 'ZTE', '', NULL, 1),
(13, 'CT0013', 'TPLINK', '', NULL, 1),
(14, 'CT0014', 'VASCOLINK', '', NULL, 1),
(15, 'CT0015', 'SPEKTRA', '', NULL, 1),
(16, 'CT0016', 'SEAGATE', '', NULL, 1),
(17, 'CT0017', 'WD', '', NULL, 1),
(18, 'CT0018', 'EPSON', '', NULL, 1),
(19, 'CT0019', 'LOGITEC', '', NULL, 1),
(20, 'CT0020', 'HILOOK', '', NULL, 1),
(21, 'CT0021', 'DAHUA', '', NULL, 1),
(22, 'CT0022', 'TOTOLINK', '', NULL, 1),
(23, 'CT0023', 'RUIJIE', '', NULL, 1),
(24, 'CT0024', 'SAMSUNG', '', NULL, 1),
(25, 'CT0025', 'ASUS', '', NULL, 1),
(26, 'CT0026', 'LENOVO', '', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_category`
--

CREATE TABLE `db_category` (
  `id` int(50) NOT NULL,
  `category_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` mediumtext COLLATE utf8mb4_unicode_ci,
  `company_id` int(5) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_category`
--

INSERT INTO `db_category` (`id`, `category_code`, `category_name`, `description`, `company_id`, `status`) VALUES
(1, 'CT0001', 'Laptop Acer', '', NULL, 1),
(2, 'CT0002', 'STORAGE', '', NULL, 1),
(3, 'CT0003', 'WIRELESS ROUTER', 'TENDA 301', NULL, 1),
(4, 'CT0004', 'NETWORK', 'NETWORK CONVERTER', NULL, 1),
(5, 'CT0005', 'SWICT PRINTER', 'SWICT PRINTER PARALEL  1-2 /1-4', NULL, 1),
(6, 'CT0006', 'SWICT PRITER', '', NULL, 1),
(7, 'CT0007', 'CADY DRIVE', 'CADY DRIVE  LAPTOP', NULL, 1),
(8, 'CT0008', 'DISPLAY', 'MONITOR DAN SEJENISNYA', NULL, 1),
(9, 'CT0009', 'VASCOLINK', '', NULL, 1),
(10, 'CT0010', 'PROJECTOR', '', NULL, 1),
(11, 'CT0011', 'MOUSE WIRELESS', '', NULL, 1),
(12, 'CT0012', 'WEB CAME', '', NULL, 1),
(13, 'CT0013', 'PRINTER', '', NULL, 1),
(14, 'CT0014', 'SECURYTY', '', NULL, 1),
(15, 'CT0015', 'SCREEN', '', NULL, 1),
(16, 'CT0016', 'CHARGER', '', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_cobpayments`
--

CREATE TABLE `db_cobpayments` (
  `id` int(50) NOT NULL,
  `customer_id` int(5) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `payment` double(20,2) DEFAULT NULL,
  `payment_note` mediumtext COLLATE utf8mb4_unicode_ci,
  `system_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` time DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_company`
--

CREATE TABLE `db_company` (
  `id` int(50) NOT NULL,
  `company_code` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `owner` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `company_name` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_website` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `website` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_logo` text COLLATE utf8mb4_unicode_ci,
  `logo` mediumtext COLLATE utf8mb4_unicode_ci,
  `upi_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upi_code` text COLLATE utf8mb4_unicode_ci,
  `signature` text COLLATE utf8mb4_unicode_ci,
  `show_signature` int(1) DEFAULT '0',
  `country` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `postcode` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gst_no` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `vat_no` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pan_no` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bank_details` mediumtext COLLATE utf8mb4_unicode_ci,
  `cid` int(10) DEFAULT NULL,
  `category_init` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_init` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'INITAL CODE',
  `supplier_init` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'INITAL CODE',
  `purchase_init` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'INITAL CODE',
  `purchase_return_init` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_init` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'INITAL CODE',
  `sales_init` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'INITAL CODE',
  `sales_return_init` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `expense_init` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `invoice_view` int(5) DEFAULT NULL COMMENT '1=Standard,2=Indian GST',
  `status` int(1) DEFAULT NULL,
  `sms_status` int(1) DEFAULT NULL COMMENT '1=Enable 0=Disable',
  `sales_terms_and_conditions` text COLLATE utf8mb4_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_company`
--

INSERT INTO `db_company` (`id`, `company_code`, `owner`, `company_name`, `company_website`, `mobile`, `phone`, `email`, `website`, `company_logo`, `logo`, `upi_id`, `upi_code`, `signature`, `show_signature`, `country`, `state`, `city`, `address`, `postcode`, `gst_no`, `vat_no`, `pan_no`, `bank_details`, `cid`, `category_init`, `item_init`, `supplier_init`, `purchase_init`, `purchase_return_init`, `customer_init`, `sales_init`, `sales_return_init`, `expense_init`, `invoice_view`, `status`, `sms_status`, `sales_terms_and_conditions`) VALUES
(1, '', '', 'EKLONTONG', NULL, '087751745073http://192.168.58.245:85/pos-kasir-v2/theme/dist/img/avatar5.png', '087751745073', 'admin@example.com', '', 'Screenshot_from_2024-01-02_01-31-08.png', 'logo-0.png', '', NULL, NULL, 0, 'Indonesia', 'Jawa Timur', 'Pacitan', 'Jl. Veteran No.24 RT. 002 RW. 003 Pacitan', '635111', '', '', '', '', 1, 'CT', 'IT', 'SP', 'PU', 'PR', 'CU', 'SL', 'PR', 'EX', 1, 1, 0, ''),
(2, NULL, 'Nur Musari', 'Toko Klontong Rumah Tangga', NULL, '087751948827', '087751948827', 'nurmusarisitoh@gmail.com', NULL, NULL, 'logo-0.png', NULL, NULL, NULL, 0, 'Indonesia', NULL, NULL, 'Mlati Arjosari', NULL, NULL, NULL, NULL, NULL, 1, 'CT', 'IT', 'SP', 'PU', 'PR', 'CU', 'SL', 'PR', 'EX', 1, 1, 0, NULL),
(3, NULL, 'Nur Musari', 'Toko Klontong Rumah Tangga', NULL, '087751948827', '087751948827', 'nurmusarisitoh@gmail.com', NULL, NULL, 'logo-0.png', NULL, NULL, NULL, 0, 'Indonesia', NULL, NULL, 'Mlati Arjosari', NULL, NULL, NULL, NULL, NULL, 1, 'CT', 'IT', 'SP', 'PU', 'PR', 'CU', 'SL', 'PR', 'EX', 1, 1, 0, NULL),
(4, NULL, 'Nur Musari', 'Toko Klontong Rumah ', NULL, '087751948827', '087751948827', 'nurmusarisitoh@gmail.com', NULL, NULL, 'logo-0.png', NULL, NULL, NULL, 0, 'Indonesia', NULL, NULL, 'Mlati Arjosari', NULL, NULL, NULL, NULL, NULL, 1, 'CT', 'IT', 'SP', 'PU', 'PR', 'CU', 'SL', 'PR', 'EX', 1, 1, 0, NULL),
(6, NULL, 'sinta dewi', 'DKN', NULL, '082245872607', '082245872607', 'sintadewi@gmail.com', NULL, NULL, 'logo-0.png', NULL, NULL, NULL, 0, 'Indonesia', NULL, NULL, 'pacitan', NULL, NULL, NULL, NULL, NULL, 1, 'CT', 'IT', 'SP', 'PU', 'PR', 'CU', 'SL', 'PR', 'EX', 1, 1, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `db_country`
--

CREATE TABLE `db_country` (
  `id` int(50) NOT NULL,
  `country_code` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country` varchar(4050) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `added_on` date DEFAULT NULL,
  `company_id` int(5) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_country`
--

INSERT INTO `db_country` (`id`, `country_code`, `country`, `added_on`, `company_id`, `status`) VALUES
(1, '', 'India', '2017-07-10', 1, 1),
(2, NULL, 'USA', NULL, NULL, 1),
(3, NULL, 'Indonesia', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_currency`
--

CREATE TABLE `db_currency` (
  `id` int(50) NOT NULL,
  `currency_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `currency_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `currency` blob,
  `symbol` mediumtext COLLATE utf8mb4_unicode_ci,
  `status` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_currency`
--

INSERT INTO `db_currency` (`id`, `currency_name`, `currency_code`, `currency`, `symbol`, `status`) VALUES
(1, 'Bulgaria-Bulgarian lev(BGN)', NULL, 0xd0bbd0b2, NULL, 1),
(2, 'Switzerland \r-Swiss franc (CHF)', NULL, 0x434846, NULL, 1),
(3, 'Czechia-Czech koruna(CZK))', NULL, 0x4bc48d20, NULL, 1),
(4, 'Denmark-Danish krone(DKK)', NULL, 0x6b7220, NULL, 1),
(5, 'Euro area countries -Euro(EUR)', NULL, 0xe282ac20, NULL, 1),
(6, 'United Kingdom-Pounds sterling (GBP)', NULL, 0xc2a3, NULL, 1),
(7, 'Croatia -Croatian Kuna (HRK)', NULL, 0x6b6e, NULL, 1),
(8, 'Georgia -Georgian lari (GEL)', NULL, 0x2623383338323b, NULL, 1),
(9, 'Hungary -Hungarian forint (HUF)', NULL, 0x6674, NULL, 1),
(10, 'Norway -Norwegian krone (NOK)', NULL, 0x6b72, NULL, 1),
(11, 'Poland -Polish zloty (PLN)', NULL, 0x7ac58220, NULL, 1),
(12, 'Russia -Russian ruble (RUB)', NULL, 0x2623383338313b20, NULL, 1),
(13, 'Romania -Romanian leu (RON)', NULL, 0x6c6569, NULL, 1),
(14, 'Sweden - Swedish krona (SEK)', NULL, 0x6b72, NULL, 1),
(15, 'Turkey -Turkish lira (TRY)', NULL, 0x2623383337383b20, NULL, 1),
(16, 'Ukraine - Ukrainian hryvna  (UAH)', NULL, 0xe282b420, NULL, 1),
(17, 'UAE -Emirati dirham (AED)', NULL, 0xd8af2ed8a520, NULL, 1),
(18, 'Israel - Israeli shekel (ILS)', NULL, 0x2623383336323b20, NULL, 1),
(19, 'Kenya - Kenyan shilling(KES)', NULL, 0x4b7368, NULL, 1),
(20, 'Morocco - Moroccan dirham (MAD)', NULL, 0x2ed8af2ed98520, NULL, 1),
(21, 'Nigeria - Nigerian naira (NGN)', NULL, 0xe282a620, NULL, 1),
(22, 'South Africa -South african rand** (ZAR)', NULL, 0x52, NULL, 1),
(23, 'Brazil- Brazilian real(BRL)', NULL, 0x5224, NULL, 1),
(24, 'Canada-Canadian dollars (CAD)', NULL, 0x24, NULL, 1),
(25, 'Chile -Chilean peso (CLP)', NULL, 0x24, NULL, 1),
(26, 'Colombia -Colombian peso (COP)', NULL, 0x24, NULL, 1),
(27, 'Mexico - Mexican peso (MXN)', NULL, 0x24, NULL, 1),
(28, 'Peru -Peruvian sol(PEN)', NULL, 0x532f2e20, NULL, 1),
(29, 'USA -US dollar (USD)', NULL, 0x24, NULL, 1),
(30, 'Australia -Australian dollars (AUD)', NULL, 0x24, NULL, 1),
(31, 'Bangladesh -Bangladeshi taka (BDT) ', NULL, 0x2623323534373b20, NULL, 1),
(32, 'China - Chinese yuan (CNY)', NULL, 0x262332303830333b20, NULL, 1),
(33, 'Hong Kong - Hong Kong dollar(HKD)', NULL, 0x262333363b20, NULL, 1),
(34, 'Indonesia - Indonesian rupiah (IDR)', NULL, 0x5270, NULL, 1),
(35, 'India - Indian rupee', 'INR', 0xe282b9, '?', 1),
(36, 'Japan - Japanese yen (JPY)', NULL, 0xc2a5, NULL, 1),
(37, 'Malaysia - Malaysian ringgit (MYR)', NULL, 0x524d, NULL, 1),
(38, 'New Zealand - New Zealand dollar (NZD)', NULL, 0x24, NULL, 1),
(39, 'Philippines- Philippine peso (PHP)', NULL, 0xe282b120, NULL, 1),
(40, 'Pakistan- Pakistani rupee (PKR)', NULL, 0x527320, NULL, 1),
(41, 'Singapore - Singapore dollar (SGD)', NULL, 0x24, NULL, 1),
(42, 'South Korea - South Korean won (KRW)', NULL, 0x2623383336313b20, NULL, 1),
(43, 'Sri Lanka - Sri Lankan rupee (LKR)', NULL, 0x5273, NULL, 1),
(44, 'Thailand- Thai baht (THB)', NULL, 0x2623333634373b20, NULL, 1),
(45, 'Vietnam - Vietnamese dong', 'VND', 0xe282ab, NULL, 1),
(46, 'Bitcoin - BTC or XBT', 'BTC ', 0xe282bf, NULL, 1),
(47, 'Ripples', 'XRP', 0x585250, NULL, 1),
(48, 'Monero', 'XMR', 0xc9b1, NULL, 1),
(49, 'Litecoin', 'LTC', 0xc581, NULL, 1),
(50, 'Ethereum', 'ETH', 0xce9e, NULL, 1),
(51, 'Euro', 'EUR', 0xe282ac, NULL, 1),
(52, 'Pounds sterling', 'GBP', 0xc2a3, NULL, 1),
(53, 'US dollar', 'USD', 0x24, NULL, 1),
(54, 'Japanese yen', 'JPY', 0xc2a5, NULL, 1),
(55, 'Omani rial', 'OMR', 0xd8b12ed8b92e, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_customers`
--

CREATE TABLE `db_customers` (
  `id` int(50) NOT NULL,
  `customer_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gstin` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tax_number` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `vatin` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `opening_balance` double(20,2) DEFAULT NULL,
  `sales_due` double(20,2) DEFAULT NULL,
  `sales_return_due` double(20,2) DEFAULT NULL,
  `country_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `postcode` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_time` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_id` int(5) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_customers`
--

INSERT INTO `db_customers` (`id`, `customer_code`, `customer_name`, `mobile`, `phone`, `email`, `gstin`, `tax_number`, `vatin`, `opening_balance`, `sales_due`, `sales_return_due`, `country_id`, `state_id`, `city`, `postcode`, `address`, `system_ip`, `system_name`, `created_date`, `created_time`, `created_by`, `company_id`, `status`) VALUES
(1, 'CU0001', 'Pembeli Langsung', '', '', '', '', '', NULL, NULL, 0.00, NULL, '', '', NULL, '', '', NULL, NULL, '2019-01-01', '10:55:54 pm', 'admin', NULL, 1),
(2, 'CU0002', 'RSUD dr. Darsono Pacitan', '0357881410', '0357881410', 'rsud@pacitankab.go.id', '0', '0', NULL, 0.00, 0.00, NULL, '3', '54', 'Pacitan', '63611', 'Jl. Jend. A. Yani No.51, Pacitan', '192.168.58.245', '192.168.58.245', '2024-01-01', '10:07:02 pm', 'admin', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_customer_payments`
--

CREATE TABLE `db_customer_payments` (
  `id` int(50) NOT NULL,
  `salespayment_id` int(5) DEFAULT NULL,
  `customer_id` int(5) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_type` varchar(50) DEFAULT NULL,
  `payment` double(20,2) DEFAULT NULL,
  `payment_note` text,
  `system_ip` varchar(50) DEFAULT NULL,
  `system_name` varchar(50) DEFAULT NULL,
  `created_time` varchar(50) DEFAULT NULL,
  `created_date` varchar(50) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_customer_payments`
--

INSERT INTO `db_customer_payments` (`id`, `salespayment_id`, `customer_id`, `payment_date`, `payment_type`, `payment`, `payment_note`, `system_ip`, `system_name`, `created_time`, `created_date`, `created_by`, `status`) VALUES
(229, 23, 1, '2024-01-09', 'Cash', 606375.00, 'Cash', '103.138.10.6', '103.138.10.6', '04:37:05', '2024-01-09', 'admintoko', 1),
(230, 24, 1, '2024-01-15', 'Cash', 111000.00, '', '103.138.10.6', '103.138.10.6', '10:40:42', '2024-01-15', 'admintoko', 1),
(231, 25, 1, '2024-02-16', 'Cash', 252000.00, 'Paid By Cash', '103.138.10.6', '103.138.10.6', '11:10:57', '2024-02-16', 'admintoko', 1),
(232, 26, 1, '2024-06-15', 'Cash', 5206000.00, 'Paid By Cash', '192.168.85.211', '192.168.85.211', '10:10:41', '2024-06-15', 'admintoko', 1),
(233, 27, 1, '2024-06-15', 'Cash', 5206000.00, 'Paid By Cash', '192.168.85.211', '192.168.85.211', '10:15:43', '2024-06-15', 'admintoko', 1),
(234, 28, 1, '2024-06-15', 'Cash', 730000.00, 'Paid By Cash', '192.168.85.211', 'RORIK-NB', '11:18:01', '2024-06-15', 'admintoko', 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_expense`
--

CREATE TABLE `db_expense` (
  `id` int(50) NOT NULL,
  `expense_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category_id` int(5) DEFAULT NULL,
  `expense_date` date DEFAULT NULL,
  `reference_no` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `expense_for` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `expense_amt` double(20,2) DEFAULT NULL,
  `note` mediumtext COLLATE utf8mb4_unicode_ci,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `company_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_expense`
--

INSERT INTO `db_expense` (`id`, `expense_code`, `category_id`, `expense_date`, `reference_no`, `expense_for`, `expense_amt`, `note`, `created_by`, `created_date`, `created_time`, `system_ip`, `system_name`, `status`, `company_id`) VALUES
(3, 'EX0001', 1, '2024-06-16', '', 'pembelian atk', 10000.00, 'atl pensil penggaris', 'Nur Musari', '2024-06-16', '01:06:17', NULL, 'android', 1, 4),
(4, 'EX0004', 1, '2024-06-16', 'shshs', 'pembelian minum', 5000.00, 'minum jus', 'Nur Musari', '2024-06-16', '01:17:37', NULL, 'android', 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `db_expense_category`
--

CREATE TABLE `db_expense_category` (
  `id` int(50) NOT NULL,
  `category_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` mediumtext COLLATE utf8mb4_unicode_ci,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_expense_category`
--

INSERT INTO `db_expense_category` (`id`, `category_code`, `category_name`, `description`, `created_by`, `status`) VALUES
(1, 'EC0001', 'Default', '', 'admintoko', 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_hold`
--

CREATE TABLE `db_hold` (
  `id` int(50) NOT NULL,
  `reference_id` varchar(50) DEFAULT NULL,
  `reference_no` varchar(50) DEFAULT NULL,
  `sales_date` date DEFAULT NULL,
  `sales_status` varchar(50) DEFAULT NULL,
  `customer_id` int(5) DEFAULT NULL,
  `other_charges_input` double(20,2) DEFAULT NULL,
  `other_charges_tax_id` int(5) DEFAULT NULL,
  `other_charges_amt` double(20,2) DEFAULT NULL,
  `discount_to_all_input` double(20,2) DEFAULT NULL,
  `discount_to_all_type` varchar(50) DEFAULT NULL,
  `tot_discount_to_all_amt` double(20,2) DEFAULT NULL,
  `subtotal` double(20,2) DEFAULT NULL,
  `round_off` double(20,2) DEFAULT NULL,
  `grand_total` double(20,2) DEFAULT NULL,
  `sales_note` text,
  `pos` int(1) DEFAULT NULL COMMENT '1=yes 0=no'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `db_holditems`
--

CREATE TABLE `db_holditems` (
  `id` int(50) NOT NULL,
  `hold_id` int(5) DEFAULT NULL,
  `item_id` int(5) DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `sales_qty` double(20,2) DEFAULT NULL,
  `price_per_unit` double(20,2) DEFAULT NULL,
  `tax_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tax_id` int(5) DEFAULT NULL,
  `tax_amt` double(20,2) DEFAULT NULL,
  `discount_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `discount_input` double(20,2) DEFAULT NULL,
  `discount_amt` double(20,2) DEFAULT NULL,
  `unit_total_cost` double(20,2) DEFAULT NULL,
  `total_cost` double(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_items`
--

CREATE TABLE `db_items` (
  `id` int(50) NOT NULL,
  `item_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `custom_barcode` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `category_id` int(10) DEFAULT NULL,
  `sku` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hsn` varbinary(50) DEFAULT NULL,
  `unit_id` int(10) DEFAULT NULL,
  `alert_qty` int(10) DEFAULT NULL,
  `brand_id` int(5) DEFAULT NULL,
  `lot_number` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `expire_date` date DEFAULT NULL,
  `price` double(20,2) DEFAULT NULL,
  `tax_id` int(5) DEFAULT NULL,
  `purchase_price` double(20,2) DEFAULT NULL,
  `tax_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `profit_margin` double(20,2) DEFAULT NULL,
  `sales_price` double(20,2) DEFAULT NULL,
  `final_price` double(20,2) DEFAULT NULL,
  `stock` double(20,2) DEFAULT NULL,
  `item_image` mediumtext COLLATE utf8mb4_unicode_ci,
  `system_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_id` int(5) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `discount_type` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `discount` double(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_items`
--

INSERT INTO `db_items` (`id`, `item_code`, `custom_barcode`, `item_name`, `description`, `category_id`, `sku`, `hsn`, `unit_id`, `alert_qty`, `brand_id`, `lot_number`, `expire_date`, `price`, `tax_id`, `purchase_price`, `tax_type`, `profit_margin`, `sales_price`, `final_price`, `stock`, `item_image`, `system_ip`, `system_name`, `created_date`, `created_time`, `created_by`, `company_id`, `status`, `discount_type`, `discount`) VALUES
(1, 'IT0001', '', 'Acer Aspire 5 A514 56P 59SK i5 1335U/16GB/512GB SSD/14&quot; WUXGA/W11+OHS', '', 1, 'X8H40501016', '', 9, 0, 1, '', NULL, 9410000.00, 1, 9410000.00, 'Exclusive', NULL, 9410000.00, 9410000.00, 100.00, NULL, '192.168.58.249', 'cobaaaa.com', '2024-01-01', '05:52:59 pm', 'admin', NULL, 1, 'Percentage', 0.00),
(2, 'IT0002', '', 'ACER Aspire Lite AL14 31P C0G4 Intel N100 8/512GB SSD W11 WUXGA IPS', '', 1, 'X8H4050988', '', 9, 0, 1, '', NULL, 5066000.00, 1, 5066000.00, 'Exclusive', NULL, 5066000.00, 5066000.00, 98.00, 'uploads/items/1704121821.jpg', '192.168.58.245', '192.168.58.245', '2024-01-01', '10:10:21 pm', 'admin', NULL, 1, 'Percentage', 0.00),
(3, 'IT0003', 'SMCSAMFLASH512GB', 'SSD SAMFLASH 512GB', '', 2, 'SMCSSDSMF512', '', 13, 0, 2, '', NULL, 550000.00, 3, 577500.00, 'Exclusive', 5.00, 577500.00, 606375.00, 9.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-09', '04:35:13 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(4, 'IT0005', '', 'ROUTER TENDA 301', 'ECER', 3, '', '', 9, 1, 3, '', NULL, 137000.00, 1, 137000.00, 'Exclusive', 2.00, 140000.00, 140000.00, 106.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '09:56:10 am', 'admintoko', NULL, 1, 'Percentage', 0.00),
(5, 'IT0007', 'SIX001600501999888', 'CONVERTER NETLINK', 'CONVERTER NETLINK', 4, '', '', 9, 28, 4, '', NULL, 100000.00, 4, 111000.00, 'Exclusive', 10.00, 110000.00, 122100.00, 28.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '10:36:28 am', 'admintoko', NULL, 1, 'Fixed', 10000.00),
(6, 'IT0004', '0052819503104', 'CONVERTER FIBER EYOTA', 'CONVERTER EYOTA', 4, '', '', 9, 20, 5, '', NULL, 100000.00, 4, 111000.00, 'Exclusive', 10.00, 110000.00, 122100.00, 19.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '10:39:34 am', 'admintoko', NULL, 1, 'Fixed', 10000.00),
(7, 'IT0008', '6932849427141', 'TENDA F3', '', 4, 'TENDA-F3', '', 9, 1, 3, '', NULL, 150000.00, 1, 150000.00, 'Exclusive', 7.00, 160000.00, 160000.00, 23.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '10:55:15 am', 'admintoko', NULL, 1, 'Fixed', 10000.00),
(8, 'IT0009', '8179428209981', 'AUTO SWICT PRINTER  1 - 2', '', 6, 'NYK-SWITCH-2', '', 9, 1, 6, '', NULL, 100000.00, 4, 111000.00, 'Exclusive', 10.00, 110000.00, 122100.00, 10.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '11:07:57 am', 'admintoko', NULL, 1, 'Percentage', 1.00),
(9, 'IT00010', '', 'CADY DRIVE', 'CADY DRIVE SLIM', 2, '', '', 9, 1, 8, '', NULL, 45000.00, 4, 49950.00, 'Exclusive', 10.00, 49500.00, 54945.00, 40.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '11:18:44 am', 'admintoko', NULL, 1, 'Fixed', 10000.00),
(10, 'IT0011', '', 'MONITOR ACER 19.5&quot; HDMI', 'MONITOR ACER 19.5&quot;', 8, '', '', 9, 1, 1, '', NULL, 950000.00, 1, 950000.00, 'Exclusive', 10.00, 1045000.00, 1045000.00, 8.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '11:36:00 am', 'admintoko', NULL, 1, 'Percentage', 0.00),
(11, 'IT0012', '', 'SWITCH HUB TP-LINK 5 PORT', '', 4, '', '', 9, 1, 9, '', NULL, 85000.00, 1, 85000.00, 'Exclusive', 12.00, 95200.00, 95200.00, 5.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '11:42:14 am', 'admintoko', NULL, 1, 'Percentage', 0.00),
(12, 'IT0013', '', 'HTB NETLINK 4SC2E', '', 4, '', '', 9, 1, 4, '', NULL, 260000.00, 1, 260000.00, 'Exclusive', 9.70, 285220.00, 285220.00, 8.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '12:06:47 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(13, 'IT0014', '', 'HTB TARMOC 3SC2E', '', 4, '', '', 9, 1, 10, '', NULL, 200000.00, 1, 200000.00, 'Exclusive', 12.50, 225000.00, 225000.00, 5.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '12:11:32 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(14, 'IT0015', '', 'HTB TARMOC 6SC 2E', '', 4, '', '', 9, 1, 10, '', NULL, 270000.00, 1, 270000.00, 'Exclusive', 12.00, 302400.00, 302400.00, 8.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '12:13:43 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(15, 'IT0016', '', 'HTB TARMOC 2SC2E', '', 4, '', '', 9, 1, 10, '', NULL, 180000.00, 1, 180000.00, 'Exclusive', 11.20, 200160.00, 200160.00, 5.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '12:16:14 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(16, 'IT0017', '', 'KABEL PATCH CORE  1M', '', 4, '', '', 9, 1, 11, '', NULL, 8000.00, 1, 8000.00, 'Exclusive', 25.00, 10000.00, 10000.00, 40.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '02:13:41 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(17, 'IT0018', '', 'SWITCHHUB GIGABYTE  8 PORT TP-LINK', '', 4, 'TPLINK-SW-GB-08', '', 9, 1, 9, '', NULL, 300000.00, 1, 300000.00, 'Exclusive', 28.00, 385000.00, 385000.00, 5.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '02:45:49 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(18, 'IT0019', '', 'EPON ZTE F450', '', 4, 'ZTE-ROUTER-', '', 9, 1, 12, '', NULL, 123000.00, 1, 123000.00, 'Exclusive', 25.00, 153750.00, 153750.00, 5.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '02:57:18 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(19, 'IT0020', '', 'CONVERTER SFP TPLINK  MC220L', '', 4, '', '', 9, 4, 13, '', NULL, 225000.00, 1, 225000.00, 'Exclusive', 12.00, 252000.00, 252000.00, 3.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '03:43:24 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(20, 'IT0021', '', 'ROUTER WIFI TPLINK  AC 1200  ARCHER C64', '', 4, '', '', 9, 2, 9, '', NULL, 395000.00, 1, 395000.00, 'Exclusive', 12.00, 442400.00, 442400.00, 2.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '03:59:03 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(21, 'IT0022', '', 'ROUTER TPLINK AC1200 ARCHER C54', '', 4, '', '', 9, 2, 9, '', NULL, 265000.00, 1, 265000.00, 'Inclusive', 15.00, 304750.00, 304750.00, 2.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '04:04:11 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(22, 'IT0023', '', 'ROUTER TPLINK AC-1200  GIGABYTE ARCHER A6', '', 4, '', '', 9, 1, 9, '', NULL, 540000.00, 4, 599400.00, 'Exclusive', 12.00, 604800.00, 671328.00, 1.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '04:06:42 pm', 'admintoko', NULL, 1, 'Fixed', 0.00),
(23, 'IT0024', '', 'ROUTER TPLINK AC1900 GIGABITE ARCHER80', '', 4, '', '', 9, 1, 9, '', NULL, 520000.00, 1, 520000.00, 'Exclusive', 10.00, 572000.00, 634920.00, 1.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '04:09:13 pm', 'admintoko', NULL, 1, 'Fixed', 0.00),
(24, 'IT0025', '', 'ROUTERE TPLINK AX1800 DUAL BAND ARCHER AX23', '', 4, '', '', 9, 1, 9, '', NULL, 775000.00, 1, 775000.00, 'Exclusive', 10.00, 852500.00, 852500.00, 1.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '04:11:29 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(25, 'IT0026', '', 'ROUTER TPLINK WIFI 6 ARCHER AX10', '', 4, '', '', 9, 1, 9, '', NULL, 675000.00, 1, 675000.00, 'Exclusive', 10.00, 742500.00, 742500.00, 1.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '04:16:24 pm', 'admintoko', NULL, 1, 'Percentage', 10000.00),
(26, 'IT0027', '', 'CONEKTOR RG 45  CAT 5 FASCOLINK  ISI 50 PCS', '', 9, '', '', 9, 100, 14, '', NULL, 1500.00, 4, 1665.00, 'Exclusive', 10.00, 1650.00, 1831.50, 100000.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '04:21:11 pm', 'admintoko', NULL, 1, 'Fixed', 100.00),
(27, 'IT0028', '', 'CONECTOR RG45  SPEKTRA', '', 4, '', '', 9, 100, 15, '', NULL, 2500.00, 4, 2775.00, 'Exclusive', 10.00, 2750.00, 3052.50, 100.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-15', '04:25:13 pm', 'admintoko', NULL, 1, 'Fixed', 100.00),
(28, 'IT0029', '', 'HARDISK 2.5 SEAGATE 500GB RFB', '', 2, 'SGT-2.5-HIJAU-500-RFB', '', 9, 1, 16, '', NULL, 180000.00, 1, 180000.00, 'Exclusive', 39.00, 250000.00, 250000.00, 3.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-17', '01:45:32 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(29, 'IT0030', '', 'HARDISK 2.5 WD 1TB RFB', '', 2, 'WD-2.5-BLUE-500-RFB', '', 9, 1, 17, '', NULL, 430000.00, 1, 430000.00, 'Exclusive', 12.00, 480000.00, 480000.00, 3.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-17', '01:54:18 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(30, 'IT0031', '', 'HARDISK 3.5 SEAGATE 500GB RFB', '', 2, 'SGT-3.5-HIJAU-500-RFB', '', 9, 1, 16, '', NULL, 100000.00, 1, 100000.00, 'Exclusive', 40.00, 140000.00, 140000.00, 3.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-17', '01:59:10 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(31, 'IT0031', '', 'HARDISK 3.5 SEAGATE 1TB RFB', '', 2, 'SGT-3.5-HIJAU-1-RFB', '', 9, 0, 16, '', NULL, 300000.00, 1, 300000.00, 'Exclusive', 17.00, 350000.00, 350000.00, 3.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-17', '02:02:14 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(32, 'IT0033', '', 'HARDISK 2.5 WD 1TB MFI', '', 2, 'SGT-3.5-HIJAU-1-MFI', '', 9, 0, 16, '', NULL, 890000.00, 1, 890000.00, 'Exclusive', 7.00, 950000.00, 950000.00, 5.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-17', '02:04:31 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(33, 'IT0034', '', 'HARDISK 3.5 SEAGATE 2TB', '', 2, 'SGT-3.5-HIJAU-1-MFI', '', 9, 0, 16, '', NULL, 1150000.00, 1, 1150000.00, 'Exclusive', 7.00, 1225000.00, 1225000.00, 5.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-17', '02:06:13 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(34, 'IT0035', '  ', 'Projector Epson EB-01', '', 10, '', '', 12, 1, 18, '', NULL, 5580000.00, 1, 5580000.00, 'Exclusive', 10.00, 6138000.00, 6138000.00, 3.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-21', '11:44:00 am', 'admintoko', NULL, 1, 'Fixed', 20000.00),
(35, 'IT0036', '', 'Projector Epson E-500', '', 10, '', '', 12, 1, 18, '', NULL, 5800000.00, 1, 5800000.00, 'Exclusive', 10.00, 6380000.00, 6380000.00, 1.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-21', '12:18:16 pm', 'admintoko', NULL, 1, 'Fixed', 20000.00),
(36, 'IT0037', '', 'MOUSE LOGITEC WIRELESS M331', '', 11, '', '', 9, 10, 19, '', NULL, 200000.00, 1, 200000.00, 'Exclusive', 10.00, 220000.00, 220000.00, 10.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-21', '12:22:36 pm', 'admintoko', NULL, 1, 'Fixed', 2000.00),
(37, 'IT0038', '', 'MOUSE LOGITEC WIRELESS  M350', '', 11, '', '', 9, 5, 19, '', NULL, 275000.00, 1, 275000.00, 'Exclusive', 10.00, 302500.00, 302500.00, 5.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-21', '12:24:44 pm', 'admintoko', NULL, 1, 'Fixed', 2000.00),
(38, 'IT0039', '', 'MOUSE LOGITEC WIRELESS M185', '', 11, '', '', 9, 10, 19, '', NULL, 150000.00, 1, 150000.00, 'Exclusive', 10.00, 165000.00, 165000.00, 0.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-21', '12:30:08 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(39, 'IT0040', '', 'WEBCAM LOGITEC C310', 'WEB CAME LOGITEC C310', 12, '', '', 9, 2, 19, '', NULL, 320000.00, 1, 320000.00, 'Exclusive', 10.00, 352000.00, 352000.00, 0.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-21', '12:32:15 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(40, 'IT0041', '', 'WEBCAM LOGITEC C505', '', 12, '', '', 9, 1, 19, '', NULL, 450000.00, 1, 450000.00, 'Exclusive', 10.00, 495000.00, 495000.00, 11.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-21', '12:33:27 pm', 'admintoko', NULL, 1, 'Percentage', 0.00),
(41, 'IT0042', '', 'KABEL BELDEN LAN CAT 5', '', 4, '', '', 7, 2, 19, '', NULL, 1550000.00, 1, 1550000.00, 'Exclusive', 10.00, 1705000.00, 1705000.00, 0.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-21', '12:35:48 pm', 'admintoko', NULL, 1, 'Fixed', 5000.00),
(42, 'IT0043', '', 'PRINTER LX310', '', 13, 'EPSON-310', '', 12, 1, 18, '', NULL, 2650000.00, 1, 2650000.00, 'Exclusive', 13.00, 3000000.00, 3000000.00, 2.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-23', '11:08:58 am', 'admintoko', NULL, 1, 'Percentage', 0.00),
(43, 'IT0044', '', 'HILOOK DVR ACCUSENSE 4CH DVR-204G-M1', '', 14, 'HILOOK-204G-DVR', '', 12, 1, 20, '', NULL, 430000.00, 1, 430000.00, 'Exclusive', 40.00, 600000.00, 600000.00, 3.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-25', '11:48:27 am', 'admintoko', NULL, 1, 'Percentage', 0.00),
(44, 'IT0045', '', 'HILOOK DVR ACCUSENSE 8CH DVR-208G-M1', '', 14, '', '', 12, 1, 20, '', NULL, 578780.00, 1, 578780.00, 'Exclusive', 38.00, 800000.00, 800000.00, 3.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-25', '11:50:17 am', 'admintoko', NULL, 1, 'Percentage', 0.00),
(45, 'IT0046', '', 'HILOOK CAMERA 4IN1 BL 2MP THC-B120-PC(3.6mm)', '', 14, 'HILOOK-CAM-OUT-2MP', '', 12, 1, 20, '', NULL, 128000.00, 1, 128000.00, 'Exclusive', 95.00, 250000.00, 250000.00, 14.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-25', '11:54:50 am', 'admintoko', NULL, 1, 'Percentage', 0.00),
(46, 'IT0047', '', 'HILOOK CAMERA 4IN1 BL 2MP THC-T120-PC(2.8mm)', '', 14, 'HILOOK-CAM-IN-2MP', '', 12, 1, 20, '', NULL, 138550.00, 1, 138550.00, 'Exclusive', 88.00, 260000.00, 260000.00, 0.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-25', '11:58:56 am', 'admintoko', 4, 1, 'Percentage', 0.00),
(47, 'IT0048', '', 'DAHUA COOPER CAMERA HDCVI BL 2MP DH-HAC-B1A21P (3.6mm)', '', 14, 'DAHUA-CAM-2MP-OUT', '', 12, 1, 21, '', NULL, 144000.00, 1, 144000.00, 'Exclusive', 88.00, 270000.00, 270000.00, 0.00, NULL, '103.138.10.6', '103.138.10.6', '2024-01-25', '12:06:04 pm', 'admintoko', 4, 1, 'Percentage', 0.00),
(48, 'IT0049', '', 'TOTOLINK N200RE', '', 4, 'TOTO-N200-V4', '', 12, 1, 22, '', NULL, 157000.00, 1, 157000.00, 'Exclusive', 2.00, 160000.00, 160000.00, 59.00, NULL, '103.138.10.6', '103.138.10.6', '2024-02-06', '10:52:05 am', 'admintoko', 4, 1, 'Percentage', 0.00),
(49, 'IT0050', '', 'RUIJIE RG-RAP2200(F)', '', 4, 'RG-RAP220-F', '', 12, 1, 23, '', NULL, 1000000.00, 1, 1000000.00, 'Exclusive', 9.00, 1085000.00, 1085000.00, 0.00, NULL, '103.138.10.6', '103.138.10.6', '2024-02-15', '03:43:10 pm', 'admintoko', 4, 1, 'Percentage', 0.00),
(50, 'IT0051', '', 'RUIJIE RG-RAP2200(E)', '', 4, 'RG-RAP220-E', '', 12, 1, 23, '', NULL, 1100000.00, 1, 1100000.00, 'Exclusive', 17.00, 1285000.00, 1285000.00, 0.00, NULL, '103.138.10.6', '103.138.10.6', '2024-02-15', '03:44:41 pm', 'admintoko', 4, 1, 'Percentage', 0.00),
(51, 'IT0052', '', 'LAYAR LED 14&quot; SLIM 40 PIN', '', 15, '', '', 13, 0, 24, '', NULL, 270000.00, 1, 270000.00, 'Exclusive', 30.00, 350000.00, 350000.00, 0.00, NULL, '103.138.10.6', '103.138.10.6', '2024-03-04', '08:43:12 am', 'admintoko', 4, 1, 'Percentage', 0.00),
(52, 'IT0053', '', 'LAYAR LED 14&quot; SLIM 30 PIN HD', '', 15, '', '', 13, 0, 24, '', NULL, 225000.00, 1, 225000.00, 'Exclusive', 51.00, 340000.00, 340000.00, 0.00, NULL, '103.138.10.6', '103.138.10.6', '2024-03-04', '08:44:10 am', 'admintoko', 4, 1, 'Percentage', 0.00),
(53, 'IT0054', '', 'ADAPTOR ASUS 19V3.7A', '', 16, '', '', 13, 0, 25, '', NULL, 110000.00, 1, 110000.00, 'Exclusive', 64.00, 180000.00, 180000.00, 2.00, NULL, '103.138.10.6', '103.138.10.6', '2024-03-04', '08:45:44 am', 'admintoko', 4, 1, 'Percentage', 0.00),
(54, 'IT0055', '', 'ADAPTOR LENOVO 19V3.7A', '', 16, '', '', 13, 0, 26, '', NULL, 110000.00, 1, 110000.00, 'Exclusive', 64.00, 180000.00, 180000.00, 2.00, NULL, '103.138.10.6', '103.138.10.6', '2024-03-04', '08:46:42 am', 'admintoko', 4, 1, 'Percentage', 0.00),
(55, 'IT40055', NULL, 'Mouse Logitect Wiralles 2.4 and 5', NULL, 11, '-', 0x2d, 11, 1, 0, '0', NULL, 60000.00, 0, 60000.00, '0', 0.00, 70000.00, 70000.00, 9.00, NULL, '-', 'AndroidApp', '2024-06-11', '18:18:05', 'Nur Musari', 4, 1, '0', 0.00),
(56, 'IT40056', '123456789', 'SSD 512 WD BOSKU', 'Barag wd paling ampuh', 2, '-', 0x2d, 9, 1, 0, '0', '1970-01-01', 350000.00, 0, 350000.00, '0', 0.00, 400000.00, 400000.00, 9.00, '1718130293_20a15509270e8d92cbf3.png', '-', 'AndroidApp', '2024-06-12', '12:08:03', 'Nur Musari', 4, 1, '0', 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `db_languages`
--

CREATE TABLE `db_languages` (
  `id` int(50) NOT NULL,
  `language` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_languages`
--

INSERT INTO `db_languages` (`id`, `language`, `status`) VALUES
(0, 'English', 0),
(1, 'Indonesian', 1),
(2, 'Hindi', 0),
(3, 'Kannada', 0),
(5, 'Chinese', 0),
(6, 'Russian', 0),
(7, 'Spanish', 0),
(8, 'Arabic', 0),
(9, 'Albanian', 0),
(10, 'Dutch', 0),
(11, 'Bangla', 0),
(12, 'Urdu', 0),
(13, 'Italian', 0),
(14, 'Marathi', 0),
(15, 'Khmer', 0),
(16, 'French', 0);

-- --------------------------------------------------------

--
-- Table structure for table `db_paymenttypes`
--

CREATE TABLE `db_paymenttypes` (
  `id` int(50) NOT NULL,
  `payment_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_paymenttypes`
--

INSERT INTO `db_paymenttypes` (`id`, `payment_type`, `status`) VALUES
(1, 'Cash', 1),
(2, 'Card', 1),
(3, 'Paytm', 1),
(4, 'Finance', 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_permissions`
--

CREATE TABLE `db_permissions` (
  `id` int(50) NOT NULL,
  `role_id` int(5) DEFAULT NULL,
  `permissions` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_permissions`
--

INSERT INTO `db_permissions` (`id`, `role_id`, `permissions`) VALUES
(99, 2, 'users_edit'),
(100, 2, 'users_view'),
(101, 2, 'tax_add'),
(102, 2, 'tax_edit'),
(103, 2, 'tax_delete'),
(104, 2, 'tax_view'),
(105, 2, 'currency_add'),
(106, 2, 'currency_edit'),
(107, 2, 'currency_delete'),
(108, 2, 'currency_view'),
(109, 2, 'units_add'),
(110, 2, 'units_edit'),
(111, 2, 'units_delete'),
(112, 2, 'units_view'),
(113, 2, 'places_add'),
(114, 2, 'places_edit'),
(115, 2, 'places_delete'),
(116, 2, 'places_view'),
(117, 2, 'expense_add'),
(118, 2, 'expense_edit'),
(119, 2, 'expense_delete'),
(120, 2, 'expense_view'),
(121, 2, 'items_add'),
(122, 2, 'items_edit'),
(123, 2, 'items_delete'),
(124, 2, 'items_view'),
(125, 2, 'brand_add'),
(126, 2, 'brand_edit'),
(127, 2, 'brand_delete'),
(128, 2, 'brand_view'),
(129, 2, 'suppliers_add'),
(130, 2, 'suppliers_edit'),
(131, 2, 'suppliers_delete'),
(132, 2, 'suppliers_view'),
(133, 2, 'customers_add'),
(134, 2, 'customers_edit'),
(135, 2, 'customers_delete'),
(136, 2, 'customers_view'),
(137, 2, 'purchase_add'),
(138, 2, 'purchase_edit'),
(139, 2, 'purchase_delete'),
(140, 2, 'purchase_view'),
(141, 2, 'sales_add'),
(142, 2, 'sales_edit'),
(143, 2, 'sales_delete'),
(144, 2, 'sales_view'),
(145, 2, 'sales_payment_view'),
(146, 2, 'sales_payment_add'),
(147, 2, 'sales_payment_delete'),
(148, 2, 'sales_report'),
(149, 2, 'purchase_report'),
(150, 2, 'expense_report'),
(151, 2, 'profit_report'),
(152, 2, 'stock_report'),
(153, 2, 'item_sales_report'),
(154, 2, 'purchase_payments_report'),
(155, 2, 'sales_payments_report'),
(156, 2, 'expired_items_report'),
(157, 2, 'items_category_add'),
(158, 2, 'items_category_edit'),
(159, 2, 'items_category_delete'),
(160, 2, 'items_category_view'),
(161, 2, 'print_labels'),
(162, 2, 'import_items'),
(163, 2, 'expense_category_add'),
(164, 2, 'expense_category_edit'),
(165, 2, 'expense_category_delete'),
(166, 2, 'expense_category_view'),
(167, 2, 'dashboard_view'),
(168, 2, 'purchase_return_add'),
(169, 2, 'purchase_return_edit'),
(170, 2, 'purchase_return_delete'),
(171, 2, 'purchase_return_view'),
(172, 2, 'purchase_return_report'),
(173, 2, 'sales_return_add'),
(174, 2, 'sales_return_edit'),
(175, 2, 'sales_return_delete'),
(176, 2, 'sales_return_view'),
(177, 2, 'sales_return_report'),
(178, 2, 'sales_return_payment_view'),
(179, 2, 'sales_return_payment_add'),
(180, 2, 'sales_return_payment_delete'),
(181, 2, 'purchase_return_payment_view'),
(182, 2, 'purchase_return_payment_add'),
(183, 2, 'purchase_return_payment_delete'),
(184, 2, 'purchase_payment_view'),
(185, 2, 'purchase_payment_add'),
(186, 2, 'purchase_payment_delete'),
(187, 2, 'payment_types_add'),
(188, 2, 'payment_types_edit'),
(189, 2, 'payment_types_delete'),
(190, 2, 'payment_types_view'),
(191, 2, 'import_customers'),
(192, 2, 'import_suppliers'),
(193, 2, 'item_purchase_report'),
(194, 2, 'pos'),
(195, 2, 'view_all_users_sales_invoices'),
(196, 2, 'view_all_users_sales_return_invoices'),
(197, 2, 'view_all_users_purchase_invoices'),
(198, 2, 'view_all_users_purchase_return_invoices');

-- --------------------------------------------------------

--
-- Table structure for table `db_purchase`
--

CREATE TABLE `db_purchase` (
  `id` int(50) NOT NULL,
  `purchase_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reference_no` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `purchase_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `supplier_id` int(5) DEFAULT NULL,
  `warehouse_id` int(5) DEFAULT NULL,
  `other_charges_input` double(20,2) DEFAULT NULL,
  `other_charges_tax_id` int(5) DEFAULT NULL,
  `other_charges_amt` double(20,2) DEFAULT NULL,
  `discount_to_all_input` double(20,2) DEFAULT NULL,
  `discount_to_all_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tot_discount_to_all_amt` double(20,2) DEFAULT NULL,
  `subtotal` double(20,2) DEFAULT NULL COMMENT 'Purchased qty',
  `round_off` double(20,2) DEFAULT NULL COMMENT 'Pending Qty',
  `grand_total` double(20,2) DEFAULT NULL,
  `purchase_note` mediumtext COLLATE utf8mb4_unicode_ci,
  `payment_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `paid_amount` double(20,2) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_ip` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_id` int(5) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `return_bit` int(1) DEFAULT NULL COMMENT 'Purchase return raised'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_purchaseitems`
--

CREATE TABLE `db_purchaseitems` (
  `id` int(50) NOT NULL,
  `purchase_id` int(5) DEFAULT NULL,
  `purchase_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_id` int(5) DEFAULT NULL,
  `purchase_qty` double(20,2) DEFAULT NULL,
  `price_per_unit` double(20,2) DEFAULT NULL,
  `tax_id` int(5) DEFAULT NULL,
  `tax_amt` double(20,2) DEFAULT NULL,
  `tax_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `unit_discount_per` double(20,2) DEFAULT NULL,
  `discount_amt` double(20,2) DEFAULT NULL,
  `unit_total_cost` double(20,2) DEFAULT NULL,
  `total_cost` double(20,2) DEFAULT NULL,
  `profit_margin_per` double(20,2) DEFAULT NULL,
  `unit_sales_price` double(20,2) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `discount_type` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `discount_input` double(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_purchaseitemsreturn`
--

CREATE TABLE `db_purchaseitemsreturn` (
  `id` int(50) NOT NULL,
  `purchase_id` int(5) DEFAULT NULL,
  `return_id` int(5) DEFAULT NULL,
  `return_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_id` int(5) DEFAULT NULL,
  `return_qty` double(20,2) DEFAULT NULL,
  `price_per_unit` double(20,2) DEFAULT NULL,
  `tax_id` int(5) DEFAULT NULL,
  `tax_amt` double(20,2) DEFAULT NULL,
  `tax_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `unit_discount_per` double(20,2) DEFAULT NULL,
  `discount_amt` double(20,2) DEFAULT NULL,
  `unit_total_cost` double(20,2) DEFAULT NULL,
  `total_cost` double(20,2) DEFAULT NULL,
  `profit_margin_per` double(20,2) DEFAULT NULL,
  `unit_sales_price` double(20,2) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `discount_type` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `discount_input` double(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_purchasepayments`
--

CREATE TABLE `db_purchasepayments` (
  `id` int(50) NOT NULL,
  `purchase_id` int(5) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `payment` double(20,2) DEFAULT NULL,
  `payment_note` mediumtext COLLATE utf8mb4_unicode_ci,
  `system_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` time DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_purchasepaymentsreturn`
--

CREATE TABLE `db_purchasepaymentsreturn` (
  `id` int(50) NOT NULL,
  `purchase_id` int(11) DEFAULT NULL,
  `return_id` int(5) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `payment` double(20,2) DEFAULT NULL,
  `payment_note` mediumtext COLLATE utf8mb4_unicode_ci,
  `system_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` time DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_purchasereturn`
--

CREATE TABLE `db_purchasereturn` (
  `id` int(50) NOT NULL,
  `purchase_id` int(11) DEFAULT NULL,
  `return_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reference_no` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `return_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `supplier_id` int(5) DEFAULT NULL,
  `warehouse_id` int(5) DEFAULT NULL,
  `other_charges_input` double(20,2) DEFAULT NULL,
  `other_charges_tax_id` int(5) DEFAULT NULL,
  `other_charges_amt` double(20,2) DEFAULT NULL,
  `discount_to_all_input` double(20,2) DEFAULT NULL,
  `discount_to_all_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tot_discount_to_all_amt` double(20,2) DEFAULT NULL,
  `subtotal` double(20,2) DEFAULT NULL COMMENT 'Purchased qty',
  `round_off` double(20,2) DEFAULT NULL COMMENT 'Pending Qty',
  `grand_total` double(20,2) DEFAULT NULL,
  `return_note` mediumtext COLLATE utf8mb4_unicode_ci,
  `payment_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `paid_amount` double(20,2) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_ip` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_id` int(5) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_roles`
--

CREATE TABLE `db_roles` (
  `id` int(50) NOT NULL,
  `role_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` mediumtext COLLATE utf8mb4_unicode_ci,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_roles`
--

INSERT INTO `db_roles` (`id`, `role_name`, `description`, `status`) VALUES
(1, 'Admin', 'All Rights Permitted.', 1),
(2, 'Admin Toko', 'Administrator Toko', 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_sales`
--

CREATE TABLE `db_sales` (
  `id` int(50) NOT NULL,
  `sales_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reference_no` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sales_date` date DEFAULT NULL,
  `sales_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_id` int(5) DEFAULT NULL,
  `warehouse_id` int(5) DEFAULT NULL,
  `other_charges_input` double(20,2) DEFAULT NULL,
  `other_charges_tax_id` int(5) DEFAULT NULL,
  `other_charges_amt` double(20,2) DEFAULT NULL,
  `discount_to_all_input` double(20,2) DEFAULT NULL,
  `discount_to_all_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tot_discount_to_all_amt` double(20,2) DEFAULT NULL,
  `subtotal` double(20,2) DEFAULT NULL,
  `round_off` double(20,2) DEFAULT NULL,
  `biaya_pajak` double(20,2) DEFAULT NULL,
  `biaya_pajak_total` double(20,2) DEFAULT NULL,
  `grand_total` double(20,2) DEFAULT NULL,
  `sales_note` mediumtext COLLATE utf8mb4_unicode_ci,
  `payment_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `paid_amount` double(20,2) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_ip` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_id` int(5) DEFAULT NULL,
  `pos` int(1) DEFAULT NULL COMMENT '1=yes 0=no',
  `status` int(1) DEFAULT NULL,
  `return_bit` int(1) DEFAULT NULL COMMENT 'sales return raised'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_sales`
--

INSERT INTO `db_sales` (`id`, `sales_code`, `reference_no`, `sales_date`, `sales_status`, `customer_id`, `warehouse_id`, `other_charges_input`, `other_charges_tax_id`, `other_charges_amt`, `discount_to_all_input`, `discount_to_all_type`, `tot_discount_to_all_amt`, `subtotal`, `round_off`, `biaya_pajak`, `biaya_pajak_total`, `grand_total`, `sales_note`, `payment_status`, `paid_amount`, `created_date`, `created_time`, `created_by`, `system_ip`, `system_name`, `company_id`, `pos`, `status`, `return_bit`) VALUES
(1, 'SL0001', '', '2024-01-09', 'Final', 1, NULL, NULL, NULL, NULL, NULL, 'in_percentage', NULL, 606375.00, NULL, NULL, NULL, 606375.00, '', 'Paid', 606375.00, '2024-01-09', '04:37:05 pm', 'admintoko', '103.138.10.6', '103.138.10.6', NULL, NULL, 1, NULL),
(2, 'SL0002', '', '2024-01-15', 'Final', 1, NULL, NULL, NULL, NULL, NULL, 'in_percentage', NULL, 111000.00, NULL, NULL, NULL, 111000.00, '', 'Paid', 111000.00, '2024-01-15', '10:40:42 am', 'admintoko', '103.138.10.6', '103.138.10.6', NULL, NULL, 1, NULL),
(3, 'SL0003', NULL, '2024-02-16', 'Final', 1, NULL, 0.00, NULL, 0.00, 0.00, 'in_percentage', 0.00, 252000.00, 0.00, NULL, NULL, 252000.00, NULL, 'Paid', 252000.00, '2024-02-16', '11:10:57 am', 'admintoko', '103.138.10.6', '103.138.10.6', NULL, 1, 1, NULL),
(19, 'SL0004', NULL, '2024-06-16', 'Final', 2, NULL, 0.00, NULL, 0.00, 0.00, 'in_percentage', 0.00, 0.00, 0.00, NULL, NULL, 1625000.00, NULL, NULL, NULL, '2024-06-16', '00;42:00', 'Nur Musari', NULL, 'ANDROID', 4, 1, 1, NULL),
(20, 'SL0020', NULL, '2024-06-16', 'Final', 2, NULL, 0.00, NULL, 0.00, 0.00, 'in_percentage', 0.00, 0.00, 0.00, NULL, NULL, 1535000.00, NULL, NULL, NULL, '2024-06-16', '01;01:09', 'Nur Musari', NULL, 'ANDROID', 4, 1, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `db_salesitems`
--

CREATE TABLE `db_salesitems` (
  `id` int(50) NOT NULL,
  `sales_id` int(5) DEFAULT NULL,
  `sales_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_id` int(5) DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `sales_qty` double(20,2) DEFAULT NULL,
  `price_per_unit` double(20,2) DEFAULT NULL,
  `tax_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tax_id` int(5) DEFAULT NULL,
  `tax_amt` double(20,2) DEFAULT NULL,
  `discount_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `discount_input` double(20,2) DEFAULT NULL,
  `discount_amt` double(20,2) DEFAULT NULL,
  `unit_total_cost` double(20,2) DEFAULT NULL,
  `total_cost` double(20,2) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `purchase_price` double(20,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_salesitems`
--

INSERT INTO `db_salesitems` (`id`, `sales_id`, `sales_status`, `item_id`, `description`, `sales_qty`, `price_per_unit`, `tax_type`, `tax_id`, `tax_amt`, `discount_type`, `discount_input`, `discount_amt`, `unit_total_cost`, `total_cost`, `status`, `purchase_price`) VALUES
(42, 1, 'Final', 3, '', 1.00, 577500.00, 'Exclusive', 3, 28875.00, 'Percentage', NULL, 0.00, 606375.00, 606375.00, 1, 550000.00),
(43, 2, 'Final', 6, '', 1.00, 110000.00, 'Exclusive', 4, 11000.00, 'Fixed', 10000.00, 10000.00, 112100.00, 111000.00, 1, 100000.00),
(44, 3, 'Final', 19, '', 1.00, 252000.00, 'Exclusive', 1, NULL, 'Percentage', 0.00, 0.00, 252000.00, 252000.00, 1, 225000.00),
(75, 19, 'Final', 53, '', 1.00, 180000.00, 'Exclusive', 1, NULL, 'Percentage', 0.00, 0.00, 180000.00, 180000.00, 1, 110000.00),
(76, 19, 'Final', 50, '', 1.00, 1285000.00, 'Exclusive', 1, NULL, 'Percentage', 0.00, 0.00, 1285000.00, 1285000.00, 1, 1100000.00),
(77, 19, 'Final', 48, '', 1.00, 160000.00, 'Exclusive', 1, NULL, 'Percentage', 0.00, 0.00, 160000.00, 160000.00, 1, 157000.00),
(78, 20, 'Final', 55, '', 1.00, 70000.00, 'Exclusive', 1, NULL, 'Percentage', 0.00, 0.00, 70000.00, 70000.00, 1, 60000.00),
(79, 20, 'Final', 54, '', 1.00, 180000.00, 'Exclusive', 1, NULL, 'Percentage', 0.00, 0.00, 180000.00, 180000.00, 1, 110000.00),
(80, 20, 'Final', 50, '', 1.00, 1285000.00, 'Exclusive', 1, NULL, 'Percentage', 0.00, 0.00, 1285000.00, 1285000.00, 1, 1100000.00);

-- --------------------------------------------------------

--
-- Table structure for table `db_salesitemsreturn`
--

CREATE TABLE `db_salesitemsreturn` (
  `id` int(50) NOT NULL,
  `sales_id` int(5) DEFAULT NULL,
  `return_id` int(5) DEFAULT NULL,
  `return_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_id` int(5) DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `return_qty` double(20,2) DEFAULT NULL,
  `price_per_unit` double(20,2) DEFAULT NULL,
  `tax_id` int(5) DEFAULT NULL,
  `tax_amt` double(20,2) DEFAULT NULL,
  `tax_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `discount_input` double(20,2) DEFAULT NULL,
  `discount_amt` double(20,2) DEFAULT NULL,
  `discount_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `unit_total_cost` double(20,2) DEFAULT NULL,
  `total_cost` double(20,2) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `purchase_price` double(20,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_salespayments`
--

CREATE TABLE `db_salespayments` (
  `id` int(50) NOT NULL,
  `sales_id` int(5) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `payment` double(20,2) DEFAULT NULL,
  `payment_note` mediumtext COLLATE utf8mb4_unicode_ci,
  `change_return` double(20,2) DEFAULT NULL COMMENT 'Refunding the greater amount',
  `system_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` time DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_salespayments`
--

INSERT INTO `db_salespayments` (`id`, `sales_id`, `payment_date`, `payment_type`, `payment`, `payment_note`, `change_return`, `system_ip`, `system_name`, `created_time`, `created_date`, `created_by`, `status`) VALUES
(23, 1, '2024-01-09', 'Cash', 606375.00, 'Cash', NULL, '103.138.10.6', '103.138.10.6', '04:37:05', '2024-01-09', 'admintoko', 1),
(24, 2, '2024-01-15', 'Cash', 111000.00, '', NULL, '103.138.10.6', '103.138.10.6', '10:40:42', '2024-01-15', 'admintoko', 1),
(25, 3, '2024-02-16', 'Cash', 252000.00, 'Paid By Cash', 0.00, '103.138.10.6', '103.138.10.6', '11:10:57', '2024-02-16', 'admintoko', 1),
(26, 4, '2024-06-15', 'Cash', 5206000.00, 'Paid By Cash', 0.00, '192.168.85.211', '192.168.85.211', '10:10:41', '2024-06-15', 'admintoko', 1),
(27, 5, '2024-06-15', 'Cash', 5206000.00, 'Paid By Cash', 0.00, '192.168.85.211', '192.168.85.211', '10:15:43', '2024-06-15', 'admintoko', 1),
(28, 6, '2024-06-15', 'Cash', 730000.00, 'Paid By Cash', 0.00, '192.168.85.211', 'RORIK-NB', '11:18:01', '2024-06-15', 'admintoko', 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_salespaymentsreturn`
--

CREATE TABLE `db_salespaymentsreturn` (
  `id` int(50) NOT NULL,
  `sales_id` int(5) DEFAULT NULL,
  `return_id` int(5) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `payment` double(20,2) DEFAULT NULL,
  `payment_note` mediumtext COLLATE utf8mb4_unicode_ci,
  `change_return` double(20,2) DEFAULT NULL COMMENT 'Refunding the greater amount',
  `system_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` time DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_salesreturn`
--

CREATE TABLE `db_salesreturn` (
  `id` int(50) NOT NULL,
  `sales_id` int(5) DEFAULT NULL,
  `return_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reference_no` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `return_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_id` int(5) DEFAULT NULL,
  `warehouse_id` int(5) DEFAULT NULL,
  `other_charges_input` double(20,2) DEFAULT NULL,
  `other_charges_tax_id` int(5) DEFAULT NULL,
  `other_charges_amt` double(20,2) DEFAULT NULL,
  `discount_to_all_input` double(20,2) DEFAULT NULL,
  `discount_to_all_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tot_discount_to_all_amt` double(20,2) DEFAULT NULL,
  `subtotal` double(20,2) DEFAULT NULL,
  `round_off` double(20,2) DEFAULT NULL,
  `grand_total` double(20,2) DEFAULT NULL,
  `return_note` mediumtext COLLATE utf8mb4_unicode_ci,
  `payment_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `paid_amount` double(20,2) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_ip` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_id` int(5) DEFAULT NULL,
  `pos` int(1) DEFAULT NULL COMMENT '1=yes 0=no',
  `status` int(1) DEFAULT NULL,
  `return_bit` int(1) DEFAULT NULL COMMENT 'Return raised or not 1 or null'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_sitesettings`
--

CREATE TABLE `db_sitesettings` (
  `id` int(50) NOT NULL,
  `version` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `site_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `logo` mediumtext COLLATE utf8mb4_unicode_ci COMMENT 'path',
  `language_id` int(5) DEFAULT NULL,
  `currency_id` int(5) DEFAULT NULL,
  `currency_placement` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `timezone` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_format` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time_format` int(5) DEFAULT NULL,
  `sales_discount` double(20,2) DEFAULT NULL,
  `site_url` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `site_title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `meta_title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `meta_desc` mediumtext COLLATE utf8mb4_unicode_ci,
  `meta_keywords` mediumtext COLLATE utf8mb4_unicode_ci,
  `currencysymbol_id` int(5) DEFAULT NULL,
  `regno_key` varchar(6) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `copyright` mediumtext COLLATE utf8mb4_unicode_ci,
  `facebook_url` mediumtext COLLATE utf8mb4_unicode_ci,
  `twitter_url` mediumtext COLLATE utf8mb4_unicode_ci,
  `youtube_url` mediumtext COLLATE utf8mb4_unicode_ci,
  `analytic_code` mediumtext COLLATE utf8mb4_unicode_ci,
  `fav_icon` mediumtext COLLATE utf8mb4_unicode_ci COMMENT 'path',
  `footer_logo` mediumtext COLLATE utf8mb4_unicode_ci COMMENT 'path',
  `company_id` int(1) DEFAULT NULL,
  `purchase_code` mediumtext COLLATE utf8mb4_unicode_ci,
  `change_return` int(1) DEFAULT NULL COMMENT 'show in pos',
  `sales_invoice_format_id` int(5) DEFAULT NULL,
  `sales_invoice_footer_text` text COLLATE utf8mb4_unicode_ci,
  `round_off` int(1) DEFAULT NULL COMMENT '1=Enble, 0=Disable',
  `machine_id` text COLLATE utf8mb4_unicode_ci,
  `domain` text COLLATE utf8mb4_unicode_ci,
  `show_upi_code` int(1) DEFAULT '0',
  `unique_code` text COLLATE utf8mb4_unicode_ci,
  `disable_tax` int(1) DEFAULT '0' COMMENT 'If set Disable the tax from app',
  `number_to_words` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT 'Default'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_sitesettings`
--

INSERT INTO `db_sitesettings` (`id`, `version`, `site_name`, `logo`, `language_id`, `currency_id`, `currency_placement`, `timezone`, `date_format`, `time_format`, `sales_discount`, `site_url`, `site_title`, `meta_title`, `meta_desc`, `meta_keywords`, `currencysymbol_id`, `regno_key`, `copyright`, `facebook_url`, `twitter_url`, `youtube_url`, `analytic_code`, `fav_icon`, `footer_logo`, `company_id`, `purchase_code`, `change_return`, `sales_invoice_format_id`, `sales_invoice_footer_text`, `round_off`, `machine_id`, `domain`, `show_upi_code`, `unique_code`, `disable_tax`, `number_to_words`) VALUES
(1, '2.4', 'EKLONTONG', 'Screenshot_from_2024-01-02_01-33-01.jpg', 1, 34, 'Left', 'Asia/Jakarta\r\n', 'dd-mm-yyyy', 12, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 3, 'Terima Kasih Atas Kepercayaanya Sudah Berbelanja Di Toko Kami', 0, '1', 'rheamultikarya.tiketpacitan.site', 0, 'kymev1fjor34t89hqgs26dliw7n5zb', 0, 'Default');

-- --------------------------------------------------------

--
-- Table structure for table `db_smsapi`
--

CREATE TABLE `db_smsapi` (
  `id` int(50) NOT NULL,
  `info` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `key` varchar(600) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `key_value` varchar(600) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `delete_bit` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_smsapi`
--

INSERT INTO `db_smsapi` (`id`, `info`, `key`, `key_value`, `delete_bit`) VALUES
(144, 'url', 'weblink', 'http://www.example.in/api/sendhttp.php', NULL),
(145, 'mobile', 'mobiles', '', NULL),
(146, 'message', 'message', '', NULL),
(147, '', 'authkey', 'xxxxxxxxxxxxxxxxxxxx', NULL),
(148, '', 'sender', 'ULTPOS', NULL),
(149, '', 'route', '1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `db_smstemplates`
--

CREATE TABLE `db_smstemplates` (
  `id` int(50) NOT NULL,
  `template_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `variables` text COLLATE utf8mb4_unicode_ci,
  `company_id` int(5) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `undelete_bit` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_smstemplates`
--

INSERT INTO `db_smstemplates` (`id`, `template_name`, `content`, `variables`, `company_id`, `status`, `undelete_bit`) VALUES
(1, 'GREETING TO CUSTOMER ON SALES', 'Hi {{customer_name}},\r\nYour sales Id is {{sales_id}},\r\nSales Date {{sales_date}},\r\nTotal amount  {{sales_amount}},\r\nYou have paid  {{paid_amt}},\r\nand customer total due amount is  {{cust_tot_due_amt}}\r\nThank you Visit Again', '{{customer_name}}<br>\r\n{{sales_id}}<br>\r\n{{sales_date}}<br>\r\n{{sales_amount}}<br>\r\n{{paid_amt}}<br>\r\n{{cust_tot_due_amt}}<br>\r\n{{invoice_due_amt}}<br>\r\n{{company_name}}<br>\r\n{{company_mobile}}<br>\r\n{{company_address}}<br>\r\n{{company_website}}<br>\r\n{{company_email}}<br>', NULL, 1, 1),
(2, 'GREETING TO CUSTOMER ON SALES RETURN', 'Hi {{customer_name}},\r\nYour sales return Id is {{return_id}},\r\nReturn Date {{return_date}},\r\nTotal amount  {{return_amount}},\r\nWe paid  {{paid_amt}},\r\nand customer total due amount is  {{cust_tot_due_amt}}\r\nThank you Visit Again', '{{customer_name}}<br>\r\n{{return_id}}<br>\r\n{{return_date}}<br>\r\n{{return_amount}}<br>\r\n{{paid_amt}}<br>\r\n{{cust_tot_due_amt}}<br>\r\n{{invoice_due_amt}}<br>\r\n{{company_name}}<br>\r\n{{company_mobile}}<br>\r\n{{company_address}}<br>\r\n{{company_website}}<br>\r\n{{company_email}}<br>', NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_sobpayments`
--

CREATE TABLE `db_sobpayments` (
  `id` int(50) NOT NULL,
  `supplier_id` int(5) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `payment` double(20,2) DEFAULT NULL,
  `payment_note` mediumtext COLLATE utf8mb4_unicode_ci,
  `system_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` time DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_states`
--

CREATE TABLE `db_states` (
  `id` int(50) NOT NULL,
  `state_code` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` varchar(4050) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country_code` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country_id` int(5) DEFAULT NULL,
  `country` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `added_on` date DEFAULT NULL,
  `company_id` int(5) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_states`
--

INSERT INTO `db_states` (`id`, `state_code`, `state`, `country_code`, `country_id`, `country`, `added_on`, `company_id`, `status`) VALUES
(54, NULL, 'Jawa Timur', NULL, NULL, 'Indonesia', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_stockentry`
--

CREATE TABLE `db_stockentry` (
  `id` int(50) NOT NULL,
  `entry_date` date DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `qty` int(5) DEFAULT NULL,
  `note` text COLLATE utf8mb4_unicode_ci,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_stockentry`
--

INSERT INTO `db_stockentry` (`id`, `entry_date`, `item_id`, `qty`, `note`, `status`) VALUES
(4, '2024-01-02', 2, 100, '', 1),
(5, '2024-01-02', 1, 100, '', 1),
(6, '2024-01-09', 3, 10, '', 1),
(7, '2024-01-15', 4, 50, '', 1),
(8, '2024-01-15', 5, 28, '', 1),
(9, '2024-01-15', 6, 20, '', 1),
(12, '2024-01-15', 4, 60, '', 1),
(13, '2024-01-15', 7, 26, '', 1),
(14, '2024-01-15', 8, 5, '', 1),
(15, '2024-01-15', 9, 20, 'CADI DRIVE SLIM', 1),
(16, '2024-01-15', 10, 8, '', 1),
(17, '2024-01-15', 11, 5, '', 1),
(18, '2024-01-15', 12, 8, '', 1),
(19, '2024-01-15', 13, 5, '', 1),
(20, '2024-01-15', 14, 8, '', 1),
(21, '2024-01-15', 15, 5, '', 1),
(22, '2024-01-15', 16, 40, '', 1),
(23, '2024-01-15', 9, 20, '', 1),
(24, '2024-01-15', 8, 5, '', 1),
(25, '2024-01-15', 17, 5, '', 1),
(26, '2024-01-15', 18, 5, '', 1),
(27, '2024-01-15', 19, 4, '', 1),
(28, '2024-01-15', 20, 2, '', 1),
(29, '2024-01-15', 21, 2, '', 1),
(30, '2024-01-15', 22, 1, '', 1),
(31, '2024-01-15', 23, 1, '', 1),
(32, '2024-01-15', 24, 1, '', 1),
(33, '2024-01-15', 25, 1, '', 1),
(34, '2024-01-15', 26, 100000, '', 1),
(35, '2024-01-15', 27, 100, '', 1),
(36, '2024-01-17', 28, 3, '', 1),
(37, '2024-01-17', 29, 3, '', 1),
(38, '2024-01-17', 30, 3, '', 1),
(39, '2024-01-17', 31, 3, '', 1),
(40, '2024-01-17', 32, 5, '', 1),
(41, '2024-01-17', 33, 5, '', 1),
(42, '2024-01-21', 34, 2, '', 1),
(43, '2024-01-21', 35, 1, '', 1),
(44, '2024-01-21', 34, 1, '', 1),
(45, '2024-01-21', 36, 10, '', 1),
(46, '2024-01-21', 37, 5, '', 1),
(47, '2024-01-21', 40, 1, '', 1),
(48, '2024-01-23', 42, 2, '', 1),
(49, '2024-01-25', 43, 3, '', 1),
(50, '2024-01-25', 44, 3, '', 1),
(51, '2024-01-25', 45, 14, '', 1),
(52, '2024-01-25', 46, 6, '', 1),
(53, '2024-01-25', 47, 4, '', 1),
(54, '2024-01-25', 40, 10, '', 1),
(55, '2024-02-06', 48, 60, '', 1),
(56, '2024-02-15', 49, 2, '', 1),
(57, '2024-02-15', 50, 2, '', 1),
(58, '2024-03-04', 51, 2, '', 1),
(59, '2024-03-04', 52, 2, '', 1),
(60, '2024-03-04', 53, 3, '', 1),
(61, '2024-03-04', 54, 3, '', 1),
(62, '2024-06-11', 55, 10, NULL, 1),
(63, '2024-06-11', 56, 10, 'Barag wd paling ampuh', 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_suppliers`
--

CREATE TABLE `db_suppliers` (
  `id` int(50) NOT NULL,
  `supplier_code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `supplier_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gstin` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tax_number` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `vatin` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `opening_balance` double(20,2) DEFAULT NULL,
  `purchase_due` double(20,2) DEFAULT NULL,
  `purchase_return_due` double(20,2) DEFAULT NULL,
  `country_id` int(5) DEFAULT NULL,
  `state_id` int(5) DEFAULT NULL,
  `city` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `postcode` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_time` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_id` int(5) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_suppliers`
--

INSERT INTO `db_suppliers` (`id`, `supplier_code`, `supplier_name`, `mobile`, `phone`, `email`, `gstin`, `tax_number`, `vatin`, `opening_balance`, `purchase_due`, `purchase_return_due`, `country_id`, `state_id`, `city`, `postcode`, `address`, `system_ip`, `system_name`, `created_date`, `created_time`, `created_by`, `company_id`, `status`) VALUES
(1, 'SP0001', 'DISCOUNT NOTEBOOK SBY', '', '', '', '', '', NULL, 0.00, NULL, NULL, 3, 54, '', '', '', '103.138.10.6', '103.138.10.6', '2024-03-04', '08:47:48 am', 'admintoko', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_supplier_payments`
--

CREATE TABLE `db_supplier_payments` (
  `id` int(50) NOT NULL,
  `purchasepayment_id` int(5) DEFAULT NULL,
  `supplier_id` int(5) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_type` varchar(50) DEFAULT NULL,
  `payment` double(20,2) DEFAULT NULL,
  `payment_note` text,
  `system_ip` varchar(50) DEFAULT NULL,
  `system_name` varchar(50) DEFAULT NULL,
  `created_time` varchar(50) DEFAULT NULL,
  `created_date` varchar(50) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `db_tax`
--

CREATE TABLE `db_tax` (
  `id` int(50) NOT NULL,
  `tax_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tax` double(20,2) DEFAULT NULL,
  `group_bit` int(1) DEFAULT NULL COMMENT '1=Yes, 0=No',
  `subtax_ids` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Tax groups IDs',
  `status` int(1) DEFAULT NULL,
  `undelete_bit` int(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_tax`
--

INSERT INTO `db_tax` (`id`, `tax_name`, `tax`, `group_bit`, `subtax_ids`, `status`, `undelete_bit`) VALUES
(1, 'None', 0.00, NULL, NULL, 1, 1),
(2, '10 %', 10.00, NULL, NULL, 1, 0),
(3, '5 %', 5.00, NULL, NULL, 1, 0),
(4, 'PPN', 11.00, NULL, NULL, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `db_timezone`
--

CREATE TABLE `db_timezone` (
  `id` int(50) NOT NULL,
  `timezone` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_timezone`
--

INSERT INTO `db_timezone` (`id`, `timezone`, `status`) VALUES
(1, 'Africa/Abidjan\r', 1),
(2, 'Africa/Accra\r', 1),
(3, 'Africa/Addis_Ababa\r', 1),
(4, 'Africa/Algiers\r', 1),
(5, 'Africa/Asmara\r', 1),
(6, 'Africa/Asmera\r', 1),
(7, 'Africa/Bamako\r', 1),
(8, 'Africa/Bangui\r', 1),
(9, 'Africa/Banjul\r', 1),
(10, 'Africa/Bissau\r', 1),
(11, 'Africa/Blantyre\r', 1),
(12, 'Africa/Brazzaville\r', 1),
(13, 'Africa/Bujumbura\r', 1),
(14, 'Africa/Cairo\r', 1),
(15, 'Africa/Casablanca\r', 1),
(16, 'Africa/Ceuta\r', 1),
(17, 'Africa/Conakry\r', 1),
(18, 'Africa/Dakar\r', 1),
(19, 'Africa/Dar_es_Salaam\r', 1),
(20, 'Africa/Djibouti\r', 1),
(21, 'Africa/Douala\r', 1),
(22, 'Africa/El_Aaiun\r', 1),
(23, 'Africa/Freetown\r', 1),
(24, 'Africa/Gaborone\r', 1),
(25, 'Africa/Harare\r', 1),
(26, 'Africa/Johannesburg\r', 1),
(27, 'Africa/Juba\r', 1),
(28, 'Africa/Kampala\r', 1),
(29, 'Africa/Khartoum\r', 1),
(30, 'Africa/Kigali\r', 1),
(31, 'Africa/Kinshasa\r', 1),
(32, 'Africa/Lagos\r', 1),
(33, 'Africa/Libreville\r', 1),
(34, 'Africa/Lome\r', 1),
(35, 'Africa/Luanda\r', 1),
(36, 'Africa/Lubumbashi\r', 1),
(37, 'Africa/Lusaka\r', 1),
(38, 'Africa/Malabo\r', 1),
(39, 'Africa/Maputo\r', 1),
(40, 'Africa/Maseru\r', 1),
(41, 'Africa/Mbabane\r', 1),
(42, 'Africa/Mogadishu\r', 1),
(43, 'Africa/Monrovia\r', 1),
(44, 'Africa/Nairobi\r', 1),
(45, 'Africa/Ndjamena\r', 1),
(46, 'Africa/Niamey\r', 1),
(47, 'Africa/Nouakchott\r', 1),
(48, 'Africa/Ouagadougou\r', 1),
(49, 'Africa/Porto-Novo\r', 1),
(50, 'Africa/Sao_Tome\r', 1),
(51, 'Africa/Timbuktu\r', 1),
(52, 'Africa/Tripoli\r', 1),
(53, 'Africa/Tunis\r', 1),
(54, 'Africa/Windhoek\r', 1),
(55, 'AKST9AKDT\r', 1),
(56, 'America/Adak\r', 1),
(57, 'America/Anchorage\r', 1),
(58, 'America/Anguilla\r', 1),
(59, 'America/Antigua\r', 1),
(60, 'America/Araguaina\r', 1),
(61, 'America/Argentina/Buenos_Aires\r', 1),
(62, 'America/Argentina/Catamarca\r', 1),
(63, 'America/Argentina/ComodRivadavia\r', 1),
(64, 'America/Argentina/Cordoba\r', 1),
(65, 'America/Argentina/Jujuy\r', 1),
(66, 'America/Argentina/La_Rioja\r', 1),
(67, 'America/Argentina/Mendoza\r', 1),
(68, 'America/Argentina/Rio_Gallegos\r', 1),
(69, 'America/Argentina/Salta\r', 1),
(70, 'America/Argentina/San_Juan\r', 1),
(71, 'America/Argentina/San_Luis\r', 1),
(72, 'America/Argentina/Tucuman\r', 1),
(73, 'America/Argentina/Ushuaia\r', 1),
(74, 'America/Aruba\r', 1),
(75, 'America/Asuncion\r', 1),
(76, 'America/Atikokan\r', 1),
(77, 'America/Atka\r', 1),
(78, 'America/Bahia\r', 1),
(79, 'America/Bahia_Banderas\r', 1),
(80, 'America/Barbados\r', 1),
(81, 'America/Belem\r', 1),
(82, 'America/Belize\r', 1),
(83, 'America/Blanc-Sablon\r', 1),
(84, 'America/Boa_Vista\r', 1),
(85, 'America/Bogota\r', 1),
(86, 'America/Boise\r', 1),
(87, 'America/Buenos_Aires\r', 1),
(88, 'America/Cambridge_Bay\r', 1),
(89, 'America/Campo_Grande\r', 1),
(90, 'America/Cancun\r', 1),
(91, 'America/Caracas\r', 1),
(92, 'America/Catamarca\r', 1),
(93, 'America/Cayenne\r', 1),
(94, 'America/Cayman\r', 1),
(95, 'America/Chicago\r', 1),
(96, 'America/Chihuahua\r', 1),
(97, 'America/Coral_Harbour\r', 1),
(98, 'America/Cordoba\r', 1),
(99, 'America/Costa_Rica\r', 1),
(100, 'America/Creston\r', 1),
(101, 'America/Cuiaba\r', 1),
(102, 'America/Curacao\r', 1),
(103, 'America/Danmarkshavn\r', 1),
(104, 'America/Dawson\r', 1),
(105, 'America/Dawson_Creek\r', 1),
(106, 'America/Denver\r', 1),
(107, 'America/Detroit\r', 1),
(108, 'America/Dominica\r', 1),
(109, 'America/Edmonton\r', 1),
(110, 'America/Eirunepe\r', 1),
(111, 'America/El_Salvador\r', 1),
(112, 'America/Ensenada\r', 1),
(113, 'America/Fort_Wayne\r', 1),
(114, 'America/Fortaleza\r', 1),
(115, 'America/Glace_Bay\r', 1),
(116, 'America/Godthab\r', 1),
(117, 'America/Goose_Bay\r', 1),
(118, 'America/Grand_Turk\r', 1),
(119, 'America/Grenada\r', 1),
(120, 'America/Guadeloupe\r', 1),
(121, 'America/Guatemala\r', 1),
(122, 'America/Guayaquil\r', 1),
(123, 'America/Guyana\r', 1),
(124, 'America/Halifax\r', 1),
(125, 'America/Havana\r', 1),
(126, 'America/Hermosillo\r', 1),
(127, 'America/Indiana/Indianapolis\r', 1),
(128, 'America/Indiana/Knox\r', 1),
(129, 'America/Indiana/Marengo\r', 1),
(130, 'America/Indiana/Petersburg\r', 1),
(131, 'America/Indiana/Tell_City\r', 1),
(132, 'America/Indiana/Vevay\r', 1),
(133, 'America/Indiana/Vincennes\r', 1),
(134, 'America/Indiana/Winamac\r', 1),
(135, 'America/Indianapolis\r', 1),
(136, 'America/Inuvik\r', 1),
(137, 'America/Iqaluit\r', 1),
(138, 'America/Jamaica\r', 1),
(139, 'America/Jujuy\r', 1),
(140, 'America/Juneau\r', 1),
(141, 'America/Kentucky/Louisville\r', 1),
(142, 'America/Kentucky/Monticello\r', 1),
(143, 'America/Knox_IN\r', 1),
(144, 'America/Kralendijk\r', 1),
(145, 'America/La_Paz\r', 1),
(146, 'America/Lima\r', 1),
(147, 'America/Los_Angeles\r', 1),
(148, 'America/Louisville\r', 1),
(149, 'America/Lower_Princes\r', 1),
(150, 'America/Maceio\r', 1),
(151, 'America/Managua\r', 1),
(152, 'America/Manaus\r', 1),
(153, 'America/Marigot\r', 1),
(154, 'America/Martinique\r', 1),
(155, 'America/Matamoros\r', 1),
(156, 'America/Mazatlan\r', 1),
(157, 'America/Mendoza\r', 1),
(158, 'America/Menominee\r', 1),
(159, 'America/Merida\r', 1),
(160, 'America/Metlakatla\r', 1),
(161, 'America/Mexico_City\r', 1),
(162, 'America/Miquelon\r', 1),
(163, 'America/Moncton\r', 1),
(164, 'America/Monterrey\r', 1),
(165, 'America/Montevideo\r', 1),
(166, 'America/Montreal\r', 1),
(167, 'America/Montserrat\r', 1),
(168, 'America/Nassau\r', 1),
(169, 'America/New_York\r', 1),
(170, 'America/Nipigon\r', 1),
(171, 'America/Nome\r', 1),
(172, 'America/Noronha\r', 1),
(173, 'America/North_Dakota/Beulah\r', 1),
(174, 'America/North_Dakota/Center\r', 1),
(175, 'America/North_Dakota/New_Salem\r', 1),
(176, 'America/Ojinaga\r', 1),
(177, 'America/Panama\r', 1),
(178, 'America/Pangnirtung\r', 1),
(179, 'America/Paramaribo\r', 1),
(180, 'America/Phoenix\r', 1),
(181, 'America/Port_of_Spain\r', 1),
(182, 'America/Port-au-Prince\r', 1),
(183, 'America/Porto_Acre\r', 1),
(184, 'America/Porto_Velho\r', 1),
(185, 'America/Puerto_Rico\r', 1),
(186, 'America/Rainy_River\r', 1),
(187, 'America/Rankin_Inlet\r', 1),
(188, 'America/Recife\r', 1),
(189, 'America/Regina\r', 1),
(190, 'America/Resolute\r', 1),
(191, 'America/Rio_Branco\r', 1),
(192, 'America/Rosario\r', 1),
(193, 'America/Santa_Isabel\r', 1),
(194, 'America/Santarem\r', 1),
(195, 'America/Santiago\r', 1),
(196, 'America/Santo_Domingo\r', 1),
(197, 'America/Sao_Paulo\r', 1),
(198, 'America/Scoresbysund\r', 1),
(199, 'America/Shiprock\r', 1),
(200, 'America/Sitka\r', 1),
(201, 'America/St_Barthelemy\r', 1),
(202, 'America/St_Johns\r', 1),
(203, 'America/St_Kitts\r', 1),
(204, 'America/St_Lucia\r', 1),
(205, 'America/St_Thomas\r', 1),
(206, 'America/St_Vincent\r', 1),
(207, 'America/Swift_Current\r', 1),
(208, 'America/Tegucigalpa\r', 1),
(209, 'America/Thule\r', 1),
(210, 'America/Thunder_Bay\r', 1),
(211, 'America/Tijuana\r', 1),
(212, 'America/Toronto\r', 1),
(213, 'America/Tortola\r', 1),
(214, 'America/Vancouver\r', 1),
(215, 'America/Virgin\r', 1),
(216, 'America/Whitehorse\r', 1),
(217, 'America/Winnipeg\r', 1),
(218, 'America/Yakutat\r', 1),
(219, 'America/Yellowknife\r', 1),
(220, 'Antarctica/Casey\r', 1),
(221, 'Antarctica/Davis\r', 1),
(222, 'Antarctica/DumontDUrville\r', 1),
(223, 'Antarctica/Macquarie\r', 1),
(224, 'Antarctica/Mawson\r', 1),
(225, 'Antarctica/McMurdo\r', 1),
(226, 'Antarctica/Palmer\r', 1),
(227, 'Antarctica/Rothera\r', 1),
(228, 'Antarctica/South_Pole\r', 1),
(229, 'Antarctica/Syowa\r', 1),
(230, 'Antarctica/Vostok\r', 1),
(231, 'Arctic/Longyearbyen\r', 1),
(232, 'Asia/Aden\r', 1),
(233, 'Asia/Almaty\r', 1),
(234, 'Asia/Amman\r', 1),
(235, 'Asia/Anadyr\r', 1),
(236, 'Asia/Aqtau\r', 1),
(237, 'Asia/Aqtobe\r', 1),
(238, 'Asia/Ashgabat\r', 1),
(239, 'Asia/Ashkhabad\r', 1),
(240, 'Asia/Baghdad\r', 1),
(241, 'Asia/Bahrain\r', 1),
(242, 'Asia/Baku\r', 1),
(243, 'Asia/Bangkok\r', 1),
(244, 'Asia/Beirut\r', 1),
(245, 'Asia/Bishkek\r', 1),
(246, 'Asia/Brunei\r', 1),
(247, 'Asia/Calcutta\r', 1),
(248, 'Asia/Choibalsan\r', 1),
(249, 'Asia/Chongqing\r', 1),
(250, 'Asia/Chungking\r', 1),
(251, 'Asia/Colombo\r', 1),
(252, 'Asia/Dacca\r', 1),
(253, 'Asia/Damascus\r', 1),
(254, 'Asia/Dhaka\r', 1),
(255, 'Asia/Dili\r', 1),
(256, 'Asia/Dubai\r', 1),
(257, 'Asia/Dushanbe\r', 1),
(258, 'Asia/Gaza\r', 1),
(259, 'Asia/Harbin\r', 1),
(260, 'Asia/Hebron\r', 1),
(261, 'Asia/Ho_Chi_Minh\r', 1),
(262, 'Asia/Hong_Kong\r', 1),
(263, 'Asia/Hovd\r', 1),
(264, 'Asia/Irkutsk\r', 1),
(265, 'Asia/Istanbul\r', 1),
(266, 'Asia/Jakarta\r', 1),
(267, 'Asia/Jayapura\r', 1),
(268, 'Asia/Jerusalem\r', 1),
(269, 'Asia/Kabul\r', 1),
(270, 'Asia/Kamchatka\r', 1),
(271, 'Asia/Karachi\r', 1),
(272, 'Asia/Kashgar\r', 1),
(273, 'Asia/Kathmandu\r', 1),
(274, 'Asia/Katmandu\r', 1),
(275, 'Asia/Kolkata\r', 1),
(276, 'Asia/Krasnoyarsk\r', 1),
(277, 'Asia/Kuala_Lumpur\r', 1),
(278, 'Asia/Kuching\r', 1),
(279, 'Asia/Kuwait\r', 1),
(280, 'Asia/Macao\r', 1),
(281, 'Asia/Macau\r', 1),
(282, 'Asia/Magadan\r', 1),
(283, 'Asia/Makassar\r', 1),
(284, 'Asia/Manila\r', 1),
(285, 'Asia/Muscat\r', 1),
(286, 'Asia/Nicosia\r', 1),
(287, 'Asia/Novokuznetsk\r', 1),
(288, 'Asia/Novosibirsk\r', 1),
(289, 'Asia/Omsk\r', 1),
(290, 'Asia/Oral\r', 1),
(291, 'Asia/Phnom_Penh\r', 1),
(292, 'Asia/Pontianak\r', 1),
(293, 'Asia/Pyongyang\r', 1),
(294, 'Asia/Qatar\r', 1),
(295, 'Asia/Qyzylorda\r', 1),
(296, 'Asia/Rangoon\r', 1),
(297, 'Asia/Riyadh\r', 1),
(298, 'Asia/Saigon\r', 1),
(299, 'Asia/Sakhalin\r', 1),
(300, 'Asia/Samarkand\r', 1),
(301, 'Asia/Seoul\r', 1),
(302, 'Asia/Shanghai\r', 1),
(303, 'Asia/Singapore\r', 1),
(304, 'Asia/Taipei\r', 1),
(305, 'Asia/Tashkent\r', 1),
(306, 'Asia/Tbilisi\r', 1),
(307, 'Asia/Tehran\r', 1),
(308, 'Asia/Tel_Aviv\r', 1),
(309, 'Asia/Thimbu\r', 1),
(310, 'Asia/Thimphu\r', 1),
(311, 'Asia/Tokyo\r', 1),
(312, 'Asia/Ujung_Pandang\r', 1),
(313, 'Asia/Ulaanbaatar\r', 1),
(314, 'Asia/Ulan_Bator\r', 1),
(315, 'Asia/Urumqi\r', 1),
(316, 'Asia/Vientiane\r', 1),
(317, 'Asia/Vladivostok\r', 1),
(318, 'Asia/Yakutsk\r', 1),
(319, 'Asia/Yekaterinburg\r', 1),
(320, 'Asia/Yerevan\r', 1),
(321, 'Atlantic/Azores\r', 1),
(322, 'Atlantic/Bermuda\r', 1),
(323, 'Atlantic/Canary\r', 1),
(324, 'Atlantic/Cape_Verde\r', 1),
(325, 'Atlantic/Faeroe\r', 1),
(326, 'Atlantic/Faroe\r', 1),
(327, 'Atlantic/Jan_Mayen\r', 1),
(328, 'Atlantic/Madeira\r', 1),
(329, 'Atlantic/Reykjavik\r', 1),
(330, 'Atlantic/South_Georgia\r', 1),
(331, 'Atlantic/St_Helena\r', 1),
(332, 'Atlantic/Stanley\r', 1),
(333, 'Australia/ACT\r', 1),
(334, 'Australia/Adelaide\r', 1),
(335, 'Australia/Brisbane\r', 1),
(336, 'Australia/Broken_Hill\r', 1),
(337, 'Australia/Canberra\r', 1),
(338, 'Australia/Currie\r', 1),
(339, 'Australia/Darwin\r', 1),
(340, 'Australia/Eucla\r', 1),
(341, 'Australia/Hobart\r', 1),
(342, 'Australia/LHI\r', 1),
(343, 'Australia/Lindeman\r', 1),
(344, 'Australia/Lord_Howe\r', 1),
(345, 'Australia/Melbourne\r', 1),
(346, 'Australia/North\r', 1),
(347, 'Australia/NSW\r', 1),
(348, 'Australia/Perth\r', 1),
(349, 'Australia/Queensland\r', 1),
(350, 'Australia/South\r', 1),
(351, 'Australia/Sydney\r', 1),
(352, 'Australia/Tasmania\r', 1),
(353, 'Australia/Victoria\r', 1),
(354, 'Australia/West\r', 1),
(355, 'Australia/Yancowinna\r', 1),
(356, 'Brazil/Acre\r', 1),
(357, 'Brazil/DeNoronha\r', 1),
(358, 'Brazil/East\r', 1),
(359, 'Brazil/West\r', 1),
(360, 'Canada/Atlantic\r', 1),
(361, 'Canada/Central\r', 1),
(362, 'Canada/Eastern\r', 1),
(363, 'Canada/East-Saskatchewan\r', 1),
(364, 'Canada/Mountain\r', 1),
(365, 'Canada/Newfoundland\r', 1),
(366, 'Canada/Pacific\r', 1),
(367, 'Canada/Saskatchewan\r', 1),
(368, 'Canada/Yukon\r', 1),
(369, 'CET\r', 1),
(370, 'Chile/Continental\r', 1),
(371, 'Chile/EasterIsland\r', 1),
(372, 'CST6CDT\r', 1),
(373, 'Cuba\r', 1),
(374, 'EET\r', 1),
(375, 'Egypt\r', 1),
(376, 'Eire\r', 1),
(377, 'EST\r', 1),
(378, 'EST5EDT\r', 1),
(379, 'Etc./GMT\r', 1),
(380, 'Etc./GMT+0\r', 1),
(381, 'Etc./UCT\r', 1),
(382, 'Etc./Universal\r', 1),
(383, 'Etc./UTC\r', 1),
(384, 'Etc./Zulu\r', 1),
(385, 'Europe/Amsterdam\r', 1),
(386, 'Europe/Andorra\r', 1),
(387, 'Europe/Athens\r', 1),
(388, 'Europe/Belfast\r', 1),
(389, 'Europe/Belgrade\r', 1),
(390, 'Europe/Berlin\r', 1),
(391, 'Europe/Bratislava\r', 1),
(392, 'Europe/Brussels\r', 1),
(393, 'Europe/Bucharest\r', 1),
(394, 'Europe/Budapest\r', 1),
(395, 'Europe/Chisinau\r', 1),
(396, 'Europe/Copenhagen\r', 1),
(397, 'Europe/Dublin\r', 1),
(398, 'Europe/Gibraltar\r', 1),
(399, 'Europe/Guernsey\r', 1),
(400, 'Europe/Helsinki\r', 1),
(401, 'Europe/Isle_of_Man\r', 1),
(402, 'Europe/Istanbul\r', 1),
(403, 'Europe/Jersey\r', 1),
(404, 'Europe/Kaliningrad\r', 1),
(405, 'Europe/Kiev\r', 1),
(406, 'Europe/Lisbon\r', 1),
(407, 'Europe/Ljubljana\r', 1),
(408, 'Europe/London\r', 1),
(409, 'Europe/Luxembourg\r', 1),
(410, 'Europe/Madrid\r', 1),
(411, 'Europe/Malta\r', 1),
(412, 'Europe/Mariehamn\r', 1),
(413, 'Europe/Minsk\r', 1),
(414, 'Europe/Monaco\r', 1),
(415, 'Europe/Moscow\r', 1),
(416, 'Europe/Nicosia\r', 1),
(417, 'Europe/Oslo\r', 1),
(418, 'Europe/Paris\r', 1),
(419, 'Europe/Podgorica\r', 1),
(420, 'Europe/Prague\r', 1),
(421, 'Europe/Riga\r', 1),
(422, 'Europe/Rome\r', 1),
(423, 'Europe/Samara\r', 1),
(424, 'Europe/San_Marino\r', 1),
(425, 'Europe/Sarajevo\r', 1),
(426, 'Europe/Simferopol\r', 1),
(427, 'Europe/Skopje\r', 1),
(428, 'Europe/Sofia\r', 1),
(429, 'Europe/Stockholm\r', 1),
(430, 'Europe/Tallinn\r', 1),
(431, 'Europe/Tirane\r', 1),
(432, 'Europe/Tiraspol\r', 1),
(433, 'Europe/Uzhgorod\r', 1),
(434, 'Europe/Vaduz\r', 1),
(435, 'Europe/Vatican\r', 1),
(436, 'Europe/Vienna\r', 1),
(437, 'Europe/Vilnius\r', 1),
(438, 'Europe/Volgograd\r', 1),
(439, 'Europe/Warsaw\r', 1),
(440, 'Europe/Zagreb\r', 1),
(441, 'Europe/Zaporozhye\r', 1),
(442, 'Europe/Zurich\r', 1),
(443, 'GB\r', 1),
(444, 'GB-Eire\r', 1),
(445, 'GMT\r', 1),
(446, 'GMT+0\r', 1),
(447, 'GMT0\r', 1),
(448, 'GMT-0\r', 1),
(449, 'Greenwich\r', 1),
(450, 'Hong Kong\r', 1),
(451, 'HST\r', 1),
(452, 'Iceland\r', 1),
(453, 'Indian/Antananarivo\r', 1),
(454, 'Indian/Chagos\r', 1),
(455, 'Indian/Christmas\r', 1),
(456, 'Indian/Cocos\r', 1),
(457, 'Indian/Comoro\r', 1),
(458, 'Indian/Kerguelen\r', 1),
(459, 'Indian/Mahe\r', 1),
(460, 'Indian/Maldives\r', 1),
(461, 'Indian/Mauritius\r', 1),
(462, 'Indian/Mayotte\r', 1),
(463, 'Indian/Reunion\r', 1),
(464, 'Iran\r', 1),
(465, 'Israel\r', 1),
(466, 'Jamaica\r', 1),
(467, 'Japan\r', 1),
(468, 'JST-9\r', 1),
(469, 'Kwajalein\r', 1),
(470, 'Libya\r', 1),
(471, 'MET\r', 1),
(472, 'Mexico/BajaNorte\r', 1),
(473, 'Mexico/BajaSur\r', 1),
(474, 'Mexico/General\r', 1),
(475, 'MST\r', 1),
(476, 'MST7MDT\r', 1),
(477, 'Navajo\r', 1),
(478, 'NZ\r', 1),
(479, 'NZ-CHAT\r', 1),
(480, 'Pacific/Apia\r', 1),
(481, 'Pacific/Auckland\r', 1),
(482, 'Pacific/Chatham\r', 1),
(483, 'Pacific/Chuuk\r', 1),
(484, 'Pacific/Easter\r', 1),
(485, 'Pacific/Efate\r', 1),
(486, 'Pacific/Enderbury\r', 1),
(487, 'Pacific/Fakaofo\r', 1),
(488, 'Pacific/Fiji\r', 1),
(489, 'Pacific/Funafuti\r', 1),
(490, 'Pacific/Galapagos\r', 1),
(491, 'Pacific/Gambier\r', 1),
(492, 'Pacific/Guadalcanal\r', 1),
(493, 'Pacific/Guam\r', 1),
(494, 'Pacific/Honolulu\r', 1),
(495, 'Pacific/Johnston\r', 1),
(496, 'Pacific/Kiritimati\r', 1),
(497, 'Pacific/Kosrae\r', 1),
(498, 'Pacific/Kwajalein\r', 1),
(499, 'Pacific/Majuro\r', 1),
(500, 'Pacific/Marquesas\r', 1),
(501, 'Pacific/Midway\r', 1),
(502, 'Pacific/Nauru\r', 1),
(503, 'Pacific/Niue\r', 1),
(504, 'Pacific/Norfolk\r', 1),
(505, 'Pacific/Noumea\r', 1),
(506, 'Pacific/Pago_Pago\r', 1),
(507, 'Pacific/Palau\r', 1),
(508, 'Pacific/Pitcairn\r', 1),
(509, 'Pacific/Pohnpei\r', 1),
(510, 'Pacific/Ponape\r', 1),
(511, 'Pacific/Port_Moresby\r', 1),
(512, 'Pacific/Rarotonga\r', 1),
(513, 'Pacific/Saipan\r', 1),
(514, 'Pacific/Samoa\r', 1),
(515, 'Pacific/Tahiti\r', 1),
(516, 'Pacific/Tarawa\r', 1),
(517, 'Pacific/Tongatapu\r', 1),
(518, 'Pacific/Truk\r', 1),
(519, 'Pacific/Wake\r', 1),
(520, 'Pacific/Wallis\r', 1),
(521, 'Pacific/Yap\r', 1),
(522, 'Poland\r', 1),
(523, 'Portugal\r', 1),
(524, 'PRC\r', 1),
(525, 'PST8PDT\r', 1),
(526, 'ROC\r', 1),
(527, 'ROK\r', 1),
(528, 'Singapore\r', 1),
(529, 'Turkey\r', 1),
(530, 'UCT\r', 1),
(531, 'Universal\r', 1),
(532, 'US/Alaska\r', 1),
(533, 'US/Aleutian\r', 1),
(534, 'US/Arizona\r', 1),
(535, 'US/Central\r', 1),
(536, 'US/Eastern\r', 1),
(537, 'US/East-Indiana\r', 1),
(538, 'US/Hawaii\r', 1),
(539, 'US/Indiana-Starke\r', 1),
(540, 'US/Michigan\r', 1),
(541, 'US/Mountain\r', 1),
(542, 'US/Pacific\r', 1),
(543, 'US/Pacific-New\r', 1),
(544, 'US/Samoa\r', 1),
(545, 'UTC\r', 1),
(546, 'WET\r', 1),
(547, 'W-SU\r', 1),
(548, 'Zulu\r', 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_units`
--

CREATE TABLE `db_units` (
  `id` int(50) NOT NULL,
  `unit_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` mediumtext COLLATE utf8mb4_unicode_ci,
  `company_id` int(5) DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_units`
--

INSERT INTO `db_units` (`id`, `unit_name`, `description`, `company_id`, `status`) VALUES
(7, 'Box', 'Box Information', NULL, 1),
(8, 'Drums', 'Drums Information', NULL, 1),
(9, 'Pieces', 'Pieces Information', NULL, 1),
(10, 'Grams', 'Grams Description', NULL, 1),
(11, 'Packets', 'Packets information', NULL, 1),
(12, 'Unit', 'Unit Description', NULL, 1),
(13, 'PCS', '', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_users`
--

CREATE TABLE `db_users` (
  `id` int(50) NOT NULL,
  `username` varchar(1350) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` blob,
  `member_of` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `firstname` varchar(1350) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lastname` varchar(1350) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(405) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(1350) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `photo` blob,
  `gender` varchar(1350) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `country` varchar(1620) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` varchar(1350) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city` varchar(1620) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` blob,
  `postcode` varchar(270) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_name` varchar(1350) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` int(5) DEFAULT NULL,
  `profile_picture` text COLLATE utf8mb4_unicode_ci,
  `created_date` date DEFAULT NULL,
  `created_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_ip` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_id` int(5) DEFAULT NULL,
  `status` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `db_users`
--

INSERT INTO `db_users` (`id`, `username`, `password`, `member_of`, `firstname`, `lastname`, `mobile`, `email`, `photo`, `gender`, `dob`, `country`, `state`, `city`, `address`, `postcode`, `role_name`, `role_id`, `profile_picture`, `created_date`, `created_time`, `created_by`, `system_ip`, `system_name`, `company_id`, `status`) VALUES
(1, 'admin', 0x6531306164633339343962613539616262653536653035376632306638383365, '', NULL, NULL, '9845454454', 'admin@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '', '2018-11-27', '::1', NULL, NULL, NULL, 1, 1),
(2, 'admintoko', 0x6531306164633339343962613539616262653536653035376632306638383365, NULL, NULL, NULL, '087751745073', 'admintoko@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, '', '2024-01-02', '01:20:49 am', 'admin', '192.168.58.245', '192.168.58.245', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `db_warehouse`
--

CREATE TABLE `db_warehouse` (
  `id` int(50) NOT NULL,
  `warehouse_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notif_device`
--

CREATE TABLE `notif_device` (
  `id` bigint(20) NOT NULL,
  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `device_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `client` varchar(50) NOT NULL,
  `client_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `app_version` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fcm_regid` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `os_regid` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `user_app` bigint(20) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notif_device`
--

INSERT INTO `notif_device` (`id`, `device_id`, `device_name`, `client`, `client_version`, `app_version`, `fcm_regid`, `os_regid`, `user_app`, `created_at`, `updated_at`) VALUES
(1, '98fdd1d0a50ee912', 'HMD Global TA-1053', 'ANDROID', '9', '1 (1.0)', 'e1HYIVCTSqOe-2ivGRKtee:APA91bFAyuOmTo6fREiWPyPdvPOKvucLYpB-JaW2tMnssFPaVDKR76eoIqMRjYUU7DbJeIRYM5DeFJsrnsttr2I0pkL7laUR51Bdsll7vkx1NS4SPnyyc5rGCTB_WkA0xkh1_ye7uLfh', '63aca408-7bf2-11ec-b60a-6a777c8b1c2e', NULL, '2022-01-23 09:15:29', '2022-01-24 08:35:35'),
(2, '906f5f54db5cd883', 'OPPO CPH1969', 'ANDROID', '11', '1 (1.0)', 'd7e1YKvZQX6fHZ5MscJ3_5:APA91bFNfyyNVMBc8awUjAY2q_O0cKCwjZ7daO6oq3vynKv-nk01kR39d3fSQGL6YH6npV_xkcQrES4tHBhVasQ6j-ZLSD8APRivf8Y2TK646XP9D6xBncpapLCzQsQVgzjDcsyUQayp', '', 1, '2023-12-03 00:06:17', '2023-12-03 13:28:18');

-- --------------------------------------------------------

--
-- Table structure for table `temp_holdinvoice`
--

CREATE TABLE `temp_holdinvoice` (
  `id` int(5) NOT NULL,
  `invoice_id` int(5) DEFAULT NULL,
  `invoice_date` date DEFAULT NULL,
  `reference_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_id` int(5) DEFAULT NULL,
  `item_qty` int(5) DEFAULT NULL,
  `item_price` double(10,2) DEFAULT NULL,
  `tax` double(10,2) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pos` int(5) DEFAULT NULL,
  `status` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_app`
--

CREATE TABLE `user_app` (
  `id` bigint(11) NOT NULL,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(50) NOT NULL,
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `provider` varchar(50) NOT NULL,
  `company_id` int(11) NOT NULL,
  `reset_code` int(5) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_app`
--

INSERT INTO `user_app` (`id`, `name`, `email`, `phone`, `password`, `image`, `status`, `provider`, `company_id`, `reset_code`, `created_at`, `updated_at`) VALUES
(1, 'rorik setya bud', 'roriksetya@gmail.com', '', '$2y$10$Gz.WA0vedGriAjwaHL7yn.WMOS39T4TeJ.Yz5rxvmm5Z1lczTVsBS', NULL, 'ACTIVE', 'EMAIL', 0, NULL, '2023-12-03 00:48:46', '2023-12-03 00:48:46'),
(2, 'Nur Musarisitoh', 'nurmusarisitoh@gmail.com', '087751948827', '$2y$10$yq7kiODlBMMD1ZIzGDbFTuhb8bpMWv2h/g1eBD0BknCyQGzj5sEoG', NULL, 'ACTIVE', '', 4, NULL, '2024-06-11 18:11:36', '2024-06-16 11:01:39'),
(3, 'sinta dewi', 'sintadewi@gmail.com', '082245872607', '$2y$10$/wEvD0I56zv5T4ap/5zo..ZZWp1p0.mIM55QhW1yChmVeWbGa2ztS', NULL, 'ACTIVE', '', 6, NULL, '2024-06-16 12:29:13', '2024-06-16 12:29:13');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `app_version`
--
ALTER TABLE `app_version`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ci_sessions`
--
ALTER TABLE `ci_sessions`
  ADD KEY `ci_sessions_timestamp` (`timestamp`);

--
-- Indexes for table `db_brands`
--
ALTER TABLE `db_brands`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_category`
--
ALTER TABLE `db_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_cobpayments`
--
ALTER TABLE `db_cobpayments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_company`
--
ALTER TABLE `db_company`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_country`
--
ALTER TABLE `db_country`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_currency`
--
ALTER TABLE `db_currency`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_customers`
--
ALTER TABLE `db_customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_customer_payments`
--
ALTER TABLE `db_customer_payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `salespayment_id` (`salespayment_id`);

--
-- Indexes for table `db_expense`
--
ALTER TABLE `db_expense`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_expense_category`
--
ALTER TABLE `db_expense_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_hold`
--
ALTER TABLE `db_hold`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `db_holditems`
--
ALTER TABLE `db_holditems`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sales_id` (`hold_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `db_items`
--
ALTER TABLE `db_items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_languages`
--
ALTER TABLE `db_languages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_paymenttypes`
--
ALTER TABLE `db_paymenttypes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_permissions`
--
ALTER TABLE `db_permissions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_purchase`
--
ALTER TABLE `db_purchase`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_purchaseitems`
--
ALTER TABLE `db_purchaseitems`
  ADD PRIMARY KEY (`id`),
  ADD KEY `purchase_id` (`purchase_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `db_purchaseitemsreturn`
--
ALTER TABLE `db_purchaseitemsreturn`
  ADD PRIMARY KEY (`id`),
  ADD KEY `purchase_id` (`purchase_id`),
  ADD KEY `return_id` (`return_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `db_purchasepayments`
--
ALTER TABLE `db_purchasepayments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_purchasepaymentsreturn`
--
ALTER TABLE `db_purchasepaymentsreturn`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_purchasereturn`
--
ALTER TABLE `db_purchasereturn`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_roles`
--
ALTER TABLE `db_roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_sales`
--
ALTER TABLE `db_sales`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_salesitems`
--
ALTER TABLE `db_salesitems`
  ADD PRIMARY KEY (`id`),
  ADD KEY `item_id` (`item_id`),
  ADD KEY `sales_id` (`sales_id`);

--
-- Indexes for table `db_salesitemsreturn`
--
ALTER TABLE `db_salesitemsreturn`
  ADD PRIMARY KEY (`id`),
  ADD KEY `item_id` (`item_id`),
  ADD KEY `return_id` (`return_id`),
  ADD KEY `sales_id` (`sales_id`);

--
-- Indexes for table `db_salespayments`
--
ALTER TABLE `db_salespayments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_salespaymentsreturn`
--
ALTER TABLE `db_salespaymentsreturn`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_salesreturn`
--
ALTER TABLE `db_salesreturn`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_sitesettings`
--
ALTER TABLE `db_sitesettings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `currencysymbol_id` (`currencysymbol_id`);

--
-- Indexes for table `db_smsapi`
--
ALTER TABLE `db_smsapi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_smstemplates`
--
ALTER TABLE `db_smstemplates`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_sobpayments`
--
ALTER TABLE `db_sobpayments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_states`
--
ALTER TABLE `db_states`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_stockentry`
--
ALTER TABLE `db_stockentry`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_suppliers`
--
ALTER TABLE `db_suppliers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_supplier_payments`
--
ALTER TABLE `db_supplier_payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `supplier_id` (`supplier_id`),
  ADD KEY `purchasepayment_id` (`purchasepayment_id`);

--
-- Indexes for table `db_tax`
--
ALTER TABLE `db_tax`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_timezone`
--
ALTER TABLE `db_timezone`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_units`
--
ALTER TABLE `db_units`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_users`
--
ALTER TABLE `db_users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `db_warehouse`
--
ALTER TABLE `db_warehouse`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notif_device`
--
ALTER TABLE `notif_device`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `temp_holdinvoice`
--
ALTER TABLE `temp_holdinvoice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_app`
--
ALTER TABLE `user_app`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `app_version`
--
ALTER TABLE `app_version`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `db_brands`
--
ALTER TABLE `db_brands`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `db_category`
--
ALTER TABLE `db_category`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `db_cobpayments`
--
ALTER TABLE `db_cobpayments`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_company`
--
ALTER TABLE `db_company`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `db_country`
--
ALTER TABLE `db_country`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `db_currency`
--
ALTER TABLE `db_currency`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `db_customers`
--
ALTER TABLE `db_customers`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `db_customer_payments`
--
ALTER TABLE `db_customer_payments`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=235;

--
-- AUTO_INCREMENT for table `db_expense`
--
ALTER TABLE `db_expense`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `db_expense_category`
--
ALTER TABLE `db_expense_category`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `db_hold`
--
ALTER TABLE `db_hold`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_holditems`
--
ALTER TABLE `db_holditems`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_items`
--
ALTER TABLE `db_items`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `db_languages`
--
ALTER TABLE `db_languages`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `db_paymenttypes`
--
ALTER TABLE `db_paymenttypes`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `db_permissions`
--
ALTER TABLE `db_permissions`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=199;

--
-- AUTO_INCREMENT for table `db_purchase`
--
ALTER TABLE `db_purchase`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_purchaseitems`
--
ALTER TABLE `db_purchaseitems`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_purchaseitemsreturn`
--
ALTER TABLE `db_purchaseitemsreturn`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_purchasepayments`
--
ALTER TABLE `db_purchasepayments`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_purchasepaymentsreturn`
--
ALTER TABLE `db_purchasepaymentsreturn`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_purchasereturn`
--
ALTER TABLE `db_purchasereturn`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_roles`
--
ALTER TABLE `db_roles`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `db_sales`
--
ALTER TABLE `db_sales`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `db_salesitems`
--
ALTER TABLE `db_salesitems`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `db_salesitemsreturn`
--
ALTER TABLE `db_salesitemsreturn`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_salespayments`
--
ALTER TABLE `db_salespayments`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `db_salespaymentsreturn`
--
ALTER TABLE `db_salespaymentsreturn`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_salesreturn`
--
ALTER TABLE `db_salesreturn`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_sitesettings`
--
ALTER TABLE `db_sitesettings`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `db_smsapi`
--
ALTER TABLE `db_smsapi`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=150;

--
-- AUTO_INCREMENT for table `db_smstemplates`
--
ALTER TABLE `db_smstemplates`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `db_sobpayments`
--
ALTER TABLE `db_sobpayments`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_states`
--
ALTER TABLE `db_states`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `db_stockentry`
--
ALTER TABLE `db_stockentry`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT for table `db_suppliers`
--
ALTER TABLE `db_suppliers`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `db_supplier_payments`
--
ALTER TABLE `db_supplier_payments`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_tax`
--
ALTER TABLE `db_tax`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `db_timezone`
--
ALTER TABLE `db_timezone`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=549;

--
-- AUTO_INCREMENT for table `db_units`
--
ALTER TABLE `db_units`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `db_users`
--
ALTER TABLE `db_users`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `db_warehouse`
--
ALTER TABLE `db_warehouse`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notif_device`
--
ALTER TABLE `notif_device`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `temp_holdinvoice`
--
ALTER TABLE `temp_holdinvoice`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_app`
--
ALTER TABLE `user_app`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `db_customer_payments`
--
ALTER TABLE `db_customer_payments`
  ADD CONSTRAINT `db_customer_payments_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `db_customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `db_customer_payments_ibfk_2` FOREIGN KEY (`salespayment_id`) REFERENCES `db_salespayments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `db_hold`
--
ALTER TABLE `db_hold`
  ADD CONSTRAINT `db_hold_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `db_customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `db_holditems`
--
ALTER TABLE `db_holditems`
  ADD CONSTRAINT `db_holditems_ibfk_2` FOREIGN KEY (`hold_id`) REFERENCES `db_hold` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `db_holditems_ibfk_3` FOREIGN KEY (`item_id`) REFERENCES `db_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `db_purchaseitems`
--
ALTER TABLE `db_purchaseitems`
  ADD CONSTRAINT `db_purchaseitems_ibfk_1` FOREIGN KEY (`purchase_id`) REFERENCES `db_purchase` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `db_purchaseitems_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `db_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `db_purchaseitemsreturn`
--
ALTER TABLE `db_purchaseitemsreturn`
  ADD CONSTRAINT `db_purchaseitemsreturn_ibfk_1` FOREIGN KEY (`purchase_id`) REFERENCES `db_purchase` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `db_purchaseitemsreturn_ibfk_2` FOREIGN KEY (`return_id`) REFERENCES `db_purchasereturn` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `db_purchaseitemsreturn_ibfk_3` FOREIGN KEY (`item_id`) REFERENCES `db_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `db_salesitems`
--
ALTER TABLE `db_salesitems`
  ADD CONSTRAINT `db_salesitems_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `db_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `db_salesitems_ibfk_2` FOREIGN KEY (`sales_id`) REFERENCES `db_sales` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `db_salesitemsreturn`
--
ALTER TABLE `db_salesitemsreturn`
  ADD CONSTRAINT `db_salesitemsreturn_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `db_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `db_salesitemsreturn_ibfk_2` FOREIGN KEY (`return_id`) REFERENCES `db_salesreturn` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `db_salesitemsreturn_ibfk_3` FOREIGN KEY (`sales_id`) REFERENCES `db_sales` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `db_supplier_payments`
--
ALTER TABLE `db_supplier_payments`
  ADD CONSTRAINT `db_supplier_payments_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `db_suppliers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `db_supplier_payments_ibfk_2` FOREIGN KEY (`purchasepayment_id`) REFERENCES `db_purchasepayments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
