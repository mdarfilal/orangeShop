# Test Technique #

Orange souhaite diriger le client vers la boutique la plus proche lorsque celui-ci veut changer de mobile.
Il vous est demandé de développer une application afin de pouvoir répondre à ce besoin.
Pour cela nous vous fournissons un projet contenant deux fichiers :	

 -	un fichier csv qui contient la longitude, la latitude, une description de chaque boutique et le stock pour chaque mobile.

 -	une interface OrangeShopFinder avec une méthode à implémenter
cette méthode retourne la boutique la plus proche qui a un stock supérieur à 0 pour un id mobile donné.


# Usage :

## Récupérer le projet

git clone https://github.com/mdarfilal/orangeShop.git

## Se rendre dans le projet

cd orangeShop

## Lancer le build maven

mvn clean install

## Lancer l'éxécutable

java -jar target/orangeShop-0.0.1-SNAPSHOT.jar

## Intéroger l'API (ou à l'aide du navigateur)

curl 'http://localhost:8080/api/v1/shop/?longitude=48.78192&latitude=2.22137&mobile=weiwei'

# API

GET /api/v1/shop/ : Renvoie la boutique le plus proche de l'utilisateur considérant son type de mobile

Params :

longitude : Required - Format=X.X (double)

latitude : Required - Format=X.X (double)

mobile : Required - Format=XXX (String)

# Techno

- Spring Boot 2.0.1

- JUnit 5

- Mockito 2.18.0
