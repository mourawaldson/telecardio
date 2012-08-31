
DROP TABLE feedback
go

CREATE TABLE feedback (
       id                   int IDENTITY,
       nota                 int NOT NULL,
       id_exame             int NOT NULL,
       id_profissional      int NOT NULL,
       CONSTRAINT XPKfeedback 
              PRIMARY KEY (id)
)
go


DROP TABLE profissionais_orgaos
go

CREATE TABLE profissionais_orgaos (
       id                   int IDENTITY,
       habilitacao          int NOT NULL,
       id_profissional      int NOT NULL,
       id_orgao_classe      int NOT NULL,
       id_estado            int NOT NULL,
       CONSTRAINT XPKprofissionais_orgaos 
              PRIMARY KEY (id)
)
go


DROP TABLE exames
go

CREATE TABLE exames (
       id                   int IDENTITY,
       data_solicitacao     datetime NOT NULL,
       parecer_justificativa text NULL,
       id_profissional      int NULL,
       id_paciente          int NOT NULL,
       status               char(1) NOT NULL,
       xml                  varchar(255) NOT NULL,
       pdf                  varchar(255) NULL,
       medico_designado_crm int NULL,
       data_conclusao       datetime NULL,
       cid                  int NULL,
       email_requisitante   varchar(50) NULL,
       estado               int NOT NULL,
       altura_paciente      real NULL,
       peso_paciente        real NULL,
       observacoes          text NULL,
       CONSTRAINT XPKexames 
              PRIMARY KEY (id)
)
go


DROP TABLE profissionais
go

CREATE TABLE profissionais (
       id                   int IDENTITY,
       id_usuario           int NOT NULL,
       id_profissional_tipo int NOT NULL,
       CONSTRAINT XPKprofissionais 
              PRIMARY KEY (id)
)
go


DROP TABLE profissionais_tipos
go

CREATE TABLE profissionais_tipos (
       id                   int IDENTITY,
       nome                 varchar(50) NOT NULL,
       id_orgao_classe      int NOT NULL,
       CONSTRAINT XPKprofissionais_tipos 
              PRIMARY KEY (id)
)
go


DROP TABLE pacientes
go

CREATE TABLE pacientes (
       data_ultimo_contato  datetime NULL,
       id                   int IDENTITY,
       id_convenio          int NULL,
       nome_mae             varchar(100) NULL,
       id_pessoa            int NOT NULL,
       id_tipo_sanguineo    int NOT NULL,
       CONSTRAINT XPKpacientes 
              PRIMARY KEY (id)
)
go


DROP TABLE funcionalidades_usuarios
go

CREATE TABLE funcionalidades_usuarios (
       id                   int IDENTITY,
       id_usuario           int NOT NULL,
       id_funcionalidade    int NOT NULL,
       CONSTRAINT XPKfuncionalidades_usuarios 
              PRIMARY KEY (id)
)
go


DROP TABLE usuarios
go

CREATE TABLE usuarios (
       id                   int IDENTITY,
       login                varchar(20) NOT NULL,
       senha                varchar(32) NOT NULL,
       id_pessoa            int NOT NULL,
       id_perfil            int NOT NULL,
       email                varchar(100) NOT NULL,
       CONSTRAINT XPKusuarios 
              PRIMARY KEY (id)
)
go


DROP TABLE pessoas
go

CREATE TABLE pessoas (
       id                   int IDENTITY,
       nome                 varchar(100) NOT NULL,
       cpf                  varchar(11) NULL,
       data_nascimento      datetime NOT NULL,
       status               char(1) NOT NULL,
       id_endereco          int NULL,
       sexo                 char(1) NOT NULL,
       CONSTRAINT XPKpessoas 
              PRIMARY KEY (id)
)
go


DROP TABLE convenios
go

CREATE TABLE convenios (
       id                   int IDENTITY,
       nome                 varchar(50) NOT NULL,
       site                 varchar(50) NOT NULL,
       id_endereco          int NOT NULL,
       status               char(1) NOT NULL,
       CONSTRAINT XPKconvenios 
              PRIMARY KEY (id)
)
go


