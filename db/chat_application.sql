SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `archivos`;
CREATE TABLE `archivos` (
                            `IDArchivo` int(10) unsigned NOT NULL AUTO_INCREMENT,
                            `ExtensionArchivo` varchar(255) DEFAULT NULL,
                            `HashDifuminado` varchar(255) DEFAULT NULL,
                            `Estado` char(1) NOT NULL DEFAULT '0',
                            PRIMARY KEY (`IDArchivo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
                           `IDUsuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
                           `NombreUsuario` varchar(255) DEFAULT NULL,
                           `Contraseña` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`IDUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

INSERT INTO `usuario` VALUES ('36', 'Diego', '123');
INSERT INTO `usuario` VALUES ('37', 'Santiago', '123');
INSERT INTO `usuario` VALUES ('38', 'María', '123');

DROP TABLE IF EXISTS `cuenta_usuario`;
CREATE TABLE `cuenta_usuario` (
                                  `IDUsuario` int(10) unsigned NOT NULL,
                                  `NombreUsuario` varchar(255) DEFAULT NULL,
                                  `Genero` char(1) NOT NULL DEFAULT '',
                                  `Imagen` longblob,
                                  `CadenaImagen` varchar(255) DEFAULT '',
                                  `Estado` char(1) NOT NULL DEFAULT '1',
                                  PRIMARY KEY (`IDUsuario`),
                                  CONSTRAINT `cuenta_usuario_ibfk_1` FOREIGN KEY (`IDUsuario`) REFERENCES `usuario` (`IDUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `cuenta_usuario` VALUES ('36', 'Diego', '', null, '', '1');
INSERT INTO `cuenta_usuario` VALUES ('37', 'Santiago', '', null, '', '1');
INSERT INTO `cuenta_usuario` VALUES ('38', 'María', '', null, '', '1');
