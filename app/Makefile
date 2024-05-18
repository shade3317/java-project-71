setup:
	./gradlew wrapper --gradle-version 8.7

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean installDist

run-dist:
	@./build/install/app/bin/app

run:
	./gradlew run

lint:
	./gradlew checkstyleMain

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

check-updates:
	./gradlew dependencyUpdates

run-help:
	./build/install/app/bin/app -h

run-json:
	./build/install/app/bin/app src/test/resources/file3.json src/test/resources/file4.json

run-yml:
	./build/install/app/bin/app src/test/resources/file3.yml src/test/resources/file4.yml

run-json-fPlain:
	./build/install/app/bin/app -f plain src/test/resources/file3.json src/test/resources/file4.json

run-yml-fPlain:
	./build/install/app/bin/app -f plain src/test/resources/file3.yml src/test/resources/file4.yml

run-json-fJson:
	./build/install/app/bin/app -f json src/test/resources/file3.json src/test/resources/file4.json

run-yml-fJson:
	./build/install/app/bin/app -f json src/test/resources/file3.yml src/test/resources/file4.yml

.PHONY: build