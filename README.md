# README

O projeto foi desenvolvido em um Sistema Linux - Ubuntu 20.04 LTS

## 1. Instalar o Java e o Maven para rodar o Back End

## Para utilizar o projeto veja a instalação dos componentes no sistema Linux:

```bash
sudo apt update
```

Primeiro, vamos ver como Instalar o Maven no Ubuntu pelo site oficial. Siga os procedimento abaixo para realizar esta tarefa.

### **Instalação do Java**

Você pode instalar o pacote **Open JDK** padrão para Ubuntu usando:

```
apt-get update
```

```
sudo apt install default-jdk
```

Isso pode levar alguns minutos. Assim que tiver completado, verifique a instalação do Java usando:

```
java -version
```

Isso vai mostrar a versão instalada do Java.

### **Download do Apache Maven**

Você pode verificar a página oficial do Maven para ver qual a versão mais recente do Maven e fazer o download dela usando o **comando wget**.

Você pode fazer o download dela para o diretório **/tmp**, como mostrado abaixo.

```
wget https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -P /tmp
```

O arquivo será baixado como um arquivo em formato **.tar.gz**. Você pode extrair esse arquivo para o diretório **/opt** usando o comando:

```
sudo tar xf /tmp/apache-maven-*.tar.gz -C /opt
```

### **Configurar Ambiente para Maven**

Para garantir que o Maven funcione apropriadamente, você precisa configurar algumas variáveis de ambiente. Isso inclui **JAVA_HOME**, **M3_HOME**, **MAVEN_HOME** e **PATH**.

Para fazer essa mudança, crie um arquivo nomeado **maven.sh** dentro do diretório **/etc/profile.d/**.

```
sudo vi /etc/profile.d/maven.sh
```

A configuração abaixo precisa ser adicionado a esse arquivo recém-criado.

```
export JAVA_HOME=/usr/lib/jvm/default-java
export M3_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}
```

Salve esse arquivo pressionando **escape (ESC)** no teclado e, então, digite **:wq** e forneça os privilégios exigidos para o arquivo usando o comando abaixo:

```
sudo chmod +x /etc/profile.d/maven.sh
```

Saiba que este comando apenas configura as variáveis de ambiente. Para recarregar a ação e carregar o comando, use:

```
source /etc/profile.d/maven.sh
```

## 2 . Instalado os componentes, abra a pasta do projeto back end (email_javax_rs_api) em um terminal e rode os seguintes comando em sequencia:

```bash
mvn run compile
```

```bash
mvn exec:java
```

Se tudo tiver sido jeito corretamente o projeto estará rodando em [http://localhost:8080/](http://localhost:8081/)