DROP TABLE enderecos
go

CREATE TABLE enderecos (
       id                   int IDENTITY,
       logradouro           varchar(200) NOT NULL,
       numero               varchar(10) NOT NULL,
       complemento          varchar(100) NULL,
       cep                  varchar(8) NOT NULL,
       id_estado            int NOT NULL,
       bairro               varchar(20) NOT NULL,
       municipio            varchar(100) NULL,
       CONSTRAINT XPKenderecos 
              PRIMARY KEY (id)
)
go


DROP TABLE funcionalidades_perfis
go

CREATE TABLE funcionalidades_perfis (
       id                   int IDENTITY,
       id_perfil            int NOT NULL,
       id_funcionalidade    int NOT NULL,
       CONSTRAINT XPKfuncionalidades_perfis 
              PRIMARY KEY (id)
)
go


DROP TABLE funcionalidades
go

CREATE TABLE funcionalidades (
       id                   int IDENTITY,
       nome                 varchar(100) NOT NULL,
       descricao            text NOT NULL,
       CONSTRAINT XPKfuncionalidades 
              PRIMARY KEY (id)
)
go


DROP TABLE orgaos_classes_estados
go

CREATE TABLE orgaos_classes_estados (
       id_estado            int NOT NULL,
       id_orgao_classe      int NOT NULL,
       CONSTRAINT XPKorgaos_classes_estados 
              PRIMARY KEY (id_estado, id_orgao_classe)
)
go


DROP TABLE estados
go

CREATE TABLE estados (
       id                   int IDENTITY,
       nome                 varchar(20) NOT NULL,
       uf                   varchar(2) NOT NULL,
       CONSTRAINT XPKestados 
              PRIMARY KEY (id)
)
go


DROP TABLE CID10
go

CREATE TABLE CID10 (
       cid                  int IDENTITY,
       descricao            text NOT NULL,
       nome                 varchar(100) NOT NULL,
       codigo               int NOT NULL,
       CONSTRAINT XPKCID10 
              PRIMARY KEY (cid)
)
go


DROP TABLE telefones_entidades
go

CREATE TABLE telefones_entidades (
       id                   int IDENTITY,
       entidade             varchar(50) NOT NULL,
       id_entidade          int NOT NULL,
       id_telefone          int NOT NULL,
       CONSTRAINT XPKtelefones_entidades 
              PRIMARY KEY (id)
)
go


DROP TABLE telefones
go

CREATE TABLE telefones (
       id                   int IDENTITY,
       numero               int NOT NULL,
       id_telefone_tipo     int NOT NULL,
       ddd                  int NOT NULL,
       ddi                  int NOT NULL,
       CONSTRAINT XPKtelefones 
              PRIMARY KEY (id)
)
go


DROP TABLE telefones_tipos
go

CREATE TABLE telefones_tipos (
       id                   int IDENTITY,
       nome                 varchar(20) NOT NULL,
       CONSTRAINT XPKtelefones_tipos 
              PRIMARY KEY (id)
)
go


DROP TABLE configuracoes
go

CREATE TABLE configuracoes (
       caminho_xml          varchar(255) NOT NULL,
       caminho_pdf          varchar(255) NOT NULL,
       id                   int IDENTITY,
       status               char(1) NOT NULL,
       limite_habilitacoes_medico int NOT NULL,
       limite_habilitacoes_enfermeiro int NOT NULL,
       limite_exame_aberto  int NOT NULL,
       limite_exame_laudando int NOT NULL,
       intervalo_checagem_email int NOT NULL,
       senha_checagem_email varchar(32) NOT NULL,
       porta_checagem_email int NOT NULL,
       host_checagem_email  varchar(50) NOT NULL,
       email_checagem_email varchar(50) NOT NULL,
       nome                 varchar(50) NOT NULL,
       CONSTRAINT XPKconfiguracoes 
              PRIMARY KEY (id)
)
go


DROP TABLE logs
go

