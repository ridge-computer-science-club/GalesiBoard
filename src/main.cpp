/*
This is the program to control GalesiBoard. It currently displays animated text on the board.
*/

// These are constants that we define for our program, such as what type of LEDs we are using, how bright we want the LED to be, and the refresh rate (update rate) of the LED.

// How many LEDs are in each coloumn and row.
constexpr size_t ROWS = 5;
constexpr size_t COLS = 77;

// How bright the LEDs should be, on a scale from 0 to 255.
#define BRIGHTNESS 255
// The specific type of LED being controlled, so the FastLED library knows how to format the transmitted colour data.
#define LED_TYPE WS2812B
// The order in which the LED accepts colour data - [R]ed, [G]reen, [B]lue.
#define COLOR_ORDER RGB

// These statements include the libraries that we are using, and tell the compiler that we want to use code from them. 'Libraries' are pieces of code that have already been written (typically by someone other than yourself), so that you don't have to write everything from scratch every time you want to make a program.

// This library is the Arduino Standard Library, which provides all the basic functionality that the hardware provides (for example: timing, input and output, the loop() and setup() functions).
#include <Arduino.h>

// This is the FastLED library, which is used to control the WS2812B LEDs that are on the board. It provides a lot of very useful functions, and takes care of all the low level data handling for us.
#include <FastLED.h>

// This is the file which contains all of the font data for text.
#include <characters.hpp>

// This is the file which contains the writeText function used to display text.
#include <writeText.hpp>

CRGB leds[ROWS][COLS] = {0};
CRGB bottom[COLS];

char incomingByte;      // a variable to read incoming serial data into
String hi = "";

void setup() {
  FastLED.addLeds<LED_TYPE, 23>(leds[0], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 22>(leds[1], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 21>(leds[2], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 20>(leds[3], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 10>(leds[4], COLS).setCorrection(TypicalLEDStrip);

  FastLED.addLeds<LED_TYPE, 9>(bottom, COLS).setCorrection(TypicalLEDStrip);

  FastLED.setBrightness(BRIGHTNESS);
  // initialize serial communication:
  Serial.begin(9600);
}

void loop() {
  // see if there's incoming serial data:
 
  while (Serial.available() > 0) {
    // read the oldest byte in the serial buffer:
    incomingByte = Serial.read();
    hi = hi.append(incomingByte);
    writeText(hi.c_str(), CRGB::SeaGreen, 5, 0, leds);
    }
   // Serial.write(incomingByte);
}
