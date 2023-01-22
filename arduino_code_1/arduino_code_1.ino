// Example testing sketch for various DHT humidity/temperature sensors
// Written by ladyada, public domain

//https://www.instructables.com/How-to-use-a-photoresistor-or-photocell-Arduino-Tu/
//https://randomnerdtutorials.com/complete-guide-for-dht11dht22-humidity-and-temperature-sensor-with-arduino/

#include "DHT.h"

#define DHTPIN 2     // what pin we're connected to

// Uncomment whatever type you're using!
#define DHTTYPE DHT11   // DHT 11 
//#define DHTTYPE DHT22   // DHT 22  (AM2302)
//#define DHTTYPE DHT21   // DHT 21 (AM2301)

// Initialize DHT sensor for normal 16mhz Arduino
DHT dht(DHTPIN, DHTTYPE);
const int pResistor = A0;
int value;


void setup() {
  Serial.begin(9600); 
  pinMode(pResistor, INPUT);
 
  dht.begin();
}

void loop() {
  
  // Wait a few seconds between measurements.
  delay(500);
  value = analogRead(pResistor);

  // Reading temperature or humidity takes about 250 milliseconds!
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  float h = dht.readHumidity();
  // Read temperature as Celsius
  float t = dht.readTemperature();
  // Read temperature as Fahrenheit
  float f = dht.readTemperature(true);
  
  // Check if any reads failed and exit early (to try again).
  if (isnan(h) || isnan(t) || isnan(f)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  // Compute heat index
  // Must send in temp in Fahrenheit!
  float hi = dht.computeHeatIndex(f, h);

  Serial.print("Humidity:\t");
  if(h < 10)
  {
    Serial.print("0");
  }
  Serial.print(h);
  Serial.print(" %\t");
  //Serial.print("Temperature:\t"); 
  //Serial.print(t);
  //Serial.print(" *C\t");
  //Serial.print(f);
  //Serial.print(" *F\t");
  //Serial.print("Heat index:\t");
  //Serial.print(hi);
  //Serial.print(" *F\t");
  Serial.print("Light:\t");
  if(value < 100)
  {
    Serial.print("0");
  }
  if(value < 10)
  {
    Serial.print("0");
  }
  Serial.println(value);
  
}