CREATE TABLE logs (
       id                   int IDENTITY,
       id_usuario           int NOT NULL,
       acao                 varchar(100) NOT NULL,
       data_hora            datetime NOT NULL,
       alvo                 varchar(20) NOT NULL,
       descricao            text NOT NULL,
       nome_usuario         varchar(100) NOT NULL,
       CONSTRAINT XPKlogs 
              PRIMARY KEY (id)
)
go


DROP TABLE orgaos_classes
go

CREATE TABLE orgaos_classes (
       id                   int IDENTITY,
       sigla                varchar(20) NOT NULL,
       nome                 varchar(100) NOT NULL,
       CONSTRAINT XPKorgaos_classes 
              PRIMARY KEY (id)
)
go


DROP TABLE perfis
go

CREATE TABLE perfis (
       id                   int IDENTITY,
       nome                 varchar(50) NOT NULL,
       CONSTRAINT XPKperfis 
              PRIMARY KEY (id)
)
go


DROP TABLE tipo_sanguineo
go

CREATE TABLE tipo_sanguineo (
       id                   int NOT NULL,
       nome                 varchar(3) NOT NULL,
       CONSTRAINT XPKtipo_sanguineo 
              PRIMARY KEY (id)
)
go


ALTER TABLE feedback
       ADD CONSTRAINT R_84
              FOREIGN KEY (id_profissional)
                             REFERENCES profissionais
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE feedback
       ADD CONSTRAINT R_77
              FOREIGN KEY (id_exame)
                             REFERENCES exames
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE profissionais_orgaos
       ADD CONSTRAINT R_99
              FOREIGN KEY (id_estado)
                             REFERENCES estados
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE profissionais_orgaos
       ADD CONSTRAINT R_83
              FOREIGN KEY (id_orgao_classe)
                             REFERENCES orgaos_classes
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE profissionais_orgaos
       ADD CONSTRAINT R_81
              FOREIGN KEY (id_profissional)
                             REFERENCES profissionais
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE exames
       ADD CONSTRAINT R_75
              FOREIGN KEY (cid)
                             REFERENCES CID10
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE exames
       ADD CONSTRAINT R_28
              FOREIGN KEY (id_profissional)
                             REFERENCES profissionais
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE exames
       ADD CONSTRAINT R_26
              FOREIGN KEY (id_paciente)
                             REFERENCES pacientes
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE profissionais
       ADD CONSTRAINT R_97
              FOREIGN KEY (id_profissional_tipo)
                             REFERENCES profissionais_tipos
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE profissionais
       ADD CONSTRAINT R_49
              FOREIGN KEY (id_usuario)
                             REFERENCES usuarios
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE profissionais_tipos
       ADD CONSTRAINT R_93
              FOREIGN KEY (id_orgao_classe)
                             REFERENCES orgaos_classes
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE pacientes
       ADD CONSTRAINT R_100
              FOREIGN KEY (id_tipo_sanguineo)
                             REFERENCES tipo_sanguineo
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE pacientes
       ADD CONSTRAINT R_80
              FOREIGN KEY (id_pessoa)
                             REFERENCES pessoas
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE pacientes
       ADD CONSTRAINT R_58
              FOREIGN KEY (id_convenio)
                             REFERENCES convenios
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE funcionalidades_usuarios
       ADD CONSTRAINT R_65
              FOREIGN KEY (id_funcionalidade)
                             REFERENCES funcionalidades
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE funcionalidades_usuarios
       ADD CONSTRAINT R_64
              FOREIGN KEY (id_usuario)
                             REFERENCES usuarios
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE usuarios
       ADD CONSTRAINT R_96
              FOREIGN KEY (id_perfil)
                             REFERENCES perfis
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE usuarios
       ADD CONSTRAINT R_79
              FOREIGN KEY (id_pessoa)
                             REFERENCES pessoas
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE pessoas
       ADD CONSTRAINT R_78
              FOREIGN KEY (id_endereco)
                             REFERENCES enderecos
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE convenios
       ADD CONSTRAINT R_69
              FOREIGN KEY (id_endereco)
                             REFERENCES enderecos
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE enderecos
       ADD CONSTRAINT R_67
              FOREIGN KEY (id_estado)
                             REFERENCES estados
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE funcionalidades_perfis
       ADD CONSTRAINT R_89
              FOREIGN KEY (id_funcionalidade)
                             REFERENCES funcionalidades
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE funcionalidades_perfis
       ADD CONSTRAINT R_87
              FOREIGN KEY (id_perfil)
                             REFERENCES perfis
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE orgaos_classes_estados
       ADD CONSTRAINT R_95
              FOREIGN KEY (id_orgao_classe)
                             REFERENCES orgaos_classes
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE orgaos_classes_estados
       ADD CONSTRAINT R_94
              FOREIGN KEY (id_estado)
                             REFERENCES estados
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE telefones_entidades
       ADD CONSTRAINT R_86
              FOREIGN KEY (id_telefone)
                             REFERENCES telefones
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


