#!/bin/bash

./gradlew chromeCucumberTest -Ptestdata=randalls-qa2 -PcucumberFeature=desktop/Registration.feature -PcucumberTags=@mk & ./gradlew chromeCucumberTest -Ptestdata=tomthumb-qa2 -PcucumberFeature=desktop/Registration.feature -PcucumberTags=@mk