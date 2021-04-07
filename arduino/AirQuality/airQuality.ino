// Import required libraries
#ifdef ESP32
#include <WiFi.h>
#include <ESPAsyncWebServer.h>
#include <SPIFFS.h>
#else
#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <Hash.h>
#include <ESPAsyncTCP.h>
#include <ESPAsyncWebServer.h>
#include <FS.h>
#endif
#include <Wire.h>
#include <Adafruit_Sensor.h>

#define sensor    A0                      //sensor on Analog 0

// Replace with your network credentials
const char* ssid = "WIFI";
const char* password = "PASSWORD";

// Store last x elements of data constantly
const int ELEMENTS_TO_STORE = 10;
String gas[ELEMENTS_TO_STORE];
int iter = 0;
#define REFRESH_INTERVAL 2000L // ms
static unsigned long lastRefreshTime = 0;


// Create AsyncWebServer object on port 80
AsyncWebServer server(80);

String readGas() {
  return String(analogRead(sensor));
}

String gasHistory() {
  if (iter == 0)
    return "";

  String serialized = "";
  serialized += String(millis() - lastRefreshTime) + ",";
  serialized += String(REFRESH_INTERVAL) + ",";
  
  for (int i = 0; i < iter - 1; i++) {
    serialized += gas[i] + ",";
  }
  serialized += gas[iter - 1];
  return serialized;
}


void setup() {
  // Serial port for debugging purposes
  Serial.begin(115200);
  pinMode(sensor, INPUT);                 //set sensor for input

  // Initialize SPIFFS
  if (!SPIFFS.begin()) {
    Serial.println("An Error has occurred while mounting SPIFFS");
    return;
  }

  // Connect to Wi-Fi
  pinMode(LED_BUILTIN, OUTPUT);     // Initialize the LED_BUILTIN pin as an output
  digitalWrite(LED_BUILTIN, LOW);   // Turn the LED on
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi..");
  }

  // Print ESP32 Local IP Address
  digitalWrite(LED_BUILTIN, HIGH);   // Turn the LED off
  Serial.println(WiFi.localIP());
  Serial.println(WiFi.macAddress());

  // Route for root / web page
  server.on("/", HTTP_GET, [](AsyncWebServerRequest * request) {
    request->send(SPIFFS, "/index.html");
  });
  server.on("/gas", HTTP_GET, [](AsyncWebServerRequest * request) {
    request->send_P(200, "text/plain", readGas().c_str());
  });

  server.on("/gasHistory", HTTP_GET, [](AsyncWebServerRequest * request) {
    request->send_P(200, "text/plain", gasHistory().c_str());
  });


  // Start server
  server.begin();
}

void loop() {
  if (millis() - lastRefreshTime >= REFRESH_INTERVAL)
  {
    lastRefreshTime += REFRESH_INTERVAL;

    if (iter == ELEMENTS_TO_STORE)
    {
      for (int i = 0; i < ELEMENTS_TO_STORE - 1; i++)
      {
        gas[i] = gas[i + 1];
      }
      gas[ELEMENTS_TO_STORE - 1] = readGas();
      return;
    }

    gas[iter] = readGas();
    iter++;
  }
}
