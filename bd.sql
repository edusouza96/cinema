

CREATE TABLE IF NOT EXISTS `assento` (
  `codigoAssento` int(10) unsigned NOT NULL auto_increment,
  `sessao_codigoSessao` int(10) unsigned NOT NULL,
  `assentosLivres` int(10) unsigned NOT NULL,
  `data` date default NULL,
  PRIMARY KEY  (`codigoAssento`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `filme` (
  `codigoFilme` int(10) unsigned NOT NULL auto_increment,
  `nomeFilme` varchar(100) collate utf8_unicode_ci NOT NULL,
  `genero` varchar(100) collate utf8_unicode_ci NOT NULL,
  `sinopse` text collate utf8_unicode_ci,
  PRIMARY KEY  (`codigoFilme`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `filme`
--

INSERT INTO `filme` (`codigoFilme`, `nomeFilme`, `genero`, `sinopse`) VALUES
(1, 'filme 1', 'genero 1', 'sinopse 1'),
(2, 'filme 2', 'genero 2', 'sinopse 2'),
(3, 'filme 3', 'genero 3', '');

-- --------------------------------------------------------

--
-- Table structure for table `sala`
--

CREATE TABLE IF NOT EXISTS `sala` (
  `numeroSala` int(10) unsigned NOT NULL,
  `quantidadeSala` int(11) default NULL,
  PRIMARY KEY  (`numeroSala`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sala`
--

INSERT INTO `sala` (`numeroSala`, `quantidadeSala`) VALUES
(100, 1000),
(200, 2000);

-- --------------------------------------------------------

--
-- Table structure for table `sessao`
--

CREATE TABLE IF NOT EXISTS `sessao` (
  `codigoSessao` int(10) unsigned NOT NULL auto_increment,
  `sala_numeroSala` int(10) unsigned NOT NULL,
  `filme_codigoFilme` int(10) unsigned NOT NULL,
  `horario` time NOT NULL,
  PRIMARY KEY  (`codigoSessao`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `venda` (
  `registroVenda` int(10) unsigned NOT NULL auto_increment,
  `sessao_codigoSessao` int(10) unsigned NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY  (`registroVenda`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;
