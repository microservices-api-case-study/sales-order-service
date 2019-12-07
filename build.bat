mvn clean install spring-boot:repackage
del target\site\jacoco\jacoco.csv
del target\site\jacoco\jacoco.xml
del /f /q /s src\main\resources\static\jacoco > NUL
rmdir /q /s src\main\resources\static\jacoco > NUL
xcopy /s /i target\site\jacoco src\main\resources\static\jacoco
mvn clean install spring-boot:repackage