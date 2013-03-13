CLASSPATH = .:/usr/share/java/junit4.jar

romanconverter: RomanConverter.java
	javac -cp $(CLASSPATH) RomanConverter.java

romanconvertertest: romanconverter RomanConverterTest.java
	javac -cp $(CLASSPATH) RomanConverterTest.java

clean:
	rm -f *.class

test: romanconvertertest
	java -cp $(CLASSPATH) org.junit.runner.JUnitCore RomanConverterTest