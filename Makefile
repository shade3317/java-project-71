setup:
	make -C app setup

clean:
	make -C app clean

build:
	make -C app build

install:
	make -C app install

run-dist:
	make -C app run-dist

run:
	make -C app run

lint:
	make -C app lint

test:
	make -C app test

report:
	make -C app report

check-updates:
	make -C app check-updates

run-help:
	make -C app run-help

run-json:
	make -C app run-json

run-yml:
	make -C app run-yml

run-json-fPlain:
	make -C app run-json-fPlain

run-yml-fPlain:
	make -C app run-yml-fPlain

run-json-fJson:
	make -C app run-json-fJson

run-yml-fJson:
	make -C app run-yml-fJson

.PHONY: build