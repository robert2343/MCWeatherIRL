## Inspiration
I thought it would be cool to have a video game be affected by the physical environment in real time.

## What it does
There is a photoresistor and humidity sensor controlled by an Arduino that send data to a Minecraft server, which changes the in-game time of day and weather in accordance with the data from the Arduino.

## How we built it
Used an Arduino with sensors, and we used the Java library jSerialComm in a Minecraft mod to read from the Arduino. The Minecraft mod then changes the in-game time and weather.

## Challenges we ran into
We were having trouble including an external JAR library in a Minecraft mod. Our code for parsing the data for the Arduino required a lot of debugging. We did not find a way to actually run a debugger on the Minecraft mod, which made debugging much more difficult. We also could not find a way to change the brightness setting programmatically from within the mod, so we resorted to changing the time of day between night and day.

## Accomplishments that we're proud of
We have a basic proof of concept for how we can use real sensor data to communicate with a Minecraft mod over a serial port, and interpret the data in real time.

## What we learned
I learned a lot about Minecraft modding, particularly the Gradle build system for Java.

## What's next for Minecraft Weather IRL
Make more sensors for more features, make the ways in which the game reacts to different data more nuanced.

