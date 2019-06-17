/*
This is the program to control GalesiBoard. It currently displays animated text on the board.
*/
// This library is the Arduino Standard Library, which provides all the basic functionality that the hardware provides (for example: timing, input and output, the loop() and setup() functions).
#include <Arduino.h>

// This is the FastLED library, which is used to control the WS2812B LEDs that are on the board. It provides a lot of very useful functions, and takes care of all the low level data handling for us.
#include <FastLED.h>

// This file has all the program constants in one place for easy access
#include <constants.hpp>

// This is the file which contains all of the font data for text.
#include <characters.hpp>

// This is the file which contains the writeText function used to display text.
#include <writeText.hpp>


CRGB leds[ROWS][COLS] = {0};
CRGB bottom[COLS];

void setup()
{
  FastLED.addLeds<LED_TYPE, 13, COLOUR_ORDER>(leds[0], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 12, COLOUR_ORDER>(leds[1], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 14, COLOUR_ORDER>(leds[2], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 27, COLOUR_ORDER>(leds[3], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 26, COLOUR_ORDER>(leds[4], COLS).setCorrection(TypicalLEDStrip);


  FastLED.setBrightness(BRIGHTNESS);

  Serial.begin(9600);
  Serial.println("starting");
}

// Serial handling from https://forum.arduino.cc/index.php?topic=396450
#define TEXT_BUFFER_LENGTH 64
char textBuffer[TEXT_BUFFER_LENGTH];

boolean newData = false;

void recieveData()
{
  static boolean incomingData = false;
  static uint8_t index = 0;
  char receivedCharacter;

  while (Serial.available() > 0 && newData == false)
  {
    receivedCharacter = Serial.read();

    if (incomingData)
    {
      if (receivedCharacter != END_TEXT)
      {
        textBuffer[index] = receivedCharacter;
        index++;
        if (index >= TEXT_BUFFER_LENGTH)
        {
          index = TEXT_BUFFER_LENGTH - 1;
        }
      }
      else
      {
        textBuffer[index] = '\0'; // terminate the string
        incomingData = false;
        index = 0;
        newData = true;
      }
    }
    else if (receivedCharacter == START_TEXT)
    {
      incomingData = true;
    }
  }
}

void loop()
{
  /* 
  for (int i = 80; i > 6; i--)
  {
    fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
    if (print.length()<13){  
      writeText(print.c_str(), CRGB::Green, i, 0, leds);}
    else{
      writeText(print.substring(0,print.indexOf(" ")+1).c_str(), CRGB::Green, i, 6, leds);
      writeText(print.substring(print.indexOf(" ")+1).c_str(), CRGB:: Green, i, 0, leds);
    }
    delay(50);
    FastLED.show();
  }
  */
  recieveData();
 
  if (newData)
  {

    fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);

    Serial.print("Received: ");
    Serial.println(textBuffer);
    writeText(textBuffer, CRGB::Cyan, 0, 0, leds);
    newData = false;
    FastLED.show();
  }

  delay(10);
}