ALTER TABLE telefones
       ADD CONSTRAINT R_74
              FOREIGN KEY (id_telefone_tipo)
                             REFERENCES telefones_tipos
                             ON DELETE NO ACTION
                             ON UPDATE NO ACTION
go


INSERT tipo_sanguineo(id,nome) VALUES (1,'A-')
INSERT tipo_sanguineo(id,nome) VALUES (2,'B-')
INSERT tipo_sanguineo(id,nome) VALUES (3,'O-')
INSERT tipo_sanguineo(id,nome) VALUES (4,'AB-')
INSERT tipo_sanguineo(id,nome) VALUES (5,'A+')
INSERT tipo_sanguineo(id,nome) VALUES (6,'B+')
INSERT tipo_sanguineo(id,nome) VALUES (7,'O+')
INSERT tipo_sanguineo(id,nome) VALUES (8,'AB+')
INSERT tipo_sanguineo(id,nome) VALUES (-1,'STS')

INSERT perfis(nome) VALUES ('Administrador')
INSERT perfis(nome) VALUES ('Médico')
INSERT perfis(nome) VALUES ('Enfermeiro')
INSERT perfis(nome) VALUES ('Auxiliar de Enfermagem')

INSERT orgaos_classes(sigla,nome) VALUES ('CRM','Conselho Regional de Medicina')
INSERT orgaos_classes(sigla,nome) VALUES ('COREN','Conselho Regional de Enfermagem')

INSERT profissionais_tipos(nome,id_orgao_classe) VALUES ('Médico','1')
INSERT profissionais_tipos(nome,id_orgao_classe) VALUES ('Enfermeiro','2')
INSERT profissionais_tipos(nome,id_orgao_classe) VALUES ('Auxiliar de Enfermagem','2')

INSERT estados(nome,uf) VALUES ('Acre','AC')
INSERT estados(nome,uf) VALUES ('Alagoas','AL')
INSERT estados(nome,uf) VALUES ('Amapá','AP')
INSERT estados(nome,uf) VALUES ('Amazonas','AM')
INSERT estados(nome,uf) VALUES ('Bahia','BA')
INSERT estados(nome,uf) VALUES ('Ceará','CE')
INSERT estados(nome,uf) VALUES ('Distrito Federal','DF')
INSERT estados(nome,uf) VALUES ('Espírito Santo','ES')
INSERT estados(nome,uf) VALUES ('Goiás','GO')
INSERT estados(nome,uf) VALUES ('Maranhão','MA')
INSERT estados(nome,uf) VALUES ('Mato Grosso','MT')
INSERT estados(nome,uf) VALUES ('Mato Grosso do Sul','MS')
INSERT estados(nome,uf) VALUES ('Minas Gerais','MG')
INSERT estados(nome,uf) VALUES ('Pará','PA')
INSERT estados(nome,uf) VALUES ('Paraíba','PB')
INSERT estados(nome,uf) VALUES ('Paraná','PR')
INSERT estados(nome,uf) VALUES ('Pernambuco','PE')
INSERT estados(nome,uf) VALUES ('Piauí','PI')
INSERT estados(nome,uf) VALUES ('Roraima','RR')
INSERT estados(nome,uf) VALUES ('Rondônia','RO')
INSERT estados(nome,uf) VALUES ('Rio de Janeiro','RJ')
INSERT estados(nome,uf) VALUES ('Rio Grande do Norte','RN')
INSERT estados(nome,uf) VALUES ('Rio Grande do Sul','RS')
INSERT estados(nome,uf) VALUES ('Santa Catarina','SC')
INSERT estados(nome,uf) VALUES ('São Paulo','SP')
INSERT estados(nome,uf) VALUES ('Sergipe','SE')
INSERT estados(nome,uf) VALUES ('Tocantins','TO')

INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('1','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('1','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('2','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('2','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('3','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('3','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('4','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('4','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('5','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('5','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('6','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('6','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('7','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('7','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('8','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('8','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('9','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('9','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('10','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('10','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('11','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('11','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('12','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('12','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('13','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('13','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('14','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('14','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('15','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('15','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('16','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('16','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('17','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('17','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('18','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('18','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('19','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('19','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('20','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('20','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('21','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('21','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('22','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('22','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('23','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('23','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('24','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('24','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('25','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('25','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('26','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('26','2')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('27','1')
INSERT orgaos_classes_estados(id_estado,id_orgao_classe) VALUES ('27','2')

INSERT telefones_tipos(nome) VALUES ('Residencial')
INSERT telefones_tipos(nome) VALUES ('Trabalho')
INSERT telefones_tipos(nome) VALUES ('Celular')
INSERT telefones_tipos(nome) VALUES ('Fax')

INSERT telefones(numero,id_telefone_tipo,ddd,ddi) VALUES ('88464515','2','81','55')

INSERT telefones_entidades(entidade,id_entidade,id_telefone) VALUES ('usuarios','1','1')

INSERT configuracoes(caminho_xml,caminho_pdf,status,limite_habilitacoes_medico,limite_habilitacoes_enfermeiro,limite_exame_aberto,limite_exame_laudando,intervalo_checagem_email,senha_checagem_email,porta_checagem_email,host_checagem_email,email_checagem_email,nome) VALUES ('exames_xml','exames_pdf','A','2','1','48','10','5','12agility34','993','imap.gmail.com','agility.sistemas@gmail.com','Padrão')

INSERT enderecos(logradouro,numero,complemento,cep,id_estado,bairro,municipio) VALUES ('R. nome','1','Casa','12345621','17','Bairro','Olinda')
INSERT enderecos(logradouro,numero,complemento,cep,id_estado,bairro,municipio) VALUES ('R. nome','2','Apt. 105','12345621','17','Bairro','Jaboatão dos Guararapes')
INSERT enderecos(logradouro,numero,complemento,cep,id_estado,bairro,municipio) VALUES ('R. nome','3','Casa','12345621','17','Bairro','Recife')

INSERT pessoas(nome,cpf,data_nascimento,status,id_endereco,sexo) VALUES ('Waldson','00000000000',convert(datetime,'Dec 11 1987 10:00AM'),'A','1','M')
INSERT pessoas(nome,cpf,data_nascimento,status,id_endereco,sexo) VALUES ('Vitor','11111111111',convert(datetime,'May  5 1975 12:00AM'),'A','2','M')
INSERT pessoas(nome,cpf,data_nascimento,status,id_endereco,sexo) VALUES ('Hector','22222222222',convert(datetime,'May  5 1975 12:00AM'),'A','3','M')
INSERT pessoas(nome,cpf,data_nascimento,status,id_endereco,sexo) VALUES ('administrador','22222222222',convert(datetime,'May  5 1975 12:00AM'),'A','3','M')

INSERT usuarios(login,senha,id_pessoa,id_perfil,email) VALUES ('waldson','123456','1','1','waldsoncabral@gmail.com')
INSERT usuarios(login,senha,id_pessoa,id_perfil,email) VALUES ('vitor','123456','2','1','vitorlins@gmail.com')
INSERT usuarios(login,senha,id_pessoa,id_perfil,email) VALUES ('hvb','123456','3','1','hectorvianab@gmail.com')
INSERT usuarios(login,senha,id_pessoa,id_perfil,email) VALUES ('adm','123456','3','1','hectorvianab@gmail.com')

go