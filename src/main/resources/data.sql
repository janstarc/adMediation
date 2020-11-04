INSERT INTO `ad_type` (`id_ad_type`, `description_type`)
VALUES
('1', 'banner'),
('2', 'interstitial'),
('3', 'rewardedVideo');

INSERT INTO `ad_provider` (`id_ad_provider`, `description_provider`)
VALUES
('1', 'Facebook'),
('2', 'Google');

INSERT INTO `country` (`country_code`, `country_name`)
VALUES
('SL', 'Slovenia'),
('DE', 'Germany');

INSERT INTO `performance_data` (`id_performance_data`, `performance_score`, `ad_provider_id_ad_provider`, `ad_type_id_ad_type`, `country_country_code`)
VALUES
('', '15', '1', '1', 'SL'),
('', '15', '1', '2', 'SL'),
('', '22', '1', '3', 'SL');