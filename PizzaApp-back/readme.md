For certifications (handshake SSL):

Creating:
keytool -genkeypair -alias pizzaDelivery -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore pizzaDelivery.p12 -validity 3650

Extracting:
keytool -export -keystore src/main/resources/keystore/pizzaDelivery.p12 -alias pizzaDelivery -file myCertificate.crt

Adding to JVM (admin) password: 'changeit'
Bash:
keytool -importcert -file myCertificate.crt -alias pizzaDelivery -keystore /c/Program\ Files/Amazon\ Corretto/jdk17.0.3_6/lib/security/cacerts
keytool -importcert -file myCertificate.crt -alias pizzaDelivery -keystore “c:/Program Files/Amazon Corretto/jdk17.0.3_6/lib/security/cacerts”

keytool -export -keystore heroku_cacerts -alias springboot -file myCertificate.crt


```
Importing .cer certificate file downloaded from browser (open the url and dig for details) into cacerts keystore in java_home\jre\lib\security worked for me, as opposed to attemps to generate and use my own keystore.

Go to your java_home\jre\lib\security
(Windows) Open admin command line there using cmd and CTRL+SHIFT+ENTER
Run keytool to import certificate:
(Replace yourAliasName and path\to\certificate.cer respectively)
..\..\bin\keytool -import -trustcacerts -keystore cacerts -storepass changeit -noprompt -alias yourAliasName -file path\to\certificate.cer
This way you don't have to specify any additional JVM options and the certificate should be recognized by the JRE.
```
keytool -import -trustcacerts -keystore cacerts -storepass changeit -noprompt -alias joao -file uipathCertification_24.08.22.cer

keytool -import -trustcacerts -keystore cacerts -storepass changeit -noprompt -alias joao_second -file account.uipath_24.08.22.cer
