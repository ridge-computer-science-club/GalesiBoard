/*
This is the program to control GalesiBoard. It currently displays animated text on the board.
*/

// These statements include the libraries that we are using, and tell the compiler that we want to use code from them. 'Libraries' are pieces of code that have already been written (typically by someone other than yourself), so that you don't have to write everything from scratch every time you want to make a program.

// This library is the Arduino Standard Library, which provides all the basic functionality that the hardware provides (for example: timing, input and output, the loop() and setup() functions).
#include <Arduino.h>

// This is the FastLED library, which is used to control the WS2812B LEDs that are on the board. It provides a lot of very useful functions, and takes care of all the low level data handling for us.
#include <FastLED.h>

// This is the file which contains all of the font data to display text.
#include <characters.h>

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

CRGB leds[ROWS][COLS] = {0};
CRGB bottom[COLS];

/**
    Writes text into the provided 2-dimensional CRGB array.

    @param text[] The string of text to display.
    @param colour The colour of the text.
    @param x The X location of the text on the board.
    @param y The y location of the text on the board.
*/
void writeText(const char text[], const CRGB colour, const size_t x, const size_t y, CRGB (&ledArray)[ROWS][COLS])
{
  for (int textIndex = 0; textIndex < strlen(text); ++textIndex)
  {
    uint8_t asciiCodeOffset;
    if (text[textIndex] == ' '){
      asciiCodeOffset = 26;
    }
    else if (text[textIndex] == '!')
    {
      asciiCodeOffset = 27;
    } else {
      asciiCodeOffset = text[textIndex] - STARTING_CHAR;
    }

    for (int characterRow = 0; characterRow < CHARACTER_SIZE; ++characterRow)
    {

      for (int bit = 0; bit < CHARACTER_SIZE; ++bit)
      {
        const size_t ledRow = characterRow + (ROWS - CHARACTER_SIZE) - y;
        // Check if text overflows array, break to prevent writing out of bounds.
        if (ledRow > (ROWS - 1))
          break;
        if (ledRow < 0)
          continue;

        const size_t ledCol = (CHARACTER_SIZE - (bit + 1)) + ((CHARACTER_SIZE + 1) * textIndex) + x;
        // Check if text overflows array, break to prevent writing out of bounds.
        if (ledCol > (COLS - 1))
          continue;
        if (ledCol < 0)
          break;
        // 
        const bool isPixel = (characters[asciiCodeOffset][characterRow] >> bit) & 0x1;
        ledArray[ledRow][ledCol] = isPixel? colour : CRGB::Black;
      }
    }
  }
}

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
